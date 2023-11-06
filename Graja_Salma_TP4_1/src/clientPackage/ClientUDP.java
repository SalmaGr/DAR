package clientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
		private static int PORT = 1234;
		private static byte[] receiveData = new byte[1024];
	    public static void main(String[] args) {
	        try {
	        	// Crée un DatagramSocket pour la communication en réseau
	        	DatagramSocket socket = new DatagramSocket();
	        	
	        	Scanner scan = new Scanner(System.in);
	        	// Demande de saisie du nom et prénom du client
	        	System.out.println("Saisir votre nom et prénom : ");
	        	String message = scan.next();
	        	// Convertir la chaîne de caractères en un tableau de bytes pour l'envoi
	        	byte[] sendData = message.getBytes();
	        	// Récupèrer l'adresse IP du serveur en utilisant le nom "localhost"
	        	InetAddress serverAddress = InetAddress.getByName("localhost");
	        	// Crée un paquet à envoyer contenant les données et l'adresse du serveur
	        	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
	        	// Envoie le paquet de données au serveur
	        	socket.send(sendPacket);
	        	// Créer un tableau de bytes pour recevoir les données du serveur
	        	byte[] receiveData = new byte[1024];
	        	// Créer un paquet pour recevoir les données du serveur
	        	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	        	// Attend et reçoit des données du serveur dans le paquet
	        	socket.receive(receivePacket);
	        	// Affiche l'adresse du serveur depuis le paquet reçu
	        	System.out.println("Adresse du serveur : " + receivePacket.getAddress());
	        	System.out.println("Numéro de port du serveur : " + receivePacket.getPort());
	        	// Convertit les données reçues en une chaîne de caractères
	        	String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
	        	System.out.println(response);
	        	socket.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
