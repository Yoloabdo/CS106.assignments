import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class hello extends GraphicsProgram {	
	public void run() {
		GOval mo = new GOval(25, 60, 43, 73);
		GOval lo = new GOval(70, 40, 43, 73);
		mo.setColor(Color.BLUE);
		add (mo);
		add (lo);
		add (mo);
	}
}
