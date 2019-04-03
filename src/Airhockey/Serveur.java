package Airhockey;

import java.net.*;
import java.util.LinkedList;
import java.io.*;

public class Serveur extends Thread {

	private Socket socket;
	private String IP;
	private ServerSocket serv;
	private int port;

	/*public static void main(String[] args) {
		try {
				ServerSocket socketServeur = new ServerSocket(port);
				System.out.println("Lancement du serveur");
				while (true) {
					Socket socketClient = socketServeur.accept();
					Serveur t = new Serveur(socketClient);
					t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public Serveur(int port) {
		
		this.port = port;
		creationServeur();
	}	
	
	public void creationServeur() {
		
		try {
			serv = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println("Erreur dans la creation du serveur : " + e);	
		}
		Thread t = new Thread(this);
		t.start();	
		System.out.println("serveur lancé");
	}
	
	public void run() {
		traitements();
	}
	public void traitements() {

		/*try {

			String message = "";

			System.out.println("Connexion avec le client : " + socket.getInetAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintStream out = new PrintStream(socket.getOutputStream());

			message = in.readLine();

			out.println("Bonjour " + message);

			socket.close();

		} catch (Exception e) {

			e.printStackTrace();

		}*/

	}

}