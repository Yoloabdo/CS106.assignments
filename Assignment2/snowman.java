import acm.program.*;
import acm.graphics.*;

public class snowman extends GraphicsProgram {

	
	public void run(){
		
		
		// Defining place of the snowman
		int x = getWidth()/2;
		int y = getHeight()/4;
		
		//Head radious
		int radious = 45;
		// drawing Head first

		drawCircle(x , y , radious );

		// Eyes
		
		drawCircle(x - radious/4+2,  y - radious/4, 8 );
		drawCircle(x + radious/4-2,  y - radious/4, 8 );
		
		// Clearly it's nose
		
		nose(x,y);
		
		// Then mouth
		
		drawCircle(x + 2,  y + radious/4 +2, 12 );
		
		// Belly i guess
		drawCircle(x , y+(radious+radious/2) , radious*2 );
		
		//second Belly 
		drawCircle(x , y+(radious*4) , radious*3 );
		
		//cubes 
		drawCube(x - 5, y + radious/2+5, 15);
		drawCube(x - 5, y + radious/2+35, 15);
		drawCube(x - 5, y + radious/2+65, 15);




	}
	
	public void nose(int x,int y){
		
		y += 45/8;
		GLabel v = new GLabel("v");
		add (v, x, y);
	}
	public void drawCircle(int x, int y, int radious){
		GOval Head = new GOval(x - radious/2,y - radious/2, radious,radious);
		add(Head);
		
		
	}
	public void drawCube(int x, int y, int lenth){
		GRect Head = new GRect(x ,y, lenth,lenth);
		add(Head);
		
		
	}

}
