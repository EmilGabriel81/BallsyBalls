package com.homeplaying.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.homeplaying.listeners.GameEventListener;
import com.homeplaying.listeners.GameMainLoop;
import com.homeplaying.objects.Asset;
import com.homeplaying.objects.Ball;

public class GameMainPanel extends JPanel {

	private Timer timer;
	private Ball ball;
	public static final int PANELWIDHT = 900;
	public static final int PANELHEIGHT = 750;
	private List<Ball> balls;
	private Random r;
	private Color color;
	private boolean gameOn;
	
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
		gameOn = true;
		initBalls();
		initVar();
		initLayout();
		//checkCollision();
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
		// System.out.println("Repainting");
		drawBackGround(g);
			drawBoard(g);
			//drawSides(g);
			drawRectSides(g);
	}
	
	
	private void drawRectSides(Graphics g) {
		g.setColor(Color.BLUE);
		if(rLeft!= null) {
			g.fillRect((int)rLeft.getX() ,(int)rLeft.getY() ,(int)rLeft.getWidth() ,(int) rLeft.getHeight());				
		}
		if(rRight != null) {
			g.fillRect((int)rRight.getX() ,(int)rRight.getY() ,(int)rRight.getWidth() ,(int) rRight.getHeight());
		}
		if(rUp != null) {
			g.fillRect((int)rUp.getX() ,(int)rUp.getY() ,(int)rUp.getWidth() ,(int) rUp.getHeight());	
		}
		if(rDown != null) {
			g.fillRect((int)rDown.getX() ,(int)rDown.getY() ,(int)rDown.getWidth() ,(int)rDown.getHeight());
		}
	}

	private void drawBackGround(Graphics g) {
		// System.out.println("drawBackground called");
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 912, 762);

	}

	private void drawBoard(Graphics g) {
		if(!gameOn) {
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.BLUE);
			g.drawString("Pause", 30, 30);
		}
			drawRandomBalls(g);		
	}
	
	

	private void checkSides() {
		
		int pointY;
		int pointX;
		int ballSize;
		
		for(Ball b : balls) {
			if(b.getX() <= 0) {
				pointY = b.getY();
				ballSize = b.getSize();
				rLeft = new Rectangle(0,pointY, 10, ballSize);	
				
			}if(b.getX()+b.getSize() >= PANELWIDHT) {
				pointY = b.getY();
				ballSize = b.getSize();
				rRight = new Rectangle(PANELWIDHT-10, pointY, 10, ballSize);
				
			}
			if(b.getY() <= 0) {
				pointX = b.getX();
				ballSize = b.getSize();
				rUp = new Rectangle(pointX, 0, ballSize, 10);
				
			}if(b.getY()+b.getSize() >= PANELHEIGHT) {
				pointX = b.getX();
				ballSize = b.getSize();
				rDown = new Rectangle(pointX, PANELHEIGHT -10, ballSize, 10);
				
			}
		}
		
	}

	private void drawRandomBalls(Graphics g) {

		for (Ball rBall : this.balls) {
			g.setColor((rBall.getColor()));
			g.fillOval(rBall.getX(), rBall.getY(), rBall.getSize(), rBall.getSize());
			//g.setColor(color.BLUE);
			//g.drawRect(rBall.getX(),rBall.getY(),rBall.getSize(),rBall.getSize());
		}

	}

	

	public void render() {
		// On single method for updates
			if(gameOn) {
				update();
			}
			repaint();	
		
	}

	private void update() {
		// System.out.println("Updateing");
		checkCollision();
		moveBalls();
		checkSides();
		
	}

	private void checkCollision() {
//		for(int i = 0; i < balls.size();i++ ) {
//			Iterator<Ball>ballsIterator = balls.iterator();
//				while(ballsIterator.hasNext()) {
//					theBall = (Ball) ballsIterator.next();
//				}
//				theBall = (Ball) balls.get(i);
//				if(theBall.getBounds().intersects(myBall.getBounds())) {
//					myBall.setReverseX(theBall.getReverseX()*-1);
//					myBall.setReverseY(theBall.getReverseY()*-1);
//					//System.out.println("Intersection");
//				}
//		}
		
		for(int i = 0; i < balls.size();i++ ) {
			for(int j = i + 1; j < balls.size();j++ ) {
				if(balls.get(i).getBounds().intersects(balls.get(j).getBounds())) {
					int tempX = balls.get(i).getReverseX();
					int tempY = balls.get(i).getReverseY();

					balls.get(i).setReverseX(balls.get(j).getReverseX());
					balls.get(i).setReverseY(balls.get(j).getReverseY());

					balls.get(j).setReverseX(tempX);
					balls.get(j).setReverseY(tempY);
					
					/**
					 *      -------- Almost the same result ----
					 * 
					balls.get(i).setReverseX(balls.get(i).getReverseX()*-1);
					balls.get(i).setReverseY(balls.get(i).getReverseY()*-1);
					
					balls.get(j).setReverseX(balls.get(j).getReverseX()*-1);
					balls.get(j).setReverseY(balls.get(j).getReverseY()*-1);
					
					System.out.println("Collision");
					 * 
					 */
			
			}
				
		    }
		
		}
		
	}
	
//	public boolean collide(Rectangle a, Rectangle b) {
//		if(a.intersects(b)) {
//			return true;
//		}
//		return false;
//	}


	private void moveBalls() {
		for (Ball rBall : this.balls) {
			rBall.moveBall();
//			rBall.moveX(directionX);
//			rBall.moveY(directionY);
			
		}
	}
	

	private void initBalls() {
		r = new Random();
		balls = new ArrayList<Ball>();
		int randomBallls = r.nextInt(19) + 1;
		for (int i = 0; i < randomBallls; i++) {
			// Random position on the panel
			int randX = r.nextInt(PANELWIDHT - 21) + 1;
			int randY = r.nextInt(PANELHEIGHT - 21) + 1;
			// random direction of the ball
			int randDirectX = r.nextInt(9) + 1;
			int randDirectY = r.nextInt(8) + 1;
			// random size of the ball
			int randSize = r.nextInt(35) + 5;
			// random color of the ball
			int red = r.nextInt(254) + 1;
			int green = r.nextInt(254) + 1;
			int blue = r.nextInt(254) + 1;
			color = new Color(red, green, blue);
			//if (i % 2 == 0) {
				this.balls.add(new Ball(randX, randY, randDirectX, randDirectY, randSize, color));
			//}else {this.balls.add(new Ball(randX, randY, randDirectX, randDirectY, randSize, color));}
		}
		for (Ball defaultBall : this.balls) {
			System.out.println(defaultBall.toString());
		}
		System.out.println("Random number was: "+randomBallls+", number of balls: " + balls.size());
	}
	
	
	
	// Key Adapters.................

	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(isPlaying()) {
				gameOn = false;
			}else {gameOn = true;}
		}
	}

	private boolean isPlaying() {
		return gameOn;	
	}

	public void KeyReleased(KeyEvent e) {
		//System.out.println("Key Released : " + e.getKeyCode());

	}

}
