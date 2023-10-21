package serverPackage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			System.out.println("Je suis un serveur");
			ServerSocket serverSocket = new ServerSocket(600);
			System.out.println("Je suis un serveur, j'attends un client");
			Socket socket = serverSocket.accept();
			System.out.println("Un client est connecté");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			int op1, op2;
			int resultat = 0;
			String operation;
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			op1 = Integer.parseInt(br.readLine());
			op2 = Integer.parseInt(br.readLine());
			operation = br.readLine();
			switch (operation) {
			case "+":
				resultat = op1 + op2;break;
			case "-":
				resultat = op1 - op2;break;
			case "*":
				resultat = op1 * op2;break;
			case "/":
				resultat = op1 / op2;break;
			case "%":
				resultat = op1 % op2;break;
			}
			PrintWriter pw = new PrintWriter(os, true);
			pw.println(resultat);
			System.out.println("Déconnexion serveur");
			socket.close();
			serverSocket.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
}
