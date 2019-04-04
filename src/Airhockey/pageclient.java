package Airhockey;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.ServerSocket;
import net.miginfocom.swing.MigLayout;
import Airhockey.pageclient.Bouton2Listener;
import Airhockey.pageclient.BoutonListener;
import Airhockey.pageclient.PlayAnimation;
import Airhockey.Panneau;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

class TestImagePanel extends JPanel {

	 
	private static final long serialVersionUID = 1L;
	private Image img;
	public TestImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}
	public TestImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
public class pageclient extends JFrame {

	private JFrame frame;
	private JTextField Message;
	private static String J1;
	private static String J2;
	private static String IP;
	private static int ID;
	final static int port = 2009;
	private Panneau pan = new Panneau();
	private JPanel container = new JPanel();
	private JLabel label = new JLabel("Le JLabel");
	private JButton bouton = new JButton("Go");
	private JButton bouton2 = new JButton("Pause");
	private int compteur = 0;
	private boolean animated = true;
	private boolean animated2 = false;
	private boolean backX, backY;
	private boolean but1, but2;
	float x;
	private float y;
	private Thread t;
	private ServerSocket serv;
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private int PointJ1=0;
	private int PointJ2=-1;
	private JLabel lblPointJ1;
	private JLabel lblPointJ2;
	private JLabel TextNom1;
	private JLabel TextNom2;
	private Serveur Serv;
	private Scanner sc = new Scanner(System.in);
	private int posXJ1;
	private int posYJ1;
	private int posXJ2;
	private int posYJ2;
	private float posPalletY;
	private float posPalletX;
	private JTextArea Messagerie;
	
