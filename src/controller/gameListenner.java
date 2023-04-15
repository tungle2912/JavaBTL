package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import untils.Container;
import untils.Load;

public class gameListenner extends MouseAdapter implements MouseMotionListener, MouseListener, KeyListener {
	GameManager manager;
	private long lastFireTime;
	private long fireDelay;

	public gameListenner(GameManager gamemanager) {
		manager = gamemanager;
		lastFireTime = 0;
		fireDelay = 200;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		manager.getPlane().move(e.getX(), e.getY());
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastFireTime >= fireDelay) {
			Load.getAudio("fire.wav");
			manager.getPlane().fire(manager.getArrBullet());
			lastFireTime = currentTime;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		manager.getPlane().move(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		manager.getPlane().fire(manager.getArrBullet());
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
			manager.getPlane().moveLeft();
			break;
		}
		case KeyEvent.VK_S: {
			manager.getPlane().moveDown();
			break;
		}
		case KeyEvent.VK_D: {
			manager.getPlane().moveRight();
			break;
		}
		case KeyEvent.VK_W: {
			manager.getPlane().moveUp();
			break;
		}
		case KeyEvent.VK_ENTER: {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastFireTime >= fireDelay) {
				Load.getAudio("fire.wav");
				manager.getPlane().fire(manager.getArrBullet());
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
