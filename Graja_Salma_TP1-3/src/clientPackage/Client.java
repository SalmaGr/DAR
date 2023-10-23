package clientPackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("je suis un client");
			InetAddress inetaddress = InetAddress.getByName("192.168.247.96");
			/* Utilisé pour établir une communication réseau avec une machine distante 
			   dont l'adresse IP est "192.168.247.96".         */
			InetSocketAddress inetsoketaddress = new InetSocketAddress(inetaddress, 600);
			// Utilisé pour définir la destination de la communication réseau.
			Socket socket = new Socket();
			socket.connect(inetsoketaddress);
			System.out.println("je suis un client connecte");
			//traitement
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			int nb;
			System.out.println("nb =");	
			Scanner scanner = new Scanner(System.in);
			nb=scanner.nextInt();
			os.write(nb);
			int operation;
			do {	System.out.println("Donnez l'opérateur (1: '+', 2: '-', 3: '*', 4: '/',5: '%')");
					operation = scanner.nextInt();
				} while (operation !=1 && operation !=2 && operation !=3 && operation !=4 &&operation !=5);
			os.write(operation);
			switch (operation) {
			case 1:
				System.out.println("laddition de "+nb+"+5="+is.read());
				break;
			case 2:
				System.out.println("La soustraction de " + nb + " - 5 = " + is.read());
			    break;
			case 3:
			    System.out.println("La multiplication de " + nb + " * 5 = "+ is.read());
			    break;
			case 4:
			    System.out.println("La division de " + nb + " / 5 = "+ is.read());
			    break;
			case 5:
			    System.out.println("Le reste de la division de " + nb + " par 5 = "+ is.read());
			    break;}
			System.out.println("deconnexion client");
			socket.close();
			}catch(Exception e) {e.printStackTrace();}

	}

}