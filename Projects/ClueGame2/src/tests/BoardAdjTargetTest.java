package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
	@BeforeAll
	public static void setUp() throws BadConfigFormatException, IOException {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are PINK on the planning spreadsheet
	@Test
	public void testAdjacenciesRooms()
	{
		// we want to test a couple of different rooms.
		// First, Target that only has a single door but a secret room
		Set<BoardCell> testList = board.getAdjList(1, 1);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(4, 4)));
		assertTrue(testList.contains(board.getCell(19, 19)));
		
		// now test the Sephora (note not marked since multiple test here)
		testList = board.getAdjList(2, 18);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(5, 16)));
		
		// one more room, Macy's
		testList = board.getAdjList(19, 19);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(19, 15)));
		assertTrue(testList.contains(board.getCell(1, 1)));
	}

	
	// Ensure door locations include their rooms and also additional walkways
	// These cells are PINK on the planning spreadsheet
	@Test
	public void testAdjacencyDoor()
	{
		Set<BoardCell> testList = board.getAdjList(11, 2);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(15, 1)));
		assertTrue(testList.contains(board.getCell(11, 3)));

		testList = board.getAdjList(18, 1);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(21, 1)));
		assertTrue(testList.contains(board.getCell(17, 1)));
		
		testList = board.getAdjList(17, 11);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(17, 12)));
		assertTrue(testList.contains(board.getCell(16, 11)));
		assertTrue(testList.contains(board.getCell(20, 10)));
	}
	
	// Test a variety of walkway scenarios
	// These tests are BROWN on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on bottom edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(15, 21);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(15, 20)));
		
		// Test near a door but not adjacent
		testList = board.getAdjList(19, 5);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(18, 5)));
		assertTrue(testList.contains(board.getCell(20, 5)));
		assertTrue(testList.contains(board.getCell(19, 6)));

		// Test adjacent to walkways
		testList = board.getAdjList(11, 1);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(11, 0)));
		assertTrue(testList.contains(board.getCell(11, 2)));


		// Test next to closet
		testList = board.getAdjList(8,10);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(8, 11)));
		assertTrue(testList.contains(board.getCell(7, 10)));
		assertTrue(testList.contains(board.getCell(8, 9)));
	
	}
	
	
	// Tests out of room center, 1, 3 and 4
	// These are GREEN on the planning spreadsheet
	@Test
	public void testTargetsInDSW() {
		// test a roll of 1
		board.calcTargets(board.getCell(10, 18), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(10, 14)));
		assertTrue(targets.contains(board.getCell(11, 14)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(10, 18), 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(8, 14)));
		assertTrue(targets.contains(board.getCell(13, 14)));	
		assertTrue(targets.contains(board.getCell(12, 15)));
		assertTrue(targets.contains(board.getCell(9, 15)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(10, 18), 4);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(14, 14)));
		assertTrue(targets.contains(board.getCell(13, 15)));	
		assertTrue(targets.contains(board.getCell(8, 13)));
		assertTrue(targets.contains(board.getCell(7, 14)));	
	}
	
	@Test
	public void testTargetsInMacys() {
		// test a roll of 1
		board.calcTargets(board.getCell(19, 19), 1);
		Set<BoardCell> targets= board.getTargets();
//		for (BoardCell cell : targets) {
//			System.out.println(cell);
//		}
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(19, 15)));
		assertTrue(targets.contains(board.getCell(1, 1)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(19, 19), 3);
		targets= board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(17, 15)));
		assertTrue(targets.contains(board.getCell(18, 14)));	
		assertTrue(targets.contains(board.getCell(21, 16)));
		assertTrue(targets.contains(board.getCell(1, 1)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(19, 19), 4);
		targets= board.getTargets();
		assertEquals(12, targets.size());
		assertTrue(targets.contains(board.getCell(18, 13)));
		assertTrue(targets.contains(board.getCell(23, 15)));	
		assertTrue(targets.contains(board.getCell(16, 15)));
		assertTrue(targets.contains(board.getCell(1, 1)));	
	}

	// Tests out of room center, 1, 3 and 4
	// These are GREEN on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
		// test a roll of 1, at door
		board.calcTargets(board.getCell(10, 14), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(10, 18)));
		assertTrue(targets.contains(board.getCell(9, 14)));		
		
		// test a roll of 3
		board.calcTargets(board.getCell(10, 14), 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(10, 18)));
		assertTrue(targets.contains(board.getCell(8, 15)));
		assertTrue(targets.contains(board.getCell(8, 13)));		
		
		// test a roll of 4
		board.calcTargets(board.getCell(10, 14), 4);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(10, 18)));
		assertTrue(targets.contains(board.getCell(8, 12)));
		assertTrue(targets.contains(board.getCell(9, 15)));	
		assertTrue(targets.contains(board.getCell(8, 14)));	
	}

	@Test
	public void testTargetsInWalkway1() {
		// test a roll of 1
		board.calcTargets(board.getCell(11, 1), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(11, 0)));
		assertTrue(targets.contains(board.getCell(11, 2)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(11, 1), 3);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(15, 1)));
		assertTrue(targets.contains(board.getCell(8, 1)));
		assertTrue(targets.contains(board.getCell(11, 4)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(11, 1), 4);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(15, 1)));
		assertTrue(targets.contains(board.getCell(8, 1)));
		assertTrue(targets.contains(board.getCell(11, 5)));	
		assertTrue(targets.contains(board.getCell(10, 4)));	
	}

	@Test
	public void testTargetsInWalkway2() {
		// test a roll of 1
		board.calcTargets(board.getCell(13, 6), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(13, 7)));
		assertTrue(targets.contains(board.getCell(12, 6)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(13, 6), 3);
		targets= board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCell(15, 5)));
		assertTrue(targets.contains(board.getCell(13, 5)));
		assertTrue(targets.contains(board.getCell(10, 6)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(13, 6), 4);
		targets= board.getTargets();
		assertEquals(15, targets.size());
		assertTrue(targets.contains(board.getCell(15, 1)));
		assertTrue(targets.contains(board.getCell(16, 5)));
		assertTrue(targets.contains(board.getCell(11, 6)));	
	}

	@Test
	// test to make sure occupied locations do not cause problems
	public void testTargetsOccupied() {
		// test a roll of 4 blocked 2 down
		board.getCell(15, 6).setOccupied(true);
		board.calcTargets(board.getCell(13, 6), 4);
		board.getCell(15, 6).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
//		System.out.println(board.getCell(13, 6).getAdjList());
//		System.out.println(targets);
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCell(15, 1)));
		assertTrue(targets.contains(board.getCell(11, 4)));
		assertTrue(targets.contains(board.getCell(14, 5)));	
		assertFalse( targets.contains( board.getCell(15, 6))) ;
		assertFalse( targets.contains( board.getCell(17, 6))) ;
	
		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(10, 18).setOccupied(true);
		board.getCell(12, 14).setOccupied(true);
		board.calcTargets(board.getCell(11, 14), 1);
		board.getCell(10, 18).setOccupied(false);
		board.getCell(12, 14).setOccupied(false);
		targets= board.getTargets();
		//REMOVE LATER
		
//		assertEquals(2, targets.size());	
//		assertTrue(targets.contains(board.getCell(12, 14)));
//		assertTrue(targets.contains(board.getCell(10, 18)));	
		
		// check leaving a room with a blocked doorway
		board.getCell(11, 14).setOccupied(true);
		board.calcTargets(board.getCell(10, 18), 3);
		board.getCell(11, 14).setOccupied(false);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(9, 15)));	
		assertTrue(targets.contains(board.getCell(8, 14)));

	}
}
