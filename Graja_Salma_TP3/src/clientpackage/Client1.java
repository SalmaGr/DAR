package clientpackage;

import java.net.Socket;

public class Client1{

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost",500)) {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
