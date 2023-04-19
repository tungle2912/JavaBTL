package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import utils.Constants;
import utils.Load;

public class Boss extends Enemy {

	private int countmove;

	public Boss() {
		img = Load.getImage("boss.png");
		x = Constants.w_Frame / 2 - img.getWidth(null) / 2;
		y = 0;
		hp = 10;
		countmove = 0;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	public boolean die() {
		hp--;
		return hp == 0;
	}

	public void move() {
		Random r = new Random();
		if (countmove >= 200) {
			speedX = r.nextInt(8) - 5;
			speedY = r.nextInt(8) - 5;
			countmove = 0;
		} else {
			countmove++;
		}
		x += speedX;
		y += speedY;
		if (x < 0) {
			speedX = Math.abs(speedX);
		}
		if (x > Constants.w_Frame - img.getWidth(null)) {
			speedX = -Math.abs(speedX);
		}
		if (y < 0 || y > Constants.H_Frame / 2) {
			speedY = -speedY;
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void addBoom(ArrayList<Boom> arrBoom) {
		count++;
		if (count < 100) {
			return;
		}
		count = 0;
		int x1 = x + img.getWidth(null) / 2;
		int y1 = y + img.getHeight(null) / 2;
		arrBoom.add(new Boom(x1 - 40, y1 + 50));
		arrBoom.add(new Boom(x1, y1 + 40));
		arrBoom.add(new Boom(x1 + 40, y1 + 50));
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}

}
