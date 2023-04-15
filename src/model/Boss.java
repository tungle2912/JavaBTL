package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import untils.Container;
import untils.Load;

public class Boss extends Enemy {
	private Image img;
	private int x;
	private int y;
	private int hp;
	private int count;
	private int countmove;
	private int speedX;
	private int speedY;

	public Boss() {
		img = Load.getImage("boss.png");
		x = Container.w_Frame/2 -img.getWidth(null)/2;
		y = 0;
		hp = 100;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	public boolean die() {
		hp--;
		return hp == 0;
	}

	public void move() {
		Random r=new Random();
		if (countmove >= 200) {
			speedX = r.nextInt(10)-5;
			speedY =r.nextInt(10)-5;
			countmove = 0;
		} else {
			countmove++;
		}
		x += speedX;
		y += speedY;
		if (x < 0 ) {
			speedX = Math.abs(speedX);
		}
		if(x > Container.w_Frame-img.getWidth(null) ) {
			speedX = -Math.abs(speedX);
		}
		if (y < 0 || y > Container.H_Frame / 2) {
			speedY = -speedY;
		}
	}
	public void moveup() {
		for(int i=0;i<15;i++) {
			x-=1;
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
		arrBoom.add(new Boom(x1 - 40, y1+50));
		arrBoom.add(new Boom(x1, y1+40));
		arrBoom.add(new Boom(x1 + 40, y1+50));
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}

}
