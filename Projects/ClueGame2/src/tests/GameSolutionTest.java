package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
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
public class GameSolutionTest {
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
		//
		// Create some card constants for test
		//
		//people
		selenaCard = new Card("Selena", CardType.PERSON);
		justinCard = new Card("Justin", CardType.PERSON);
		haileyCard = new Card("Hailey", CardType.PERSON);
		kylieCard = new Card("Kylie", CardType.PERSON);
		taylorCard = new Card("Taylor", CardType.PERSON);
		abelCard = new Card("Abel", CardType.PERSON);
		//rooms
		macysCard = new Card("Macy's", CardType.ROOM);
		targetCard = new Card("Target", CardType.ROOM);
		sephoraCard = new Card("Sephora", CardType.ROOM);
		pacsunCard = new Card("Pacsun", CardType.ROOM);
		appleCard = new Card("Apple", CardType.ROOM);
		hmCard = new Card("H&M", CardType.ROOM);
		dswCard = new Card("DSW", CardType.ROOM);
		nikeCard = new Card("Nike", CardType.ROOM);
		foodCourtCard = new Card("Food Court", CardType.ROOM);
		//weapons
		pepperSprayCard = new Card("Pepper Spray", CardType.WEAPON);
		plasticBagCard = new Card("Plastic Bag", CardType.WEAPON);
		tazerCard = new Card("Tazer", CardType.WEAPON);
		baseballBatCard = new Card("Baseball Bat", CardType.WEAPON);
		blenderCard = new Card("Blender", CardType.WEAPON);
		acidCard = new Card("Acid", CardType.WEAPON);

	}
	
	
	@Test
	public void testCheckAccusation()
	{
		board.setSolution(selenaCard, tazerCard, targetCard);
		System.out.print("Person: ");
		System.out.println(board.getSolution().getPerson());
		System.out.print("Room: ");
		System.out.println(board.getSolution().getRoom());
		System.out.print("Weapon: ");
		System.out.println(board.getSolution().getWeapon());
		assertTrue(board.checkAccusation(tazerCard, targetCard, selenaCard));
		//Should be wrong solution
		assertFalse(board.checkAccusation(acidCard, targetCard, selenaCard));
		assertFalse(board.checkAccusation(tazerCard, pacsunCard, selenaCard));
		assertFalse(board.checkAccusation(tazerCard, targetCard, justinCard));


	}
	
	/*Test Disproves Suggestion*/
	
	 @Test
	 public void testDisproveSuggestion() {
		//create a known solution
		 Solution tempSol = new Solution(taylorCard, blenderCard, foodCourtCard);
		 Card suggestedRoom = hmCard;
		 Card suggestedPerson = kylieCard;
		 Card suggestedWeapon = tazerCard;
		 
		 //One correct card
		 ComputerPlayer player = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));

		 player.getCards().clear();
		 player.addCard(hmCard);
		 player.addCard(haileyCard);
		 player.addCard(acidCard);
		 assertEquals(hmCard, player.disproveSuggestion(suggestedPerson, suggestedWeapon, suggestedRoom));
		 
		 //do a loop and check that each correct card shows up at least once
		 //two cards
		 ComputerPlayer player2 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		 player2.getCards().clear();
		 player2.addCard(kylieCard);
		 player2.addCard(targetCard);
		 player2.addCard(tazerCard);
		 int card1Counter = 0;
		 int card2Counter = 0;
		 for (int i = 0; i < 100; i++) {
			 if (player2.disproveSuggestion(suggestedPerson, suggestedWeapon, suggestedRoom)== kylieCard) {
				 card1Counter ++;
			 }
			 else if (player2.disproveSuggestion(suggestedPerson, suggestedWeapon, suggestedRoom)== tazerCard) {
				 card2Counter ++;
			 }
		 }
		 assert(card1Counter > 1);
		 assert(card2Counter > 1);
		 
		 
		 //no correct cards
		 ComputerPlayer player3 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		 player3.getCards().clear();
		 player3.addCard(pacsunCard);
		 player3.addCard(selenaCard);
		 player3.addCard(plasticBagCard);
		 assertEquals(null, player3.disproveSuggestion(suggestedPerson, suggestedWeapon, suggestedRoom));
		 
		 
	 }
	 
	 /*Test handleSuggestion */
	  
	 @Test
	  public void testNoMatching() {
		 ArrayList<Player> players = new ArrayList<Player>();
		 ComputerPlayer player0 = new ComputerPlayer("Abel", "Green", "Computer", new BoardCell(15,14));
		 ComputerPlayer player1 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		 ComputerPlayer player2 = new ComputerPlayer("Kylie", "Pink", "Computer", new BoardCell(2,2));
		 HumanPlayer human = new HumanPlayer("Selena", "Red", "Human", new BoardCell(5,6));
				 
		 player0.getCards().clear();
		 player1.getCards().clear();
		 player2.getCards().clear();
		 human.getCards().clear();
		 
		 players.add(player0);
		 players.add(player1);
		 players.add(player2);
		 players.add(human);
		 
		 //make sure this player's suggestion is selenaCard, macysCard, pepperSprayCard
//		 Card suggestedPerson = player0.createSuggestion().getPerson();
//		 Card suggestedRoom = player0.createSuggestion().getRoom();
//		 Card suggestedWeapon = player0.createSuggestion().getWeapon();
		 Card suggestedPerson = selenaCard;
		 Card suggestedRoom = macysCard;
		 Card suggestedWeapon = pepperSprayCard;
		 
		 board.setSolution(taylorCard, blenderCard, foodCourtCard);
		 
		 //round where no one can disapprove
		 player1.addCard(abelCard);
		 player1.addCard(hmCard);
		 player1.addCard(acidCard);
		 
		 player2.addCard(kylieCard);
		 player2.addCard(targetCard);
		 player2.addCard(plasticBagCard);
		 
		 human.addCard(justinCard);
		 human.addCard(pacsunCard);
		 human.addCard(tazerCard);
		 
		 //no cards should be disproved by a player and no cards should be returned by handle suggestion
		 assertEquals(null, player0.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, player1.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, player2.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, human.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, board.handleSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon, players, player0));
		 
	 }
	 
	 @Test
	  public void testSuggesingPlayerMatches() {
		 ArrayList<Player> players = new ArrayList<Player>();
		 ComputerPlayer player0 = new ComputerPlayer("Abel", "Green", "Computer", new BoardCell(15,14));
		 ComputerPlayer player1 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		 ComputerPlayer player2 = new ComputerPlayer("Kylie", "Pink", "Computer", new BoardCell(2,2));
		 HumanPlayer human = new HumanPlayer("Selena", "Red", "Human", new BoardCell(5,6));
		 
		 player0.getCards().clear();
		 player1.getCards().clear();
		 player2.getCards().clear();
		 human.getCards().clear();
		 
		 players.add(player0);
		 players.add(player1);
		 players.add(player2);
		 players.add(human);
		 
		 //make sure this player's suggestion is selenaCard, macysCard, pepperSprayCard
		 //player0.createSuggestion(board);
		 Card suggestedPerson = selenaCard;
		 Card suggestedRoom = macysCard;
		 Card suggestedWeapon = pepperSprayCard;
		 
		 board.setSolution(taylorCard, blenderCard, foodCourtCard);
		 
		 //round where only suggesting player has the card
		 player0.addCard(selenaCard);
		 player0.addCard(appleCard);
		 player0.addCard(baseballBatCard);
		 
		 player1.addCard(abelCard);
		 player1.addCard(hmCard);
		 player1.addCard(acidCard);
		 
		 player2.addCard(kylieCard);
		 player2.addCard(targetCard);
		 player2.addCard(plasticBagCard);
		 
		 human.addCard(justinCard);
		 human.addCard(pacsunCard);
		 human.addCard(tazerCard);
		 
		 //no cards should be disproved by a player and no cards should be returned by handle suggestion
		 assertEquals(null, player0.disproveSuggestion(taylorCard, blenderCard, foodCourtCard));
		 assertEquals(null, player1.disproveSuggestion(taylorCard, blenderCard, foodCourtCard));
		 assertEquals(null, player2.disproveSuggestion(taylorCard, blenderCard, foodCourtCard));
		 assertEquals(null, human.disproveSuggestion(taylorCard, blenderCard, foodCourtCard));
		 assertEquals(null, board.handleSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon, players, player0));
		 
	 }
	 
	 @Test
	  public void testOnlyHuman() {
		 ArrayList<Player> players = new ArrayList<Player>();
		 ComputerPlayer player0 = new ComputerPlayer("Abel", "Green", "Computer", new BoardCell(15,14));
		 ComputerPlayer player1 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		 ComputerPlayer player2 = new ComputerPlayer("Kylie", "Pink", "Computer", new BoardCell(2,2));
		 HumanPlayer human = new HumanPlayer("Selena", "Red", "Human", new BoardCell(5,6));
		 
		 player0.getCards().clear();
		 player1.getCards().clear();
		 player2.getCards().clear();
		 human.getCards().clear();
		 
		 players.add(player0);
		 players.add(player1);
		 players.add(player2);
		 players.add(human);
		 
		 //make sure this player's suggestion is selenaCard, macysCard, pepperSprayCard
		 //player0.createSuggestion(board);
		 Card suggestedPerson = selenaCard;
		 Card suggestedRoom = macysCard;
		 Card suggestedWeapon = pepperSprayCard;
		 
		 board.setSolution(taylorCard, blenderCard, foodCourtCard);
		 
		//round where only human can disprove
		 //using same solution and accusation
		 player1.addCard(haileyCard);
		 player1.addCard(hmCard);
		 player1.addCard(acidCard);
		 
		 player2.addCard(kylieCard);
		 player2.addCard(targetCard);
		 player2.addCard(plasticBagCard);
		 
		 human.addCard(justinCard);
		 human.addCard(macysCard);
		 human.addCard(tazerCard);
		 
		 assertEquals(null, player0.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, player1.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, player2.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(macysCard, human.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(macysCard, board.handleSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon, players, player0));
	 }
	 
	 @Test
	  public void testTwoMatching() {
		 ArrayList<Player> players = new ArrayList<Player>();
		 ComputerPlayer player0 = new ComputerPlayer("Abel", "Green", "Computer", new BoardCell(15,14));
		 ComputerPlayer player1 = new ComputerPlayer("Justin", "Blue", "Computer", new BoardCell(3,4));
		 ComputerPlayer player2 = new ComputerPlayer("Kylie", "Pink", "Computer", new BoardCell(2,2));
		 HumanPlayer human = new HumanPlayer("Selena", "Red", "Human", new BoardCell(5,6));
		 
		 player0.getCards().clear();
		 player1.getCards().clear();
		 player2.getCards().clear();
		 human.getCards().clear();
		 
		 players.add(player0);
		 players.add(player1);
		 players.add(player2);
		 players.add(human);
		 
		 //make sure this player's suggestion is selenaCard, macysCard, pepperSprayCard
		 //player0.createSuggestion(board);
		 Card suggestedPerson = selenaCard;
		 Card suggestedRoom = macysCard;
		 Card suggestedWeapon = pepperSprayCard;
		 
		 board.setSolution(taylorCard, blenderCard, foodCourtCard);
		 		 
		 //round where 2 players can disprove, only return the first card that is found
		 //ensure that other players cannot disprove after player 1 disproves.
		 
		 player1.addCard(selenaCard);
		 player1.addCard(hmCard);
		 player1.addCard(acidCard);
		 
		 player2.addCard(kylieCard);
		 player2.addCard(macysCard);
		 player2.addCard(plasticBagCard);
		 
		 human.addCard(justinCard);
		 human.addCard(pacsunCard);
		 human.addCard(tazerCard);
		 
		 assertEquals(null, player0.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(selenaCard, player1.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(macysCard, player2.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(null, human.disproveSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon));
		 assertEquals(selenaCard, board.handleSuggestion(suggestedPerson, suggestedRoom, suggestedWeapon, players, player0));
		 
	 }
	 
	 

}
