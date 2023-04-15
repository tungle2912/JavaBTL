package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import untils.Container;
import untils.Load;

public class Boom {
	private Image img;
	private int x;
	private int y;
	private int speed;

	public Boom(int x, int y) {
		this.img = Load.getImage("boom3.png");
		this.x = x;
		this.y = y;
		this.speed = 5;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	public boolean move() {
		y += speed;
		return y > Container.H_Frame;
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}

}
