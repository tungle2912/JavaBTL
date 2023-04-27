package model;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import utils.Constants;
import utils.Load;

public class Plane implements IGame{
	private int x;
	private int y;
	private Image image;
	private int score;
	private int hp;

	public Plane() {
		this.image = Load.getImage("spaceship.png");
		this.x = Constants.w_Frame/2 - image.getWidth(null) / 2;
		this.y = Constants.H_Frame - image.getHeight(null) - 100;
//		this.x=600;
//		this.y=550;
		this.hp = 3;
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean die() {
		hp--;
		return hp==0;
	}

	public void move(int newx,int newy) {
		int xr=x;
		int yr=y;
		x=newx;
		y=newy;
		if(x<=0 ||x >= Constants.w_Frame-image.getWidth(null)) {
			x=xr;
		}
		if(y<=0 || y>= Constants.H_Frame-image.getHeight(null)) {
			y=yr;
		}	
	}
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
	}
	public void fire(ArrayList<Bullet> arrBullet) {
		int xbullet=x +image.getWidth(null)/2;
		Bullet b=new Bullet(xbullet,y);
		arrBullet.add(b);
	}
	@Override
	public void move() {
	}
	
	@Override
	public boolean disappear() {
		return false;
	}
	public void moveLeft() {
		if(this.x>=5) {
			x-=5;
		}
	}
	public void moveRight() {
		if(this.x<=Constants.w_Frame-image.getWidth(null)) {
			x+=5;
		}
	}
	public void moveUp() {
		if(this.x>=5) {
			y-=5;
		}
	}
	public void moveDown() {
		if(this.x>=5) {
			y+=5;
		}
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	
	
	
	
	
	

}
