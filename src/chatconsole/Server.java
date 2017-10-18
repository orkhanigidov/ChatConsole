package chatconsole;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final int max_client = 30;
	private static final clientThread[] threads = new clientThread[max_client];

	public static void start_server() {
		int port_number = 2222;
		System.out.println("Server started on port " + port_number);

		try {
			serverSocket = new ServerSocket(port_number);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		while (true) {
			try {
				new Server().waitConnection();
				new Server().addClient();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private void addClient() {
		for (int i = 0; i < max_client; i++) {
			if (threads[i] == null) {
				threads[i] = new clientThread(clientSocket, threads);
				threads[i].start();
				break;
			}
		}
	}

	private void waitConnection() {
		try {
			clientSocket = serverSocket.accept();
			System.out.println(
					"Connection from : " + clientSocket.getInetAddress() + " on port " + clientSocket.getPort());
			// return clientSocket;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// return null;
	}
}
