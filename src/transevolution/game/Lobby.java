package transevolution.game;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Lobby {
	// <Server>
	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static Users[] user = new Users[10];
	static DataInputStream in;
	// </Server>
	
	JFrame chatfenster;
//	String a = "Bereit";
	
	Lobby() {
		Action connect = new AbstractAction("Server starten") {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
//				changeauf("Starte Spiel");
				
				
			}
		};
		Action senden = new AbstractAction("senden") {
			
			@Override
			public void actionPerformed(ActionEvent e2) {
				
			}
		};
		
		chatfenster = new JFrame("TransEvolution Network");
		chatfenster.getContentPane().setLayout(null);
		JButton serverVerbindung = new JButton(connect);
		JButton sendeButton = new JButton(senden);
		JButton start = new JButton("Bereit");
		JTextArea nachrichtenBox = new JTextArea();
		JScrollPane chatBox = new JScrollPane();
		serverVerbindung.setBounds(200, 20, 200, 25);
		nachrichtenBox.setBounds(20, 70, 200, 70);
		chatBox.setBounds(275, 70, 275, 130);
		sendeButton.setBounds(20, 150, 200, 50);
		start.setBounds(200, 300, 200, 25);
		chatfenster.getContentPane().add(serverVerbindung);
		chatfenster.getContentPane().add(nachrichtenBox);
		chatfenster.getContentPane().add(chatBox);
		chatfenster.getContentPane().add(sendeButton);
		chatfenster.getContentPane().add(start);
		chatfenster.getContentPane().setBackground(SystemColor.control);
		chatfenster.setSize(600, 400);
		chatfenster.setVisible(true);
//		chatfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args) throws Exception {
		new Lobby();
		
	}
//	
//	private void changeauf(String b) {
//		a.setText(b);
//	}
}
