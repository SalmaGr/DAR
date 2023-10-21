package serverPackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
			try {
				// La première étape :	
				System.out.println("je suis un serveur");
				ServerSocket serversocket = new ServerSocket(500);	
				// new ServerSocket(8080) reserve le port 1234 
				/* ServerSocket est principalement réservée aux classes de type serveur 
			 	pour attendre et accepter les connexions entrantes des clients. */
				System.out.println("je suis un serveur j'attends un client");
				Socket socket = serversocket.accept();
				/* La méthode accept() attend une connexion entrante sur le port 8080
				 bloque l'exécution jusqu'à ce qu'une connexion soit établie */
				
				/* La deuxième étape consiste à détecter la connexion d'un client au serveur via le même port.
				 Une fois cette connexion détectée, la méthode accept() accepte cette connexion et renvoie 
				 un nouvel objet Socket qui représente la liaison avec le client. 
				 À partir de là, le programme peut communiquer avec le client en utilisant le Socket retourné
				 et le traitement commence à être effectué. */
				System.out.println("un client est connecte");
				// traitement
				InputStream is = socket.getInputStream(); 
				/*La méthode getInputStream() appartient aux classes Socket et est utilisée pour
				lire les données envoyées par le client connecté au Socket. */
				OutputStream os = socket.getOutputStream();
				/*La méthode getOutputStream() appartient aux classes Socket et est utilisée pour 
				écrire des données à envoyer au client connecté au Socket.*/
				int nb = is.read();
				System.out.println(nb);	
				//int a =nb + 5;
				os.write(nb*5);
				//os.write(a);
				// La dernière étape : Fermer socket
				System.out.println("deconnexion serveur");
				socket.close();
		        serversocket.close();
				}catch (Exception e) {e.printStackTrace();}

	}

}