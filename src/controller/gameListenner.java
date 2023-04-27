package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import utils.Constants;
import utils.Load;
import view.MyPanel;

public class GameListenner extends MouseAdapter implements MouseMotionListener, MouseListener, KeyListener {
	private MyPanel myPanel;
	private long lastFireTime;
	private long fireDelay;

	public GameListenner(MyPanel myPanel) {
		this.myPanel=myPanel;
		lastFireTime = 0;
		fireDelay = 200;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		myPanel.getPlane().move(e.getX(), e.getY());
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastFireTime >= fireDelay) {
			Load.getAudio("fire.wav");
			myPanel.getPlane().fire(myPanel.getArrBullet());
			lastFireTime = currentTime;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		myPanel.getPlane().move(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		myPanel.getPlane().fire(myPanel.getArrBullet());
		Load.getAudio("fire.wav");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: {
			myPanel.getPlane().moveLeft();
			break;
		}
		case KeyEvent.VK_S: {
			myPanel.getPlane().moveDown();
			break;
		}
		case KeyEvent.VK_D: {
			myPanel.getPlane().moveRight();
			break;
		}
		case KeyEvent.VK_W: {
			myPanel.getPlane().moveUp();
			break;
		}
//		case KeyEvent.VK_SPACE: {
//			manager.setCheckRun(!manager.isCheckRun());;
//			break;
//		}
//		case KeyEvent.VK_W: {
//			manager.getPlane().moveUp();
//			break;
//		}
		case KeyEvent.VK_ENTER: {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastFireTime >= fireDelay) {
				Load.getAudio("fire.wav");
				myPanel.getPlane().fire(myPanel.getArrBullet());
				lastFireTime = currentTime;
			}
			break;
		}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	

	}

}
