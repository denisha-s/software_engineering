package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

//tests for checkAccusation(), disproveSuggestion(), handleSuggestion();
public class ComputerAITest {
	private static Board board;
	private static Card macysCard,targetCard, sephoraCard, pacsunCard, appleCard, hmCard, dswCard, 
	nikeCard, foodCourtCard, selenaCard, justinCard, haileyCard, kylieCard, taylorCard, abelCard, 
	pepperSprayCard, plasticBagCard, tazerCard, baseballBatCard, blenderCard, acidCard;

	//Setup the board before each test
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
	public void testCreateSuggestionRoom() {
		Card room = null;

		//make sure the suggestion is in the correct room
		ComputerPlayer player0 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		//Check room: Target
		player0.setLocation(board.getCell(1, 1));
		for (Card c : board.getRoomCards()) {
			if (c.getName().equals("Target")) {
				room = c;
			}
		}
		
		Solution suggestion = player0.createSuggestion(board);
		assertEquals(room, suggestion.getRoom());
		
	}
	
	@Test
	public void testCreateSuggestionOneUnseen() {
		ComputerPlayer player0 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));

		//Test one unseen card suggestion
		//currently in Apple room
		player0.setLocation(board.getCell(2, 10));
		
		//add all weapons to players seen list except for last one
		for (int i = 0; i < board.getWeaponCards().size() - 1; i++) {
			if (!player0.getSeenCards().contains(board.getWeaponCards().get(i))) {
				player0.addSeenCard(board.getWeaponCards().get(i));
			}
		}
		//add all people to players seen list except for last one
		for (int i = 0; i < board.getPeopleCards().size() - 1; i++) {
			if (!player0.getSeenCards().contains(board.getPeopleCards().get(i))) {
				player0.addSeenCard(board.getPeopleCards().get(i));
			}
		}
		
		Card unseenWeaponCard = board.getWeaponCards().get(board.getWeaponCards().size() -1 );
		Card unseenPersonCard = board.getPeopleCards().get(board.getPeopleCards().size() -1 );
		Solution suggestion = player0.createSuggestion(board);
		
		assertEquals(unseenWeaponCard, suggestion.getWeapon());
		assertEquals(unseenPersonCard, suggestion.getPerson());
		
		
		
	}
	
	@Test
	public void testCreateSuggestionMultipleUnseen() {
		ComputerPlayer player0 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));

		//Test one unseen card suggestion
		//currently in Apple room
		player0.setLocation(board.getCell(2, 10));
		
		//add all weapons to players seen list except for last one
		HashSet<Card> people = new HashSet<Card>();
		HashSet<Card> weapons = new HashSet<Card>();
		
		for (int i = 0; i < 100; i++) {
			Solution suggestion = player0.createSuggestion(board);
			people.add(suggestion.getPerson());
			weapons.add(suggestion.getWeapon());
		}
		
		
		assertEquals(board.getWeaponCards().size(), people.size());
		assertEquals(board.getPeopleCards().size(), weapons.size());
		
	}

	
	
	@Test
	public void testSelectTargetsRoom() {
		ComputerPlayer player0 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		player0.setLocation(board.getCell(4, 4));
		//Picking a room out of other options
		//2 walkway cells
		BoardCell walkway1 = board.getCell(5,3);
		BoardCell walkway2 = board.getCell(5, 5);
		//center cell to target
		BoardCell roomCenter = board.getCell(1, 1);
		
		Set<Card> seenList = player0.getSeenCards();
		

		Set<BoardCell> targets = new HashSet<BoardCell>();
		
		targets.add(walkway1);
		targets.add(walkway2);
		targets.add(roomCenter);
		
		Set<BoardCell> chosenCells = new HashSet<BoardCell>();
		
		BoardCell currLoc;
		
		if (seenList.contains(targetCard)) {
			for(int i = 0; i < 100; i++) {
				player0.selectTarget(targets, board);
				currLoc = player0.getLocation();
				chosenCells.add(currLoc);
			}
			
			assertTrue(chosenCells.contains(walkway1));
			assertTrue(chosenCells.contains(walkway2));
			assertTrue(chosenCells.contains(roomCenter));
		}
		else {
			player0.selectTarget(targets, board);
			assertEquals(roomCenter, player0.getLocation());
			//if room in list has not been seen, select that room
		}
			
	}
	
	@Test
	public void testSelectTargetsRandom() {
		ComputerPlayer player0 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		player0.setLocation(board.getCell(5, 4));
		//Picking a room out of other options
		//3 walkway cells
		BoardCell walkway1 = board.getCell(5,3);
		BoardCell walkway2 = board.getCell(5, 5);
		BoardCell walkway3 = board.getCell(6, 4);
		
		Set<BoardCell> targets = new HashSet<BoardCell>();
		Set<BoardCell> chosenCells = new HashSet<BoardCell>();
		
		targets.add(walkway1);
		targets.add(walkway2);
		targets.add(walkway3);
		
		
		BoardCell currLoc;
		
		for(int i = 0; i < 100; i++) {
			player0.selectTarget(targets, board);
			currLoc = player0.getLocation();
			//System.out.println(currLoc);
			chosenCells.add(currLoc);
		}
		
		assertTrue(chosenCells.contains(walkway1));
		assertTrue(chosenCells.contains(walkway2));
		assertTrue(chosenCells.contains(walkway3));
		
	}
	
	

}

