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
import java.io.File;
import java.io.IOException;

import net.miginfocom.swing.MigLayout;
import Airhockey.pageclient.Bouton2Listener;
import Airhockey.pageclient.BoutonListener;
import Airhockey.pageclient.PlayAnimation;
import Airhockey.Panneau;

import javax.swing.JPanel;
import javax.swing.JLabel;
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
	private static String IP;
	private static int ID;

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
	  private int x, y;
	  private Thread t;

	/**
	 * Launch the application.
	 */
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
	
	/**
	 * Create the application.
	 */
		  public pageclient() {

		initialize();
		
	}
	//contructeur de la page Client 
	public pageclient(String S1,String S2,int I1) {
		J1=S1;
		IP=S2;
		ID=I1;
		if(ID==1) {/*Création serveur*/}
		if(ID==2) {/*Connexion au serveur*/}
		initialize();
		pageclient.this.frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */

		  
		  
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 2000,1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100][1200][][500][100]", "[100][50][50][800][100]"));
		
		JLabel lblNewLabel_1 = new JLabel("Votre nom");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 1,grow");
		
		JLabel lblNewLabel_7 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_7, "cell 1 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Nom de l'adversaire");
		frame.getContentPane().add(lblNewLabel_2, "cell 1 1,grow");
		
		JLabel lblNewLabel_5 = new JLabel("Point joueur 1");
		frame.getContentPane().add(lblNewLabel_5, "flowx,cell 1 2,grow");
		
		JLabel lblNewLabel_3 = new JLabel("0");
		frame.getContentPane().add(lblNewLabel_3, "cell 1 2,grow");
		
		JLabel lblNewLabel_6 = new JLabel("Point joueur 2");
		frame.getContentPane().add(lblNewLabel_6, "cell 1 2,grow");
		
		JLabel lblNewLabel_4 = new JLabel("0");
		frame.getContentPane().add(lblNewLabel_4, "cell 1 2,grow");
		
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
		container.add(Terrain, BorderLayout.SOUTH);
	    this.setContentPane(container);
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
		frame.getContentPane().add(Messagerie, "flowy,cell 3 3,grow");	
	}
	
	 private void go(){
 	    x = pan.getPosX();
 	    y = pan.getPosY();

 	    while(this.animated){
 	      if(x < 1)backX = false;
 	      if(x > pan.getWidth()-50)backX = true;          
 	      if(y < 1)backY = false;
 	      if(y > pan.getHeight()-50)backY = true;
 	      if((y > (pan.getHeight()/2)-100)&&(y < (pan.getHeight()/2)+100)&&(x < 10))but1 = true;
 	      else but1 = false;
 	      if((y > (pan.getHeight()/2)-100)&&(y < (pan.getHeight()/2)+100)&&(x>pan.getWidth()-70))but2 = true;
 	      else but2 = false;
 	      if((y == pan.getPosD()-75)&&(y < pan.getPosD()))
 	      {
 	    	  if((x >= pan.getPosC()-75)&&(x <= pan.getPosC()+100))
	    	    	 {
	    	    		 backY = true;
	    	    	 }
 	      }
 	      if((y == pan.getPosD()+100)&&(y > pan.getPosD()+75))
 	      {
 	    	  if((x >= pan.getPosC()-75)&&(x <= pan.getPosC()+100))
	    	    	 {
	    	    		 backY = false;
	    	    	 }
 	      }
 	      if(x == pan.getPosC()-75)
 	      {
 	    	  if((y >= pan.getPosD()-75)&&(y <= pan.getPosD()+100))
	    	    	 {
	    	    		 backX = true;
	    	    	 }
 	      }
 	      if(x == pan.getPosC()+100)
 	      {
 	    	  if((y >= pan.getPosD()-75)&&(y <= pan.getPosD()+100))
	    	    	 {
	    	    		 backX = false;
	    	    	 }
 	      }
 	      if(!backX)pan.setPosX(++x);
 	      else pan.setPosX(--x);
 	      if(!backY) pan.setPosY(y++);
 	      else pan.setPosY(y--);
 	      if(but1)//gauche
 	    	  {
 	    	  	pan.setPosY(pan.getHeight()/2);
 	    	  	pan.setPosX(pan.getWidth()/2);
	    		      animated = false;      
	    		      animated2 = false; 
	    		      bouton.setEnabled(true);
	    		      bouton2.setEnabled(false);

 	    	  }
 	      if(but2)//droite
 	    	  {
 	    	  	pan.setPosY(pan.getHeight()/2); 
 	    	  	pan.setPosX(pan.getWidth()/2);
	    		      animated = false;      
	    		      animated2 = false; 
	    		      bouton.setEnabled(true);
	    		      bouton2.setEnabled(false);
 	    	  }


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
