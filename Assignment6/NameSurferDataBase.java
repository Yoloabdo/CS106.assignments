import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import acm.util.ErrorException;



/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	private NameSurferEntry Entry;
	private HashMap<String, NameSurferEntry> namesDB = new HashMap<String, NameSurferEntry>();
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		try {
			/* Open the file for reading. */
			BufferedReader NameSurf = new BufferedReader(new FileReader(filename));
			
			/* Read all the lines from the file, adding each to hash map implementing NameSurferEntry. */
			while(true) {
				String line = NameSurf.readLine();
				if(line == null) break;
				Entry = new NameSurferEntry(line);
				namesDB.put(Entry.getName(), Entry);
				
			}
	  		
			/* Close the file. */
			NameSurf.close();
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		
		name = name.toLowerCase();
		Character x = name.charAt(0);
		Character y = Character.toUpperCase(x);
		name = name.replace(x, y);
		if(namesDB.containsKey(name)){
			return namesDB.get(name);
		}
		return null;
	}
	
}

