package serverPackage2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			System.out.println("Je suis un serveur");
			ServerSocket serverSocket = new ServerSocket(600);
			System.out.println("Je suis un serveur, j'attends un client...");
			Socket socket = serverSocket.accept();
			System.out.println("Un client est connecté");

			// Recevoie de l'objet operation du client 
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			operation o1= (operation) is.readObject();

			int op1 = o1.getOp1();
			int op2 = o1.getOp2();
			char operation = o1.getOperation();

			int resultat = 0;
			switch (operation) {
			case '+':
				resultat = op1 + op2;
				break;
			case '-':
				resultat = op1 - op2;
				break;
			case '*':
				resultat = op1 * op2;
				break;
			case '/':
				if (op2 != 0) {
					resultat = op1 / op2;
				} else {
					resultat = 0; // division impossible
				}
				break;
			case '%':
				if (op2 != 0) {
					resultat = op1 % op2;
				} else {
					op1 = 0; // division impossible
				}
				break;
			}

			// calcul l'operation
			o1.setResultat(resultat);
			
			// envoie du reponse vers le client
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(o1);

			System.out.println("Serveur: la Resultat est envoyeée vers le client");
			System.out.println("Déconnexion serveur");
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
