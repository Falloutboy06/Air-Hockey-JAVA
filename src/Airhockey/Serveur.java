package Airhockey;

import java.net.*;
import java.util.LinkedList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.*;

public class Serveur extends Thread {

	private Socket socket;
	private String IP;
	private ServerSocket serv;
	private int port;
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
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
		System.out.println("serveur lanc�");
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


	                out.println(getName());
	            
	                textField.setEditable(true);
	          
	                messageArea.append(message.substring(8) + "\n");
	            
	        

			socket.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}