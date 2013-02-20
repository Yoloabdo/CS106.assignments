/*
 * File: Artistry.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import java.awt.Color;

import acm.program.*;
import acm.graphics.*;
public class Artistry extends GraphicsProgram {
	public void run() {
		/* You fill this in.  Remember that you must have
		 * 
		 * 1. At most twenty GObjects,
		 * 2. At least one filled object,
		 * 3. At least two different colors of objects, and
		 * 4. At least three different types of objects (not
		 *    counting the GLabel you use to sign your name).
		 * 
		 * Also, be sure to sign your name in the bottom-right
		 * corner of the canvas.
		 */
		DrawAcar();
		addArtist();
		
	}
	//artist name
	private void addArtist(){
		GLabel label = new GLabel("Artistry by Abdulrhman");
		double x = (getWidth() - label.getWidth());
		double y = (getHeight() - label.getAscent());
		label.setLocation(x, y);
		add(label);
	}
	private void DrawAcar(){
		double cx = getWidth()/2;
		double cy = getHeight()/2;
		double carWidth = 300;
		double carHeight = 150;
		double Couch = 25;
		double window = 15;
		double doorH = 80;
		double doorW = 35;
		drawRectangle(cx - carWidth/2, cy - carHeight/2, carWidth, carHeight, Color.BLUE  );
		drawRectangle(cx - carWidth/2 + 10, cy - carHeight/4, window, window, Color.yellow  );
		drawRectangle(cx - carWidth/2 + 40, cy - carHeight/4, window, window, Color.yellow  );		
		drawRectangle(cx - carWidth/2 + 70, cy - carHeight/4, window, window, Color.yellow  );
		drawRectangle(cx - carWidth/2 + 100, cy - carHeight/4, window, window, Color.yellow  );
		drawRectangle(cx  , cy - carHeight/4, doorW, doorH, Color.black  );
		drawRectangle(cx + carWidth/2 - 40, cy - carHeight/4, window, window, Color.yellow  );		
		drawRectangle(cx + carWidth/2 - 70, cy - carHeight/4, window, window, Color.yellow  );
		drawRectangle(cx + carWidth/2 - 100, cy - carHeight/4, window, window, Color.yellow  );
		drawCircle(cx + carWidth/4 , cy + carHeight/2, Couch, Color.black);
		drawCircle(cx - carWidth/4 , cy + carHeight/2, Couch, Color.black);

	}
	/*
	* This method draws a general rectangle with its top left
	* at position x,y with a specified width, height and color.
	*/
	private void drawRectangle(double x, double y,
	double width, double height, Color c){
		GRect rect = new GRect(x,y,width, height);
		rect.setFilled(true);
		rect.setColor(c);
		add(rect);
	}
	/*
	* This method draws a general circle centered at (cx,cy),
	* with a given radius r and a Color c.
	*/
	private void drawCircle(double cx, double cy, double r, Color c){
		double x = cx - r;
		double y = cy - r;
		GOval circle = new GOval(2*r, 2*r);
		circle.setColor(c);
		circle.setFilled(true);
		add(circle, x, y);
	}
}
