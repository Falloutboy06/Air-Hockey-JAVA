package Airhockey;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.*;

public class Serveur extends Thread {

	private Socket socket;
	private String IP;
	private ServerSocket serveurSocket;
	private Socket clientSocket;
	private int port=2009;
	private BufferedReader in;
	private PrintWriter out;
	Scanner sc = new Scanner(System.in);
	
	public Serveur(int port) {
		this.port = port;
		creationServeur();
	}	
	public void creationServeur() {
		
		try {
			serveurSocket = new ServerSocket(port);
		    clientSocket = serveurSocket.accept();
		    Thread envoi= new Thread(new Runnable() {
		          String msg;
		          @Override
		          public void run() {
		             while(true){
		                msg = sc.nextLine();
		                System.out.println(msg);
		                out.flush();
		             }
		          }
		       });
		       envoi.start();
		   
		       Thread recevoir= new Thread(new Runnable() {
		          String msg ;
		          @Override
		          public void run() {
		             try {
		                msg = "";
		                //tant que le client est connecté
		                while(msg!=null){
		                   System.out.println("Client : "+msg);
		                   msg = in.readLine();
		                }
		                //sortir de la boucle si le client a déconecté
		                System.out.println("Client déconecté");
		                //fermer le flux et la session socket
		                out.close();
		                clientSocket.close();
		                serveurSocket.close();
		             } catch (IOException e) {
		                  e.printStackTrace();
		             }
		         }
		      });
		      recevoir.start();
		} catch (Exception e) {  
			
			System.out.println("Erreur dans la creation du serveur : " + e);	
		}
		Thread t = new Thread(this);
		t.start();	
		System.out.println("serveur lancé");
	}
}