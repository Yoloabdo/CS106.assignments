/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;


public class NameSurferEntry implements NameSurferConstants {
	private int[] rankings = new int[NDECADES];
	private String Name;

	
	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {

		//gets the name
		int nameEnd = line.indexOf(" ");
		Name = line.substring(0, nameEnd);

		//gets the popularity ranking and puts it into an array
		String numbers = line.substring(nameEnd + 1);
		StringTokenizer tokenizer = new StringTokenizer(numbers);
		for(int count = 0; tokenizer.hasMoreTokens(); count++) {
			rankings[count] = Integer.parseInt(tokenizer.nextToken());
		}
	}


	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		
		return Name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	*/
	public int getRank(int decade) {
		switch(decade){
		case 1900: return rankings[0];
		case 1910: return rankings[1];
		case 1920: return rankings[2];
		case 1930: return rankings[3];
		case 1940: return rankings[4];
		case 1950: return rankings[5];
		case 1960: return rankings[6];
		case 1970: return rankings[7];
		case 1980: return rankings[8];
		case 1990: return rankings[9];
		case 2000: return rankings[10];
		case 2010: return rankings[11];
		default: return 0;
		}
		
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		
		return "\"" + Name + Arrays.toString(rankings) + "\"";
	}
}

