/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */
 
import acm.graphics.*;
import acm.program.*;
import java.awt.*;
 
public class Target extends GraphicsProgram {
	// defining radius for circles
	private static final double r1 = 72;
	private static final double r2 = 72*.65;
	private static final double r3 = 72*.3;
	public void run() {
		// Getting it in the center
		
		double x = getWidth()/2;
		double y = getHeight()/2;
		
		// using the method in 3 circles
		drawCircle(x, y, r1, Color.red);
		drawCircle(x, y, r2, Color.WHITE);
		drawCircle(x, y, r3, Color.red);
		
	}
	
	// method to create a circle
	private void drawCircle(double cx, double cy, double r, Color c){
		double x = cx - r;
		double y = cy - r;
		GOval circle = new GOval(2*r, 2*r);
		circle.setColor(c);
		circle.setFilled(true);
		add(circle, x, y);
	}

}