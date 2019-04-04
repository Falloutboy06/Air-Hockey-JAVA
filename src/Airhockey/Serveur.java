package Airhockey;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.*;
import Airhockey.Panneau;
import Airhockey.pageclient;
public class Serveur extends Thread {

	private Socket socket;
	private String IP;
	private ServerSocket serveurSocket;
	private Socket clientSocket;
	private int port=2009;
	private int posXJ1;
	private int posYJ1;
	private int posXJ2;
	private int posYJ2;
	private float posPalletY;
	private float posPalletX;
	private Panneau pan = new Panneau();
	Scanner sc = new Scanner(System.in);
	int ID;
	
	
	public Serveur(int port) {
		this.port = port;
		creationServeur();
	}	
	public void creationServeur() {
		Point point = MouseInfo.getPointerInfo().getLocation();
		pan.getJoueur();
		try {
			serveurSocket = new ServerSocket(port);
		    clientSocket = serveurSocket.accept();
		    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
		    Thread envoi= new Thread(new Runnable() {
		          String msg="0";
		          @Override
		          public void run() {
		             while(true){
		            	 if (ID==1)
			                {
			                	posXJ1 = (int)point.getX()-150;
			                	posYJ1 = (int)point.getY()-250;
			                	posPalletY= pan.getPosY();
			                	posPalletX= pan.getPosX();
			                }
			               if (ID==2)
			               {
				                posXJ2 = (int)point.getX()-150;
				                posYJ2 = (int)point.getY()-250;
			                }
		                msg = sc.nextLine();
		                System.out.println(msg);
		                System.out.flush();

		             }
		          }
		       });
		       envoi.start();
		   
		       Thread recevoir= new Thread(new Runnable() {
		          String msg="0" ;
		          @Override
		          public void run() {
		             try {
		                //tant que le client est connecté
		                while(msg!=null){
		                   System.out.println("Client : "+msg);
		                   msg = in.readLine();
		                   if (ID==1)
			                {
		                	   pan.setPosA(posXJ2);
		                	   pan.setPosB(posYJ2);
			                }
			               if (ID==2)
			               {
			            	   pan.setPosA(posXJ1);
		 	                	pan.setPosB(posYJ1);
		 	                	pan.setPosY(posPalletY);
		 	                	pan.setPosX(posPalletX);
			                }
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