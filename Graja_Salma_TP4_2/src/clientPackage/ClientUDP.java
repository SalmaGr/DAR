package clientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
    private static int PORT = 1234; // Définition du port sur lequel le client envoie la requête
    private static byte[] receiveData = new byte[1024]; // Tableau de bytes pour stocker les données reçues

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(); // Création d'un socket pour le client
            String message = " "; // Message vide pour la requête
            byte[] sendData = message.getBytes(); // Conversion du message en tableau de bytes
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Adresse IP du serveur (dans ce cas, local)
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT); // Création d'un paquet pour envoyer la requête au serveur
            socket.send(sendPacket); // Envoi de la requête au serveur
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // Création d'un paquet pour recevoir la réponse du serveur
            socket.receive(receivePacket); // Réception de la réponse du serveur
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength()); // Conversion de la réponse en chaîne de caractères
            System.out.println("Date : " + response); // Affichage de la réponse du serveur
            socket.close(); // Fermeture du socket client

        } catch (Exception e) {
            e.printStackTrace(); // Gestion des exceptions en cas d'erreur
        }
    }
}
