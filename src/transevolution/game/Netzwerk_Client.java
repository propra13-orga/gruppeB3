package transevolution.game;

import java.io.DataInputStream;
import java.net.Socket;

public class Netzwerk_Client {
	
	static Socket sock;
	static DataInputStream inputstream;
	
	public static void main(String[] args) throws Exception {
		System.out.println("verbinden...");
		sock = new Socket("localhost", 8000);
		System.out.println("Verbindung hergestellt");
		inputstream = new DataInputStream(sock.getInputStream());
		// DataInputStream empfaengt Dateien, die vom Server gesendet werden
		String in = inputstream.readUTF();
		System.out.println("Nachricht vom Server: "+ in);
	}

}
