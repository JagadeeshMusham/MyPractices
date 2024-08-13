package com.musham.socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
// File Name GreetingServer.java
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer {
	// private ServerSocket serverSocket;

	// public GreetingServer(int port) throws IOException {
	// serverSocket = new ServerSocket(port);
	// serverSocket.setSoTimeout(10000);
	// }
	//
	// public void run() {
	// while (true) {
	// try {
	// System.out.println("Waiting for client on port " +
	// serverSocket.getLocalPort() + "...");
	// Socket server = serverSocket.accept();
	//
	// System.out.println("Just connected to " + server.getRemoteSocketAddress());
	// DataInputStream in = new DataInputStream(server.getInputStream());
	//
	// System.out.println(in.readUTF());
	// DataOutputStream out = new DataOutputStream(server.getOutputStream());
	// out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
	// + "\nGoodbye!");
	// server.close();
	//
	// } catch (SocketTimeoutException s) {
	// System.out.println("Socket timed out!");
	// break;
	// } catch (IOException e) {
	// e.printStackTrace();
	// break;
	// }
	// }

	private ServerSocket serverSocket;

	public void server() {

		try {
			serverSocket = new ServerSocket(999);

			while (true) {
				try (Socket server = serverSocket.accept()) {

				DataInputStream dis = new DataInputStream(server.getInputStream());
				DataOutputStream dos = new DataOutputStream(server.getOutputStream());

				System.out.println("Read data from client: " + dis.readUTF());

				dos.writeUTF("Thank You for connecting from client" + server.getRemoteSocketAddress());

//				server.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		int port = Integer.parseInt(args[0]);
		GreetingServer gs = new GreetingServer();
		gs.server();
	}
}