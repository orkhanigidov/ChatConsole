package chatconsole;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

	private static Socket clientSocket = null;
	private static PrintStream output = null;
	private static DataInputStream input = null;
	private static BufferedReader inputLine = null;

	public void start_client() {
		int port_number = 2222;
		String host = "localhost";

		try {
			clientSocket = new Socket(host, port_number);
			setup();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		if (clientSocket != null && output != null && input != null) {
			try {
				// String message;
				new Client().startListen(input);
				boolean closed = false;
				while (!closed) {
					output.println(inputLine.readLine());
					// message = inputLine.readLine();
					// output.println(message);
					// output.flush();
				}
				output.close();
				input.close();
				clientSocket.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	}

	private void setup() {
		try {
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			output = new PrintStream(clientSocket.getOutputStream());
			input = new DataInputStream(clientSocket.getInputStream());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void startListen(DataInputStream input) {
		Thread thread = new Thread(new Message(input));
		thread.start();
	}
}
