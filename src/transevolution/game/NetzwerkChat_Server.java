package transevolution.game;

import java.io.*;
import java.net.*;

public class NetzwerkChat_Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static Users[] user = new Users[10];
	static DataInputStream in;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		while(true){
		socket = serverSocket.accept();
		for(int i = 0; i<10; i++){
		System.out.println("Connection from: " + socket.getInetAddress());
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		if(user[i] == null){
			user[i] = new Users(out, in, user);
			Thread thread = new Thread(user[i]);
			thread.start();
			break;
		
		}
		}
		}
	}
}
	
class Users implements Runnable {
	
	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[10];
	String name;
	
	public Users(DataOutputStream out, DataInputStream in, Users[] user) {
		this.out = out;
		this.in = in;
		this.user = user;
	}
	
	public void run() {
		try {
			name = in.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				String message = in.readUTF();
				for (int i=0; i<10; i++){
					if(user[i] != null){
						user[i].out.writeUTF(name + ": " + message);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.out = null;
				this.in = null;
			}
			
		}
	}
}