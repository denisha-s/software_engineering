package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;

public class FileInitTest {
	// Constants that I will use to test whether the file was loaded correctly
		public static final int LEGEND_SIZE = 11;
		public static final int NUM_ROWS = 24;
		public static final int NUM_COLUMNS = 22;

		// NOTE: I made Board static because I only want to set it up one
		// time (using @BeforeAll), no need to do setup before each test.
		private static Board board;

		@BeforeAll
		public static void setUp() throws BadConfigFormatException, IOException {
			// Board is singleton, get the only instance
			board = Board.getInstance();
			// set the file names to use my config files
			board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
			// Initialize will load BOTH config files
			board.initialize();
		}

		@Test
		public void testRoomLabels() {
			// To ensure data is correctly loaded, test retrieving a few rooms
			// from the hash, including the first and last in the file and a few others
			assertEquals("Macy's", board.getRoom('M').getName() );
			assertEquals("Food Court", board.getRoom('F').getName() );
			assertEquals("Sephora", board.getRoom('S').getName() );
			assertEquals("Pacsun", board.getRoom('P').getName() );
			assertEquals("Walkway", board.getRoom('W').getName() );


		}

		@Test
		public void testBoardDimensions() {
			// Ensure we have the proper number of rows and columns
			assertEquals(NUM_ROWS, board.getNumRows());
			assertEquals(NUM_COLUMNS, board.getNumColumns());
		}

		// Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus
		// two cells that are not a doorway.
		// These cells are white on the planning spreadsheet
		@Test
		public void FourDoorDirections() {
			BoardCell cell = board.getCell(8, 5);
			assertTrue(cell.isDoorway());
			assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
			cell = board.getCell(5, 16);
			assertTrue(cell.isDoorway());
			assertEquals(DoorDirection.UP, cell.getDoorDirection());
			cell = board.getCell(19, 15);
			assertTrue(cell.isDoorway());
			assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
			cell = board.getCell(17, 10);
			assertTrue(cell.isDoorway());
			assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
			// Test that walkways are not doors
			cell = board.getCell(14, 6);
			assertFalse(cell.isDoorway());
		}
		

		// Test that we have the correct number of doors
		@Test
		public void testNumberOfDoorways() {
			int numDoors = 0;
			for (int row = 0; row < board.getNumRows(); row++)
				for (int col = 0; col < board.getNumColumns(); col++) {
					BoardCell cell = board.getCell(row, col);
					if (cell.isDoorway())
						numDoors++;
				}
			Assert.assertEquals(21, numDoors);
		}

		// Test a few room cells to ensure the room initial is correct.
		@Test
		public void testRooms() {
			// just test a standard room location
			BoardCell cell = board.getCell( 19, 12);
			Room room = board.getRoom( cell ) ;
			assertTrue( room != null );
			assertEquals( room.getName(), "Food Court" ) ;
			assertFalse( cell.isLabel() );
			assertFalse( cell.isRoomCenter() ) ;
			assertFalse( cell.isDoorway()) ;

			// this is a label cell to test
			cell = board.getCell(14, 1);
			room = board.getRoom( cell ) ;
			assertTrue( room != null );
			assertEquals( room.getName(), "Nike" ) ;
			assertTrue( cell.isLabel() );
			assertTrue( room.getLabelCell() == cell );
			
			// this is a room center cell to test
			cell = board.getCell(2, 10);
			room = board.getRoom( cell ) ;
			assertTrue( room != null );
			assertEquals( room.getName(), "Apple" ) ;
			assertTrue( cell.isRoomCenter() );
			assertTrue( room.getCenterCell() == cell );
			
			// this is a secret passage test
			cell = board.getCell(16, 21);
			room = board.getRoom( cell ) ;
			assertTrue( room != null );
			assertEquals( room.getName(), "Macy's" ) ;
			assertTrue( cell.getSecretPassage() == 'T' );
			
			// test a walkway
			cell = board.getCell(17, 16);
			room = board.getRoom( cell ) ;
			// Note for our purposes, walkways and closets are rooms
			assertTrue( room != null );
			assertEquals( room.getName(), "Walkway" ) ;
			assertFalse( cell.isRoomCenter() );
			assertFalse( cell.isLabel() );
			
			// test a closet
			cell = board.getCell(8, 0);
			room = board.getRoom( cell ) ;
			assertTrue( room != null );
			assertEquals( room.getName(), "Unused" ) ;
			assertFalse( cell.isRoomCenter() );
			assertFalse( cell.isLabel() );
		}
}
