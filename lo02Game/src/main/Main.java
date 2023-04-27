package main;

import javax.swing.JFrame;

/**
 * Main
 * 
 * @author CHEN Jiahsun
 * @version 1.0
 */
public class Main {

	public static JFrame window;

	public Main() {
		window = new JFrame();
	}

	/**
	 * the main entrance.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Main main = new Main();
		// we can close the window properly
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// we can not resize the window
		window.setResizable(false);
		// set game title
		window.setTitle("Brutal Game");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		// displace center
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
		gamePanel.setUpGame();

		// DEMO

	}
}
