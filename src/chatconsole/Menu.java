package chatconsole;

import java.util.Scanner;

public class Menu {

	private static Scanner in;

	public void menu() {

		GetsAndSets ob = new GetsAndSets();
		in = new Scanner(System.in);

		System.out.println("\n      Chat");
		System.out.println("---------------");
		System.out.println("1-Log in");
		System.out.println("2-Sign up");
		System.out.println("3-EXIT");
		System.out.println("---------------");

		try {
			System.out.print("Enter: ");
			int n = in.nextInt();

			if (n == 1) {
				Login.login(ob);
			}

			if (n == 2) {
				Signup.enterValues(ob);
				Signup.writeValues(ob);
			}

			if (n == 3) {
				System.out.println("Program closed...");
				System.exit(0);
			}

			if (n <= 0 || n > 3) {
				System.out.println("Please choose 1, 2 or 3");
				menu();
			}
		} catch (Exception ex) {
			System.out.println("Enter a number");
			menu();
		}
	}
}