	public pageclient() {
		initialize();
	}
	public void CreationServeur()
	{
		System.out.println("Lancement du serveur");
		Serv = new Serveur(port);
	}  
	public void ConnexionServeur() 
	{
		Point point = MouseInfo.getPointerInfo().getLocation();
		System.out.println("Connexion au serveur");
		try {
			clientSocket = new Socket(IP,port);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			
			Thread envoyer = new Thread(new Runnable() {
	             String msg;
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
	                  msg = Messagerie.getText();
	                  out.println(msg);
	                  out.flush();
	                }
	             }
	         });
	         envoyer.start();
	        Thread recevoir = new Thread(new Runnable() {
	            String msg;
	            @Override
	            public void run() {
	               try {
	                 msg = in.readLine();
	                 while(msg!=null){
	                	 
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
	 	                msg = in.readLine();
	 	                Messagerie.setText(msg);
	 	                System.out.println(msg);
	                 }
	                 System.out.println("Serveur déconecté");
	                 out.close();
	                 clientSocket.close();
	               } catch (IOException e) {
	                   e.printStackTrace();
	               }
	            }
	        });
	        recevoir.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 2000,1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100][1200][][500,grow][100]", "[100][50][50][800,grow][100]"));
		
		JLabel NomJ1 = new JLabel("Nom joueur 1");
		frame.getContentPane().add(NomJ1, "cell 1 1,grow");
		
		JLabel TextNom1 = new JLabel("");
		frame.getContentPane().add(TextNom1, "cell 1 1,grow");
		
		JLabel NomJ2 = new JLabel("Nom joueur 2");
		frame.getContentPane().add(NomJ2, "cell 1 1,grow");
		
		JLabel lblNewLabel_5 = new JLabel("Point joueur 1");
		frame.getContentPane().add(lblNewLabel_5, "flowx,cell 1 2,grow");
		
		lblPointJ1 = new JLabel("");
		frame.getContentPane().add(lblPointJ1, "cell 1 2,grow");
		lblPointJ1.setText(""+PointJ1+"");
		
		JLabel lblNewLabel_6 = new JLabel("Point joueur 2");
		frame.getContentPane().add(lblNewLabel_6, "cell 1 2,grow");
		
		lblPointJ2 = new JLabel("");
		frame.getContentPane().add(lblPointJ2, "cell 1 2,grow");
		lblPointJ2.setText(""+PointJ2+"");
		
		JLabel TextNom2 = new JLabel("");
		frame.getContentPane().add(TextNom2, "cell 1 1,grow");
		
		Message = new JTextField();
		frame.getContentPane().add(Message, "flowx,cell 3 2,grow");
		Message.setColumns(10);
		
		//Test pour positionner le nom des joueur (à ne pas déplacer!!!)
		if(ID==1) {
			TextNom1.setText(J1);
		}
		else if(ID==2) {
			TextNom2.setText(J1);
		}
		
		/********Déclaration du Jpanel pour le jeu*********/
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    /********Boutons go et stop*********/
	    bouton.addActionListener(new BoutonListener()); 
	    bouton.setEnabled(false);
	    bouton2.addActionListener(new Bouton2Listener());

	    JPanel Terrain = new JPanel();
	    Terrain.add(bouton);
	    Terrain.add(bouton2);
		container.add(Terrain, BorderLayout.SOUTH);
	    this.setContentPane(container);
	    this.setVisible(false);
		frame.getContentPane().add(container, "cell 1 3,grow");
		container.addMouseListener(null);
	    //container.add(new TestImagePanel(new ImageIcon("Terrain hockey.jpg").getImage()));
	    go();
	    Messagerie = new JTextArea();
		Messagerie.setEditable(false);
		frame.getContentPane().add(Messagerie, "cell 3 3,grow");
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ID==1){Messagerie.append(J1+" a écris : "+Message.getText()+"\n\r");}
				if(ID==2){Messagerie.append(J1+" a écris : "+Message.getText()+"\n\r");}
				Message.setText("");
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 3 2");		
	}
	public pageclient(String S1,String S2,int I1) {
		J1=S1;
		IP=S2;
		ID=I1;
		pan.setJoueur(ID);
		
		if(ID==1) {
			System.out.println(J1);
			CreationServeur();
			System.out.println("je suis dans le ID1");
			ConnexionServeur();
		}
		else if(ID==2) {
			ConnexionServeur();
			
		}
		initialize();
		pageclient.this.frame.setVisible(true);
	}
	private void ButGauche()//le Joueur 2 marque dans le but Gauche
	{
		pan.setPosY(pan.getHeight()/2);
 	  	pan.setPosX(pan.getWidth()/2-100);
		animated = false;      
		animated2 = false; 
		bouton.setEnabled(true);
		bouton2.setEnabled(false);
		PointJ2++;
		lblPointJ2.setText(""+PointJ2+"");
		TestVictoire();
	}
	private void ButDroit()//Le Joueur 1 marque dans le but Droit
	{
		pan.setPosY(pan.getHeight()/2); 
 	  	pan.setPosX(pan.getWidth()/2+100);
		animated = false;      
		animated2 = false; 
		bouton.setEnabled(true);
		bouton2.setEnabled(false);
		PointJ1++;
		lblPointJ1.setText(""+PointJ1+"");
		TestVictoire();
	}
	private void TestVictoire()
	{
		if(PointJ1==10) {
			String st1 = "Victoire Joueur 1 !!!";
			JOptionPane.showMessageDialog(null, st1);
			PointJ1=0;
			lblPointJ1.setText(""+PointJ1+"");
			PointJ2=0;
			lblPointJ2.setText(""+PointJ2+"");
			pan.setPosY(pan.getHeight()/2); 
	 	  	pan.setPosX(pan.getWidth()/2);
			animated = false;      
			animated2 = false; 
			bouton.setEnabled(true);
			bouton2.setEnabled(false);
			
		}else if(PointJ2==10){
			String st2 = "Victoire Joueur 2 !!!";
			JOptionPane.showMessageDialog(null, st2);
			PointJ1=0;
			lblPointJ1.setText(""+PointJ1+"");
			PointJ2=0;
			lblPointJ2.setText(""+PointJ2+"");
			pan.setPosY(pan.getHeight()/2); 
	 	  	pan.setPosX(pan.getWidth()/2);
			animated = false;      
			animated2 = false; 
			bouton.setEnabled(true);
			bouton2.setEnabled(false);
		}
	}
	 private void go(){
	 	    x = pan.getPosX();
	 	    y = pan.getPosY();
	 	    float deltax=(0);
	 	    float deltay=(0);
	 	    while(this.animated){
	 	    	/********Verification collision murs*********/
	 	      if(x < 1)backX = false;
	 	      if(x > pan.getWidth()-50)backX = true;          
	 	      if(y < 1)backY = false;
	 	      if(y > pan.getHeight()-50)backY = true;
	 	     /********Verification but*********/
	 	      if((y > (pan.getHeight()/2)-100)&&(y < (pan.getHeight()/2)+100)&&(x < 10))but1 = true;
	 	      else but1 = false;
	 	      if((y > (pan.getHeight()/2)-100)&&(y < (pan.getHeight()/2)+100)&&(x>pan.getWidth()-70))but2 = true;
	 	      else but2 = false;
	 	      
/*****************************JOUEUR 1************************************/	 		 	      
	 	      
	 	      
	/*******************Face du haut*********************/ 	     
	 	      if((y >= pan.getPosD()-50)&&(y < pan.getPosD()+25))
	 	      {
	 	    	  if((x >= pan.getPosC()-75)&&(x < pan.getPosC()-40))
		    	    	 {
	 	    		  		 deltax=1;
	 	    		  		 deltay=1;
		    	    		 backY = true;
		    	    	 }
	 	      }
	 	     if((y >= pan.getPosD()-50)&&(y < pan.getPosD()+25))
		      {
		    	  if((x >= pan.getPosC()-40)&&(x < pan.getPosC()-5))
		    	    	 {
				    		 deltax=(float) 0.75;
			  		  		 deltay=(float) 1.25;
		    	    		 backY = true;
		    	    	 }
		      }
	 	    if((y >= pan.getPosD()-50)&&(y < pan.getPosD()+25))
		      {
		    	  if((x >= pan.getPosC()-5)&&(x < pan.getPosC()+30))
		    	    	 {
		    		   		 deltax=(float) 0;
		  		  		     deltay=(float) 1.5;
		    	    		 backY = true;
		    	    	 }
		      }
	 	   if((y >= pan.getPosD()-50)&&(y < pan.getPosD()+25))
		      {
		    	  if((x >= pan.getPosC()+30)&&(x <= pan.getPosC()+65))
		    	    	 {
		    		         deltax=(float) 0.75;
			  		         deltay=(float) 1.25;
		    	    		 backY = true;
		    	    	 }
		      }
	 	   if((y >= pan.getPosD()-50)&&(y < pan.getPosD()+25))
		      {
		    	  if((x >= pan.getPosC()+65)&&(x <= pan.getPosC()+100))
		    	    	 {
		    		         deltax=(float) 1.0;
			  		         deltay=(float) 1.0;
		    	    		 backY = true;
		    	    	 }
		      }
	 	      
	 	  /*******************Face du bas*********************/	   
	 	   
	 	  if((y <= pan.getPosD()+100)&&(y > pan.getPosD()+50))
		      {
		    	  if((x >= pan.getPosC()-75)&&(x < pan.getPosC()-40))
		    	    	 {
		    		  		 deltax=1;
		    		  		 deltay=1;
		    	    		 backY = false;
		    	    	 }
		      }
	 	 if((y <= pan.getPosD()+100)&&(y > pan.getPosD()+50))
		      {
		    	  if((x >= pan.getPosC()-40)&&(x < pan.getPosC()-5))
		    	    	 {
				    		 deltax=(float) 0.75;
			  		  		 deltay=(float) 1.25;
		    	    		 backY = false;
		    	    	 }
		      }
	 	if((y <= pan.getPosD()+100)&&(y > pan.getPosD()+50))
		      {
		    	  if((x >= pan.getPosC()-5)&&(x < pan.getPosC()+30))
		    	    	 {
		    		   		 deltax=(float) 0;
		  		  		     deltay=(float) 1.5;
		    	    		 backY = false;
		    	    	 }
		      }
	 	if((y <= pan.getPosD()+100)&&(y > pan.getPosD()+50))
		      {
		    	  if((x >= pan.getPosC()+30)&&(x <= pan.getPosC()+65))
		    	    	 {
		    		         deltax=(float) 0.75;
			  		         deltay=(float) 1.25;
		    	    		 backY = false;
		    	    	 }
		      }
	 	if((y <= pan.getPosD()+100)&&(y > pan.getPosD()+50))
		      {
		    	  if((x >= pan.getPosC()+65)&&(x <= pan.getPosC()+100))
		    	    	 {
		    		         deltax=(float) 1.0;
			  		         deltay=(float) 1.0;
		    	    		 backY = false;
		    	    	 }
		      }
	 	  /*******************Face de gauche*********************/
	 	      
	 	     if((x >= pan.getPosC()-50)&&(x < pan.getPosC()))
		      {
		    	  if((y >= pan.getPosD()-75)&&(y < pan.getPosD()-40))
		    	    	 {
		    		  		 deltax=1;
		    		  		 deltay=1;
		    	    		 backX = true;
		    	    	 }
		      }
	 	    if((x >= pan.getPosC()-50)&&(x < pan.getPosC()))
		      {
		    	  if((y >= pan.getPosD()-40)&&(y < pan.getPosD()-5))
		    	    	 {
				    		 deltay=(float) 0.75;
			  		  		 deltax=(float) 1.25;
			  		  		backX = true;
		    	    	 }
		      }
	 	   if((x >= pan.getPosC()-50)&&(x < pan.getPosC()))
		      {
		    	  if((y >= pan.getPosD()-5)&&(y < pan.getPosD()+30))
		    	    	 {
		    		   		 deltay=(float) 0;
		  		  		     deltax=(float) 1.5;
		  		  		 backX = true;
		    	    	 }
		      }
	 	  if((x >= pan.getPosC()-50)&&(x < pan.getPosC()))
		      {
		    	  if((y >= pan.getPosD()+30)&&(y <= pan.getPosD()+65))
		    	    	 {
		    		         deltay=(float) 0.75;
			  		         deltax=(float) 1.25;
			  		       backX = true;
		    	    	 }
		      }
	 	 if((x >= pan.getPosC()-50)&&(x < pan.getPosC()))
		      {
		    	  if((y >= pan.getPosD()+65)&&(y <= pan.getPosD()+100))
		    	    	 {
		    		         deltay=(float) 1.0;
			  		         deltax=(float) 1.0;
			  		       backX = true;
		    	    	 }
		      }
	 	     /*******************Face de droite*********************/     
	 	if((x >= pan.getPosC()+50)&&(x < pan.getPosC()+100))
	 	      {
	 	    	  if((y >= pan.getPosD()-75)&&(y < pan.getPosD()-40))
	 	    	    	 {
	 	    		  		deltax=1;
	 	    		  		deltay=1;
	 	    		  		backX = false;
	 	    	    	 }
	 	      }
	 	if((x >= pan.getPosC()+50)&&(x < pan.getPosC()+100))
	 	      {
	 	    	  if((y >= pan.getPosD()-40)&&(y < pan.getPosD()-5))
	 	    	    	 {
	 			    		 deltay=(float) 0.75;
	 		  		  		 deltax=(float) 1.25;
	 		  		  	backX = false;
	 	    	    	 }
	 	      }
	 	if((x >= pan.getPosC()+50)&&(x < pan.getPosC()+100))
	 	      {
	 	    	  if((y >= pan.getPosD()-5)&&(y < pan.getPosD()+30))
	 	    	    	 {
	 	    		   		 deltay=(float) 0;
	 	  		  		     deltax=(float) 1.5;
	 	  		  		     backX = false;
	 	    	    	 }
	 	      }
	 	if((x >= pan.getPosC()+50)&&(x < pan.getPosC()+100))
	 	      {
	 	    	  if((y >= pan.getPosD()+30)&&(y <= pan.getPosD()+65))
	 	    	    	 {
	 	    		         deltay=(float) 0.75;
	 		  		         deltax=(float) 1.25;
	 		  		         backX = false;
	 	    	    	 }
	 	      }
	 	if((x >= pan.getPosC()+50)&&(x < pan.getPosC()+100))
	 	      {
	 	    	  if((y >= pan.getPosD()+65)&&(y <= pan.getPosD()+100))
	 	    	    	 {
	 	    		         deltay=(float) 1.0;
	 		  		         deltax=(float) 1.0;
	 		  		      backX = false;
	 	    	    	 }
	 	      }
	 	
/*****************************JOUEUR 2************************************/	 	
	 	
		/*******************Face du haut*********************/ 	     
	      if((y >= pan.getPosB()-50)&&(y < pan.getPosB()+25))
	      {
	    	  if((x >= pan.getPosA()-75)&&(x < pan.getPosA()-40))
	    	    	 {
	    		  		 deltax=1;
	    		  		 deltay=1;
	    	    		 backY = true;
	    	    	 }
	      }
	     if((y >= pan.getPosB()-50)&&(y < pan.getPosB()+25))
	      {
	    	  if((x >= pan.getPosA()-40)&&(x < pan.getPosA()-5))
	    	    	 {
			    		 deltax=(float) 0.75;
		  		  		 deltay=(float) 1.25;
	    	    		 backY = true;
	    	    	 }
	      }
	    if((y >= pan.getPosB()-50)&&(y < pan.getPosB()+25))
	      {
	    	  if((x >= pan.getPosA()-5)&&(x < pan.getPosA()+30))
	    	    	 {
	    		   		 deltax=(float) 0;
	  		  		     deltay=(float) 1.5;
	    	    		 backY = true;
	    	    	 }
	      }
	   if((y >= pan.getPosB()-50)&&(y < pan.getPosB()+25))
	      {
	    	  if((x >= pan.getPosA()+30)&&(x <= pan.getPosA()+65))
	    	    	 {
	    		         deltax=(float) 0.75;
		  		         deltay=(float) 1.25;
	    	    		 backY = true;
	    	    	 }
	      }
	   if((y >= pan.getPosB()-50)&&(y < pan.getPosB()+25))
	      {
	    	  if((x >= pan.getPosA()+65)&&(x <= pan.getPosA()+100))
	    	    	 {
	    		         deltax=(float) 1.0;
		  		         deltay=(float) 1.0;
	    	    		 backY = true;
	    	    	 }
	      }
	      
	  /*******************Face du bas*********************/	   
	   
	  if((y <= pan.getPosB()+100)&&(y > pan.getPosB()+50))
	      {
	    	  if((x >= pan.getPosA()-75)&&(x < pan.getPosA()-40))
	    	    	 {
	    		  		 deltax=1;
	    		  		 deltay=1;
	    	    		 backY = false;
	    	    	 }
	      }
	 if((y <= pan.getPosB()+100)&&(y > pan.getPosB()+50))
	      {
	    	  if((x >= pan.getPosB()-40)&&(x < pan.getPosB()-5))
	    	    	 {
			    		 deltax=(float) 0.75;
		  		  		 deltay=(float) 1.25;
	    	    		 backY = false;
	    	    	 }
	      }
	if((y <= pan.getPosB()+100)&&(y > pan.getPosB()+50))
	      {
	    	  if((x >= pan.getPosA()-5)&&(x < pan.getPosA()+30))
	    	    	 {
	    		   		 deltax=(float) 0;
	  		  		     deltay=(float) 1.5;
	    	    		 backY = false;
	    	    	 }
	      }
	if((y <= pan.getPosB()+100)&&(y > pan.getPosB()+50))
	      {
	    	  if((x >= pan.getPosA()+30)&&(x <= pan.getPosA()+65))
	    	    	 {
	    		         deltax=(float) 0.75;
		  		         deltay=(float) 1.25;
	    	    		 backY = false;
	    	    	 }
	      }
	if((y <= pan.getPosB()+100)&&(y > pan.getPosB()+50))
	      {
	    	  if((x >= pan.getPosA()+65)&&(x <= pan.getPosA()+100))
	    	    	 {
	    		         deltax=(float) 1.0;
		  		         deltay=(float) 1.0;
	    	    		 backY = false;
	    	    	 }
	      }
	  /*******************Face de gauche*********************/
	      
	     if((x >= pan.getPosA()-50)&&(x < pan.getPosA()))
	      {
	    	  if((y >= pan.getPosB()-75)&&(y < pan.getPosB()-40))
	    	    	 {
	    		  		 deltax=1;
	    		  		 deltay=1;
	    	    		 backX = true;
	    	    	 }
	      }
	    if((x >= pan.getPosA()-50)&&(x < pan.getPosA()))
	      {
	    	  if((y >= pan.getPosB()-40)&&(y < pan.getPosB()-5))
	    	    	 {
			    		 deltay=(float) 0.75;
		  		  		 deltax=(float) 1.25;
		  		  		backX = true;
	    	    	 }
	      }
	   if((x >= pan.getPosA()-50)&&(x < pan.getPosA()))
	      {
	    	  if((y >= pan.getPosB()-5)&&(y < pan.getPosB()+30))
	    	    	 {
	    		   		 deltay=(float) 0;
	  		  		     deltax=(float) 1.5;
	  		  		 backX = true;
	    	    	 }
	      }
	  if((x >= pan.getPosA()-50)&&(x < pan.getPosA()))
	      {
	    	  if((y >= pan.getPosB()+30)&&(y <= pan.getPosB()+65))
	    	    	 {
	    		         deltay=(float) 0.75;
		  		         deltax=(float) 1.25;
		  		       backX = true;
	    	    	 }
	      }
	 if((x >= pan.getPosA()-50)&&(x < pan.getPosA()))
	      {
	    	  if((y >= pan.getPosB()+65)&&(y <= pan.getPosB()+100))
	    	    	 {
	    		         deltay=(float) 1.0;
		  		         deltax=(float) 1.0;
		  		       backX = true;
	    	    	 }
	      }
	     /*******************Face de droite*********************/     
	if((x >= pan.getPosA()+50)&&(x < pan.getPosA()+100))
	      {
	    	  if((y >= pan.getPosB()-75)&&(y < pan.getPosB()-40))
	    	    	 {
	    		  		deltax=1;
	    		  		deltay=1;
	    		  		backX = false;
	    	    	 }
	      }
	if((x >= pan.getPosA()+50)&&(x < pan.getPosA()+100))
	      {
	    	  if((y >= pan.getPosB()-40)&&(y < pan.getPosB()-5))
	    	    	 {
			    		 deltay=(float) 0.75;
		  		  		 deltax=(float) 1.25;
		  		  	backX = false;
	    	    	 }
	      }
	if((x >= pan.getPosA()+50)&&(x < pan.getPosA()+100))
	      {
	    	  if((y >= pan.getPosB()-5)&&(y < pan.getPosB()+30))
	    	    	 {
	    		   		 deltay=(float) 0;
	  		  		     deltax=(float) 1.5;
	  		  		     backX = false;
	    	    	 }
	      }
	if((x >= pan.getPosA()+50)&&(x < pan.getPosA()+100))
	      {
	    	  if((y >= pan.getPosB()+30)&&(y <= pan.getPosB()+65))
	    	    	 {
	    		         deltay=(float) 0.75;
		  		         deltax=(float) 1.25;
		  		         backX = false;
	    	    	 }
	      }
	if((x >= pan.getPosA()+50)&&(x < pan.getPosA()+100))
	      {
	    	  if((y >= pan.getPosB()+65)&&(y <= pan.getPosB()+100))
	    	    	 {
	    		         deltay=(float) 1.0;
		  		         deltax=(float) 1.0;
		  		      backX = false;
	    	    	 }
	      }
	 	
	 	
	 	
	 	     /*******************Mouvement du pallet********************/
	
	if(ID==1) {
	 	      if(!backX)pan.setPosX(x=x+deltax);
	 	      else pan.setPosX(x=x-deltax);
	 	      if(!backY) pan.setPosY(y=y+deltay);
	 	      else pan.setPosY(y=y-deltay);
	}
 	      if(but1){ButGauche();}
 	      if(but2){ButDroit();}
 	      pan.repaint();
 	      try {
 	        Thread.sleep(3);
 	      } catch (InterruptedException e) {
 	        e.printStackTrace();
 	      }
 	    }     
 	  }
	 
	 

	 /********Boutons Start*********/

 	  public class BoutonListener implements ActionListener{
 		    public void actionPerformed(ActionEvent arg0) {
 		    	if(t != null)
 		    	{
 		    		t.interrupt();
 		    	}
 		      animated = true;
 		      t = new Thread(new PlayAnimation());
 		      t.start();
 		      bouton.setEnabled(false);
 		      bouton2.setEnabled(true);
 		    }
 		  }

 	 /********Boutons stop*********/
 		  class Bouton2Listener  implements ActionListener{
 		    public void actionPerformed(ActionEvent e) {
 		      animated = false;      
 		      animated2 = false; 
 		      bouton.setEnabled(true);
 		      bouton2.setEnabled(false);
 		    }
 		  }    
 class PlayAnimation implements Runnable{
     public void run() {
       go();                   
     }               
   }
 

}
