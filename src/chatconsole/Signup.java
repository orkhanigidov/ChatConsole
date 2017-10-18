package chatconsole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Signup {

	private static Scanner in;
	private static Scanner in2;
	private static BufferedReader br;

	public static void enterValues(GetsAndSets ob) {

		in = new Scanner(System.in);

		try {
			System.out.print("Name: ");
			ob.setName(in.next());
			System.out.print("Surname: ");
			ob.setSurname(in.next());
			System.out.print("Username: ");
			ob.setUsername(in.next());

			chechUsername(ob);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void writeValues(GetsAndSets ob) {

		try {
			File f = new File("Registed.txt");
			FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);

			if (ob.getPassword().equals(ob.getRe_password())) {
				bw.newLine();
				bw.append("---------------");
				bw.newLine();
				bw.append("User:" + ob.getUsername());
				bw.newLine();
				bw.append(ob.getUsername() + "/" + ob.getPassword());
				bw.newLine();
				bw.append("Name: " + ob.getName());
				bw.newLine();
				bw.append("Surname: " + ob.getSurname());
				bw.close();
				System.out.println("Registration successful");
				new Menu().menu();
			} else {
				if (!ob.getPassword().equals(ob.getRe_password())) {
					System.out.println("Password do not match, please retype");
				}
				new Menu().menu();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void chechUsername(GetsAndSets ob) {

		in2 = new Scanner(System.in);

		try {
			FileReader fr = new FileReader("Registed.txt");
			br = new BufferedReader(fr);

			boolean check = true;
			String find = ("User:").concat(ob.getUsername());
			String line = "";

			while ((line = br.readLine()) != null) {
				if (find.equals(line)) {
					check = false;
				}
			}

			if (check == false) {
				System.out.println("This username already used, please retype another");
				enterValues(ob);
			} else {
				System.out.print("Password: ");
				ob.setPassword(in2.next());
				System.out.print("Verify Password: ");
				ob.setRe_password(in2.next());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
