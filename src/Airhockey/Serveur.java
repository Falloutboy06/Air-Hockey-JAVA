package Airhockey;

import java.net.*;

import java.io.*;

public class Serveur extends Thread {

	final static int port = 2009;
	private Socket socket;
	private String IP;

	public static void main(String[] args) {
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
	}
	public Serveur(Socket socket) {
		this.socket=socket;
		this.IP=IP;
		//constructeur a faire
	}
	public void run() {
		traitements();
	}

	public void traitements() {

		try {

			String message = "";

			System.out.println("Connexion avec le client : " + socket.getInetAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintStream out = new PrintStream(socket.getOutputStream());

			message = in.readLine();

			out.println("Bonjour " + message);

			socket.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}