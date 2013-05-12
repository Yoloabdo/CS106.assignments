/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.awt.ActiveEvent;
import java.util.ArrayList;
import java.util.Arrays;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	
	
	
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		intScore();
		for(int trials = 1; trials<= 3*nPlayers; trials++){
			for(int i = 0; i < nPlayers; i++ ){
				display.printMessage(playerNames[i]+"'s turn! Click \"Roll Dice\" button to roll the dice.");
				firstRoll(i);
				display.printMessage("Select the dice you wish to roll again and click \"Roll Dice\"");
				secondRoll(i);
				display.printMessage("Select the dice you wish to roll again and click \"Roll Dice\"");
				secondRoll(i);
				display.printMessage("Select a cateorgy for this roll.");
				
				//resetting the score.
				score = 0;
				// waiting for the category and doing the necessary job.  
				while(true){
					category = display.waitForPlayerToSelectCategory();
					if(scores[i][category] == -1){
						if(cateorgyChecker()){
								display.updateScorecard(category, i, score);
								}
							else{
								score = 0;
								display.updateScorecard(category, i, score);
							}
							//Updating upper Scores
							upperScore(i);
							
							//Updating Lower scores.
							scores[i][category] = score;
							display.updateScorecard(TOTAL, i, totalScore(i));
							lowerScore(i);
							break;
						}
					
					else{
						display.printMessage("Select an empty cateorgy for this roll.");
					}
				}
			}
		}
		 calculateWinner();
	}
	
	/**
	 * here's checking the category that player choose after rolling, it also sits the score if it's internal functions returned true
	 * with no input, because it uses category as it's local var in the class, so no need to pass it again.
	 * @return boolean + sits score
	 */
	private boolean cateorgyChecker(){
		if (category == ONES || category == TWOS || category == THREES || category == FOURS || category == FIVES || category == SIXES){
				
			if(checkNloop(category+1)){
				int sum = 0;
				for(int x =0; x<rolledDices.length; x++){
					if(rolledDices[x] == category +1){
							sum += rolledDices[x];
						}
				 }
					score = sum;
					return true;
			}
			
		}
		
		if (category == THREE_OF_A_KIND){
			if (threeOfKind()){
				//score = set internally.
				return true;
			}
		}
			
		
		if (category == FOUR_OF_A_KIND){
			if(fourOfKind()){
				//score = set internally.
				return true;
			}
		}
		if (category == FULL_HOUSE){
			if(fullhouse()){
				score = 25;
				return true;
			}
		}
		if (category == SMALL_STRAIGHT){
			if(smallStright()){
				score = 30;
				return true;
			}
		}
		if (category == LARGE_STRAIGHT){
			if(largeStright()){
				score = 40;
				return true;
			}
		}
		if(category == YAHTZEE){
			if(yahtzee()){
				score = 50;
				return true;
			}
		}
		if(category == CHANCE){
			score = score();
			return true;
		}
		
		
			
		
		return false;
	}
	
	/**
	 * here's small functions that check your input in category 
	 * @return boolean
	 */
	
	
	
	private boolean yahtzee(){
		int sum = 0;
		for(int i = 0; i < rolledDices.length; i++){
			if(rolledDices[0] == rolledDices[i]){
				sum +=1;
			}
		}
		if (sum == 5) return true;
		else return false;
	}
	
	private boolean largeStright(){
		int[] x = rolledDices;
		Arrays.sort(x);
		if(x[0] == x[1] -1 && x[1] == x[2]-1 && x[2] == x[3]-1 &&  x[3] == x[4] - 1 ){
				return true;
			}
		
		return false;
	}
	
	private boolean smallStright(){
		int[] x = rolledDices;
		Arrays.sort(x);
		if(x[0] == x[1] -1 && x[1] == x[2]-1 && x[2] == x[3]-1 ){
			return true;
		}
		if(x[1] == x[2] -1 && x[2] == x[3]-1 && x[3] == x[4]-1 ){
			return true;
		}
		
		return false;
	}
	
	private boolean threeOfKind(){
		int[] x = rolledDices;
		Arrays.sort(x);
		for(int i = 0; i < x.length; i++){
			if(x[i] == x[i+1] && x[i+1] == x[i+2]){
				score = 3* x[i];
				return true;
			}
		}
		 return false;
	 }
	
	private boolean fourOfKind(){
		int[] x = rolledDices;
		Arrays.sort(x);
		for(int i = 0; i < 2; i++){
			if(x[i] == x[i+1] && x[i+1] == x[i+2] && x[i+2] == x[i+3]){
				score = 4* x[i];
				return true;
			}
		}
		 return false;
	 }
	
	
	private boolean fullhouse(){
		int[] x = rolledDices;
		Arrays.sort(x);
		if(x[0] == x[1] && x[2] == x[3] && x[3] == x[4] ){
			return true;
		}
		if(x[0] == x[1] && x[1] == x[2] && x[3] == x[4]){
			return true;
		}
		 return false;
	 }
	

	 private boolean checkNloop(int checked){
		 for(int i =0; i<rolledDices.length; i++){
				if(rolledDices[i] == checked){
					return true;
				}
		 }
		 return false;
	 }
	
	/**
	 * rolling the dices in the game, first then second then third which handled as second one.
	 * @param player
	 */
	
	private void firstRoll(int player){
		
		display.waitForPlayerToClickRoll(player);
		
		for(int i =0; i<rolledDices.length; i++){
			rolledDices[i] = rd.nextInt(1, 6);
		}
		display.displayDice(rolledDices);
		
	}
	private void secondRoll(int player){
		display.waitForPlayerToSelectDice();
		for(int i =0; i<rolledDices.length; i++){
			if(display.isDieSelected(i)){
				rolledDices[i] = rd.nextInt(1, 6);
			}
		}
		display.displayDice(rolledDices);
	}
	
	
	/**
	 * Collecting scores functions 
	 * @return int
	 * 
	 */
	
	
	
	private int score(){
		int sumScore = 0;
		for(int i =0; i < rolledDices.length; i++){
			sumScore += rolledDices[i];
		}
		return sumScore;
		
	}
	private int totalScore(int player){
		int sumScore = 0;
		for(int j =0; j < scores[0].length; j++){
			if(scores[player][j] != -1){
			sumScore += scores[player][j];
			}
		}
		scores[player][15] = sumScore;
		return sumScore;
		
	}
	private void intScore(){
		for(int i =0; i <scores.length; i++){
			for(int j = 0; j < scores[0].length; j++){
				scores[i][j] = -1;
			}
		}
	}
	private boolean upperScoreChecker(int player){
		for(int j =0; j < 6; j++){
			if(scores[player][j] == -1){
			return false;
			}
		}
		return true;
	}
	
	private void upperScore(int player){
		int sumScore = 0;
		if(upperScoreChecker(player)){
			for(int j =0; j < 6; j++){
				if(scores[player][j] != -1){
				sumScore += scores[player][j];
				}
			}
			if(scores[player][UPPER_SCORE] == -1) display.updateScorecard(UPPER_SCORE, player, sumScore);
			if(sumScore >=63) scores[player][UPPER_BONUS] = 35;
		}
		
	}
	private boolean lowerScoreChecker(int player){
		for(int j =8; j < 15; j++){
			if(scores[player][j] == -1){
			return false;
			}
		}
		return true;
	}
	private void lowerScore(int player){
		int sumScore = 0;
		if(lowerScoreChecker(player)){
			for(int j =8; j < 15; j++){
				sumScore += scores[player][j];
				
			}
			if(scores[player][LOWER_SCORE] == -1) display.updateScorecard(LOWER_SCORE, player, sumScore);
			
			
		}
	}

	/* when game end, and all the final scores have been added up. 
	 * Calculates which player has the highest score and what the highest score is 
	 * and prints that information in a message at the very end of the game.*/
	private void calculateWinner() {
		int winningScore = 0;
		int winningPlayerNumber = 0;
		for(int i = 0; i < nPlayers; i++) {
			int x = scores[i][TOTAL-1];
			if( x > winningScore) {
				winningScore = x;
				winningPlayerNumber = i;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winningPlayerNumber] + ", you're the winner with a total score of " + winningScore + "!");
	}

		
	/* Private instance variables */
	private static int nPlayers;
	private int[][] scores	= new int[4][16];
	private int category;
	int score ;
	private int[] rolledDices = new int[N_DICE];
	
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rd = new RandomGenerator();
}
