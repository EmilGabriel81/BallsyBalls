package com.ballsyballs.ui;

import javax.swing.JFrame;

public class GameMainFrame extends JFrame {
	
	
	public GameMainFrame() {
	init();
		
	}

	private void init() {
		add(new GameMainPanel());
		pack();
	
		setTitle("Crazy Balls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	}

	
}
