package clientpackage;

import java.io.ObjectInputStream;
import java.net.Socket;

import serverPackage.Message;

public class Client1{

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",600);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message message = (Message) ois.readObject();
			System.out.println(message.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
