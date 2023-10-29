package serverPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMt extends Thread {
	private int nombreclient;

	public static void main(String[] args) {
		(new ServerMt()).start(); // Crée une instance de ServerMt et démarre le thread
	}

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(1234); // Crée un serveur socket écoutant sur le port 500
			while (true) {
				Socket socket = ss.accept(); // accepter la connexion d'un client
				new ClientProcess(socket, ++nombreclient).start();
				// Pour chaque Client, on crée un nouveau thread pour le gérer
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ClientProcess extends Thread {
		private int numclient;
		private Socket socket; // Socket associé au client

		public ClientProcess(Socket socket, int numclient) {
			this.socket = socket;
			this.numclient = numclient;
		}

		@Override
		public void run() {
			System.out.println("Le client : " + numclient + " de l'adresse IP " + socket.getRemoteSocketAddress());
			try {
				new PrintWriter(socket.getOutputStream(), true)
						.println("Bienvenue, vous êtes le client n° : " + numclient);
				// Recevoie de l'objet operation du client
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				operation o1 = (operation) ois.readObject();

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
				// calcul de l'operation
				o1.setResultat(resultat);
				// envoie du reponse vers le client
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("envoie la Resultat vers le client");
				oos.writeObject(o1);
				System.out.println("Serveur: la Resultat est envoyée vers le client");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}