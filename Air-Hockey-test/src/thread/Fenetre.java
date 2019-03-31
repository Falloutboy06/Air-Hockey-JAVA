package thread;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

	    public class Fenetre extends JFrame{

				private Panneau pan = new Panneau();
	    		  private JButton bouton = new JButton("Go");
	    		  private JButton bouton2 = new JButton("Stop");
	    		  private JButton bouton3 = new JButton("Horizontal");
	    		  private JButton bouton4 = new JButton("Vertical");
	    		  private JPanel container = new JPanel();
	    		  private JLabel label = new JLabel("Le JLabel");
	    		  private int compteur = 0;
	    		  private boolean animated = true;
	    		  private boolean animated2 = false;
	    		  private boolean animated3 = false;
	    		  private boolean backX, backY;
	    		  private int x, y;

	    		  private Thread t;
				private Fenetre Fenetre;
	    	  public Fenetre(){
	    	    this.setTitle("Animation");
	    	    this.setSize(300, 300);
	    	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	    this.setLocationRelativeTo(null);
	
	    	    container.setBackground(Color.black);
	    	    container.setLayout(new BorderLayout());
	    	    container.add(pan, BorderLayout.CENTER);
	    	    bouton.addActionListener(new BoutonListener()); 
	    	    bouton.setEnabled(false);
	    	    bouton2.addActionListener(new Bouton2Listener());
	    	    bouton3.addActionListener(new BoutonListener3());
	    	    bouton4.addActionListener(new BoutonListener4());

	    	    
	    	   // double x2 = point.getX();
	    	    //double y2 = MouseEvent.getY()+MouseEvent.getComponent().getLocation().getY();
	    	    
	    	    JPanel south = new JPanel();
	    	    south.add(bouton);
	    	    south.add(bouton2);
	    	    south.add(bouton3);
	    	    south.add(bouton4);
	    	    container.add(south, BorderLayout.SOUTH);
	    	    Font police = new Font("Tahoma", Font.BOLD, 16);
	    	    label.setFont(police);
	    	    label.setForeground(Color.white);
	    	    label.setHorizontalAlignment(JLabel.CENTER);
	    	    container.add(label, BorderLayout.NORTH);
	    	    this.setContentPane(container);
	    	    this.setVisible(true);

	    	    go();

	    	    
	    	  }

	    	  private void go(){
	    		Point point = MouseInfo.getPointerInfo().getLocation();
	    	    //Les coordonnées de départ de notre rond
	    	    x = pan.getPosX();
	    	    y = pan.getPosY();

	    	    while(this.animated){
	    	      if(x < 1)backX = false;
	    	      if(x > pan.getWidth()-50)backX = true;          
	    	      if(y < 1)backY = false;
	    	      if(y > pan.getHeight()-50)backY = true;
	    	      if(y == pan.getPosD()-25)
	    	      {
	    	    	  if((x >= pan.getPosC()-25)&&(x <= pan.getPosC()+50))
		    	    	 {
		    	    		 backY = true;
		    	    	 }
	    	      }
	    	      if(y == pan.getPosD()+50)
	    	      {
	    	    	  if((x >= pan.getPosC()-25)&&(x <= pan.getPosC()+50))
		    	    	 {
		    	    		 backY = false;
		    	    	 }
	    	      }
	    	      if(x == pan.getPosC()-25)
	    	      {
	    	    	  if((y >= pan.getPosD()-25)&&(y <= pan.getPosD()+50))
		    	    	 {
		    	    		 backX = true;
		    	    	 }
	    	      }
	    	      if(x == pan.getPosC()+50)
	    	      {
	    	    	  if((y >= pan.getPosD()-25)&&(y <= pan.getPosD()+50))
		    	    	 {
		    	    		 backX = false;
		    	    	 }
	    	      }
	    	      if(!backX)pan.setPosX(++x);
	    	      else pan.setPosX(--x);
	    	      if(!backY) pan.setPosY(y++);
	    	      else pan.setPosY(y--);
	    	       

	    	      pan.repaint();


	    	      try {
	    	        Thread.sleep(3);
	    	      } catch (InterruptedException e) {
	    	        e.printStackTrace();
	    	      }
	    	    }     
	    	  }


	    	  

	    	  private void go_horizontal(){
		    	    //Les coordonnées de départ de notre rond
		    	    x = pan.getPosX();
		    	    //Dans cet exemple, j'utilise une boucle while
		    	    //Vous verrez qu'elle fonctionne très bien
		    	    while(this.animated2){
		    	      if(x < 1)backX = false;
		    	      if(x > pan.getWidth()-50)backX = true;   
		    	      if(x == pan.getPosC()-25)
		    	      {
		    	    	  if((y >= pan.getPosD()-25)&&(y <= pan.getPosD()+50))
			    	    	 {
			    	    		 backX = true;
			    	    	 }
		    	      }
		    	      if(x == pan.getPosC()+50)
		    	      {
		    	    	  if((y >= pan.getPosD()-25)&&(y <= pan.getPosD()+50))
			    	    	 {
			    	    		 backX = false;
			    	    	 }
		    	      }
		    	      if(!backX)pan.setPosX(++x);
		    	      else pan.setPosX(--x);
		    	      pan.repaint();


		    	      try {
		    	        Thread.sleep(3);
		    	      } catch (InterruptedException e) {
		    	        e.printStackTrace();
		    	      }
		    	    }     
		    	  }
	    	  
	    	  
	    	  private void go_vertical(){
	    		  Point point = MouseInfo.getPointerInfo().getLocation();
		    	    //Les coordonnées de départ de notre rond
		    	    y = pan.getPosY();
		    	    //Dans cet exemple, j'utilise une boucle while
		    	    //Vous verrez qu'elle fonctionne très bien
		    	    while(this.animated3){      
		    	      if(y < 1)backY = false;
		    	      if(y > pan.getHeight()-50)backY = true;
		    	      if(y == pan.getPosD()-25)
		    	      {
		    	    	  if((x >= pan.getPosC()-25)&&(x <= pan.getPosC()+50))
			    	    	 {
			    	    		 backY = true;
			    	    	 }
		    	      }
		    	      if(y == pan.getPosD()+50)
		    	      {
		    	    	  if((x >= pan.getPosC()-25)&&(x <= pan.getPosC()+50))
			    	    	 {
			    	    		 backY = false;
			    	    	 }
		    	      }
		    	    	  
		    	      
		    	     /* {
		    	    	 if((x >= point.getX()-50)&&(x <= point.getX()))
		    	    	 {
		    	    		 backY = false;
		    	    	 }
		    	      }*/
		    	     // if(y == (int)point.getY()+25)backY = true;
		    	     /* {
		    	    	
		    	      }*/
		    	      if(!backY) pan.setPosY(y++);
		    	      else pan.setPosY(y--);

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
	    		      bouton3.setEnabled(true);
	    		      bouton4.setEnabled(true);
	    		    }
	    		  }
	    	  public class BoutonListener3 implements ActionListener{
	    		    public void actionPerformed(ActionEvent arg0) {
	    		    	if(t != null)
	    		    	{
	    		    		animated = false;
	    		    		animated2 = false;
	    		    		animated3 = false;
	    		    		t.interrupt();
	    		    	}
	    		      animated2 = true;
	    		      t = new Thread(new PlayAnimation2());
	    		      t.start();
	    		      bouton.setEnabled(true);
	    		      bouton2.setEnabled(true);
	    		      bouton3.setEnabled(false);
	    		      bouton4.setEnabled(true);
	    		    }
	    		  }
	    	  public class BoutonListener4 implements ActionListener{
	    		    public void actionPerformed(ActionEvent arg0) {
	    		    	if(t != null)
	    		    	{
	    		    		t.interrupt();
	    		    		animated = false;
	    		    		animated2 = false;
	    		    		animated3 = false;
	    		    	}
	    		      animated3 = true;
	    		      t = new Thread(new PlayAnimation3());
	    		      t.start();
	    		      bouton.setEnabled(true);
	    		      bouton2.setEnabled(true);
	    		      bouton3.setEnabled(true);
	    		      bouton4.setEnabled(false);
	    		    }
	    		  }

	    		  class Bouton2Listener  implements ActionListener{
	    		    public void actionPerformed(ActionEvent e) {
	    		      animated = false;      
	    		      animated2 = false; 
	    		      animated3 = false; 
	    		      bouton.setEnabled(true);
	    		      bouton2.setEnabled(false);
	    		      bouton3.setEnabled(true);
	    		      bouton4.setEnabled(true);
	    		    }
	    		  }    
	    class PlayAnimation implements Runnable{
	        public void run() {
	          go();                   
	        }               
	      } 
	    class PlayAnimation2 implements Runnable{
	        public void run() {
	          go_horizontal();                   
	        }               
	      } 
	    class PlayAnimation3 implements Runnable{
	        public void run() {
	          go_vertical();                   
	        }               
	      } 
	    }
  