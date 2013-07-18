package transevolution.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Netzwerk_Chat_Server {
	public static void main(String [] args){
		try {
			new Server().start();	// Server
			new Netzwerk_Chat_Client().start();	// Client
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}

class Server extends Thread{
	Netzwerk_Chat_Frame frame;
	ServerSocket serverSocket	 = null;
	Socket clientSocket = null;
	PrintWriter wr_out = null;
	BufferedReader wr_in = null;
	Server() throws Exception{
		serverSocket = new ServerSocket(7777);
	}

	public void run(){
		while(true){
			try {
			clientSocket = serverSocket.accept(); // Warte auf Verbindung
			wr_out = new PrintWriter(clientSocket.getOutputStream(),true);// Ausgabestrom
			wr_in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	// Eingabestrom
			frame = new Netzwerk_Chat_Frame("TransEvolution - Server", wr_out, wr_in);
			while(true){
			String incoming = wr_in.readLine();
			frame.addAusgabe(incoming);
				}
			} catch (IOException e){
				System.err.println("Fehler - ServerSocket akzeptiert nicht");
				}
		}
	}
}