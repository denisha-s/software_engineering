package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import experiment.TestBoardCell;

/*
 * BoardCell Class: This class is used to define a single cell on the game board.
 * It returns the adjacency list for a cell, can indicate a cell is part of a room, 
 * doorway, walkway, etc. 
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/3/2023
 * Sources: None
 */

public class BoardCell extends JPanel {
	private Board board;
	private int row, col;
	private char initial;
	private String label;
	private char secretPassage;
	private DoorDirection doorDirection;
	private boolean roomLabel = false;
	private boolean roomCenter = false;
	private boolean isRoom, isOccupied, isDoorway, isWalkway, isCloset, isSecretPassage = false;
	private Set<BoardCell> adjList = new HashSet<BoardCell>();
	private Rectangle rect;

	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	/**
	 * draw: draws each of the cells based on width and height.
	 * @param Graphics g
	 * @param int cellWidth
	 * @param int cellHeight
	 */
	public void draw(Graphics g, int cellWidth, int cellHeight, Board board) {
		//set the values to draw the rectangle. 
		int x = this.col * cellWidth; 
		int y = this.row * cellHeight;
		int width = cellWidth;
		int height = cellHeight;
		int	doorWidth = 5;
		int doorHeight = 5;
		
		Set<BoardCell> targets = board.getTargets();
		Map<Character, Room> roomMap = board.getRoomMap();
		
		setRect(new Rectangle(x, y, width, height));
		
		//draw the walkways
		if  (this.getWalkway() && !this.isDoorway()) {
			super.repaint();
			super.paintComponent(g);
			if (board.getTargets().contains(this) && board.getHuman().isTurn()) {
				g.setColor(Color.CYAN);
			}
			else {
				g.setColor(Color.YELLOW);
			}
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);

		}
		//draw the closets
		else if (this.initial == 'X') {
			super.repaint();
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(x, y, width, height);

		}
		//draw the doorways
		else if (this.isDoorway) {
			switch (this.doorDirection) {
			case LEFT:
				super.paintComponent(g);
				if (board.getTargets().contains(this) && board.getHuman().isTurn()) {
					g.setColor(Color.CYAN);
				}
				else {
					g.setColor(Color.YELLOW);
				}
				g.fillRect(x, y, width+10, height);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x, y, doorWidth, height);
				break;
			case RIGHT:
				super.paintComponent(g);
				if (board.getTargets().contains(this) && board.getHuman().isTurn()) {
					g.setColor(Color.CYAN);
				}
				else {
					g.setColor(Color.YELLOW);
				}
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x+(width-doorWidth), y, doorWidth, height);
				break;
			case UP:
				super.paintComponent(g);
				if (board.getTargets().contains(this) && board.getHuman().isTurn()) {
					g.setColor(Color.CYAN);
				}
				else {
					g.setColor(Color.YELLOW);
				}
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x, y, width, doorHeight);
				break;
			case DOWN:
				super.paintComponent(g);
				if (board.getTargets().contains(this) && board.getHuman().isTurn()) {
					g.setColor(Color.CYAN);
				}
				else {
					g.setColor(Color.YELLOW);
				}
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, width, height);
				g.setColor(Color.BLUE);
				g.fillRect(x, y+(height-doorHeight), width, doorHeight);
				break;
			}
		}
		//draw the rooms
		else {
			super.repaint();
			super.paintComponent(g);
			if (board.getTargets().contains(this) && board.getHuman().isTurn()) {
				g.setColor(Color.CYAN);
				g.fillRect(x, y, width, height);
			}			
			else {
				g.setColor(Color.GRAY);
				g.fillRect(x, y, width, height);
			}
		}
	}

	public void addAdj(BoardCell adj) {
		adjList.add(adj);
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Set<BoardCell> getAdjList() {
		return adjList;
	}

	public void setRoom(boolean b) {
		isRoom = b;
	}

	public boolean isRoom() {
		return isRoom;
	}

	public void setOccupied(boolean b) {
		isOccupied = b;
	}

	public boolean getOccupied() {
		return isOccupied;
	}

	public void setRoomLabel(boolean roomLabel) {
		this.roomLabel = roomLabel;
	}

	public boolean isLabel() {
		return roomLabel;
	}

	public void setRoomCenter(boolean roomCenter) {
		this.roomCenter = roomCenter;
	}

	public boolean isRoomCenter() {
		return roomCenter;
	}

	public boolean isDoorway() {
		return isDoorway;
	}

	public void setDoorway(boolean doorway) {
		this.isDoorway = doorway;
	}

	public void setDoorDirection(DoorDirection direction) {
		this.doorDirection = direction;
	}

	public DoorDirection getDoorDirection() {
		return this.doorDirection;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setInitial(char initial) {
		this.initial = initial;
	}

	public char getInitial() {
		return initial;
	}

	public void setWalkway(boolean isWalkway) {
		this.isWalkway = isWalkway;
	}

	public boolean getWalkway() {
		return isWalkway;
	}

	public void setCloset(boolean isCloset) {
		this.isCloset = isCloset;
	}

	public boolean getCloset() {
		return isCloset;
	}

	public boolean isSecretPassage() {
		return isSecretPassage;
	}

	public void setIsSecretPassage(boolean isSecretPassage) {
		this.isSecretPassage = isSecretPassage;
	}

	public void setSecretPassage(char secretPassage) {
		this.secretPassage = secretPassage;
	}

	public char getSecretPassage() {
		return secretPassage;
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}

	public void addAdjacencyWalkway(BoardCell cell, Board board2, Map<Character, Room> roomMap,
			HashMap<BoardCell, ArrayList<BoardCell>> centersMap) {		
	}

}