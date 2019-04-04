package Airhockey;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {
  private float posX = -50;
  private float posY = -50;
  private double posA = 50;
  private double posB = 50;
  private int posC = 0;
  private int posD = 0;
  private int Joueur = 0;
  /********Création et position pallet et pad*********/
  public void paintComponent(Graphics g){
    //On choisit une couleur de fond pour le rectangle
    g.setColor(Color.white);
    //On le dessine de sorte qu'il occupe toute la surface
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    //On redéfinit une couleur pour le pallet
    g.setColor(Color.black);
    //On le dessine aux coordonnées souhaitées
    g.fillOval((int)posX, (int)posY,50, 50);
    //On redéfinit une couleur pour les pads
   // g.setColor(Color.blue);
    if(Joueur==1)
    {
	    g.setColor(Color.red);
	    Point point = MouseInfo.getPointerInfo().getLocation();
	    g.fillOval(posC=(int)point.getX()-150, posD=(int)point.getY()-250, 100, 100);
	    
	    g.setColor(Color.blue);
	    //On le dessine aux coordonnées souhaitées
	    g.fillOval((int)posA, (int)posB,100, 100);
    }
    else {
    	g.setColor(Color.blue);
	    Point point = MouseInfo.getPointerInfo().getLocation();
	    g.fillOval(posC=(int)point.getX()-150, posD=(int)point.getY()-250, 100, 100);
	    
	    g.setColor(Color.red);
	    //On le dessine aux coordonnées souhaitées
	    g.fillOval((int)posA, (int)posB,100, 100);
    }
  }

  public float getPosX() {
    return posX;
  }

  public void setPosX(float f) {
    this.posX = f;
  }

  public float getPosY() {
    return posY;
  }

  public void setPosY(float f) {
    this.posY = f;
  }
  
  public double getPosA() {
	    return posA;
	  }

  public void setPosA(int posA) {
	    this.posA = posA;
	  }

  public double getPosB() {
	    return posB;
	  }

public void setPosB(int posB) {
	    this.posB = posB;
	  }

public int getPosC() {
    return posC;
  }

  public void setPosC(int posC) {
    this.posC = posC;
  }

  public int getPosD() {
    return posD;
  }

  public void setPosD(int posD) {
    this.posD = posD;
  }
  
  public void setJoueur(int Joueur) {
	    this.Joueur = Joueur;
	  }
  public int getJoueur() {
	    return Joueur;
	  }
}
