package experiment;
import java.util.*;
public class TestBoardCell {
	//attributes
	private int row, col;
	private boolean isRoom, isOccupied;
	private Set<TestBoardCell> adjList = new HashSet<TestBoardCell>();

	/*
	 * Constructor
	 */
	public TestBoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	/*
	 * add cell to adjacency list
	 */
	public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
		

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

	/*
	 * returns adjacency list for the cell
	 */
	public Set<TestBoardCell> getAdjList() {
		return adjList;

	}

	/*
	 * setter for indicating a cell is part of a room 
	 */
	public void setRoom(boolean b) {
		isRoom = b;

	}

	public boolean isRoom() {
		return isRoom;
	}

	/*
	 * indicating a cell is occupied by another player. 
	 */
	public void setOccupied(boolean b) {
		isOccupied = b;

	}

	public boolean getOccupied() {
		return isOccupied;
	}

	@Override
	public String toString() {
		return "TestBoardCell [row=" + row + ", col=" + col + "]";
	}
	
	
}
