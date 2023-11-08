package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServeurUDP {
    private static int PORT = 1234; // Définition du port sur lequel le serveur écoute
    private static byte[] buffer = new byte[1024]; // Tableau de bytes pour stocker les données reçues

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Création d'un socket pour le serveur
            System.out.println("Démarrage du serveur");
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length); // Création d'un paquet pour recevoir les données
                socket.receive(receivePacket); // Réception des données depuis le client
                String time = getCurrentTime(); // Appel de la méthode pour obtenir l'heure actuelle
                byte[] responseData = time.getBytes(); // Conversion de l'heure en tableau de bytes
                // Création d'un paquet pour envoyer la réponse au client
                DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, receivePacket.getAddress(), receivePacket.getPort()); 
                socket.send(sendPacket); // Envoi de la réponse au client
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des exceptions en cas d'erreur
        }
    }

    private static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Format de date souhaité
        Date date = new Date(); // Obtention de la date actuelle
        return dateFormat.format(date); // Formatage de la date en chaîne de caractères
    }
}
