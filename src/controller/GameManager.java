package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.*;
import utils.Constants;
import utils.Load;

public class GameManager extends JPanel {
	private Image backGround;
	private Plane plane;
	private ArrayList<Enemy> arrEnemy;
	private ArrayList<Bullet> arrBullet;
	private ArrayList<Boom> arrBoom;
	private Boss boss;
	private int count;
	private boolean check;
	private JProgressBar progressBar;
	private JLabel win;
	private boolean checkwin;

	public GameManager() {
		initGame();
		Thread t = new Thread(run);
		t.start();
		GameListenner Listenner = new GameListenner(this);
		this.addMouseListener(Listenner);
		this.addMouseMotionListener(Listenner);
		this.addKeyListener(Listenner);
		setFocusable(true); 
		
	}

	public void initGame() {
		checkwin=false;
		check=true;
		backGround = Load.getImage("background.gif");
		plane = new Plane();
		arrEnemy = new ArrayList<Enemy>();
		arrBullet = new ArrayList<Bullet>();
		arrBoom = new ArrayList<Boom>();
		 progressBar = new JProgressBar();
		initEnemy();
	}

	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backGround, 0, 0, Constants.w_Frame, Constants.H_Frame, null);
		for (Enemy enemy : arrEnemy) {
			enemy.draw(g2d);
		}
		for (Boom boom : arrBoom) {
			boom.draw(g2d);
		}
		for (Bullet bullet : arrBullet) {
			bullet.draw(g2d);
		}
		plane.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("arial", Font.BOLD, 30));
		g2d.drawString("Score " + plane.getScore(), 20, 50);

		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("arial", Font.BOLD, 30));
		g2d.drawString("Hp " + plane.getHp(), 190, 50);
	}
	


	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public ArrayList<Bullet> getArrBullet() {
		return arrBullet;
	}

	public void setArrBullet(ArrayList<Bullet> arrBullet) {
		this.arrBullet = arrBullet;
	}

	public void initEnemy() {
		if (plane.getScore() < 0) {
			for (int i = 0; i < 6; i++) {
				arrEnemy.add(new Enemy());
			}
		} else {
			if(check) {
				arrEnemy.add(new Boss());
				progressBar.setMaximum(100); 
				progressBar.setValue(100);
				progressBar.setBounds(400, 0,500, 30);
				progressBar.setForeground(Color.red);
				add(progressBar);
				check=false;
			}
			
		}

	}

	public void restartGame() {
		remove(progressBar);
		if (JOptionPane.showConfirmDialog(null, "Do you want to replay ?", "Game Over", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == 0) {
			initGame();
		} else {
			System.exit(0);
		}
	}
	public void win() {
		remove(progressBar);
		ImageIcon image = null;
		try {
			image = new ImageIcon("src/Data/win.jpg");		
		} catch (Exception e) {
			System.out.println("not file");
		}
		win = new JLabel();
		win.setLayout(new BorderLayout());
		win.setBounds(0, 0,Constants.w_Frame, Constants.H_Frame);
		JLabel background = new JLabel(image);
		background.setBounds(0, 0,Constants.w_Frame, Constants.H_Frame);
		win.add(background, BorderLayout.CENTER);
		add(win);
		
	}

	public void completeGame() {
//		if (JOptionPane.showConfirmDialog(null, "Do you want to replay ?", "You Win", JOptionPane.YES_NO_OPTION,
//				JOptionPane.NO_OPTION) == 0) {
//			initGame();
//		} else {
//			System.exit(0);
//		}
		JOptionPane pane = new JOptionPane("Do you want to replay ?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		JDialog dialog = pane.createDialog(null, "You Win");
		dialog.setLocation(Constants.w_Frame/2-pane.getWidth()/2,Constants.H_Frame-200);
		dialog.setVisible(true);
		if (pane.getValue().equals(JOptionPane.YES_OPTION)) {
			remove(win);
			initGame();
		} else {
			System.exit(0);
		}
	}

	public void AI() {
		count++;
		if (count >= 500) {
			initEnemy();
			count = 0;
		}
		if(checkwin) {
			completeGame();
		}
		for(int i=0;i<arrEnemy.size();i++) {
			if(arrEnemy.get(i).disappear()) {
				arrEnemy.remove(i);
			}
		}
		for (int i = 0; i < arrEnemy.size(); i++) {
			arrEnemy.get(i).move();
			arrEnemy.get(i).addBoom(arrBoom);
			Rectangle r = arrEnemy.get(i).getRect().intersection(plane.getRect());
			if (r.isEmpty() == false) {
			if (arrEnemy.get(i) instanceof Boss) {
				Load.getAudio("enemydie.wav");
				if (plane.die()) {
					restartGame();
				
				}
			}else {
					Load.getAudio("enemydie.wav");
				arrEnemy.remove(i);
				if (plane.die()) {
					restartGame();
				}
			}
			}

			for (int j = 0; j < arrBullet.size(); j++) {
				Rectangle rect = arrEnemy.get(i).getRect().intersection(arrBullet.get(j).getRect());
				if (rect.isEmpty() == false) {
					if (arrEnemy.get(i) instanceof Boss) {
						arrEnemy.get(i).setX(arrEnemy.get(i).getX()-5);;
						arrBullet.remove(j);
						if(arrEnemy.get(i).die()) {
							win();
							checkwin=true;
							break;
						}
						progressBar.setValue((int ) arrEnemy.get(arrEnemy.size()-1).getHp());
					} else {
						arrBullet.remove(j);
						if (arrEnemy.get(i).die()) {
							arrEnemy.remove(i);
							plane.setScore(plane.getScore() + 50);
							break;
						}
					}

				}
			}

			

		}

		for (int i = 0; i < arrBoom.size(); i++) {
		arrBoom.get(i).move();
			if (arrBoom.get(i).disappear()) {
				arrBoom.remove(i);
			} else {
				Rectangle r = arrBoom.get(i).getRect().intersection(plane.getRect());
				if (r.isEmpty() == false) {
					Load.getAudio("enemydie.wav");
					arrBoom.remove(i);
					if (plane.die()) {
						restartGame();
					}
				}
			}
		}
		for (int i = 0; i < arrBullet.size(); i++) {
			arrBullet.get(i).move();
			if (arrBullet.get(i).disappear()) {
				arrBullet.remove(i);
			}
		}

	}

	Runnable run = new Runnable() {
		@Override
		public void run() {
			while (true) {
				AI();
				repaint();
				try {
					Thread.sleep(12);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

}
