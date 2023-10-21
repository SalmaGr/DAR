package clientPackage2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import serverPackage2.operation;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 600);
			Scanner scan = new Scanner(System.in);
			System.out.println("Donnez le 1er nombre");
			int op1 = scan.nextInt();
			System.out.println("Donnez le 2eme nombre");
			int op2 = scan.nextInt();
			char o;
			 do {
	                System.out.println("Donnez l'opérateur (+, -, *, /, %)");
	                o = scan.next().charAt(0);
	            } while (o != '+' && o != '-' && o != '*' && o != '/' && o != '%');
			 
			operation op = new operation(op1, op2, o);

			// envoie de l'objet operation vers le serveur
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(op);
			
			//(new ObjectOutputStream(socket.getOutputStream())).writeObject(op);

			// Recevoie du resultat
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			operation res = (operation) is.readObject();
		
			System.out.println("Resultat: " + res.getResultat());

			//((operation) (new ObjectInputStream(socket.getInputStream())).readObject()).getResultat();
			
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
