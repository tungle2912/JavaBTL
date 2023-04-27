package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import controller.GameListenner;
import controller.GameManager;
import model.Boom;
import model.Boss;
import model.Bullet;
import model.Enemy;
import model.Plane;
import utils.Constants;
import utils.Load;

public class MyPanel extends JPanel {
	Image backGround;
	Plane plane;
	ArrayList<Enemy> arrEnemy;
	ArrayList<Bullet> arrBullet;
	ArrayList<Boom> arrBoom;
	Boss boss;
	JProgressBar progressBar;
	JLabel win;
	GameManager gamemanager;
	private int countTime;
	private Timer timer;

	public MyPanel() {
		init();
		GameListenner Listenner = new GameListenner(this);
		this.addMouseListener(Listenner);
		this.addMouseMotionListener(Listenner);
		this.addKeyListener(Listenner);
		setFocusable(true);
		Thread t = new Thread(run);
		t.start();
	}

	public void init() {
		plane = new Plane();
		backGround = Load.getImage("background.gif");
		arrEnemy = new ArrayList<Enemy>();
		arrBullet = new ArrayList<Bullet>();
		arrBoom = new ArrayList<Boom>();
		progressBar = new JProgressBar();
		gamemanager = new GameManager(this);
		countTime = 0;
		timer = new Timer(1000, (ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countTime++;
			}
		});
		timer.start();
		gamemanager.initEnemy();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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

		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("arial", Font.BOLD, 30));
		int minutes = countTime / 60;
		countTime %= 60;
		String time = String.format("%02d:%02d", minutes, countTime);
		g2d.drawString(time, Constants.w_Frame - 120, 40);
	}

	public void writeFile() {
		if (plane.getScore() != 0) {
			try {
				FileWriter writer = new FileWriter("src/data/text/diem.txt", true);
				BufferedWriter buffer = new BufferedWriter(writer);
				String scorePlane;
				if(plane.getScore()<100) {
					scorePlane=plane.getScore()+"";
				}else if(plane.getScore() < 1000){
					scorePlane=plane.getScore()+" ";
				}else {
					scorePlane=plane.getScore()+"";
				}
				buffer.write("   Score : " + scorePlane	+ "  | Time :" + countTime + "   \n");
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			timer.stop();
		} else {
			return;
		}
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

	public ArrayList<Enemy> getArrEnemy() {
		return arrEnemy;
	}

	public void setArrEnemy(ArrayList<Enemy> arrEnemy) {
		this.arrEnemy = arrEnemy;
	}

	public ArrayList<Boom> getArrBoom() {
		return arrBoom;
	}

	public void setArrBoom(ArrayList<Boom> arrBoom) {
		this.arrBoom = arrBoom;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public void setValueProgressBar(int x) {
		progressBar.setValue(x);
	}

	public void win() {
		remove(progressBar);
		ImageIcon image = null;
		try {
			image = new ImageIcon("src/data/img/win.jpg");
		} catch (Exception e) {
			System.out.println("not file");
		}
		win = new JLabel();
		win.setLayout(new BorderLayout());
		win.setBounds(0, 0, Constants.w_Frame, Constants.H_Frame);
		JLabel background = new JLabel(image);
		background.setBounds(0, 0, Constants.w_Frame, Constants.H_Frame);
		win.add(background, BorderLayout.CENTER);
		add(win);

	}

	public void creatProgressBar() {
		progressBar.setMaximum(100);
		progressBar.setValue(100);
		progressBar.setBounds(400, 0, 500, 30);
		progressBar.setForeground(Color.red);
		add(progressBar);
	}

	public void removeProgressBar() {
		remove(progressBar);
	}

	public void removeWin() {
		remove(win);
	}

//	@Override
//	public void run() {
//		while (true) {
//			gamemanager.checkCrash();
//			repaint();
//			try {
//				Thread.sleep(12);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	Runnable run = new Runnable() {
		@Override
		public void run() {
			while (true) {
				gamemanager.checkCrash();
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
