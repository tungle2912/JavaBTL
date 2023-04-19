package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface IGame {
	public void draw(Graphics2D g);
	public void move();
	public Rectangle getRect();
	public boolean disappear();

}
