package com.ballsyballs.factory;

import java.awt.Color;
import java.util.Random;

public class Creator {
	
	public static void main(String[] args) {
		getRandomNames();
	}
	
	String [] names = {"Gigi","Bula","Sula","Slow","Dizzy","Trosc","Pleosc","Duffy","Cheeky","Mac"};

	private static Random r = new Random();
	
	public static Color createRandomColor() {
		Color color;
		int red = r.nextInt(254) + 1;
		int green = r.nextInt(254) + 1;
		int blue = r.nextInt(254) + 1;
		color = new Color(red, green, blue);
		return color;
	}
	
	public static String getRandomNames() {
		int strInt = r.nextInt(9);
		System.out.println(strInt);
		return null;
	}
	
}
