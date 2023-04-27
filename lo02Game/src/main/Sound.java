package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * control the music.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class Sound {

	Clip clip;
	URL soundURL[] = new URL[10];

	public Sound() {
		soundURL[0] = getClass().getResource("/sound/backgroundmusic.wav");

	}

	/**
	 * setup the music file.
	 * 
	 * @param i the nbr of music.
	 */
	public void setFile(int i) {

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {

		}

	}

	/**
	 * start to play the music.
	 */
	public void play() {

		clip.start();

	}

	/**
	 * loop the music chip.
	 */
	public void loop() {

		clip.loop(Clip.LOOP_CONTINUOUSLY);

	}

	/**
	 * stop the music.
	 */
	public void stop() {

		clip.stop();

	}
}
