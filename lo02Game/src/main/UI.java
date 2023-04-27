package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The GUI for the game.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class UI {

	public BufferedImage img1;
	public BufferedImage img2;
	public BufferedImage img3;
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	public BufferedImage bc;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public int commandNum = 0;

	/**
	 * constructor.
	 * 
	 * @param gp
	 */
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);

	}

	/**
	 * use gp.ui.showMessage("");
	 * 
	 * @param text
	 */
	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	/**
	 * is used to draw something on game panel.
	 * 
	 * @param g2 the pen used for drawing
	 */
	public void draw(Graphics2D g2) {

		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);

		// MESSAHE

		if (messageOn == true) {

			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

			messageCounter++;

			if (messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}

		}

		// TITLE SCREEN
		if (GamePanel.gameState == gp.titleState) {
			drawTitleScreen();
		}

		if (GamePanel.gameState == gp.playState) {
			// nothing
		}
		if (GamePanel.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		if (GamePanel.gameState == 4) {
			drawfinalScreen();
		}

	}

	/**
	 * Draw the game title on setup panel.
	 */
	public void drawTitleScreen() {

		g2.setColor(new Color(70, 120, 80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		try {

			bc = ImageIO.read(getClass().getResourceAsStream("/tiles/bc1.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(bc, 0, 0, gp.screenWidth, gp.screenHeight, null);
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "BRUTAL GAME!";
		int x = getXForCenteredText(text);
		int y = gp.tileSize * 3;
		// SHADOW
		g2.setColor(Color.black);
		g2.drawString(text, x + 5, y + 5);
		// MAIN COLOR
		g2.setColor(Color.RED);
		g2.drawString(text, x, y);
		// MEUN
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

		text = "NEW GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "E X I T";
		x = getXForCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

	/**
	 * Draw the paused screen use a sring "PAUSED".
	 */
	public void drawPauseScreen() {

		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);

	}

	/**
	 * Draw the final screen.
	 */
	public void drawfinalScreen() {

		String text = "Win";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);

	}

	/**
	 * Get the position(x) for the centered text.
	 * 
	 * @param text the text that we want to place at the center.
	 * @return x
	 */
	public int getXForCenteredText(String text) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;

	}

	/****************************************************************************************/

}
