package com.ballsyballs.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import com.ballsyballs.ui.GameMainFrame;
import com.ballsyballs.ui.GameMainPanel;

public class GameEventListener extends KeyAdapter{
	
	private GameMainPanel board;
	
	public GameEventListener(GameMainPanel board) {
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//super.keyPressed(e);
		this.board.KeyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//super.keyReleased(e);
		this.board.KeyReleased(e);
	}
	

}
