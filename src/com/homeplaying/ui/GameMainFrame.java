package com.homeplaying.ui;

import javax.swing.JFrame;

public class GameMainFrame extends JFrame {
	
	
	public GameMainFrame() {
	init();
		
	}

	private void init() {
		// init main window
		add(new GameMainPanel());
		pack();
		setTitle("Crazy Balls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	}

	
}
