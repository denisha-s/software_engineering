package tests;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestsExp {
	TestBoard board;

	@BeforeEach
	//Run before each test, @BeforeAll would work just as well
	public void setUp() {
		// board should create adjacency list
		board = new TestBoard();
	}

	/*
	 * Test adjacencies for several different locations
	 * Test centers and edges
	 */
	@Test
	public void testAdjacency() {
		// test adjacency for top left cell [0][0]
		TestBoardCell topLeft = board.getCell(0,0);
		Set<TestBoardCell> testList = topLeft.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2,testList.size());
		
		// test adjacency for bottom right cell [3][3]
		TestBoardCell bottomRight = board.getCell(3, 3);
		testList = bottomRight.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(2,testList.size());
		
		// test adjacency for right edge cell [1][3]
		TestBoardCell topRight = board.getCell(1, 3);
		testList = topRight.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0, 3)));
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertEquals(3,testList.size());
		
		// test adjacency for left edge cell [3][0]
		TestBoardCell bottomLeft = board.getCell(3,0);
		testList = bottomLeft.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2, 0)));
		Assert.assertTrue(testList.contains(board.getCell(3, 1)));
		Assert.assertEquals(2,testList.size());
		
		// test adjacency for middle [2][2]
		TestBoardCell middle = board.getCell(2,2);
		testList = middle.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(4,testList.size());
	}

	/*
	 * Test targets with several rolls and start locations
	 */
	@Test
	public void testTargetsNormal() {
		TestBoardCell cell = board.getCell(0,0);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(0,3)));
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
	}

	/*
	 * Test where there might be a cell that represents a room and another that is occupied by an opponent. 
	 */
	@Test
	public void testTargetsMixed() {
		// set up occupied cells.
		board.getCell(0,2).setOccupied(true);
		board.getCell(1,2).setRoom(true);
		TestBoardCell cell = board.getCell(0,3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(2,2)));
		Assert.assertTrue(targets.contains(board.getCell(3,3)));
	}
}
