package transevolution.game;

/**
 * - Datenaustausch zwischen Server und mehreren Clients funktioniert
 * - Umsetzung vorerst in einfachem JFrame
 * - Bewegungen des einen Clients werden beim anderen Client angezeigt
 * - Clients werden untereinander in verschiedenen Farben aufgefuehrt
 * - Server listet alle verbundenen Clients auf
 * - Muss von der Ausfuehrung entsprechend unserem Spiel noch angepasst werden, Funktionalitaet gewaehrleistet 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

public class Netzwerk_Server {
	
	public static ServerSocket serverSocket;
	public static String ip = "";
	public static int port = 2406;
	public static JList liste_clients;
	public static DefaultListModel liste_clients_model;
	public static ArrayList<Socket> liste_sockets = new ArrayList<Socket>();
	public static ArrayList<Integer> liste_client_zustand = new ArrayList<Integer>();
	public static ArrayList<Netzwerk_Daten> liste_daten = new ArrayList<Netzwerk_Daten>();
	
	public static JFrame jframe;
	public static JPanel main_panel;
	public static JPanel panel1;
	public static JPanel panel2;
	public static JPanel panel3;
	public static JButton button_trennen;

	
	public static void client_trennen (int p) {
		try {
			liste_clients_model.removeElementAt(p);
			liste_client_zustand.remove(p);
			liste_daten.remove(p);
			liste_sockets.remove(p);
		}
		catch (Exception e)
		{}
	}
	
	private static Runnable akzeptieren = new Runnable() {
		
		public void run() {		// Dieser Thread startet die beiden folgenden Threads "senden" & "empfangen" direkt mit
			new Thread(senden).start();
			new Thread(empfangen).start();
			
			while (true) {		// unendliche Schleife, um Clients zu akzeptieren
				try {
					Socket socket = serverSocket.accept();
					ObjectInputStream objinstream = new ObjectInputStream(socket.getInputStream());	// InputStream vom Client, der veruscht zu verbinden, empfangen
					String nutzername = (String) objinstream.readObject();	// (String) = "Object" wird in String konvertiert
					
					boolean akzeptiert = true;
					for (int i=0; i<liste_daten.size(); i++)	// Damit nicht 2 gleiche Usernames vorhanden sind
					{	
						if (liste_daten.get(i).nutzername.toLowerCase().equals(nutzername.toLowerCase()))
						{
							akzeptiert = false;		// Bei 2x gleichem Nutzernamen: Verbindung nicht akzeptieren
							break;
						}
					}
					
					ObjectOutputStream objoutstream = new ObjectOutputStream(socket.getOutputStream());
					if (akzeptiert)
					{
						objoutstream.writeObject("TransEvolution - Server");
						// ueber getInetAddress koennen alle Daten, wie ip Adresse oder Host Name gelesen werden
						liste_clients_model.addElement(nutzername + " - " + socket.getInetAddress().getHostAddress() + " - " + socket.getInetAddress().getHostName());
						liste_client_zustand.add(0);
						liste_daten.add(new Netzwerk_Daten());
						liste_sockets.add(socket);
					}
					else
					{
						objoutstream.writeObject("Benutzername bereits vergeben");
						// Verbindung wird bei existierendem Nutzernamen getrennt und muss neu hergestellt werden
					}
				}
				catch (Exception e) 
				{}
			}
		}
	};
	
	
	private static Runnable senden = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ObjectOutputStream objoutstream;
			while(true) {
				for (int i=0; i<liste_sockets.size(); i++)
				{
					try {
						objoutstream = new ObjectOutputStream(liste_sockets.get(i).getOutputStream());
						int client_zustand = liste_client_zustand.get(i);
						objoutstream.writeObject(client_zustand);
						objoutstream = new ObjectOutputStream(liste_sockets.get(i).getOutputStream());
						objoutstream.writeObject(liste_daten);
						
						if (client_zustand == 1)	// 1 = Client wird vom Server gekicked
						{
							client_trennen(i);
							i--;
						}
						else if (client_zustand == 2)	// 2 = Server ist getrennt
						{
							client_trennen(i);
							i--;
						}
					}
					catch(Exception e)
					{}
				}
			}
			
		}
	};
	
	private static Runnable empfangen = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ObjectInputStream objinstream;
			while(true) {
				for (int i=0; i<liste_sockets.size(); i++)
				{
					try {
						objinstream = new ObjectInputStream(liste_sockets.get(i).getInputStream());
						int status_empfangen = (Integer) objinstream.readObject();
						objinstream = new ObjectInputStream(liste_sockets.get(i).getInputStream());
						Netzwerk_Daten netdaten = (Netzwerk_Daten) objinstream.readObject();
						liste_daten.set(i, netdaten);
						if(status_empfangen == 1)	// Client vom Nutzer aus getrennt
						{
							client_trennen(i);
							i--;
						}
					}
					catch (Exception e)	// Client getrennt, aber Server nicht darueber informiert
					{
						client_trennen(i);	// das selbe in gruen
						i--;
					}
				}
			}
			
		}
	};
	
	public static void main(String[] args) {
		button_trennen = new JButton();
		button_trennen.setText("Verbindung Trennen");
		button_trennen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				int auswahl = liste_clients.getSelectedIndex();
				if (auswahl != -1)
				{
					try {
						liste_client_zustand.set(auswahl, 1);
					}
					catch (Exception e)
					{
						System.err.println("Fehler: " + e.getMessage());
					}
				}
				
			}
		});
		liste_clients_model = new DefaultListModel();
		liste_clients = new JList(liste_clients_model);
		liste_clients.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				// TODO Auto-generated method stub
				if (lse.getValueIsAdjusting())
				{
					System.out.println(liste_clients.getSelectedIndex());
				}
				
			}
		});
		jframe = new JFrame();
		jframe.setTitle("TransEvolution Server - " + ip);
		jframe.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent we) {}
			public void windowIconified(WindowEvent we) {}
			public void windowDeiconified(WindowEvent we) {}
			public void windowDeactivated(WindowEvent we) {}
			@Override
			public void windowClosing(WindowEvent we) {
				// TODO Auto-generated method stub
				while (liste_sockets.size() != 0)
				{
					try {
						for (int i=0; i<liste_client_zustand.size(); i++)
						{
							liste_client_zustand.set(i, 2);
						}
					}
					catch (Exception e){}
				}
				System.exit(0);
				
			}
			public void windowClosed(WindowEvent we) {}
			public void windowActivated(WindowEvent we) {}
		});
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{}
		try {	// Verbindung zum Server mit o.a. Port wird gestartet
			ip = InetAddress.getLocalHost().getHostAddress() + ":" + port;
			serverSocket = new ServerSocket(port, 0, InetAddress.getLocalHost());
			new Thread(akzeptieren).start();
		}
		catch (IOException e)
		{
			System.err.println("Fehler: " + e.getMessage());
			System.exit(0);
		}
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1, 1, 1));
		panel1.add(button_trennen);
		
		panel2 = new JPanel();
		panel2.add(new JLabel(ip));
		
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(1, 1));
		panel3.add(panel1, BorderLayout.NORTH);
		panel3.add(new JScrollPane(liste_clients), BorderLayout.CENTER);
		panel3.add(panel2, BorderLayout.SOUTH);
		
		main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(1, 1, 1, 1));
		main_panel.add(panel3);
		main_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		jframe.setContentPane(main_panel);
		jframe.pack();
		jframe.setSize(350, 400);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
	}
	
}
