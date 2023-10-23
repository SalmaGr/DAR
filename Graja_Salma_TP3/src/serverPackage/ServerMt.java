package serverPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMt extends Thread {
	//On a utilisé les threads pour pouvoir gérer l'accès concurrent des clients.
	private int nombreclient; // par défaut 0

	public static void main(String[] args) {
		(new ServerMt()).start(); // Crée une instance de ServerMt et démarre le thread
	}
	@Override
	public void run() {
		// Le code de la méthode run est exécuté lorsque ce thread est démarré (start())
		try {
			ServerSocket ss = new ServerSocket(500); // Crée un serveur socket écoutant sur le port 500
			while (true) {
				Socket socket = ss.accept(); // accepter la connexion d'un client
				new ClientProcess(socket, ++nombreclient).start();
				// Pour chaque Client on crée un nouveau thread pour le gérer
			}
		} catch (IOException e) {
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
		System.out.println("le client : " + numclient + " de l'adresse ip " + socket.getRemoteSocketAddress());
		try {
			// Envoie un message de bienvenue au client avec son numéro
			new PrintWriter(socket.getOutputStream(), true).println("Bienvenue vous êtes le client n° :" + numclient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	}// fin classe ClientServer (sous classe)
}// fin classe ServerMT
