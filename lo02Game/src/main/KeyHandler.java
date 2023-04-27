package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * deal with the keyboard input.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	/**
	 * constractor.
	 * @param gp
	 */
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * father method.
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * get which key is pressed.
	 */
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		// TITLE STATE
		if (GamePanel.gameState == gp.titleState) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					GamePanel.gameState = gp.setState;
				}
				if (gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}

		if (GamePanel.gameState == gp.playState) {

			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_P) {
				if (GamePanel.gameState == gp.playState) {
					GamePanel.gameState = gp.pauseState;
				} else if (GamePanel.gameState == gp.pauseState) {
					GamePanel.gameState = gp.playState;
				}
			}
		}

	}


	/**
	 * get the key is released.
	 */
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
