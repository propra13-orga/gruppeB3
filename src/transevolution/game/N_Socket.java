package transevolution.game;

// Fehlversuch - eventuelle Nutzung für später, Peter 

/**
 *
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Netzwerk_Socket extends Thread {
	protected DataInputStream inputstream = null;
	protected PrintStream outputstream = null;
	private Socket socket = null;
	
	public Netzwerk_Socket(Socket sock) {
		super();
		try {
			inputstream = new DataInputStream(new BufferedInputStream(sock.getInputStream(), 49155));
			outputstream = new PrintStream(new BufferedOutputStream(sock.getOutputStream(), 49155));
			socket = sock;
		}
		catch (IOException e) {
			System.err.println(e.toString());
			System.exit(1);
		}
	}
	
	public void start() {}
// 	public void start(String[] args) {
//		if (args.length !=1) {
//			System.out.println("Nutzung: Netzwerk Socket auf Host Rechner");
//			System.exit(1);
//  }		
	
	public void senden(String send) {
		outputstream.println(send);
	}
	@SuppressWarnings("deprecation") // nerv nerv, inputstream hat bereits einen Bufferedreader
	public String empfangen() throws IOException {
		return inputstream.readLine();
	}
	
	public void verbindungSchliessen() {
		try {
			socket.close();
			socket = null;
		}
		catch (IOException e) {
			System.err.println("Socket konnte nicht geschlossen werden: " + e);
		}
	}
	
	public boolean verbindungpruefen() {
		return ((inputstream != null) && (outputstream != null) && (socket != null));
	}
	
	protected void beenden() {
		if (socket != null) {
			try { socket.close(); }
			catch (IOException e) {
				System.err.println("Socket konnte nicht geschlossen werden: " + e);
			}
			socket = null;
		}
	}

}
*/