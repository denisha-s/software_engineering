package clueGame;

/*
 * BadConfigFormatException class: This class is an exception 
 * (extends Exception) to indicate data files have bad format.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/3/2023
 * Sources: None
 */

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() {
		super("File Formatting Error");
	}
	public BadConfigFormatException (String invalidCell, String error) {
		super("Invalid cell" + invalidCell + "contains error: " + error);
		
	}
	public BadConfigFormatException (String message) {
		super(message);
	}

}
