package com.homeplaying.app;

import javax.swing.SwingUtilities;

import com.homeplaying.ui.GameMainFrame;

public class StartApp {
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new GameMainFrame();
			}
		});
	}
}
