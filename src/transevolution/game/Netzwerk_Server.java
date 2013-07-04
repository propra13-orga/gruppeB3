package transevolution.game;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Netzwerk_Server {

	static ServerSocket sSocket;
	static Socket sock;
	static DataOutputStream outputstream;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server wird gestartet...");
		sSocket = new ServerSocket(8000);
		// nur Port, Host nicht wichtig, da wir hosten
		sock = sSocket.accept();
		System.out.println("Verbindung hergestellt mit: " + sock.getInetAddress());
		outputstream = new DataOutputStream(sock.getOutputStream());
//		outputstream.writeUTF("Socket Verbindung Test");
	}
}
