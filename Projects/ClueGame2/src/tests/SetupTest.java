package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.Solution;

public class SetupTest {
	// Constants that I will use to test whether the file was loaded correctly
		public static final int NUM_PLAYERS = 6;
		public static final int NUM_WEAPONS = 6;
		public static final int NUM_CARDS = 21;
		public static final int NUM_ROOMS = 9;
		public static final int TOTAL_DEALT_CARDS = 18;

		private static Board board;

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
		
		//make sure that the number of players and cards is correct
		@Test
		public void testNumbers() {
			ArrayList<Player> players = board.getPlayers();
			ArrayList<Card> cards = board.getCards();
			// Ensure we have the right number of players and cards
			assertEquals(NUM_PLAYERS, players.size());
			assertEquals(NUM_CARDS, cards.size());
		}
		
		//test to check for players, make sure they are read and loaded correctly 
		@Test
		public void testPlayers() {
			ArrayList<Player> players = board.getPlayers();
			// To ensure data is correctly loaded, test retrieving a few players
			// from the hash, including the first and last in the file and a few others
			assertEquals("Selena", players.get(0).getName());
			assertEquals("Justin", players.get(1).getName());
			assertEquals("Hailey", players.get(2).getName());
			// Check player's color
			assertEquals("Pink", players.get(0).getColor());
			assertEquals("Blue", players.get(1).getColor());
			assertEquals("Red", players.get(2).getColor());
		}
		
		//checks to make sure cards were read and loaded correctly.
		//tests to check that a card of each type is loaded correctly. 
		@Test
		public void testCards() {
			//check for a room card
			ArrayList<Card> roomList = board.getRoomCards();
			assertEquals(CardType.ROOM, roomList.get(0).getType());
			assertEquals("Target", roomList.get(1).getName());
			
			//check for a person card
			ArrayList<Card> playerList = board.getPeopleCards();
			assertEquals(CardType.PERSON, playerList.get(0).getType());
			assertEquals("Abel", playerList.get(5).getName());

			
			//check for a weapon card
			ArrayList<Card> weaponList = board.getWeaponCards();
			assertEquals(CardType.WEAPON, weaponList.get(0).getType());
			assertEquals("Tazer", weaponList.get(2).getName());
		}
		
		//test to check that a solution was actually generated.
		@Test
		public void testSolutions() {
			Solution solution = board.getSolution();

			Card weaponSol = solution.getWeapon();
			Card personSol = solution.getPerson();
			Card roomSol = solution.getRoom();
			//make sure that at least one type of card is present
			assertEquals(CardType.WEAPON, weaponSol.getType());
			assertEquals(CardType.PERSON, personSol.getType());
			assertEquals(CardType.ROOM, roomSol.getType());
			//make sure a solution was actually generated
			assertNotNull(solution);
		}
		

		// Tests for dealing the cards
		@Test
		public void testDealtCards() {
			ArrayList<Card> dealtCards = board.getDealtCards();
			//each player gets 3 cards, 3*6 = 18, 18 cards dealt
			assertEquals(TOTAL_DEALT_CARDS, dealtCards.size());
			
			//check each player gets 3 cards
			ArrayList<Player> players = board.getPlayers();
			
			for (Player p: players) {
				assertEquals(3, p.getCards().size());
			}
			
			//make sure each card only appears once
			//a hash set will remove duplicates so we can compare sizes.
			HashSet<Card> alreadyDealt = new HashSet<Card>();
			for (Card c: dealtCards) {
				alreadyDealt.add(c);
			}
			assertEquals(dealtCards.size(), alreadyDealt.size());
		}
		
		
}
