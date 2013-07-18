package transevolution.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Netzwerk_Chat_Client extends Thread{
	
	Netzwerk_Chat_Frame frame;
	Socket socket = null;
	PrintWriter wr_out = null;
	BufferedReader wr_in = null;
	Scanner tastatur = new Scanner(System.in);
	
	Netzwerk_Chat_Client(){
		try {
			socket = new Socket("localhost", 7777);
			wr_out = new PrintWriter(socket.getOutputStream(), true);
			wr_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			frame = new Netzwerk_Chat_Frame("TransEvolution - Client", wr_out, wr_in);
		} catch (Exception e) {
			System.exit(1);
		} 
	}
	
public void run(){

	while(true){
		String incoming;
		try {
			incoming = wr_in.readLine();
			frame.addAusgabe(incoming);
		}
		catch (IOException e) {
			e.printStackTrace();
			}
		}
	}

}