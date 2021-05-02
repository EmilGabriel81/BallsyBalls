package com.homeplaying.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.homeplaying.ui.GameMainPanel;

public class GameMainLoop implements ActionListener {

	private GameMainPanel board;
	
	public GameMainLoop(GameMainPanel board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.board.render();
	}

}
