package GameSys;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameWindow extends JFrame {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9 ;
	public static final int SCALE = 2 ;
	public static final String TITLE = "MMO_V1";
	private JButton nouvellePartie;
	private JButton charger;
	private JButton quitter;
	private JTextArea txtmap;
	
	public GameWindow() {
		super(TITLE);
		this.initComposants();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		this.setMaximumSize(new Dimension(WIDTH * SCALE,HEIGHT*SCALE));
		this.setMinimumSize(new Dimension(WIDTH * SCALE,HEIGHT*SCALE));	
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void initComposants() {
	JPanel panprincipal = new JPanel();
	this.add(panprincipal);
	panprincipal.setLayout(new GridLayout());
	panprincipal.add(Menu());
	panprincipal.add(Map());
	}
	
	public JPanel Map() {
		JPanel map = new JPanel();
		map.setLayout(new BorderLayout());
		txtmap = new JTextArea();
		map.add(txtmap);
		map.setBorder(BorderFactory.createEtchedBorder());
		return map;
	}
	public JPanel Menu() {
		JPanel menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		nouvellePartie = new JButton("Nouvelle Partie");
		charger = new JButton("Charger");
		quitter = new JButton("Quitter");
		menu.add(nouvellePartie,gbc);
		menu.add(charger,gbc);
		menu.add(quitter,gbc);
		return menu;
		
	}

	public static void main(String[] args) {
		GameWindow gameWindow = new GameWindow();
		
	}
	
}
