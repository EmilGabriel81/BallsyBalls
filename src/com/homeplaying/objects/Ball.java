package com.homeplaying.objects;

import java.awt.Color;
import java.awt.Rectangle;

import com.homeplaying.ui.GameMainPanel;

public class Ball {

	private int x;
	private int y;
	private int directX;
	private int directY;
	private int reverseX = 1;
	private int reverseY = 1;
	private boolean allive = true;
	private int count;

	private int size;
	private Color color;

	public Ball(int x, int y, int speedX, int speedY, int size, Color color) {
		this.x = x;
		this.y = y;
		this.directX = speedX;
		this.directY = speedY;
		this.size = size;
		this.color = color;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void moveX(int dir) {
		this.x += dir;
	}

	public void moveY(int dir) {
		this.y += dir;
	}
	
	public boolean isAllive() {
		return allive;
	}

	public void setAllive(boolean allive) {
		this.allive = allive;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getReverseX() {
		return reverseX;
	}

	public void setReverseX(int reverseX) {
		this.reverseX = reverseX;
	}

	public int getReverseY() {
		return reverseY;
	}

	public void setReverseY(int reverseY) {
		this.reverseY = reverseY;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getSize(), this.getSize());
	}

	public void moveBall() {
		if (this.getX() + this.getSize() >= GameMainPanel.PANELWIDHT && reverseX != -1 || this.x <= 0 && reverseX != 1)
			reverseX *= -1;
		if (this.getY() + this.getSize() >= GameMainPanel.PANELHEIGHT && reverseY != -1 || this.y <= 0 && reverseY != 1)
			reverseY *= -1;
		moveX(directX * reverseX);
		moveY(directY * reverseY);
	}

	@Override
	public String toString() {
		return "Ball -- > x = " + x + ", y = " + y + ", speedX = " + directX + ", speedY = " + directY + ", size = "
				+ size;
	}

}
