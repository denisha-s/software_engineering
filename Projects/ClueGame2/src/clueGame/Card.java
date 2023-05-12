package clueGame;

/* 
 * Card Class: This class holds information about the cards 
 * including its name and cardType. Card is comparable. 
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/29/2023
 * Sources: None
 */

public class Card {
	private String cardName;
	private CardType cardType;
	
	/*
	 * constructor
	 */
	public Card(String cardName, CardType type) {
		super();
		this.cardName = cardName;
		this.cardType = type;
	}


	/*
	 * to compare two cards
	 */
	public boolean equals(Card target) {
		if (this.cardName == target.cardName) {
			return true;
		}
		else {
			return false;
		}
		
	}


	/*
	 * getters and setters
	 */
	public Object getType() {
		return cardType;
	}
	
	public String getName() {
		return cardName;
	}


	/*
	 * prints card as a string
	 */
	@Override
	public String toString() {
		return cardName;
	}
	
	
}
