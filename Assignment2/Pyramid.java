/*
 * File: Pyramid.java
 * Name: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 25;
	
	public void run() {
		BuildBricks();
	}
	
	private void BuildBricks()
	{
		for (int row = 0; row < BRICKS_IN_BASE; row++){
			//calculating bricks in a row
			int bricks_in_row = BRICKS_IN_BASE - row;
			
			//number of bricks in a row = row
			for (int brickNum = 0; brickNum < 2; brickNum++){
				//1. Calculate the center
				//2. Calculate the starting point based on the center
				//3. Add the number of bricks * brick width to find this brick's location
				int x = getWidth()/2 - (BRICK_WIDTH * bricks_in_row) / 2 + BRICK_WIDTH * brickNum  ;
				
				// 
				// Calculate the vertical location of the brick based on the row
				//
				int y = getHeight() - BRICK_HEIGHT * (row+1);
						
				//
				// Draw the brick
				//
				GRect brick = new GRect( x , y , BRICK_WIDTH , BRICK_HEIGHT );
				add(brick);
			}
			
		}
		
	}
		
	
}
