package transevolution.game;

/**
 * Beschreibung: siehe Netzwerk_Server.java
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial") // nervte mich
public class Netzwerk_Client extends JComponent {
	
	public int state = 0;
	public boolean verbunden = true;
	public static Socket socket;
	public static String ip = "";
	public static int port = 2406;
	public int x = 0;
	public int y = 0;
	public String nutzername = "";
	public ArrayList<Netzwerk_Daten> etc = new ArrayList<Netzwerk_Daten>();
	
	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		jframe.setTitle("TransEvolution - Client");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(new Netzwerk_Client());
		jframe.pack();
		jframe.setSize(650, 500);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
	}
	
	public Netzwerk_Client() {
		
//			this.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
		this.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}
		});
		
		try {
			String lokal;
			try {
				lokal = InetAddress.getLocalHost().getHostAddress()+ ":" +port;
			}
			catch (UnknownHostException ex) {
				lokal = "Netzwerk Fehler!";
			}
			
			ip = (String) JOptionPane.showInputDialog(null, "IP-Adresse: ", "Infos", JOptionPane.INFORMATION_MESSAGE, null, null, lokal);
			port = Integer.parseInt(ip.substring(ip.indexOf(":")+1));
			ip = ip.substring(0, ip.indexOf(":"));
			socket = new Socket(ip, port);
			String pc_benutzer = System.getProperty("user.name"); 	// Angemeldeter Benutzer wird vom System gelesen
			pc_benutzer = (String) JOptionPane.showInputDialog(null, "Benutzername: ", "Infos", JOptionPane.INFORMATION_MESSAGE, null, null, pc_benutzer);
			nutzername = pc_benutzer;
			ObjectOutputStream objoutstream = new ObjectOutputStream(socket.getOutputStream());
			objoutstream.writeObject(nutzername);
			ObjectInputStream objinstream = new ObjectInputStream(socket.getInputStream());
			String serverAntwort = (String) objinstream.readObject();
			JOptionPane.showMessageDialog(null, serverAntwort, "Nachricht", JOptionPane.INFORMATION_MESSAGE);
			if (serverAntwort.equals("Benutzername bereits vergeben"))
			{
				System.exit(0);
			}
			new Thread(senden).start();
			new Thread(empfangen).start();
		}
		catch (Exception e) {
			System.err.println("Fehler: " + e.getMessage());
			System.exit(0);
		}
	}
	
	Runnable senden = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ObjectOutputStream objoutstream;
			while(verbunden){
				if (socket != null) {
					try {
						Netzwerk_Daten netdaten = new Netzwerk_Daten();
						netdaten.x = x;
						netdaten.y = y;
						netdaten.nutzername = nutzername;
						objoutstream = new ObjectOutputStream(socket.getOutputStream());
						objoutstream.writeObject(state);
						objoutstream = new ObjectOutputStream(socket.getOutputStream());
						objoutstream.writeObject(netdaten);
						if (state == 1)	// Client getrennt
						{
							verbunden = false;
							socket = null;
							System.err.println("Client ist getrennt");
							System.exit(0);
						}
					}
					catch (Exception e) 
					{}
				}
				else {
					break;
				}
			}
			
		}
	};
	
	Runnable empfangen = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ObjectInputStream objinstream;
			while (verbunden) {
				try {
					objinstream = new ObjectInputStream(socket.getInputStream());
					int status_empfangen = (Integer) objinstream.readObject();
					
					if (status_empfangen == 1) 	// Vom Server getrennt oder gekicked
					{
						verbunden = false;
						socket = null;
						System.err.println("Sie wurden vom Server getrennt");
						System.exit(0);
					}
					else if (status_empfangen == 2) 	// Server ist getrennt
					{
						verbunden = false;
						socket = null;
						System.err.println("Server nicht verfuegbar");
						System.exit(0);
					}
					objinstream = new ObjectInputStream(socket.getInputStream());
					@SuppressWarnings("unchecked") // nervte mich
					ArrayList<Netzwerk_Daten> liste_daten = (ArrayList<Netzwerk_Daten>) objinstream.readObject();
					for (int i=0; i<liste_daten.size(); i++)
					{
						Netzwerk_Daten netdaten = liste_daten.get(i);
						if (liste_daten.size() != etc.size())
						{
							if (liste_daten.size() > etc.size())
							{
								etc.add(netdaten);
							}
							if (liste_daten.size() < etc.size())
							{
								etc.remove(0);
							}
						}
						else
						{
							etc.set(i, netdaten);
						}
					}
				}
				catch (Exception e)
				{}
			}
		}
	};
	
	public void billige_zeichnung(Graphics g) {
		Graphics2D grafik = (Graphics2D) g;
		grafik.setColor(Color.WHITE);
		grafik.fillRect(0, 0, getWidth(), getHeight());
		for (int i=0; i<etc.size(); i++)
		{
			try {
				Netzwerk_Daten netdaten = etc.get(i);
				if (!netdaten.nutzername.toLowerCase().equals(nutzername.toLowerCase()))	// wenn Nutzername nicht mein Nutzername
				{
					grafik.setColor(Color.RED);	// Gegenspieler in anderer Farbe darstellen
					grafik.fillOval((int) netdaten.x-50, (int) netdaten.y-50, 100, 100);
					grafik.setColor(Color.BLACK);	// Anzeige Nutzername
					grafik.drawString(netdaten.nutzername, netdaten.x-50, netdaten.y-70);
				}
			}
			catch (Exception e) 
			{}
		}
		grafik.setColor(Color.BLUE);	//Darstellung meiner einer, unser einer
		grafik.fillOval(x-50, y-50, 100, 100);
		grafik.setColor(Color.BLACK);	// Anzeige Nutzername
		grafik.drawString(nutzername, x-50, y-70);
		try {
			Thread.sleep(1);
		}
		catch (Exception e) 
		{}
		repaint();
	}

}
