package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/* 
 * Player Class: This class holds the information for the player. 
 * It also updates the player's hand when the cards are dealt.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/29/2023
 * Sources: None
 */

public abstract class Player {
	//protected static Board board;
	protected String name, color, type;
	protected int row, column;
	protected BoardCell location;
	protected ArrayList<Card> hand = new ArrayList<Card>();
	protected Set<Card> seenCards = new HashSet<Card>();
	protected boolean finishFlag;
	protected int currentOffset = 0;

	public Player(String name, String color, String type, BoardCell location) {
		super();
		this.name = name;
		this.color = color;
		this.type = type;
		this.hand = new ArrayList<Card>();
		this.location = location;
	}
	
	public abstract BoardCell doMove(Set<BoardCell> target, Board board);

	/*
	 * add a dealt card to the players hand
	 */
	public void updateHand(Card card) {
		hand.add(card);
	}
	

	/*
	 * add a card to seenCards list if they have seen it
	 */
	public void updateSeen(Card seenCard) {
		this.seenCards.add(seenCard);
	}
	
	/*
	 * if another player makes a suggestion, a player can disprove it
	 */
	public Card disproveSuggestion(Card person, Card weapon, Card room) {
		Random random = new Random();
		//create a list of all the cards you can disprove
		ArrayList<Card> canDisprove = new ArrayList<Card>();
		if (hand.contains(person)) {
			canDisprove.add(person);
		}
		if (hand.contains(weapon)) {
			canDisprove.add(weapon);
		}
		if (hand.contains(room)) {
			canDisprove.add(room);
		}
		//if you pick a random from the list,
		//if there is more than one it will pick a random one
		//if there is one, it will simply return that one card that you can disprove
		if (canDisprove.size() > 0) {
			return canDisprove.get(random.nextInt(canDisprove.size()));
		}
		
		//if you cannot disprove, return null.
		return null;
	}
	
	/**
	 * draw: the circle for each player with corresponding color
	 * @param Graphic g
	 * @param int currCol
	 * @param int currRow
	 * @param int cellWidth
	 * @param int cellHeight
	 */
	public void draw(Graphics g, int currCol, int currRow, int cellWidth, int cellHeight, int timesToOffset) {
		//set values used to create circles
		int x = currCol * cellWidth; 
		int y = currRow * cellHeight;
		int width = cellWidth;
		int height = cellHeight;
		
		x += 5*timesToOffset;
		
		g.setColor(stringToColor(this.color));
		g.fillOval(x, y, width, height);
		
		
			
	}
	
	/**
	 * stringToColor: changes the players string type color to a Color type.
	 */
	private Color stringToColor (String colorString) {
		Color color = Color.white;
		switch (colorString) {
		case "Pink":
			color = Color.pink;
			break;
		case "Blue":
			color = Color.blue;
			break;
		case "Red":
			color = Color.red;
			break;
		case "Green":
			color = Color.green;
			break;
		case "Orange":
			color = Color.orange;
			break;
		case "Magenta":
			color = Color.magenta;
			break;
		}
		return color;
	}

	
	/*
	 * getters and setters
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}

	public BoardCell getLocation() {
		return location;
	}
	public void setLocation(BoardCell location) {
		this.location = location;
	}


	public ArrayList<Card> getCards() {
		return hand;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Set<Card> getSeenCards() {
		return seenCards;
	}
	
	public void addSeenCard(Card card) {
		if (!seenCards.contains(card)) {
			this.seenCards.add(card);
		}
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isFinishFlag() {
		return finishFlag;
	}

	public void setFinishFlag(boolean finishFlag) {
		this.finishFlag = finishFlag;
	}

//	public void setBoard (Board board) {
//		this.board = board;
//	}

	/*
	 * prints player as a string
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + ", color=" + color + ", type=" + type + ", location=" + location + "]";
	}

	protected abstract Solution createSuggestion(Board board);

	
	
	
	


	
}
