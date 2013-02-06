/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

		public void run(){
			while(frontIsClear() | leftIsClear()){
				if(frontIsClear()){
					SMK();
				}
				else{
					SMK();	
					break;
				}
			}
				
		}
		private void SMK(){
			turnLeft();
			build();
			turnLeft();
			turnLeft();
			jumb();
			turnLeft();
			if (frontIsClear()){
				jumb2();
			}
			
			
			
		}
		private void jumb2(){
			for(int x = 0; x <4; x++)
				if(frontIsClear()){
				move();
				}
		}
		private void jumb(){
			while(frontIsClear()){
				move();
			}
		}
		private void build(){
			while(frontIsClear()){
				if(!beepersPresent()){
					putBeeper();		
				}
				move();
			}
			if(!beepersPresent()){
				putBeeper();		
			}
		}

		
		
	}

	