package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class ServeurUDP {
    private static int PORT = 1234;
    private static Set<InetSocketAddress> clients = new HashSet<>();
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Serveur de chat démarré sur le port " + PORT);

            while (true) {
            	// Réception d'un paquet Datagram contenant les données du client
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                // Récupération de l'adresse et du port de l'expéditeur
                InetSocketAddress senderAddress = new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort());
                // Ajout du client à la liste s'il n'est pas déjà présent
                if (!clients.contains(senderAddress)) {
                    clients.add(senderAddress);
                }
                // Extraction du message du paquet reçu
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message reçu de " + senderAddress + " : " + message);
                // Transmission du message à tous les clients, à l'exception de l'expéditeur
                for (InetSocketAddress client : clients) {
                    if (!client.equals(senderAddress)) {
                        DatagramPacket sendPacket = new DatagramPacket(
                                receivePacket.getData(),
                                receivePacket.getLength(),
                                client.getAddress(),
                                client.getPort()
                        );
                        socket.send(sendPacket);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
