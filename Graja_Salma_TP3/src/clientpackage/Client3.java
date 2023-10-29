package clientpackage;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import serverPackage.Message;

public class Client3 {
	public static void main(String[] args) {
		try {
			//Socket socket = new Socket("localhost", 500);
			//InetAddress inetaddress = InetAddress.getByName("ipserveur");
			InetAddress inetaddress = InetAddress.getByName("192.168.119.96");
			InetSocketAddress inetsoketaddress = new InetSocketAddress(inetaddress,500);
			Socket socket = new Socket();
			socket.connect(inetsoketaddress);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message message = (Message) ois.readObject();
			System.out.println(message.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
