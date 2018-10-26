package chatconsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Login {

	private static Scanner in;
	private static BufferedReader br;

	public static void login(GetsAndSets ob) {
		boolean check = false;

		try {
			FileReader fr = new FileReader("Registed.txt");
			br = new BufferedReader(fr);
			in = new Scanner(System.in);

			System.out.print("Your Username: ");
			ob.setUsername(in.next());
			System.out.print("Your Password: ");
			ob.setPassword(in.next());

			String find = ob.getUsername().concat("/").concat(ob.getPassword());
			String line;

			while ((line = br.readLine()) != null) {
				if (find.equals(line)) {
					check = true;
				}
			}

			if (check == true) {
				System.out.println("Login Successful");
				new Client().start_client();
			} else {
				System.out.println("Try again...");
				new Menu().menu();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
