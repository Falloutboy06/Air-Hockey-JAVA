package Airhockey;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class firstpage {

	private JFrame frame;
	private JTextField TextIP;
	private String IP;
	private String Pseudo;
	private JTextField TextPseudo;
	private int ID=0;
	private pageclient Windowjeu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					firstpage window = new firstpage();
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
	public firstpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[100][200][200,grow][200][100]", "[100][100][100][100][100][100][100][100]"));
		
		JLabel lblNewLabel = new JLabel("Pseudo Joueur");
		frame.getContentPane().add(lblNewLabel, "cell 2 2,alignx center,aligny bottom");
		
		TextPseudo = new JTextField();
		frame.getContentPane().add(TextPseudo, "cell 2 3,growx,aligny top");
		TextPseudo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("IP Serveur");
		frame.getContentPane().add(lblNewLabel_1, "cell 2 4,alignx center,aligny bottom");
		
		JButton btnNewButton = new JButton("Cr\u00E9er Partie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pseudo=TextPseudo.getText();
				IP=TextIP.getText();
				ID=1;
				firstpage.this.frame.setVisible(false);
				Windowjeu=new pageclient(Pseudo,IP,ID);
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 1 5,alignx center,aligny center");
		
		TextIP = new JTextField();
		frame.getContentPane().add(TextIP, "cell 2 5,growx,aligny top");
		TextIP.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Rejoindre Partie");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pseudo=TextPseudo.getText();
				IP=TextIP.getText();
				ID=2;
				firstpage.this.frame.setVisible(false);
				Windowjeu=new pageclient(Pseudo,IP,ID);
			}
		});
		frame.getContentPane().add(btnNewButton_1, "cell 3 5,alignx center,aligny center");
	}

}
