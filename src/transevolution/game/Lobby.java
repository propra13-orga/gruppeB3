package transevolution.game;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Lobby {
	protected JLabel anzeige1;
	protected JLabel anzeige2;
	protected JLabel anzeige3;
	protected JLabel anzeige4;
	protected JLabel anzeige5;
	protected JLabel anzeige6;
	// <Server>
	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static Users[] user = new Users[10];
	static DataInputStream in;
	// </Server>
	JList schwierigkeiten = null;
	JFrame chatfenster;
	String a = null;
//	final JTextField nachrichtenBox = new JTextField();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	Lobby() {
		Action connect = new AbstractAction("Server starten") {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
				Netzwerk_Server.main(null);
				
			}
		};
		
		Action wait = new AbstractAction("auf Server warten") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Netzwerk_Client.main(null);
			}
		};
		
//		Action senden = new AbstractAction("senden") {
//			
//			@Override
//			public void actionPerformed(ActionEvent e2) {
//			}
//		};
		Action spielStarten = new AbstractAction("START") {
			
			@Override
			public void actionPerformed(ActionEvent e3) {
				schwierigkeiten.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;
//						enterStateAndreinit(e.getFirstIndex().stateID);
					}
				});
			}
		};
		
		chatfenster = new JFrame("TransEvolution Network");
		chatfenster.getContentPane().setLayout(null);
		JButton serverVerbindung = new JButton(connect);
		JButton warteVerbindung = new JButton(wait);
//		JButton sendeButton = new JButton(senden);
		JButton start = new JButton(spielStarten);
//		nachrichtenBox.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				a = nachrichtenBox.getText();
//			}
//		});
//		JScrollPane chatBox = new JScrollPane();
//		JLabel chatBox = new JLabel(a);
		anzeige1 = new JLabel("Wilkommen in der Lobby");
		anzeige2 = new JLabel("Sie koennen hier einen Server starten");
		anzeige3 = new JLabel(" oder einem Server beitreten");
		anzeige4 = new JLabel("Bitte wählen Sie unten den Schwierigkeitsgrad");
		anzeige5 = new JLabel("&");
		anzeige6 = new JLabel("warten auf Ihren Gegenspieler");
		
		
		
		//Schwierigkeitsgrade
//		String schwierigkeit[] = {"easy", "medium", "hard"};
		ArrayList schwierigkeits = new ArrayList();
		schwierigkeits.add("easy");
		schwierigkeits.add("medium");
		schwierigkeits.add("hard");
		schwierigkeiten = new JList(schwierigkeits.toArray());
		
		
		serverVerbindung.setBounds(120, 20, 150, 25);
		warteVerbindung.setBounds(300, 20, 150, 25);
//		nachrichtenBox.setBounds(20, 70, 200, 70);
//		chatBox.setBounds(275, 70, 275, 130);
		anzeige1.setBounds(200, 30, 275, 130);
		anzeige2.setBounds(165, 60, 275, 130);
		anzeige3.setBounds(185, 80, 275, 130);
		anzeige4.setBounds(145, 110, 275, 130);
		anzeige5.setBounds(280, 130, 275, 130);
		anzeige6.setBounds(200, 150, 275, 130);

//		sendeButton.setBounds(20, 150, 200, 50);
		start.setBounds(200, 300, 200, 25);
		schwierigkeiten.setBounds(210, 230, 175, 55);
		chatfenster.getContentPane().add(serverVerbindung);
		chatfenster.getContentPane().add(warteVerbindung);
//		chatfenster.getContentPane().add(nachrichtenBox);
//		chatfenster.getContentPane().add(chatBox);
		chatfenster.getContentPane().add(anzeige1);
		chatfenster.getContentPane().add(anzeige2);
		chatfenster.getContentPane().add(anzeige3);
		chatfenster.getContentPane().add(anzeige4);
		chatfenster.getContentPane().add(anzeige5);
		chatfenster.getContentPane().add(anzeige6);
//		chatfenster.getContentPane().add(sendeButton);
		chatfenster.getContentPane().add(schwierigkeiten);
		chatfenster.getContentPane().add(start);
		chatfenster.getContentPane().setBackground(SystemColor.control);
		chatfenster.setSize(600, 400);
		chatfenster.setVisible(true);
//		chatfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args) throws Exception {
		new Lobby();
		
	}
}
