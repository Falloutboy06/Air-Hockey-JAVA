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
import java.io.PrintWriter;
import java.net.Socket;

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
	private static String Jadv;
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
	private Serveur serveur;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int PointJ1=0;
	private int PointJ2=0;
	private JLabel lblPointJ1;
	private JLabel lblPointJ2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pageclient window2 = new pageclient();
					window2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	  public pageclient() {
		initialize();
	}
	//contructeur de la page Client 
	public pageclient(String S1,String S2,int I1) {
		J1=S1;
		IP=S2;
		ID=I1;
		if(ID==1) {
			CreationServeur();
		}
		else if(ID==2) {
			/*Connexion au serveur*/
			//Socket socket = new Socket(IP,port);
		}
		initialize();
		pageclient.this.frame.setVisible(true);
		
	}	
	public void CreationServeur()
	{
		//serveur=new Serveur(); appel ouverture serveur//
		System.out.println("Lancement du serveur");
	}  
	public void ConnexionServeur()
	{
		/*Connexion au serveur*/
		//Socket socket = new Socket(IP,port);
	}
	
		  
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 2000,1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100][1200][][500,grow][100]", "[100][50][50][800,grow][100]"));
		
		JLabel lblNewLabel_1 = new JLabel("Votre nom");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 1,grow");
		
		JLabel lblNewLabel_7 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_7, "cell 1 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Nom de l'adversaire");
		frame.getContentPane().add(lblNewLabel_2, "cell 1 1,grow");
		
		JLabel lblNewLabel_5 = new JLabel("Point joueur 1");
		frame.getContentPane().add(lblNewLabel_5, "flowx,cell 1 2,grow");
		
		lblPointJ1 = new JLabel("0");
		frame.getContentPane().add(lblPointJ1, "cell 1 2,grow");
		
		JLabel lblNewLabel_6 = new JLabel("Point joueur 2");
		frame.getContentPane().add(lblNewLabel_6, "cell 1 2,grow");
		
		lblPointJ2 = new JLabel("0");
		frame.getContentPane().add(lblPointJ2, "cell 1 2,grow");
		
		JLabel lblNewLabel_8 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_8, "cell 1 1,grow");
		
		Message = new JTextField();
		frame.getContentPane().add(Message, "flowx,cell 3 2,grow");
		Message.setColumns(10);

		bouton.addActionListener(new BoutonListener()); 
		bouton.setEnabled(false);
		bouton2.addActionListener(new Bouton2Listener());
		
		//container.add(new TestImagePanel(new ImageIcon("Terrain hockey.jpg").getImage()));
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    bouton.addActionListener(new BoutonListener()); 
	    bouton.setEnabled(false);
	    bouton2.addActionListener(new Bouton2Listener());

	    JPanel Terrain = new JPanel();
	    Terrain.add(bouton);
	    Terrain.add(bouton2);
		//container.add(Terrain, BorderLayout.SOUTH);
	    //this.setContentPane(container);
	    this.setVisible(true);
		frame.getContentPane().add(container, "cell 1 3,grow");
		container.addMouseListener(null);
	    //Terrain.add(new TestImagePanel(new ImageIcon("Terrain hockey.jpg").getImage()));
	    go();
	     
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 3 2");
		
		JTextArea Messagerie = new JTextArea();
		Messagerie.setEditable(false);
		frame.getContentPane().add(Messagerie, "cell 3 3,grow");
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
	private void ButDroit()//Le joueur 1 marque dans le but Droit
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
			// AFFICHAGE POP UP VICTOIRE JOUEUR1 A FAIRE
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
			// AFFICHAGE POP UP VICTOIRE JOUEUR2 A FAIRE
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
	 	      if(x < 1)backX = false;
	 	      if(x > pan.getWidth()-50)backX = true;          
	 	      if(y < 1)backY = false;
	 	      if(y > pan.getHeight()-50)backY = true;
	 	      if((y > (pan.getHeight()/2)-100)&&(y < (pan.getHeight()/2)+100)&&(x < 10))but1 = true;
	 	      else but1 = false;
	 	      if((y > (pan.getHeight()/2)-100)&&(y < (pan.getHeight()/2)+100)&&(x>pan.getWidth()-70))but2 = true;
	 	      else but2 = false;
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
	 	     /****************************************************/
	 	      if(!backX)pan.setPosX(x=x+deltax);
	 	      else pan.setPosX(x=x-deltax);
	 	      if(!backY) pan.setPosY(y=y+deltay);
	 	      else pan.setPosY(y=y-deltay);
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
