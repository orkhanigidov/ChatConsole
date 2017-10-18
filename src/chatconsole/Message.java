package chatconsole;

import java.io.DataInputStream;

public class Message implements Runnable {

	private static DataInputStream input = null;

	public Message(DataInputStream input) {
		Message.input = input;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		String message;
		try {
			while ((message = input.readLine()) != null) {
				System.out.println(message);
				if (message.indexOf("Bye ") != -1) {
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
