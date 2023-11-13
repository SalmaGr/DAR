package clientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientUDP {
    private static int PORT = 1234;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            InetSocketAddress serverAddress = new InetSocketAddress("localhost", PORT);

            System.out.print("Entrez votre nom d'utilisateur : ");
            String username = scanner.nextLine();

            // Thread pour recevoir les messages du serveur
            Thread receiveThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    while (true) {
                    	// Réception d'un paquet contenant un message du serveur
                        socket.receive(receivePacket);
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            
            // Démarrage du thread de réception
            receiveThread.start();

            // Envoyer les messages au serveur
            while (true) {
                System.out.print("Message : ");
                String message = username + ": " + scanner.nextLine();
                // Conversion du message en tableau de bytes
                byte[] sendData = message.getBytes();
                // Création d'un paquet contenant le message à envoyer au serveur
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress.getAddress(), serverAddress.getPort());
                // Envoi du paquet au serveur
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
