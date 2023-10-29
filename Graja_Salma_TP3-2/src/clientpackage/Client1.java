package clientpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import serverPackage.operation;

public class Client1 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            OutputStream os = socket.getOutputStream();  
            InputStream is = socket.getInputStream(); 
            InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			System.out.println(br.readLine());
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Donnez le 1er nombre");
			int op1 = scan.nextInt();
			System.out.println("Donnez le 2eme nombre");
			int op2 = scan.nextInt();
			char o;
			 do {
	                System.out.println("Donnez l'opérateur (+, -, *, /, %)");
	                o = scan.next().charAt(0);
	            } while (o != '+' && o != '-' && o != '*' && o != '/' && o != '%');
			 
			operation op = new operation(op1, op2, o);
			// envoie de l'objet operation vers le serveur
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(op);
			//(new ObjectOutputStream(socket.getOutputStream())).writeObject(op);
			// Recevoie du resultat
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			operation res = (operation) ois.readObject();
			System.out.println("Resultat: " + res.getResultat());
			socket.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Erreur de désérialisation : Classe non trouvée.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur d'entrée/sortie lors de la communication avec le serveur.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur inattendue.");
        }
    }
}
