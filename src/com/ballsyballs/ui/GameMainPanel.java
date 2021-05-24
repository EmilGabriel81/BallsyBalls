package com.ballsyballs.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.ballsyballs.factory.Creator;
import com.ballsyballs.listeners.GameEventListener;
import com.ballsyballs.listeners.GameMainLoop;
import com.ballsyballs.objects.Ball;

public class GameMainPanel extends JPanel {

	private Timer timer;
	private Ball ball;
	public static final int PANELWIDHT = 900;
	public static final int PANELHEIGHT = 750;
	private List<Ball> balls;
	private Random r;
	private Color color;
	private boolean onPlay;
	private boolean onGame;

	private Rectangle rRight;
	private Rectangle rLeft;
	private Rectangle rUp;
	private Rectangle rDown;

	private Rectangle genericRect;

//	private int dirX = 5;
//	private int dirY = 3;
//	private int directionX = 1;
//	private int directionY = 1;

	public GameMainPanel() {
		onPlay = true;
		onGame = true;
		initBalls();
		initVar();
		initLayout();
		// checkCollision();
	}

	private void initLayout() {
		setPreferredSize(new Dimension(PANELWIDHT, PANELHEIGHT));
		setFocusable(true);

	}

	private void initVar() {

		addKeyListener(new GameEventListener(this));
		this.timer = new Timer(4, new GameMainLoop(this));
		timer.start();

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawBackGround(g);
		drawBoard(g);
		drawRectSides(g);

		// System.out.println("Repainting");
	}

