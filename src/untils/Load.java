package untils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;



public class Load {
	public static Image getImage(String name) {
		Image img = new ImageIcon(new Load().getClass().getResource("/Data/" + name)).getImage();
		return img;
	}
	public static void getAudio(String name) {
		try {
		    File soundFile = new File("src/Data/"+name);
		    AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioStream);
		    clip.start();
		} catch (LineUnavailableException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
		    e.printStackTrace();
		}
	}

}
