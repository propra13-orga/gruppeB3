/**
 * Chat ueber die Konsole
 * Code verursacht Fehler bei Tasteneingaben in Jack.java und somit in Startgame.java Zeile 528
 * Darum alles auskommentiert
 * Muss nur angepasst werden, Prinzip funktioniert
 */

//package transevolution.game;
//
//import java.io.*;
//import java.net.*;
//import java.util.*;
//
//public class NetzwerkChat_Client {
//	
//	static Socket socket;
//	static DataInputStream in;
//	static DataOutputStream out;
//	
//	public static void main(String[] args) throws Exception {
//		System.out.println("Connecting...");
//		socket = new Socket("localhost", 7777);
//		System.out.println("Connection succesful");
//		in = new DataInputStream(socket.getInputStream());
//		out = new DataOutputStream(socket.getOutputStream());
//		Input input = new Input(in);
//		Thread thread = new Thread(input);
//		thread.start();
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter your Name and then press ENTER");
//		String name = sc.nextLine();
//		out.writeUTF(name);
//		while (true) {
//			String sendMessage = sc.nextLine();
//			out.writeUTF(sendMessage);
//		}
//	}
//
//}
//
//class Input implements Runnable {
//	
//	DataInputStream in;
//	
//	public Input(DataInputStream in){
//		this.in = in;
//	}
//	
//	public void run() {
//		while(true) {
//			String message;
//			try {
//				message = in.readUTF();
//				System.out.println(message);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//}