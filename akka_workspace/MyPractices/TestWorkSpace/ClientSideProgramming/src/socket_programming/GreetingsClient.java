package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GreetingsClient {
	
	public static void main(String[] args) {
		System.out.println("Starting client");
		try (Socket socket = new Socket(999);) {
			
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF("Hello Server");
			
			System.out.println("Received message from server: " + dis.readUTF());
			
//			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
