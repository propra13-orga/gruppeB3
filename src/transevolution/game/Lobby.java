package transevolution.game;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Lobby {
	protected JLabel anzeige;
	// <Server>
	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static Users[] user = new Users[10];
	static DataInputStream in;
	// </Server>
	@SuppressWarnings("rawtypes")
	JList schwierigkeiten = null;
	JFrame chatfenster;
//	String a = "Bereit";
	String a = null;
	final JTextField nachrichtenBox = new JTextField();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	Lobby() {
		Action connect = new AbstractAction("Server starten") {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
//				changeauf("Starte Spiel");
				Netzwerk_Server.main(null);
				
			}
		};
		
		Action wait = new AbstractAction("auf Server warten") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Netzwerk_Client.main(null);
			}
		};
		
		Action senden = new AbstractAction("senden") {
			
			@Override
			public void actionPerformed(ActionEvent e2) {
			}
		};
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
		JButton sendeButton = new JButton(senden);
		JButton start = new JButton(spielStarten);
		nachrichtenBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				a = nachrichtenBox.getText();
			}
		});
//		JScrollPane chatBox = new JScrollPane();
//		JLabel chatBox = new JLabel(a);
		anzeige = new JLabel("Welcome to the Chatroom");
		
		
		
		//Schwierigkeitsgrade
//		String schwierigkeit[] = {"easy", "medium", "hard"};
		ArrayList schwierigkeits = new ArrayList();
		schwierigkeits.add("easy");
		schwierigkeits.add("medium");
		schwierigkeits.add("hard");
		schwierigkeiten = new JList(schwierigkeits.toArray());
		
		
		serverVerbindung.setBounds(120, 20, 150, 25);
		warteVerbindung.setBounds(300, 20, 150, 25);
		nachrichtenBox.setBounds(20, 70, 200, 70);
//		chatBox.setBounds(275, 70, 275, 130);
		anzeige.setBounds(275, 70, 275, 130);
		sendeButton.setBounds(20, 150, 200, 50);
		start.setBounds(200, 300, 200, 25);
		schwierigkeiten.setBounds(210, 230, 175, 55);
		chatfenster.getContentPane().add(serverVerbindung);
		chatfenster.getContentPane().add(warteVerbindung);
		chatfenster.getContentPane().add(nachrichtenBox);
//		chatfenster.getContentPane().add(chatBox);
		chatfenster.getContentPane().add(anzeige);
		chatfenster.getContentPane().add(sendeButton);
		chatfenster.getContentPane().add(schwierigkeiten);
		chatfenster.getContentPane().add(start);
		chatfenster.getContentPane().setBackground(SystemColor.control);
		chatfenster.setSize(600, 400);
		chatfenster.setVisible(true);
//		chatfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	private void eingabe(String a) {
//		chatBox.setText(a);
		anzeige.setText(a);
	}
	
	public static void main(String[] args) throws Exception {
		new Lobby();
		
	}
//	
//	private void changeauf(String b) {
//		a.setText(b);
//	}
}
