/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends Karel {
	
	public void run(){
			turnRight();
			move();
			turnLeft();
			move();
			move();
			move();
			pickBeeper();
			turnBack();
			move();
			move();
			move();
			turnRight();
			move();
			
	}
	
	private void turnBack(){
		turnLeft();
		turnLeft();
	}

	private void turnRight(){
		turnLeft();
		turnLeft();
		turnLeft();
		
	}

}
