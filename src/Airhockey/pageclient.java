package Airhockey;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

public class pageclient {

	private JFrame frame;
	private JTextField Message;

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
		
		JPanel Terrain = new JPanel();
		frame.getContentPane().add(Terrain, "cell 1 3,grow");
	    Terrain.add(new TestImagePanel(new ImageIcon("Terrain hockey.jpg").getImage()));
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 3 2");
		
		
		JTextArea Messagerie = new JTextArea();
		frame.getContentPane().add(Messagerie, "flowy,cell 3 3,grow");
		
		
	}

}
