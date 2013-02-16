/*
 * File: Hailstone.java
 * Name: Abdulrhman Eaita
 * 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		
		int hailstone = readInt("Enter a number: ");
		//Counter
		int x = 0;
		
		while (hailstone >= 0){
			
			if (hailstone % 2 == 0){
				//even number
				println(hailstone + " is even so i take half: " +
						hailstone / 2);
				hailstone = hailstone / 2;
				x++;
			}
			else{
				//Odd number
				if (hailstone == 1){
					break;
				}
				println(hailstone + " is odd, so i make 3n + 1: " +
						3*hailstone + 1);
				hailstone = 3*hailstone + 1;
				x++;
			}
		
		}
		println("The process took "+ x + " to reach 1");

	}
	
}

