package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDP {
	  private static int PORT = 1234;
	  private static byte[] buffer = new byte[1024];
	  public static void main(String[] args) {
	        try {
	            DatagramSocket socket = new DatagramSocket(PORT);
                System.out.println("Démarrage du serveur");
	            while (true) {
	            	// Créer un objet DatagramPacket pour recevoir des données dans 'buffer'
	                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
	                //receivePacket est un paquet vide
	                // Attend et reçoit des données dans le DatagramPacket "receivePacket"
	                socket.receive(receivePacket);
	                // Convertir les données reçues en une chaîne de caractères en utilisant le tampon de "receivePacket"
	                String clientName = new String(receivePacket.getData(), 0, receivePacket.getLength());
	                System.out.println(receivePacket.getAddress()+ " " + clientName);
	                String response = "Bienvenu " + clientName;
	                // Convertir la chaîne de caractères "response" en un tableau de bytes (tableau d'octets)
	                byte[] responseData = response.getBytes();
	                DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, receivePacket.getAddress(), receivePacket.getPort());
	                socket.send(sendPacket);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