	private void drawBackGround(Graphics g) {
		// System.out.println("drawBackground called");
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint customBack = new GradientPaint(0, 0, Color.YELLOW,PANELWIDHT/2,PANELHEIGHT/2+200,new Color(153, 255, 255));
		g2d.setPaint(customBack);
		//g.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, 912, 762);
		if (!onGame) {
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.BLUE);
			g.drawString("Game Over ", 380, 300);
		}

	}

	private void drawBoard(Graphics g) {
		if (!onPlay) {
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.BLUE);
			g.drawString("Pause ||", 30, 30);
			g.drawString("Balls in the game: " + balls.size(), 500, 30);
		} 
		drawRandomBalls(g);
	}

	private void drawRandomBalls(Graphics g) {

		for (Ball rBall : this.balls) {
			if (rBall.isAlive()) {
//				g.setColor((rBall.getColor()));
//				g.fillOval(rBall.getX(), rBall.getY(), rBall.getSize(), rBall.getSize());
				rBall.paintShape(g);
			}
		}
	}

	private void drawRectSides(Graphics g) {
		g.setColor(Color.BLUE);
		if (rLeft != null) {
			g.fillRect((int) rLeft.getX(), (int) rLeft.getY(), (int) rLeft.getWidth(), (int) rLeft.getHeight());
		}
		if (rRight != null) {
			g.fillRect((int) rRight.getX(), (int) rRight.getY(), (int) rRight.getWidth(), (int) rRight.getHeight());
		}
		if (rUp != null) {
			g.fillRect((int) rUp.getX(), (int) rUp.getY(), (int) rUp.getWidth(), (int) rUp.getHeight());
		}
		if (rDown != null) {
			g.fillRect((int) rDown.getX(), (int) rDown.getY(), (int) rDown.getWidth(), (int) rDown.getHeight());
		}
	}

	private void checkSides() {

		int pointY;
		int pointX;
		int ballSize;

		for (Ball b : balls) {
			if (b.isAlive()) {
				if (b.getX() <= 0) {
					pointY = b.getY();
					ballSize = b.getSize();
					rLeft = new Rectangle(0, pointY, 5, ballSize);

				}
				if (b.getX() + b.getSize() >= PANELWIDHT) {
					pointY = b.getY();
					ballSize = b.getSize();
					rRight = new Rectangle(PANELWIDHT - 5, pointY, 5, ballSize);

				}
				if (b.getY() <= 0) {
					pointX = b.getX();
					ballSize = b.getSize();
					rUp = new Rectangle(pointX, 0, ballSize, 5);

				}
				if (b.getY() + b.getSize() >= PANELHEIGHT) {
					pointX = b.getX();
					ballSize = b.getSize();
					rDown = new Rectangle(pointX, PANELHEIGHT - 5, ballSize, 5);
				}
			} // if alive

		} // for loop

	}

	public void render() {
		// A single method for updates
		if (onGame) {
			if (onPlay) {
				update();
			}
			repaint();
		}
	}

	private void update() {
		// System.out.println("Updateing");
		checkCollision();
		checkBallStats();
		moveBalls();
		checkSides();

	}

	private void checkCollision() {
		for (int i = 0; i < balls.size(); i++) {
			for (int j = i + 1; j < balls.size(); j++) {
				if (collide(balls.get(i), balls.get(j))) {
					// Swap the direction between them
					int tempX = balls.get(i).getReverseX();
					int tempY = balls.get(i).getReverseY();
					// ball i gets the coordinates from ball j
					balls.get(i).setReverseX(balls.get(j).getReverseX());
					balls.get(i).setReverseY(balls.get(j).getReverseY());
					// ball j gets the coordinates from ball i
					balls.get(j).setReverseX(tempX);
					balls.get(j).setReverseY(tempY);
					// We increase the counter on the balls
					balls.get(i).setCount(balls.get(i).getCount() + 1);
					balls.get(j).setCount(balls.get(j).getCount() + 1);
					// ball i gets the color of ball j
					Color c = balls.get(i).getColor();
					balls.get(i).setColor(balls.get(j).getColor());
					// ball j gets the color of ball i
					balls.get(j).setColor(c);

					/**
					 * -------- Almost the same result ----
					 * 
					 * balls.get(i).setReverseX(balls.get(i).getReverseX()*-1);
					 * balls.get(i).setReverseY(balls.get(i).getReverseY()*-1);
					 * 
					 * balls.get(j).setReverseX(balls.get(j).getReverseX()*-1);
					 * balls.get(j).setReverseY(balls.get(j).getReverseY()*-1);
					 * 
					 * System.out.println("Collision");
					 * 
					 */

				}

			}

		}
		//checkVisibility
		for (Ball ballz : balls) {
			if (ballz.getCount() > 10) {
				ballz.setAlive(false);
			}
		}
	}
	
	private void checkBallStats() {
		if (checkVisibility() == 1 || checkVisibility() == 0 )
			onGame = false;
	}


	private int checkVisibility() {
		int arraySize = balls.size();
		int ballsCounted = 0;
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).getCount() > 10) {
				ballsCounted++;
			}
		}
		return arraySize - ballsCounted;
	}

	public boolean collide(Ball a, Ball b) {
		if (a.isAlive() && b.isAlive()) {
			Rectangle rectA = a.getBounds();
			Rectangle rectB = b.getBounds();
			if (rectA.intersects(rectB)) {
				return true;
			}
		}
		return false;
	}

	private void moveBalls() {
		for (Ball rBall : this.balls) {
			rBall.moveBall();
		}
	}

	private void initBalls() {
		r = new Random();
		balls = new ArrayList<Ball>();
		int randomBallls = r.nextInt(18) + 2;
		for (int i = 0; i < randomBallls; i++) {
			// Random position on the panel
			int randX = r.nextInt(PANELWIDHT - 21) + 1;
			int randY = r.nextInt(PANELHEIGHT - 21) + 1;
			// random direction of the ball
			int randDirectX = r.nextInt(9) + 1;
			int randDirectY = r.nextInt(8) + 1;
			// random size of the ball
			int randSize = r.nextInt(30) + 15;
			// random color of the ball
			//int blended = r.nextInt(254) + 1;
//			int green = r.nextInt(254) + 1;
//			int blue = r.nextInt(254) + 1;
			color = Creator.createRandomColor();
			// if (i % 2 == 0) {
			this.balls.add(new Ball(randX, randY, randDirectX, randDirectY, randSize, color));
			// }else {this.balls.add(new Ball(randX, randY, randDirectX, randDirectY,
			// randSize, color));}
		}
		for (Ball defaultBall : this.balls) {
			System.out.println(defaultBall.toString());
		}
		System.out.println("Random number was: " + randomBallls + ", number of balls: " + balls.size());
	}

	private boolean isPlaying() {
		return onPlay;
	}

	// Key Adapters.................

	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isPlaying()) {
				onPlay = false;
			} else {
				onPlay = true;
			}
		}
	}

	public void KeyReleased(KeyEvent e) {
		// System.out.println("Key Released : " + e.getKeyCode());
	}

}
