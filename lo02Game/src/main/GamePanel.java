package main;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import entity.BrutalGame;
import entity.Protagonist;
import entity.Troop;
import tile.TileManager;

/**
 * this class GamePanel contains the music, the start page and the menu.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class GamePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// screen settings
	final int originalTileSize = 16; // 16 * 16 tile
	final int scale = 4;

	public final int tileSize = originalTileSize * scale; // 48 * 48 tile

	final int maxScreenCol = 20;
	final int maxScreenRow = 12;

	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// FPS
	int FPS = 60;

	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	public UI ui = new UI(this);
	Thread gameThread;
	Sound music = new Sound();
	Sound se = new Sound();
	// ENTITY

	// GAME STATE
	public static int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int setState = 3;
	public final int finalState = 4;

	/**
	 * constructor to set up the game panel, add keylistener.
	 */
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		// game panel can recognize key input
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	/**
	 * play music.
	 */
	public void setUpGame() {

		playMusic(0);

		gameState = titleState;
	}

	/**
	 * start the game thread.
	 */
	public void startGameThread() {

		gameThread = new Thread(this);
		synchronized (gameThread) {
			gameThread.start();
		}

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

		// core of the game game loop
		// as long as the game thread exists
		for (int i = 0; i < 2; i++) {
			if (gameState != setState) {
				while (gameThread != null) {

					// 1.update information such as characters
					update();

					// 2.draw the screen with the updated information
					repaint();

					try {

						double remainingTime = nextDrawTime - System.nanoTime();
						remainingTime = remainingTime / 1000000; // covert

						if (remainingTime < 0) {
							remainingTime = 0;
						}

						Thread.sleep((long) remainingTime);

						nextDrawTime += drawInterval;

					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					if (gameState == setState) {
						break;
					}
				}
			} else if (gameState == setState && i == 1) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {

						Troop troop = new Troop();

						try {
							Uiii gui = new Uiii(troop);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		}

	}

	/**
	 * update the screen.
	 */
	public void update() {

		if (gameState == playState) {

		}
		if (gameState == pauseState) {

		}

	}

	/**
	 * draw something.
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(g2);
		}

	}

	/**
	 * BACKGROUND MUSIC
	 */
	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {

		se.stop();
	}

	// SOUND EFFECT
	public void playSE(int i) {

		se.setFile(i);
		se.play();
	}

	/****************************************************************************************/

	public class Uiii extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// les composants de la vue
		public BufferedImage img1;
		public BufferedImage img2;
		public BufferedImage img3;
		private JPanel contentPane;
		private TextField configPersonnage = new TextField();
		private TextField force;
		private TextField dexterite;
		private TextField resistance;
		private TextField constitution;
		private TextField initiative;
		private Choice choice;
		private Choice strategy;
		private JCheckBox reserviste;
		private TextField pointsDistribuer;
		private TextField nom;
		private Choice programme;

		private Troop troop;
		private BrutalGame bg;
		private int state;

		public Uiii(final Troop troop) {
			this.troop = troop;
			this.bg = BrutalGame.getInstance();
			this.initFenetre();
		}

		public void initFenetre() {
			this.state = 1;
			setForeground(Color.BLACK);
			setTitle("Configuration Equipe");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.pack();
			contentPane = new JPanel();
			contentPane.setBorder(new CompoundBorder());
			contentPane.setBackground(Color.WHITE);
			setContentPane(contentPane);
			setBounds(100, 100, 1400, 900);
			contentPane.setLayout(null);
			configPersonnage.setBounds(426, 331, 360, 34);

			configPersonnage.setBackground(Color.YELLOW);
			configPersonnage.setForeground(Color.BLACK);
			configPersonnage.setFont(new Font("Tahoma", Font.ITALIC, 20));
			contentPane.add(configPersonnage);

			// Panel du maitre Gobi
			JPanel panelMaitre = new JPanel();
			panelMaitre.setBounds(79, 102, 61, 122);
			panelMaitre.setBorder(new LineBorder(new Color(0, 0, 0), 5));
			panelMaitre.setBackground(Color.CYAN);
			panelMaitre.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
			JButton jb1 = new JButton(); // pour repr閟enter un personnage, utilisation d'un JButton
			panelMaitre.add(jb1);
			jb1.setForeground(Color.CYAN);

			try {

				img1 = ImageIO.read(getClass().getResourceAsStream("/ressources/maitre.png"));

			} catch (IOException e) {
				e.printStackTrace();
			}

			jb1.setIcon(new ImageIcon(img1)); // habillage du JButon
			if (state == 1) {
				jb1.addActionListener(
						new MonEcouteurEvenements(troop.getListProtagonists(), new String("Capitaine Gobi")));
			} else if (state == 2) {
				jb1.addActionListener(
						new MonEcouteurEvenements(troop.getListProtagonists2(), new String("Capitaine Gobi")));
			}

			contentPane.add(panelMaitre);
			// Etiquette Capitaine Gobi
			JLabel lblNewLabel = new JLabel("Capitaine Gobi");
			lblNewLabel.setBounds(51, 75, 128, 24);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPane.add(lblNewLabel);

			// Idem pour les Eites
			JPanel panelElite = new JPanel();
			panelElite.setBounds(354, 102, 274, 122);
			panelElite.setBorder(null);
			panelElite.setBackground(new Color(7, 180, 184));
			panelElite.setLayout(new GridLayout(1, 4, 0, 0));

			try {

				img2 = ImageIO.read(getClass().getResourceAsStream("/ressources/elite.png"));

			} catch (IOException e) {
				e.printStackTrace();
			}

			JButton[] jb = new JButton[4];
			for (int i = 0; i < 4; i++) {
				jb[i] = new JButton();
				jb[i].setIcon(new ImageIcon(img2));
				panelElite.add(jb[i]);
				if (state == 1) {
					jb[i].addActionListener(
							new MonEcouteurEvenements(troop.getListProtagonists(), new String("Elite " + i)));
				} else if (state == 2) {
					jb[i].addActionListener(
							new MonEcouteurEvenements(troop.getListProtagonists2(), new String("Elite " + i)));
				}
			}
			contentPane.add(panelElite);
			// Etiquette Les Elites
			JLabel lblNewLabel_1 = new JLabel("Les Elites");
			lblNewLabel_1.setBounds(449, 77, 83, 21);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPane.add(lblNewLabel_1);

			JPanel panelEtu = new JPanel();
			panelEtu.setBounds(830, 102, 274, 411);
			panelEtu.setBorder(null);
			panelEtu.setBackground(new Color(7, 180, 184));
			panelEtu.setLayout(new GridLayout(4, 4, 2, 0));
			contentPane.add(panelEtu);

			// Etiquette Les Eudiants de base
			JLabel lblNewLabel_2 = new JLabel("Les Etudiants de base");
			lblNewLabel_2.setBounds(885, 76, 192, 20);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPane.add(lblNewLabel_2);

			try {

				img3 = ImageIO.read(getClass().getResourceAsStream("/ressources/etudiante.png"));

			} catch (IOException e) {
				e.printStackTrace();
			}

			JButton[] jb2 = new JButton[15];
			for (int k = 0; k < 15; k++) {
				jb2[k] = new JButton();
				jb2[k].setIcon(new ImageIcon(img3));
				panelEtu.add(jb2[k]);
				if (state == 1) {
					jb2[k].addActionListener(
							new MonEcouteurEvenements(troop.getListProtagonists(), new String("Etudiant " + k)));
				} else if (state == 2) {
					jb2[k].addActionListener(
							new MonEcouteurEvenements(troop.getListProtagonists2(), new String("Etudiant " + k)));
				}
			}

			JLabel lblNewLabel_3 = new JLabel("Joueur");
			lblNewLabel_3.setBounds(254, 24, 76, 37);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_3);
			nom = new TextField();
			nom.setBounds(336, 24, 152, 37);
			nom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(nom.getText());
					troop.setNom(nom.getText());

				}
			});
			nom.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(nom);

			JLabel lblNewLabel_13 = new JLabel("Programme");
			lblNewLabel_13.setBounds(562, 24, 130, 37);
			lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_13);
			programme = new Choice();
			programme.setBounds(698, 27, 72, 34);
			programme.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					String str = programme.getItem(programme.getSelectedIndex());
					System.out.println(str);
					troop.setProgramme(str);
				}
			});
			programme.setFont(new Font("Tahoma", Font.PLAIN, 24));
			programme.add("ISI");
			programme.add("GM");
			programme.add("A2I");
			programme.add("RT");
			programme.add("MTE");
			programme.add("GI");
			programme.add("MM");
			contentPane.add(programme);

			// Compteur des points ?distribuer
			JLabel lblNewLabel_4 = new JLabel("Points ?distribuer");
			lblNewLabel_4.setBounds(72, 309, 192, 26);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_4);
			pointsDistribuer = new TextField();
			pointsDistribuer.setBounds(297, 304, 61, 37);
			pointsDistribuer.setFont(new Font("Tahoma", Font.PLAIN, 24));
			pointsDistribuer.setText(Integer.toString(troop.getPointsDistribuer()));
			contentPane.add(pointsDistribuer);

			// Force
			JLabel lblNewLabel_5 = new JLabel("Force");
			lblNewLabel_5.setBounds(193, 389, 61, 37);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_5);
			force = new TextField();
			force.setBounds(297, 389, 61, 37);
			force.setFont(new Font("Tahoma", Font.PLAIN, 24));
			force.setText("0");
			contentPane.add(force);

			// Dexterity?
			JLabel lblNewLabel_6 = new JLabel("Dexterity?");
			lblNewLabel_6.setBounds(159, 432, 105, 37);
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_6);
			dexterite = new TextField();
			dexterite.setBounds(297, 432, 61, 37);
			dexterite.setFont(new Font("Tahoma", Font.PLAIN, 24));
			dexterite.setText("0");
			contentPane.add(dexterite);

			// Reistance
			JLabel lblNewLabel_7 = new JLabel("Resistance");
			lblNewLabel_7.setBounds(147, 479, 117, 37);
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_7);
			resistance = new TextField();
			resistance.setBounds(297, 475, 61, 37);
			resistance.setFont(new Font("Tahoma", Font.PLAIN, 24));
			resistance.setText("0");
			contentPane.add(resistance);

			// Constitution
			JLabel lblNewLabel_8 = new JLabel("Constitution");
			lblNewLabel_8.setBounds(136, 522, 128, 37);
			lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_8);
			constitution = new TextField();
			constitution.setBounds(297, 518, 61, 37);
			constitution.setFont(new Font("Tahoma", Font.PLAIN, 24));
			constitution.setText("0");
			contentPane.add(constitution);

			// Initiative
			JLabel lblNewLabel_9 = new JLabel("Initiative");
			lblNewLabel_9.setBounds(171, 569, 93, 29);
			lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_9);
			initiative = new TextField();
			initiative.setBounds(297, 561, 61, 37);
			initiative.setFont(new Font("Tahoma", Font.PLAIN, 24));
			initiative.setText("0");
			contentPane.add(initiative);

			// Affectation
			JLabel lblNewLabel_10 = new JLabel("Affectation");
			lblNewLabel_10.setBounds(422, 389, 117, 37);
			lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_10);
			choice = new Choice();
			choice.setBounds(562, 392, 224, 30);
			choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
			choice.add("BDE");
			choice.add("Bibliotheque");
			choice.add("Quartier administratif");
			choice.add("Halle industrielle");
			choice.add("Halle sportive");
			contentPane.add(choice);

			// type de strat間ie
			JLabel lblNewLabel_11 = new JLabel("Strat間ie");
			lblNewLabel_11.setBounds(443, 452, 96, 37);
			lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(lblNewLabel_11);
			strategy = new Choice();
			strategy.setBounds(562, 455, 224, 30);
			strategy.setFont(new Font("Tahoma", Font.PLAIN, 20));
			strategy.add("Offensive");
			strategy.add("Deffensive");
			strategy.add("Random");
			contentPane.add(strategy);

			// Reerviste ?
			reserviste = new JCheckBox("Reservist");
			reserviste.setBounds(579, 513, 187, 26);
			reserviste.setBackground(Color.CYAN);
			reserviste.setForeground(Color.BLACK);
			reserviste.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(reserviste);

			JButton ok = new JButton("OK");
			ok.setBounds(711, 545, 59, 45);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String key = configPersonnage.getText();
					System.out.println(key);
					Protagonist p = new Protagonist();
					p.setForce(Integer.parseInt(force.getText()));
					p.setConstitution(Integer.parseInt(constitution.getText()));
					p.setDexterity(Integer.parseInt(dexterite.getText()));
					p.setInitiative(Integer.parseInt(initiative.getText()));
					System.out.println(reserviste.isSelected());
					p.setReservist(reserviste.isSelected());
					p.setResistance(Integer.parseInt(resistance.getText()));
					p.setZone(choice.getSelectedIndex());
					p.setStrategy(strategy.getSelectedIndex());
					if (state == 1) {
						troop.getListProtagonists().put(key, p);
					} else if (state == 2) {
						troop.getListProtagonists2().put(key, p);
					}

				}
			});
			contentPane.add(ok);
			ok.setFont(new Font("Tahoma", Font.PLAIN, 18));

			JButton validation = new JButton("VALIDER");
			validation.setBounds(830, 523, 270, 83);
			validation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (state == 1) {

						state++;
					} else if (state == 2) {
						troop.checkLimits();
						System.out.println(troop);
						troop.troop();
						bg.fight();

					}

				}
			});
			validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPane.add(validation);
			JPanel panel = new JPanel();
			panel.setBounds(51, 345, 769, 261);
			panel.setBackground(new Color(7, 180, 184));
			panel.setBorder(null);
			contentPane.add(panel);
			panel.setLayout(null);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(51, 634, 609, 75);
			contentPane.add(panel_1);

			JButton btnNewButton = new JButton("YES");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(1);
				}
			});

			JButton btnNewButton_6 = new JButton("NO");
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(0);
				}
			});
			panel_1.add(btnNewButton_6);

			JButton btnNewButton_5 = new JButton("1");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(2);
				}
			});
			panel_1.add(btnNewButton_5);

			JButton btnNewButton_4 = new JButton("2");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(3);
				}
			});
			panel_1.add(btnNewButton_4);

			JButton btnNewButton_3 = new JButton("3");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(4);
				}
			});
			panel_1.add(btnNewButton_3);

			JButton btnNewButton_2 = new JButton("4");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(5);
				}
			});
			panel_1.add(btnNewButton_2);

			JButton btnNewButton_7 = new JButton("5");
			btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (bg) {
						bg.notify();
					}
					bg.click(6);
				}
			});
			panel_1.add(btnNewButton_7);
			panel_1.add(btnNewButton);
		}

		/**
		 * lisener to listen to the set up panel.
		 * 
		 * @author CHEN Jiashun
		 * @version 1.0
		 */
		final class MonEcouteurEvenements implements ActionListener {
			private Map<String, Protagonist> liste;
			private String key;

			public MonEcouteurEvenements(Map<String, Protagonist> liste, String key) {
				this.liste = liste;
				this.key = key;
			}

			public void actionPerformed(ActionEvent e) {
				Protagonist p = liste.get(key);
				configPersonnage.setText(key);
				System.out.println("jhgcvhjjhgcchgch" + key);
				force.setText(Integer.toString(p.getForce()));
				dexterite.setText(Integer.toString(p.getDexterity()));
				resistance.setText(Integer.toString(p.getResistance()));
				constitution.setText(Integer.toString(p.getConstitution()));
				initiative.setText(Integer.toString(p.getInitiative()));
				choice.select(p.getZone());
				strategy.select(p.getStrategy());
				reserviste.setSelected(p.isReservist());
			}
		}

	}

}
