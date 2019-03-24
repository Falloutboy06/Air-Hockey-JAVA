package Airhockey;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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

public class pageclient {

	private JFrame frame;
	private JTextField Message;

	private String TextMessage="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pageclient window = new pageclient();
					window.frame.setVisible(true);
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
		frame.setBounds(500, 500, 1500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100][500,grow][500,grow][100]", "[100,grow][50][50][300][100]"));
		
		JLabel lblNewLabel_1 = new JLabel("Nom Joueur 1");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 1,grow");
		
		JLabel lblNewLabel_7 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_7, "cell 1 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Nom joueur 2");
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
		frame.getContentPane().add(Message, "flowx,cell 2 2,grow");
		Message.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel(new ImageIcon("./Terrain hockey.jpg"));
		frame.getContentPane().add(lblNewLabel_9, "cell 1 3");
		
		JLabel Messagerie = new JLabel("Messagerie");
		frame.getContentPane().add(Messagerie, "cell 2 3,grow");
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextMessage=Message.getText();
				Messagerie.setText(TextMessage);
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 2 2");
		
		
	}

}
