/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {

	ArrayList <String> strlist = new ArrayList<String>();
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		read();
		return strlist.size();
	}
	
	private void read(){
		try {
			/* Open the file for reading. */
			BufferedReader hangmanWords = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			
			/* Read all the lines from the file, adding them to the array */
			
			while(true) {
				String line = hangmanWords.readLine();
				if(line == null) break;
				strlist.add(line);
			}
			
	  		
			/* Close the file. */
			hangmanWords.close();
		} catch (IOException e) {
			
		}
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strlist.get(index);
		}
}

