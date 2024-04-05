package general.socket_programming;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	public static void main(String[] args) throws Exception {
		try {
			InetAddress inet = InetAddress.getLocalHost();

			System.out.println("Inet: " + inet.toString());

			inet = InetAddress.getByName("DESKTOP-KSEO34H");

			System.out.println("Inet: " + inet.toString());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		int c;

		// Create a socket connected to internic.net, port 43. Manage this // socket
		// with a try-with-resources block.
		try (Socket s = new Socket("whois.internic.net", 43)) {

			// Obtain input and output streams.
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();

			// Construct a request string.
			String str = (args.length == 0 ? "MHProfessional.com" : args[0]) + "\n";

			// Convert to bytes.
			byte buf[] = str.getBytes();

			// Send request.
			out.write(buf);

			// Read and display response.
			while ((c = in.read()) != -1) {
				System.out.print((char) c);
			}
		} // The socket is now closed.
		catch (Exception e) {

		}

		ds = new DatagramSocket(serverPort);
		server();

	}

	public static DatagramSocket ds;

	public static int serverPort = 998;
	public static int clientPort = 999;
	public static int buffer_size = 1024;

	public static byte buffer[] = new byte[buffer_size];

	public static void server() throws Exception {
		int pos = 0;
		while (true) {
			int c = System.in.read();
			switch (c) {
			case -1:
				System.out.println("Server Quits.");

				ds.close();
				return;
			case '\r':
				break;
			case '\n':
				ds.send(new DatagramPacket(buffer, pos, InetAddress.getLocalHost(), clientPort));
				pos = 0;
				break;
			default:
				buffer[pos++] = (byte) c;
			}
		}
	}

}
