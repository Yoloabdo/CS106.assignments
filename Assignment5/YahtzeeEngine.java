/*
 * File: YahtzeeEngine.java
 * ------------------------
 * This class represents the game engine
 */

import java.util.ArrayList;

import acm.util.*;

public class YahtzeeEngine implements YahtzeeConstants {

	/**
	 * Creates an instance of this class, representing a new game with the 
	 * player quantities and names
	 * @param nPlayers The number of players
	 * @param playerNames The name of the players
	 */
	public YahtzeeEngine(int nPlayers, String[] playerNames)
	{
		// Get player info
		this.nPlayers = nPlayers;
		this.playerNames = playerNames;

		// Initialize the game state matrixes
		scores = new int[nPlayers][N_CATEGORIES + 1];

		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j <= N_CATEGORIES; j++) {
				scores[i][j] = 0;
			}
		}		
	}

	/**
	 * A flag indicating if the game has ended
	 * @return Returns true if game is over
	 */
	public boolean isGameEnded()
	{
		return isGameEnded;
	}

	/**
	 * Resets the current dice values
	 */
	private void resetDices()
	{
		for (int i = 0; i < N_DICE; i++) {
			currentDiceValues[i] = 0;
		}
	}

	/**
	 * Checks the current game state to see if it's currently not over
	 */
	private void checkGameState()
	{
		if (playedCategories == N_SCORING_CATEGORIES) {
			isGameEnded = true;
		}
	}

	/**
	 * Calculates the scores for the bonus categories
	 * Pre-condition: The players must have played on all categories
	 */
	private void calculateBonusScores()
	{
		// Calculates scores for each player
		for (int i = 0; i < nPlayers; i++) {
			// Calculates the upper score
			int score = 0;
			for (int c = ONES; c <= SIXES; c++) {
				score += scores[i][c];
			}

			// Set the score back in the table and calculate the upper bonus
			scores[i][UPPER_SCORE] = score;
			scores[i][UPPER_BONUS] = (score >= 63) ? 35 : 0;

			// Calculates the lower score
			score = 0;
			for (int c = THREE_OF_A_KIND; c <= CHANCE; c++) {
				score += scores[i][c];
			}

			// Sets the lower score back the table
			scores[i][LOWER_SCORE] = score;

			// Calculates the total score
			scores[i][TOTAL] = scores[i][UPPER_SCORE] + scores[i][UPPER_BONUS] + scores[i][LOWER_SCORE];
		}
	}

	/**
	 * Determines the winner of the game
	 * Pre-condition: Game must be ended and scores has to be calculated
	 */
	private void computeWinner()
	{
		int lastScore = 0;
		for (int i = 0; i < nPlayers; i++) {
			if (scores[i][TOTAL] > lastScore) {
				winner = i + 1;
			}
		}
	}

	/**
	 * Starts a new turn, zeroing current dice values and setting the next player
	 * @return
	 */
	public int startNewTurn()
	{
		// Resets the dices
		resetDices();

		// This if is used to cycle through the current count of players
		// Also does operations necessary between each round of players
		currentPlayer++;
		if (currentPlayer > nPlayers) {
			currentPlayer = 1;
			playedCategories++;
		}

		// Checks the game state
		checkGameState();
		if (isGameEnded) {
			calculateBonusScores();
			computeWinner();
		}

		return currentPlayer;
	}

	/**
	 * Rolls the dices for the current player
	 * Pre-condition: Player must be starting the turn
	 * @return Returns an array of N_DICE rolled dices
	 */
	public int[] rollDices()
	{
		// For each dice, gets a random number from 1 to 6
		for (int i = 0; i < N_DICE; i++) {
			currentDiceValues[i] = randomGenerator.nextInt(1, 6);
		}
		return currentDiceValues;
	}

	/**
	 * Re-rolls the dices for turn
	 * Pre-condition: Player must be already rolled the dices
	 * @param dicesToRoll A boolean array, stating the indexes of dices that needs to be re-rolled
	 * @return Returns an array of N_DICE re-rolled dices
	 */
	public int[] rerollDices(boolean[] dicesToRoll)
	{
		// Checks what dices needs to be re-rolled
		for (int i = 0; i < N_DICE; i++) {
			if (dicesToRoll[i]) {
				currentDiceValues[i] = randomGenerator.nextInt(1, 6);
			}
		}

		return currentDiceValues;
	}

	/**
	 * Calculate a score based on the specified dice set
	 * Checks for each dice in the current turn, if they exists in the passed dice set, adds up to the score
	 * @param diceNumber 
	 * @param diceSet
	 * @return
	 */
	private static int calculateScoreForDiceSet(int[] dices, int[] diceSet)
	{
		// The current score
		int score = 0;

		// Pass-through each of the current dices
		for (int i = 0; i < dices.length; i++) {
			// Check if they match with one of the dices in the valid dice set
			for (int j = 0; j < diceSet.length; j++) {
				if (dices[i] == diceSet[j]) {
					score += dices[i];
				}
			}
		}

		return score;
	}

	/**
	 * Sums the values of all dices
	 * @param dices The dices array
	 * @return Return the sum of all the dices
	 */
	private static int sumAllDices(int[] dices)
	{
		int score = 0;
		for (int i = 0; i < dices.length; i++) {
			score += dices[i];
		}
		return score;
	}

	/**
	 * Calculates the score for the category considering the current dice set
	 * @param category The selected category
	 * @return returns the selected score
	 */
	private int calculateScoreForCategory(int[] dices, int category)
	{
		int score = 0;
		switch (category) {
			case ONES:
				score = calculateScoreForDiceSet(dices, dicesOnes);
				break;
			case TWOS:
				score = calculateScoreForDiceSet(dices, dicesTwos);
				break;
			case THREES:
				score = calculateScoreForDiceSet(dices, dicesThrees);
				break;
			case FOURS:
				score = calculateScoreForDiceSet(dices, dicesFours);
				break;
			case FIVES:
				score = calculateScoreForDiceSet(dices, dicesFives);
				break;
			case SIXES:
				score = calculateScoreForDiceSet(dices, dicesSixes);
				break;
			case FULL_HOUSE:
				// Fixed Score
				score = 25;
				break;
			case SMALL_STRAIGHT:
				// Fixed score
				score = 30;
				break;
			case LARGE_STRAIGHT:
				// Fixed score
				score = 40;
				break;
			case YAHTZEE:
				// Fixed score
				score = 50;
				break;
			case THREE_OF_A_KIND:
			case FOUR_OF_A_KIND:
			case CHANCE:
				// Just sum-up all the dice values
				score = sumAllDices(dices);
				break;
		}

		return score;
	}

	/**
	 * Find a kind set of specified in size in a set of dices
	 * @param dices Dices
	 * @param cards How many kinds must form the kind
	 * @param exceptKind 
	 * @return
	 */
	private static int findKind(int[] dices, int cards, int exceptKind)
	{
		int kindCount = 0;
		for (int i = 0; i < dices.length; i++) {
			// If an exception is set, then skip this kind
			if (exceptKind > 0 && dices[i] == exceptKind) {
				continue;
			}

			// Search for a kind set
			for (int j = i + 1; j < dices.length; j++) {
				if (dices[i] == dices[j]) {
					kindCount++;
					if (kindCount >= cards) {
						return dices[i];
					}
				}
			}
		}

		// Returns 0 if not found anything
		return 0;
	}

	/**
	 * Checks if the current dices fits on a kind category
	 * @param dices The current dices
	 * @param cardsInKind The quantity of cards to fill a kind
	 * @return Returns true if the category is valid 
	 */
	private static boolean checkKindCategory(int[] dices, int cardsInKind)
	{
		return (findKind(dices, cardsInKind, 0) > 0);
	}

	/**
	 * Checks if the current dices fits on a full house category
	 * @param dices The current dices
	 * @return Returns true if the category is valid
	 */
	private static boolean checkFullHouse(int[] dices)
	{
		// First search for a set of three dices of the same number
		int threeKind = findKind(dices, 3, 0);

		// Then, if we find it, we search for a set two dices of the same number
		return (threeKind > 0 && findKind(dices, 1, threeKind) > 0) ? true : false;
	}

	/**
	 * Returns the index of the smallest array element between p1 and p2 - 1
	 * @param array The array to check for
	 * @param p1 starting element index
	 * @param p2 ending element index
	 * @return The smallest array element index between the two
	 */
	private static int findSmallest(int[] array, int p1, int p2)
	{
		int smallestIndex = p1;
		for (int i = p1 + 1; i < p2; i++) {
			if (array[i] < array[smallestIndex]) smallestIndex = i;
		}
		return smallestIndex;
	}

	/**
	 * Exchanges the elements in an array at index positions p1 and p2.
	 * @param array The array to swap elements
	 * @param p1 Index of the first element
	 * @param p2 Index of the second element
	 */
	private static void swapElements(int[] array, int p1, int p2)
	{
		int temp = array[p1];
		array[p1] = array[p2];
		array[p2] = temp;
	}

	/**
	 * Sorts an integer array into increasing order. The implementation uses
	 * an algorithm called selection sort, which can be described informally
	 * in English as follows:
	 * 
	 * 	With your left hand, point at each element in the array in turn, starting
	 * 	at index 0. At each step in the cycle:
	 * 	
	 * 		1. Find the smallest element in the range between your left hand and the
	 * 		   end of the array, and point at that element with your right hand
	 * 
	 * 		2. Move that element into its correct index position by switching the
	 * 		   elements indicated by your left and right hands
	 * 
	 * @param array The array to be sorted
	 */
	private static void sort(int[] array) 
	{
		for (int lh = 0; lh < array.length; lh++) {
			int rh = findSmallest(array, lh, array.length);
			swapElements(array, lh, rh);
		}
	}

	/**
	 * Removes duplicates from an sorted array
	 * @param array The array to remove duplicates
	 * @return Retuns the array without the duplicate items
	 */
	private static int[] removeDuplicates(int[] array)
	{
		// An temporary ArrayList to hold values
		ArrayList<Integer> tmp = new ArrayList<Integer>();

		// For each item in the original array
		for (int i = 0; i < array.length; i++) {
			boolean foundMatch = false;
			for (int j = 0; j < tmp.size(); j++) {
				if (array[i] == tmp.get(j)) {
					foundMatch = true;
				}
			}

			// If no match is found in the temp array, then adds it
			if (!foundMatch) {
				tmp.add(array[i]);
			}
		}

		// Converts the ArrayList into an array
		int[] retArray = new int[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			retArray[i] = tmp.get(i);
		}

		return retArray;
	}

	/**
	 * Check if the current dices fits 
	 * @param dices The current dices
	 * @param size The size of the straight
	 * @return Returns true if the straight is valid
	 */
	private static boolean checkStraight(int[] dices, int size)
	{
		// First let's sort our elements and remove the duplicates
		sort(dices);
		int[] noDupsDices = removeDuplicates(dices);

		// If the size of array removing the duplicates is less than the required straight size
		// Then we assume it's not a possible to have a straight here
		if (noDupsDices.length < size) {
			return false;
		}

		// Then let's check for a sequence
		boolean isValid = false;
		for (int i = 0; i <= noDupsDices.length - size; i++) {
			// Here we'll count the current sequence size
			int seqSize = 0;

			// Notice that we check for dices.length - 1. This is because on each iteration of the loop
			//	we're going to check against the next number
			for (int j = i; j < (noDupsDices.length - 1); j++) {
				// Check if the next element is the same as the current element plus one
				// That way we've found the direct next number
				if ((noDupsDices[j] + 1) == noDupsDices[j + 1]) {
					seqSize++;
				} else {
					// If the last condition is not true, then the sequence is broke
					break;
				}
			}

			// Let's check if the sequence found is of the size expected
			if (++seqSize == size) {
				isValid = true;
				break;
			}
		}

		return isValid;
	}

	/**
	 * Checks if the selected category is valid
	 * @param dices the current dices
	 * @param category The category the user selected
	 * @return Returns true if the selected category is valid
	 */
	private static boolean checkCategory(int[] dices, int category)
	{
		switch (category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
			case CHANCE:
				return true;
			case THREE_OF_A_KIND:
				return checkKindCategory(dices, 3);
			case FOUR_OF_A_KIND:
				return checkKindCategory(dices, 4);
			case YAHTZEE:
				return checkKindCategory(dices, 5);
			case SMALL_STRAIGHT:
				return checkStraight(dices, 4);
			case LARGE_STRAIGHT:
				return checkStraight(dices, 5);
			case FULL_HOUSE:
				return checkFullHouse(dices);
		}

		return false;
	}

	/**
	 * Calculates the score for the selected category for the player
	 * @param category The category number
	 * @return Returns the score for the category, considering the current dice set
	 */
	public int selectCategory(int category)
	{
		// Calculates the score
		int score = 0;
		if (checkCategory(currentDiceValues, category)) {
			score = calculateScoreForCategory(currentDiceValues, category);
		}

		// Updates game state matrixes
		scores[currentPlayer - 1][category] = score;

		return score;
	}

	/**
	 * Gets the score for the player in the specified category
	 * @param player Number of the player
	 * @param category Code of the category
	 * @return Returns the score for the category
	 */
	public int getScoreForPlayerInCategory(int player, int category)
	{
		if ((player < 1 || player > nPlayers) || (category < 0 || category > TOTAL)) {
			return 0;
		}

		return scores[player - 1][category];
	}

	/**
	 * Gets the name of the player
	 * @param player Number of the player
	 * @return Returns the name of the player
	 */
	public String getPlayerName(int player)
	{
		return playerNames[player - 1];
	}

	/**
	 * Gets the number of the current winner player
	 * @return The number of the winner of the game
	 */
	public int getWinner()
	{
		return winner;
	}

	/**
	 * Gets the name of the winner of the game
	 * @return The name of the winner
	 */
	public String getWinnerName()
	{
		return playerNames[winner - 1];
	}

	/**
	 * Gets the score of the winner of the game
	 * @return
	 */
	public int getWinnerScore()
	{
		return scores[winner - 1][TOTAL];
	}


	/* Game state */
	private int nPlayers;
	private String[] playerNames;
	private int playedCategories = 0;			// Keeps track of how many categories players have already played 
	private int[][] scores;						// Keeps track of the players scores
	private boolean isGameEnded = false;
	private int winner;							// The player who has won

	/* Current turn state */
	private int currentPlayer = 0;
	private int[] currentDiceValues = new int[N_DICE];
	private RandomGenerator randomGenerator = RandomGenerator.getInstance();

	/* Score support */
	private int[] dicesOnes = { 1, 3, 5 };
	private int[] dicesTwos = { 2, 3, 5, 6 };
	private int[] dicesThrees = { 3, 5 };
	private int[] dicesFours = { 4, 5, 6 };
	private int[] dicesFives = { 5 };
	private int[] dicesSixes = { 6 };
}