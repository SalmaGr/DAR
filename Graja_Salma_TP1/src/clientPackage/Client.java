package clientPackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("je suis un client");
			//InetAddress inetaddress = InetAddress.getByName("10.25.13.246");
			//InetSocketAddress inetsoketaddress = new InetSocketAddress(inetaddress,500);
			//Socket socket = new Socket();
			//socket.connect(inetsoketaddress);
			Socket socket =new Socket ("localhost",500);
			// On peut remplacer 'localhost' par l'adresse IP de la machine.
			System.out.println("je suis un client connecte");
			//traitement
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			int nb;
			System.out.println("nb =");
			
			Scanner scanner = new Scanner(System.in);
			nb=scanner.nextInt();
		
			os.write(nb);
			System.out.println("la multiplication de "+nb+"*5="+is.read());
			//System.out.println("laddition de "+nb+"+5="+is.read());
			System.out.println("deconnexion client");
			socket.close();
			}catch(Exception e) {e.printStackTrace();}

	}

}