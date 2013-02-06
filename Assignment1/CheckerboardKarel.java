/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run(){
		while(leftIsClear()| frontIsClear()){
			beepers();
		}
		
		
	}
	private void beepers(){
		putBeeper();
		if(frontIsClear()){
			move();
			if(frontIsClear()){
				move();
			}
			else{
				turnAway();
			}
		}
		else{
			turnAway();
		}
	}
	private void turnAway(){
		turnAround();
		while(frontIsClear()){
			move();
		}
		turnRight();
		if(beepersPresent()){
			if(rightIsClear()){
				move();
				turnRight();
				move();
			}
		}
		else{
			if(rightIsClear()){
				move();
				turnRight();
			}
		}
	}
}
