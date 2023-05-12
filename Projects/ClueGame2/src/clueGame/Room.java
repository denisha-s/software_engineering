package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Room Class: This class holds information about all the rooms, such as 
 * the location of the center and label, and the room name.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/3/2023
 * Sources: None
 */

public class Room {
	private String name;
	private BoardCell centerCell, labelCell;
	private boolean isTraversable;
	private char hasSecretPassage;
	private Set<BoardCell> myCells = new HashSet<BoardCell>();

	public Room(String name, BoardCell centerCell, BoardCell labelCell) {
		super();
		this.name = name;
		this.centerCell = centerCell;
		this.labelCell = labelCell;
	}
	
	/**
	 * draw: draws the name of each room based on the label cell specified in loading setup
	 * @param Graphic g
	 * @param int x
	 * @param int y
	 */
	public void drawLabel(Graphics g, int x, int y) {
		g.setColor(Color.BLUE);
		g.drawString(name, x, y);
	}
	
	public String getName() {
		return name;
	}
	public BoardCell getLabelCell() {
		return labelCell;
	}
	public BoardCell getCenterCell() {
		return centerCell;
	}

	//Getter and setter for isTraversable
	public boolean getTraversable() {
		return isTraversable;
	}
	public void setTraversable() {
		this.isTraversable = true;
	}

	//Getter and setter for secretPassage
	public char getHasSecretPassage() {
		return hasSecretPassage;
	}
	public void setHasSecretPassage(char hasSecretPassage) {
		this.hasSecretPassage = hasSecretPassage;
	}

}
