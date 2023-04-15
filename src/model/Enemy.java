package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import untils.Container;
import untils.Load;

public class Enemy {
	private Image img;
	private int x;
	private int y;
	private int speedx;
	private int speedy;
	private int count;
	private int hp;

	public Enemy() {
		Random r = new Random();
		int k = r.nextInt(3);
		if (k == 0) {
			img = Load.getImage("enemy.png");
		} else if (k == 1) {
			img = Load.getImage("enemy3.png");
		} else {
			img = Load.getImage("enemy2.png");

		}
		int w = Container.w_Frame - img.getWidth(null);
		x = r.nextInt(w);
		speedx = 1;
		speedy = r.nextInt(5) + 1;
		count = 0;
		hp = 2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	public boolean die() {
		hp--;
		return hp == 0;
	}
	public void moveup() {
		
	}

	public boolean checkOut() {
		return y > Container.H_Frame;
	}

	public void move() {
		x += speedx;
		y += speedx * speedy;
	}

	public void addBoom(ArrayList<Boom> arrBoom) {
		count++;
		if (count < 150) {
			return;
		}
		count = 0;
		int xb = x + img.getWidth(null) / 2;
		int yb = y + img.getHeight(null) / 2;
		Boom boom = new Boom(xb, yb);
		arrBoom.add(boom);
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}

}
