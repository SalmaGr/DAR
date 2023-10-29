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
				ServerSocket serversocket = new ServerSocket(600);
				System.out.println("je suis un serveur j'attends un client...");
				Socket socket = serversocket.accept();
	
				System.out.println("un client est connecte");
			
				InputStream is = socket.getInputStream(); 
				OutputStream os = socket.getOutputStream();
				
				int nb = is.read();
				System.out.println(nb);	
				int resultat = 0;
				int operation = is.read();
				switch (operation) {
				case 1:
					resultat = nb + 5;break;
				case 2:
					resultat = nb - 5;break;
				case 3:
					resultat = nb * 5;break;
				case 4:
					resultat = nb / 5;break;
				case 5:
					resultat = nb % 5;break;
				}
				os.write(resultat);
				// La dernière étape : Fermer socket
				System.out.println("deconnexion serveur");
				socket.close();
		        serversocket.close();
				}catch (Exception e) {e.printStackTrace();}

	}

}