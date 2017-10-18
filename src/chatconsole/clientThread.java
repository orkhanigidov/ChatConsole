package chatconsole;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

class clientThread extends Thread {

	private DataInputStream input = null;
	private PrintStream output = null;
	private Socket clientSocket = null;
	private final clientThread[] threads;
	private final int max_client;
	String name = null;

	public clientThread(Socket clientSocket, clientThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		max_client = threads.length;
		setup();
	}

	private void setup() {
		try {
			input = new DataInputStream(clientSocket.getInputStream());
			output = new PrintStream(clientSocket.getOutputStream());
		} catch (Exception ex) {
		}
	}

	@Override
	public void run() {
		try {
			enterRoom();
			forwardMessages();
			exitRoom();
			close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void close() throws IOException {
		input.close();
		output.close();
		clientSocket.close();
	}

	private void cleanThread() {
		for (int i = 0; i < max_client; i++) {
			if (threads[i] == this) {
				threads[i] = null;
			}
		}
	}

	private void exitRoom() {
		for (int i = 0; i < max_client; i++) {
			if (threads[i] != null && threads[i] != this) {
				threads[i].output.println("The user " + name + " is leaving the chat room.");
			}
		}
		output.println("Bye " + name);
		cleanThread();
	}

	@SuppressWarnings("deprecation")
	private void enterRoom() {
		try {
			output.println("Enter your name: ");
			name = input.readLine();
			output.println("Hello " + name + ", welcome to chat room.\nFor leave enter /exit in a new line");

			for (int i = 0; i < max_client; i++) {
				if (threads[i] != null && threads[i] != this) {
					threads[i].output.println("A new user " + name + " entered the chat room.");
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	private void forwardMessages() throws Exception {
		String message;
		while (true) {
			message = input.readLine();
			if (message.equals("/exit")) {
				break;
			}

			for (int i = 0; i < max_client; i++) {
				if (threads[i] != null) {
					threads[i].output.println("< " + name + " > " + message);
				}
			}
		}
	}
}
