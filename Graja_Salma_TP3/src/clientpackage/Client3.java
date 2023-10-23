package clientpackage;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client3 {
	public static void main(String[] args) {
		try {
			//Socket socket = new Socket("localhost", 500);
			//InetAddress inetaddress = InetAddress.getByName("ipserveur");
			InetAddress inetaddress = InetAddress.getByName("192.168.1.7");
			InetSocketAddress inetsoketaddress = new InetSocketAddress(inetaddress,500);
			Socket socket = new Socket();
			socket.connect(inetsoketaddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
