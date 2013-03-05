/*
 * File: Breakout.java
 * -------------------
 * Name: Abdulrhman Eaita
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private int endtrying = 0;

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
		addMouseListeners();
	}

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		
		buildBricks();
		paddle();
		ball();
		startApp();
		
		
	}
	//Creating bricks as requested in colors and separators 
	private void buildBricks(){
		for(int x = 0; x < NBRICK_ROWS; x++){
			for(int j = 0; j < NBRICKS_PER_ROW; j++){
				reak = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				reak.setFilled(true);
				reakCounter++;
				//adding bricks from the start of the line and increasing by j the width and separator 
				add(reak, + (BRICK_SEP + BRICK_WIDTH) * j , BRICK_Y_OFFSET + (BRICK_SEP + BRICK_HEIGHT) * x );
				
				// Setting color to the bricks depending on brick row number
				switch(x){
				case 0: case 1:reak.setColor(Color.red); break;
				case 2: case 3:reak.setColor(Color.orange); break;
				case 4: case 5:reak.setColor(Color.yellow); break;
				case 6: case 7:reak.setColor(Color.green); break;
				case 8: case 9:reak.setColor(Color.cyan); break;
				}
			}
		}
	}
	// Creating Paddle 
	private void paddle(){
		// declaring variables for more clarity and to be used in other method
		baddleY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		baddleX = (WIDTH - PADDLE_WIDTH)/2;
		// creating the paddle
		baddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		baddle.setFilled(true);
		add(baddle, baddleX, baddleY);
	}
	
	public void mouseMoved(MouseEvent e) {
		// tracking the mouse inside the window and ignoring it if it's out of 
		// window perspective, which is requested in assignment handout.
		// mouse is almost in the middle of the paddle.
		// height remains the same all time

		if ((e.getX() < WIDTH - PADDLE_WIDTH/2) && (e.getX() > PADDLE_WIDTH/2)) {
			baddle.setLocation(e.getX() - PADDLE_WIDTH/2, baddleY);
		}

	}
	// Creating the ball above the paddle :D
	private void ball(){
		ballY = (HEIGHT - BALL_RADIUS)/2;
		ball = new GOval(BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		add(ball, (WIDTH - BALL_RADIUS)/2, ballY);
	}
	public void startApp(){
		waitForClick();
		getVelocity();
		while (true) { 
			moveBall();
			//delay time, reduced as the player is about to end the game to increases it's difficult 
			pause(delay + reakCounter/25);
			if (reakCounter == 0){
				GLabel win = new GLabel("YOU WIN!!");
				add(win, (getWidth()- win.getWidth())/2, HEIGHT/2);
				
			}
		 } 
	}
	private void moveBall() {
		ball.move(vx, vy);
		/*
		 * top side reflection 
		 */
		if(ball.getY() - ball.getHeight()/2<0 ){
			vy = -vy;
			
			
		}
		/* 
		 * sides reflect
		 */
		else if(ball.getX() > WIDTH - ball.getHeight() || ball.getX()- ball.getHeight()/2 < 0){
			vx = -vx;
			
		}
		/*
		 * ending game if ball goes beyond the paddle, with 3 tries 
		 *  and restarting it b4 the 3 tries
		 */
		else if(ball.getY() > HEIGHT){
			endtrying++;
			if(endtrying >= 3){
				removeAll();
				GLabel end = new GLabel("YOU LOST!!");
				add(end, (getWidth()- end.getWidth())/2 , getHeight()/2);
			}
			// restart part
			else{
				removeAll();
				run();
			}
		}
		// getting what's on sides of the ball, and starting to handle it
		GObject row = collectSides();
		
		if (row == baddle){
			/*
			 * to test Y coordinates for ball and paddle
			 * println( baddle.getY()+ "|" +  ball.getY());
			 * turned out to be that there's 19 pixel between ball.y and paddle.y; fixing that with if
			 */
			
			if (ball.getY() > baddle.getY()- BALL_RADIUS ){
				vy = -vy;
				bounceClip.play();
				/* 
				 * glue issue fix, move the ball above the paddle, so that it doesn't reverse again
				 */
				if (row == baddle){
					ball.move(0, -PADDLE_HEIGHT);
				}
				
			}

		}
		/* here removing object which is brick, then subtracting the counter, also checking if
		 * bricks ended so it display final message "YOY WIN"
		 */
		else if (row != null){
			remove(row);
			reakCounter--;
			
			vy = -vy;
			
		}
		
		
	}
	// setting speed and randomness, though i'm not satisfied with it's randomness
	// but it's doing the job for now
	private void getVelocity(){
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
	    if (rgen.nextBoolean(0.5)) {
			vx = -vx; 
		}		
	}
	/* getting what's on the sides of our ball 
	 * changing x, y because we've a ball, not the midpoint.
	 */
	private GObject collectSides(){
		
		GObject obj =  getElementAt(ball.getX(), ball.getY());
		GObject obj2 = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		GObject obj3 = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		GObject obj4 = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		if(obj !=null){
			return obj;
		}
		else if(obj2 !=null){
			return obj2;
		}
		else if(obj3 !=null){
			return obj3;
		}
		else if(obj4 !=null){
			return obj4;
		}
		else{
			return null;
		}
		
	}

	// private instances
	
	private static int reakCounter = 0;
	private static double delay = 7.5;
	//Velocity
	private static double vx, vy;
	
	private static GRect reak ;
	private static GRect baddle;
	private static GOval ball;
	private static double ballY;
	private static double baddleY;
	private static double baddleX;

	
	// creating random instance 
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	// adding sound 
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
}