package experiment;
import java.util.*;

public class TestBoard {
	final static int COL = 4;
	final static int ROW = 4;
	//attributes
	private TestBoardCell[][] grid = new TestBoardCell[ROW][COL];
	private Set<TestBoardCell> targets = new HashSet<TestBoardCell>();
	private Set<TestBoardCell> visited = new HashSet<TestBoardCell>();
	private TestBoardCell cell = new TestBoardCell(0,0);

	
	/*
	 * Constructor: sets the board and finds adjacencies
	 */
	public TestBoard() {
		this.grid = new TestBoardCell[ROW][COL];
		this.targets = new HashSet<TestBoardCell>();

		//add each cell to the grid
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
		
		//find adjacencies
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if(i > 0) {
					grid[i][j].addAdjacency(grid[i-1][j]);
				}
				if(i < ROW - 1) {
					grid[i][j].addAdjacency(grid[i+1][j]);
				}
				if(j > 0) {
					grid[i][j].addAdjacency(grid[i][j-1]);
				}
				if(j < COL - 1) {
					grid[i][j].addAdjacency(grid[i][j+1]);
				}
			}

		}
	}


	/*
	 * calculates legal targets for a move from startCell of length path length
	 */
	public void calcTargets(TestBoardCell startCell, int pathLength) {
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}

	public void findAllTargets(TestBoardCell thisCell, int numSteps) {
		//Iterates through the adjacency list for the given cell
		for (TestBoardCell adjCell : thisCell.getAdjList()) {

			//Only continues recursive function if the cell has not been visited and is not occupied 
			if (!visited.contains(adjCell) && !adjCell.getOccupied()) {

				visited.add(adjCell);

				//Stops recursion if out of steps or in a room
				if(numSteps == 1 || adjCell.isRoom()) {
					targets.add(adjCell);
				}
				else {
					findAllTargets(adjCell, numSteps-1);
				}

				visited.remove(adjCell);
			}
		}
	}


	/*
	 * returns the cell from the board at row, cell
	 */
	public TestBoardCell getCell(int row, int col) {
		//create new cell with row and column
		return grid[row][col];
	}


	/*
	 * gets the targets last created by calcTargets()
	 */
	public Set<TestBoardCell> getTargets() {
		return targets;
	}


}
