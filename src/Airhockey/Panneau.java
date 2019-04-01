package Airhockey;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {
  private int posX = -50;
  private int posY = -50;
  private double posA = 50;
  private double posB = 50;
  private int posC = 0;
  private int posD = 0;
 
	


  public void paintComponent(Graphics g){
    //On choisit une couleur de fond pour le rectangle
    g.setColor(Color.white);
    //On le dessine de sorte qu'il occupe toute la surface
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    //On redéfinit une couleur pour le pallet
    g.setColor(Color.black);
    //On le dessine aux coordonnées souhaitées
    g.fillOval(posX, posY,75, 75);
    //On redéfinit une couleur pour les pads
   // g.setColor(Color.blue);
    
    g.setColor(Color.red);
    Point point = MouseInfo.getPointerInfo().getLocation();
    g.fillOval(posC=(int)point.getX()-150, posD=(int)point.getY()-250, 100, 100);
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }
  
  public double getPosA() {
	    return posA;
	  }

  public void setPosA(double posA) {
	    this.posA = posA;
	  }

  public double getPosB() {
	    return posB;
	  }

public void setPosB(double posB) {
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

}
