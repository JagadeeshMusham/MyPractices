package java.client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientProgram {
	public static int clientPort = 999;
	public static int buffer_size = 1024;
	public static DatagramSocket ds;
	public static byte buffer[] = new byte[buffer_size];

	public static void Client() throws Exception {
		while (true) {
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ds.receive(p);
			System.out.println(new String(p.getData(), 0, p.getLength()));
		}
	}

	public static void main(String args[]) throws Exception {
		ds = new DatagramSocket(clientPort);
		Client();
	}
}
