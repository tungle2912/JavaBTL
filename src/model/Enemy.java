package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import utils.Constants;
import utils.Load;

public class Enemy implements IGame {
	protected Image img;
	protected int x;
	protected int y;
	protected int speedX;
	protected int speedY;
	protected int count;
	protected int hp;

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
		int w = Constants.w_Frame - img.getWidth(null);
		x = r.nextInt(w);
		speedX = 1;
		speedY = r.nextInt(3) + 1;
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

	public boolean die() {
		hp--;
		return hp == 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	@Override
	public void move() {
		x += speedX;
		y += speedY;
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

	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}

	@Override
	public boolean disappear() {
		return y > Constants.H_Frame;
	}

}
