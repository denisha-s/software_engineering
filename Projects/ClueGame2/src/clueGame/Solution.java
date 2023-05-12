package clueGame;

/* 
 * Solution Class: This class holds information about the solution 
 * It defines which room, person, and weapon is the solution to the game. 
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/29/2023
 * Sources: None
 */

public class Solution {
	private Card person, weapon, room;

	/*
	 * constructor
	 */
	public Solution(Card person, Card weapon, Card room) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
		
	}


	/*
	 * getters and setters
	 */
	public Card getPerson() {
		return person;
	}

	public void setPerson(Card person) {
		this.person = person;
	}

	public Card getWeapon() {
		return weapon;
	}

	public void setWeapon(Card weapon) {
		this.weapon = weapon;
	}

	public Card getRoom() {
		return room;
	}

	public void setRoom(Card room) {
		this.room = room;
	}

	/*
	 * prints solution hand as a String
	 */
	@Override
	public String toString() {
		return "Solution [person=" + person + ", weapon=" + weapon + ", room=" + room + "]";
	}

}
