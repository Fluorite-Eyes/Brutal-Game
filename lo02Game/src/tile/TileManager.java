package tile;

import java.awt.Graphics2D;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * manage the tile
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class TileManager {

	GamePanel gp;
	Tile[] tile;

	public TileManager(GamePanel gp) {

		this.gp = gp;
		tile = new Tile[10];
		getTileImage();

	}

	/**
	 * use a pic as the background for the setup panel.
	 */
	public void getTileImage() {

		try {

			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/map1.jpg"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/**
	 * draw the pic on the panel.
	 * 
	 * @param g2 pen
	 */
	public void draw(Graphics2D g2) {

		g2.drawImage(tile[0].image, 0, 0, gp.screenWidth, gp.screenHeight, null);

	}

}
