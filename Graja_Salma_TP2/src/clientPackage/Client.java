package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 600);
			int op1, op2;
			String operation;
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			Scanner scan = new Scanner(System.in);
			System.out.println("Donnez le 1er nombre");
			op1 = scan.nextInt();
			System.out.println("Donnez le 2eme nombre");
			op2 = scan.nextInt();
			do {
				System.out.println("Donnez l'opérateur (+, -, *, /, %)");
				operation = scan.next();
			} while (!operation.equals("+") && !operation.equals("-") && !operation.equals("*")
					&& !operation.equals("/") && !operation.equals("%"));
			pw.println(op1);
			pw.println(op2);
			pw.println(operation);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			System.out.println(op1 + " " + operation + " " + op2 + " = " + br.readLine());
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
