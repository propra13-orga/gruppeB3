package transevolution.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Netzwerk_Chat_Frame extends JFrame{
	TextArea nachrichtenfeld;
	TextArea chatbox;
	JButton send_button;
	PrintWriter wr_out;
	BufferedReader wr_in;
	ActionListener akxion;
	
	Netzwerk_Chat_Frame(String Titel,PrintWriter out, BufferedReader in){
			wr_out = out;
			wr_in = in;
			init(Titel);
	}

	void init(String Titel){
		//setLocation(400,400);
		setSize(600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.setTitle(Titel);
		nachrichtenfeld = new TextArea();
		chatbox = new TextArea();
		send_button	= new JButton();
		akxion = new Action(wr_out, wr_in, nachrichtenfeld);
		send_button.setText("senden");
		send_button.setActionCommand("Send");
		send_button.addActionListener(akxion);
		this.setLayout(new BorderLayout());
		Container montainer = new Container();
		montainer.setLayout(new BorderLayout());
		montainer.add(nachrichtenfeld,BorderLayout.CENTER);
		montainer.add(send_button,	 BorderLayout.EAST);
		this.add(montainer,BorderLayout.SOUTH);
		this.add(chatbox,BorderLayout.CENTER);
	}

	public void addAusgabe(String add){
		String temp = chatbox.getText();
		temp += add;
		chatbox.setText(temp);
		}
	}

class Action implements ActionListener{
	PrintWriter out_writer;
	BufferedReader in_reader;
	TextArea chatmsg;
	Action(PrintWriter out, BufferedReader in, TextArea Text){
	out_writer = out;
	in_reader = in;
	chatmsg = Text;
	}

	public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("Send")){
				String Ausgabe = chatmsg.getText()+"\n";
				out_writer.print(Ausgabe);
				out_writer.flush();
				}
		}
}
