package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import utils.Constants;
import utils.Load;

public class Boom implements IGame {
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
	

	public Image getImg() {
		return img;
	}


	public void setImg(Image img) {
		this.img = img;
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


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	@Override
	public void move() {
		y += speed;
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
