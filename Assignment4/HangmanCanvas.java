/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
	private double vx ;
	private double vy ;
	private double hy ;
	private double hx ;
	private String word = "";
	private GLabel dis_word = new GLabel("");

	


/** Resets the display so that only the scaffold appears */
	public void reset() {
		vx = getWidth()/2 - BEAM_LENGTH ;
		vy = 1.4*getHeight()/2;
		hx = getWidth()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH;
		add(new GLine(vx, vy, vx, vy - SCAFFOLD_HEIGHT));
		add(new GLine(vx, vy - SCAFFOLD_HEIGHT, hx, vy - SCAFFOLD_HEIGHT ));
		add(new GLine(hx, vy - SCAFFOLD_HEIGHT, vx+ BEAM_LENGTH,hy)); 
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		double vx = getWidth()/8;
		double vy = 1.6*(getHeight()/2);
		dis_word.setLabel(word);
		dis_word.setFont("SansSerif-bold-26");
		add(dis_word, vx, vy);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char h) {
		
		double vx = getWidth()/8;
		double vy = 1.8*(getHeight()/2);
		word += h;
		GLabel wrong = new GLabel(word);
		wrong.setFont("SansSerif-23");
		add(wrong, vx, vy);
		hman(word.length());
		
	}
	/**
	 * Displaying the hangman
	 */
	
	private void hman(int i){
		
		switch(i){
			case 1: head(); break;
			case 2: body(); break;
			case 3: leftArm(); break;
			case 4: rightArm(); break;
			case 5: leftLeg(); break;
			case 6: rightLeg(); break;
			case 7: rightfoot(); break;
			case 8: leftfoot(); break;
		}
		
	}
	

	private void leftfoot() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx -HIP_WIDTH, hy + BODY_LENGTH + LEG_LENGTH, 
				hx -HIP_WIDTH - FOOT_LENGTH , hy + BODY_LENGTH + LEG_LENGTH ));
	}

	private void rightfoot() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx +HIP_WIDTH, hy + BODY_LENGTH + LEG_LENGTH, 
				hx +HIP_WIDTH +  FOOT_LENGTH,hy + BODY_LENGTH + LEG_LENGTH ));
	}

	private void rightLeg() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx,hy + BODY_LENGTH, hx +HIP_WIDTH, hy + BODY_LENGTH));			
		add(new GLine(hx +HIP_WIDTH, hy + BODY_LENGTH, hx +HIP_WIDTH, hy + BODY_LENGTH + LEG_LENGTH));
	}

	private void leftLeg() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx,hy + BODY_LENGTH, hx -HIP_WIDTH, hy + BODY_LENGTH));
		
		add(new GLine(hx -HIP_WIDTH, hy + BODY_LENGTH, hx -HIP_WIDTH, hy + BODY_LENGTH + LEG_LENGTH));
	}

	private void rightArm() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx, hy + ARM_OFFSET_FROM_HEAD, hx - UPPER_ARM_LENGTH, hy + ARM_OFFSET_FROM_HEAD));
		add(new GLine(hx - UPPER_ARM_LENGTH, hy + ARM_OFFSET_FROM_HEAD,
				hx - UPPER_ARM_LENGTH, hy + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH  ));
	}

	private void leftArm() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx, hy + ARM_OFFSET_FROM_HEAD, hx + UPPER_ARM_LENGTH, hy + ARM_OFFSET_FROM_HEAD));
		add(new GLine(hx + UPPER_ARM_LENGTH, hy + ARM_OFFSET_FROM_HEAD,
				hx + UPPER_ARM_LENGTH, hy + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH  ));
	}

	private void body() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GLine(hx,hy, hx,hy + BODY_LENGTH ));
	}

	private void head() {
		vy = 1.4*getHeight()/2;
		hy = vy - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		hx = getWidth()/2;
		add(new GOval(hx - HEAD_RADIUS/2, hy - HEAD_RADIUS, HEAD_RADIUS, HEAD_RADIUS));
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 240;
	private static final int BEAM_LENGTH = 104;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 114;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 50;
	private static final int FOOT_LENGTH = 28;

}

