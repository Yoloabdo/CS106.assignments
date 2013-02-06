/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;


public class FindRange extends ConsoleProgram {
	public void run() {

		//reciving numbers in a list
		/* starting with intiating numbers as double to get best range 
		 * 
		 * smallest set to highest range to be compared through the process
		 * larger set to 0, if we're going to compare - numbers we should
		 * assign it to -smallest.
		 * 
		 */
		double starter;
		double smallest = 999999999;
		double larger = 0;
		/*for 1st input i used do-while to take the first number as 1st process
		 * no matter what, then while condition to check if it's not equal to 0
		 * by that way we have 1st entry then condition too be applied to next
		 * entries.
		 */
		do {
			starter = readDouble("? ");
			/*here we check if the numbers intered is smallest or larger than 
			 * our initial numbers.
			 * 
			 */
			if (starter > larger){
				larger = starter;
			}
			
			if (starter < smallest){
				if (starter != 0){
					smallest = starter;
				}
				
			}
		}while (starter != 0);
		//simple printing for the output 
		println("smallest: " + smallest);
		println("largest: " + larger);
		
		
		
	}
}

