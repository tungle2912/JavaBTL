package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import untils.Load;

public class Bullet {
	private int x;
	private int y;
	private Image image;
	private int speed;
	public Bullet(int x, int y) {
		this.image = Load.getImage("bullet1.png");
		this.x = x -image.getWidth(null)/2;
		this.y = y;
		this.speed=10;
	}
	public boolean move() {
		y-=speed;
		return y<0;
	}
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image,x,y,null);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
	}
	
	
	
}
