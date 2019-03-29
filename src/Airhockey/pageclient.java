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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

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
		frame.setBounds(0, 0, 2000,1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100][1200][][500][100]", "[100][50][50][750][100]"));
		
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
		
		JLabel lblNewLabel_9 = new JLabel(new ImageIcon("./Terrain hockey.jpg"));
		frame.getContentPane().add(lblNewLabel_9, "cell 1 3");
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 3 2");
		
		JTextArea Messagerie = new JTextArea();
		frame.getContentPane().add(Messagerie, "flowy,cell 3 3,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 3 3");
		
		
	}

}
