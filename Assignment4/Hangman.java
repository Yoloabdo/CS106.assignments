/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private HangmanCanvas canvas;
	private HangmanLexicon HL = new HangmanLexicon();
	RandomGenerator rgen = new RandomGenerator();
	private String S_word = HL.getWord(rgen.nextInt(0, HL.getWordCount()-1));
	private int trials = 8;
	private String word = replaceDashes(S_word);
	private String wrong = "";

	public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    	
    }
    public void run() {
   	
    	canvas.reset();

    	println("Welcome to Hangman!");
  

    	// Loop for making it 8 trails
		while(trials >0){
			
			println("The word now looks like this: " + word);
			canvas.displayWord(word);
			println("You have" + trials + " guesses left.");
			String test = readLine("Your guess: ");
			test = test.toUpperCase();
			char h= test.charAt(0);
			if(checkIfChInString(h, S_word)){
				if(checkIfChInString(h, word)){
					println("it's already guessed!");
					continue;
				}
		    	println("That guess is correct.");
		    	replaceDashWithChar(h);
		    	
		    	
			}
			else{
				println("There are no " + h + "'s in the word.");
				if(checkIfChInString(h, wrong )){
					println("you can't enter the same letter again!");
					continue;
				}
				wrong += h;
				canvas.noteIncorrectGuess(h);
				trials--;
			}
			if(!checkIfChInString('-', word)){
				println("You win!");
				canvas.displayWord(word);
				break;
			}
			if(trials == 0){
				println("You're completely hung.");
				println("The word was: "+ S_word);
				println("You lose!");
			}
		}
	}
    
    /**
     * This method checks if Char is in a string
     * @param Char x , the word or String
     * @return Boolean
     */
    private Boolean checkIfChInString(char x, String w){
    	for(int i =0; i < w.length(); i++){
    		if(x == w.charAt(i)){
    			return true;
    		}
    	} 	
    	return false;
    }
    /**
     * 
     * @param h
     * @return
     */
   
    private String replaceDashWithChar(char h){
    	String result = "";
    	for(int i =0; i < S_word.length(); i++){
    		if(h == S_word.charAt(i)){
    			result = word.substring(0, i) + h + word.substring(i+1, S_word.length());
    			word = result;
    		}
    	}
    	return result;
    }
    private String replaceDashes(String word){
    	String result = "";
    	for(int i =0; i < word.length(); i++){
    		result += "-";
    	}
    	return result;
    }

}
