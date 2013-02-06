/*
 * File: PythagoreanTheorem.java
 * Name: Abdulrhman Eaita
 *
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		double a = readDouble("Enter a: ");
		double b = readDouble("Enter b: ");
		double c = a*a + b*b;
		println("c: " + Math.sqrt(c));
	}
}
