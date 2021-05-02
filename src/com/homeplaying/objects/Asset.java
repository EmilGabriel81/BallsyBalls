package com.homeplaying.objects;

import javax.swing.ImageIcon;

public class Asset{
	

	int x ;
	int y ;
	private ImageIcon imgIcon;
	
	private boolean dead;

	public Asset(int x, int y) {
		this.x= x;
		this.y = y;
		this.imgIcon = new ImageIcon("/images/AlienResized.png");
		this.dead = false;
		
	}
	
	
	public ImageIcon getImgIcon() {
		return imgIcon;
	}


	public boolean isDead() {
		return dead;
	}


	public void setDead(boolean dead) {
		this.dead = dead;
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


	public Asset() {
		
	}
	
}
