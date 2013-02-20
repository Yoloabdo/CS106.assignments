/** From the Book Chapter2
 * Drawing A house 
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class House extends GraphicsProgram {
	
	public void run(){
		double x = getWidth()/2;
		double y = getHeight()/2;
		
		//Main building
		int width = 250;
		int height = 150;
		GRect Home = new GRect(x - width/2, y -height/2, width, height);
		add(Home);
		
		//adding windows
		int winL = 40;
		GRect win1= new GRect(x - width/3, y - height/4, winL, winL );
		GRect win2= new GRect(x + width/6, y - height/4, winL, winL );
		add (win1);
		add (win2);
		
		// Adding Door
		int doorH = 75;
		int doorW = 40;
		GRect door = new GRect(x - width/10, y ,doorW, doorH);
		add(door);
		
		// adding door key
		int radiouss = 8;
		GOval key = new GOval(x,y + doorH/2,radiouss,radiouss);
		add(key);
		//roof
		add(new GLine(x - width/2,y -height/2,x,y/4));
		add(new GLine(x + width/2,y -height/2,x,y/4));
		}		
		

}
