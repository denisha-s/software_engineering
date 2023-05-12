package clueGame;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import experiment.TestBoardCell;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthOptionPaneUI;


/* 
 * Board Class: This class sets up the clue game given a setup and layout file. 
 * It holds the game board, and contains public methods such as calacTargets 
 * which calculates legal targets for a move and calcAdjacency which
 * creates an adjacency lists for every BoardCell. 
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/3/2023
 * Sources: None
 */

public class Board extends JPanel implements MouseListener, ActionListener{
	// attributes
	private int numRows, numColumns;
	private String layoutConfigFile, setupConfigFile;
	private BoardCell[][] grid;
	private int cellWidth;
	private int cellHeight;
	private Set<BoardCell> allCells = new HashSet<BoardCell>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
	private Map<String, String> legendMap = new HashMap<String, String>();
	private ArrayList<BoardCell> clickedCells = new ArrayList<BoardCell>();
	private ArrayList<Card> allCards, dealtCards, seenCards, weaponCards, roomCards, peopleCards;
	private ArrayList<Player> allPlayers;
	private HumanPlayer human;
	private Solution theAnswer;	
	private boolean humanTurn;
	private Player currentPlayer;
	private int dieRoll;
	private JDialog suggestionDialog = new JDialog();
	private JDialog accusationDialog = new JDialog();
	private boolean makingAccusation = true;
	private boolean makingSuggestion = false; 
	private JComboBox<String> accusedRoomCombo = new JComboBox<String>(), accusedPeopleCombo = new JComboBox<String>(), accusedWeaponCombo = new JComboBox<String>();
	private JComboBox<String> people = new JComboBox<String>(), weapon = new JComboBox<String>();
	private Card suggestedRoom, suggestedPerson, suggestedWeapon;
	private Card accusedRoom, accusedPerson, accusedWeapon;
	private GameControlPanel controlPanel;
	private ClueGame gameFrame;
	private KnownCardPanel cardPanel;
	private Player disprovingPlayer = human;
	private Player suggestingPlayer = human;
	private int boardHeight, boardWidth, offset;

	/****************************
	 * CONSTRUCTORS
	 *****************************/

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();

	// constructor is private to ensure only one can be created
	private Board() {
		super();
	}

	// this does the Singleton pattern
	public static Board getInstance() {
		return theInstance;
	}

	/******************************
	 * INITIALIZING THE BOARD
	 *******************************/

	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize() throws BadConfigFormatException, IOException {
		//clear any maps to ensure that we are not continuously adding to the map each time we run the program. Saves space.
		roomMap.clear();
		legendMap.clear();
		
		//start setting up the game
		loadSetupConfig();
		loadLayoutConfig();
		calcAdjacencies();

	}

	/********************************
	 * LOAD AND READ FILES
	 *********************************/

	/*
	 * get file names to read
	 */
	public void setConfigFiles(String layout, String setup) throws FileNotFoundException, BadConfigFormatException {
		layoutConfigFile = "data/" + layout;
		setupConfigFile = "data/" + setup;
	}

	/*
	 * read text file
	 */
	public void loadSetupConfig() throws BadConfigFormatException, IOException, FileNotFoundException {
		// set the number of rows and columns
		setNumRows();
		setNumCol();

		// set the grid
		grid = new BoardCell[numRows][numColumns];

		// read through the file separating on commas
		Scanner text = new Scanner(new FileReader(setupConfigFile));
		text.useDelimiter(",");

		// count the number of lines in the file.
		int counter = 0;
		while (text.hasNext()) {
			counter++;
			text.nextLine();
		}
		
		
		//System.out.println(backSlashCounter);
		// initialize array that will hold each line
		String[] stringArray = new String[counter];
		// variables for items being read in.
		String type = "";
		String roomName = "";
		String roomInitial = "";
		String playerName = "";
		String playerColor = "";
		String playerType = "";
		String row = "";
		String col = "";
		BoardCell playerLocation;
		String weaponName = "";
		Card c = new Card("", null);
		Player p;
		ArrayList<Card> tempAllCards = new ArrayList<Card>();
		
		allCards = new ArrayList<Card>();
		roomCards = new ArrayList<Card>();
		weaponCards = new ArrayList<Card>();
		peopleCards = new ArrayList<Card>();
		allPlayers = new ArrayList<Player>();
		
		// string array for when we split each line into the three items
		String[] splitLineRoom = new String[3];
		String[] splitLinePlayer = new String[6];
		String[] splitLineWeapon = new String[2];

		// read the file.
		text = new Scanner(new FileReader(setupConfigFile));

		// loop through each line until the end
		for (int i = 0; i < counter; i++) {
			// get next line
			String next = text.nextLine();

			// if the line is a comment, move on to the next line.
			
			if (next.contains("//")) {
				next = text.nextLine();
				counter--;
			}

			// get next line and split on commas
			stringArray[i] = next;
		

			if (i>=0 && i<=10) {
				//split the line
				splitLineRoom = stringArray[i].split(",");
				
				// get each item from line and assign accordingly
				type = splitLineRoom[0];
				roomName = splitLineRoom[1];
				roomInitial = splitLineRoom[2];
				// get rid of any whitespace
				type = type.trim();
				roomName = roomName.trim();
				roomInitial = roomInitial.trim();

			}
			else if (i>=11 && i<=16) {

				splitLinePlayer = stringArray[i].split(",");
				
				// get each item from line and assign accordingly
				type = splitLinePlayer[0];
				playerName = splitLinePlayer[1];
				playerColor = splitLinePlayer[2];
				playerType = splitLinePlayer[3];
				row = splitLinePlayer[4];
				col = splitLinePlayer[5];

				//get rid of any whitespace
				type = type.trim();
				playerName = playerName.trim();
				playerColor = playerColor.trim();
				playerType = playerType.trim();
				row = row.trim();
				col = col.trim();
				
				//get the player location use row and col read in from file
				playerLocation = new BoardCell(Integer.parseInt(row), Integer.parseInt(col));
				
				//determine the human player and the computer players
				if (playerName.equals("Selena")) {
					human = new HumanPlayer(playerName, playerColor, playerType, playerLocation);
					allPlayers.add(human);
				}
				else {
					p = new ComputerPlayer(playerName, playerColor, playerType, playerLocation);
					//add players to the player list
					allPlayers.add(p);
				}
				
				
				
			}
			else if (i >=17 && i<=22) {
				
				splitLineWeapon = stringArray[i].split(",");
				
				// get each item from line and assign accordingly
				type = splitLineWeapon[0];
				weaponName = splitLineWeapon[1];
				
				type = type.trim();
				weaponName = weaponName.trim();

			}			

			// check if the type is correct, if not throw an exception
			if (!type.equals("Room") && !type.equals("Space") && !type.equals("Player") && !type.equals("Weapon")) {
				throw new BadConfigFormatException("Invalid type");
			}

			if (type.equals("Room") || type.equals("Space")) {
				// Now we need to add each of these to legend.{
				legendMap.put(roomInitial, roomName);
			}
			
			//create cards based off of type
			if(type.equals("Room")) {
				c = new Card(roomName, CardType.ROOM);
				//add to respective list of cards
				roomCards.add(c);
				tempAllCards.add(c);
				allCards.add(c);
			}
			else if (type.equals("Player")) {
				c = new Card(playerName, CardType.PERSON);
				//add to respective list of cards
				peopleCards.add(c);
				tempAllCards.add(c);
				allCards.add(c);
			}
			else if (type.equals("Weapon")) {
				c = new Card(weaponName, CardType.WEAPON);
				//add to respective list of cards
				weaponCards.add(c);
				tempAllCards.add(c);
				allCards.add(c);
			}
		}
			
		// SET SOLUTION 
		//create temporary cards for solutions
		Card personSolution;
		Card weaponSolution;
		Card roomSolution;
		
		//use random generator so you can get a random theAnswer each time. 
		Random random = new Random();

		//pick a random card from each type. 
		personSolution = peopleCards.get(random.nextInt((6)));
		weaponSolution = weaponCards.get(random.nextInt((6)));
		roomSolution = roomCards.get(random.nextInt((9)));
		
		//create the theAnswer
		theAnswer = new Solution(personSolution, weaponSolution, roomSolution);
				
		//remove the cards added to the theAnswer from the whole list of cards so they are not handed out to the players
		tempAllCards.remove(personSolution);
		tempAllCards.remove(weaponSolution);
		tempAllCards.remove(roomSolution);
		
		dealCards(tempAllCards);
	
		// close text file
		text.close();
		
	}
	
	/*
	 * helper method for loadSetupConfig
	 * deals the cards to players
	 */
	public void dealCards(ArrayList<Card> tempAllCards) {
		Random random = new Random();
		dealtCards = new ArrayList<Card>();
		
		//deal each player 3 random cards from deck.
		for (int i = 0; i < 3; i++) {
			for (Player person : allPlayers) {
				//get a random card from entire deck and add to players hand
				Card randomCard = tempAllCards.get(random.nextInt(tempAllCards.size()));
				person.updateHand(randomCard);
				//remove from card deck and add to dealtCards list
				tempAllCards.remove(randomCard);
				dealtCards.add(randomCard);
				
				
			}
		}
	}
	
	/*
	 * read csv file
	 */
	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {
		// create a map for label and center cell for each room
		Map<Character, BoardCell> labelMap = new HashMap<Character, BoardCell>();
		Map<Character, BoardCell> centerMap = new HashMap<Character, BoardCell>();

		Scanner csv = null;

		// ensure number of columns is correct
		if (numColumns < 0) {
			throw new BadConfigFormatException("Invalid Column format");
		} else {
			csv = new Scanner(new FileReader(layoutConfigFile));

			// stores every line in the csv file
			String[] allLines = new String[numRows];
			// stores lines without commas
			String[] individualCell = new String[numColumns];

			for (int r = 0; r < numRows; r++) {
				// scan line and split by commas
				allLines[r] = csv.nextLine();
				individualCell = allLines[r].split(",");
				// now loop through columns
				if (individualCell.length != numColumns) {
					throw new BadConfigFormatException("Blank cells");
				}

				for (int c = 0; c < numColumns; c++) {
					// check if room initial is found in Legend Map
					boolean foundInLegend = false;
					for (Map.Entry<String, String> entry : legendMap.entrySet()) {
						String charAsString = Character.toString((individualCell[c].charAt(0)));
						String keyString = entry.getKey();
						if (keyString.equals(charAsString.trim())) {
							foundInLegend = true;
						}
					}
					if (foundInLegend == false) {
						throw new BadConfigFormatException("Invalid Room");
					}

					// create new Board cell
					grid[r][c] = new BoardCell(r, c);

					// set the board cell's initial
					if (individualCell[c].length() == 1) {
						grid[r][c].setInitial(individualCell[c].charAt(0));

						// check is walkway
						if (individualCell[c].charAt(0) == 'W') {
							grid[r][c].setWalkway(true);
						}

						// check is unused space
						if (individualCell[c].charAt(0) == 'X') {
							grid[r][c].setCloset(true);
						}

						// if it is not an X or W that means it is a room
						if (individualCell[c].charAt(0) != 'W') {
							grid[r][c].setRoom(true);
						}

					} else {
						grid[r][c].setInitial(individualCell[c].charAt(0));

						char secondChar = individualCell[c].charAt(1);
						// check the second symbol as either #, *, or ^ < > v
						switch (secondChar) {
						case '#':
							grid[r][c].setRoomLabel(true);
							grid[r][c].setRoom(true);
							labelMap.put(individualCell[c].charAt(0), grid[r][c]);
							break;
						case '*':
							grid[r][c].setRoomCenter(true);
							grid[r][c].setRoom(true);
							centerMap.put(individualCell[c].charAt(0), grid[r][c]);
							break;
						case '<':
							grid[r][c].setDoorDirection(DoorDirection.LEFT);
							grid[r][c].setDoorway(true);
							grid[r][c].setWalkway(true);
							break;
						case '>':
							grid[r][c].setDoorDirection(DoorDirection.RIGHT);
							grid[r][c].setDoorway(true);
							grid[r][c].setWalkway(true);
							break;
						case '^':
							grid[r][c].setDoorDirection(DoorDirection.UP);
							grid[r][c].setDoorway(true);
							grid[r][c].setWalkway(true);
							break;
						case 'v':
							grid[r][c].setDoorDirection(DoorDirection.DOWN);
							grid[r][c].setDoorway(true);
							grid[r][c].setWalkway(true);
							break;
						}

						// find the secretPassage by checking the second character and checking it
						// against the legend.
						for (Map.Entry<String, String> entry : legendMap.entrySet()) {
							String charAsString = Character.toString((individualCell[c].charAt(1)));
							String keyString = entry.getKey();
							if (keyString.equals(charAsString.trim())) {
								grid[r][c].setSecretPassage(secondChar);
								grid[r][c].setIsSecretPassage(true);
								grid[r][c].setRoom(true);
							}
						}
					}

					// add to room map
					String charAsString = Character.toString((individualCell[c].charAt(0)));
					String roomName = legendMap.get(charAsString);
					
					
					roomMap.put(individualCell[c].charAt(0), new Room(roomName,
							centerMap.get(individualCell[c].charAt(0)), labelMap.get(individualCell[c].charAt(0))));
					allCells.add(grid[r][c]);
				}

			}

		}

	}

	/*
	 * set the number of rows in board
	 */
	private int setNumRows() {
		try {
			// read file
			Scanner csv = new Scanner(new FileReader(layoutConfigFile));
			int rowCounter = 0;
			// get next line and increment counter
			while (csv.hasNext()) {
				csv.next();
				rowCounter++;
			}
			// set number of rows.
			numRows = rowCounter;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return numRows;
	}

	/*
	 * set the number of columns in board
	 */
	private int setNumCol() {
		try {
			Scanner csv = new Scanner(new FileReader(layoutConfigFile));
			String firstLine;
			try {
				// read the first line
				firstLine = Files.readAllLines(Paths.get(layoutConfigFile)).get(0);
				// split on commas
				String[] splitConfigFile = firstLine.split(",");
				// get the length of split array to get the number of columns
				numColumns = splitConfigFile.length;
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return numColumns;
	}

	/***********************************************
	 * CALCULATE ADJACENCIES
	 ************************************************/
	/*
	 * calculates adjacency list
	 */
	public void calcAdjacencies() {
		// find walkway adjacencies
		calcWalkwayAdjacencies();

		// now check roomCenters
		calcRoomCenterAdjacencies();

		// Secret Passages
		calcSecretPassageAdjacencies();

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				//if an adjacent cell is found a cell's adjacency list but is occupied remove it from the list. 
				Set<BoardCell> set = grid[row][col].getAdjList();
				for (BoardCell cell : set) {
					if (cell.getOccupied()) {
						grid[row][col].getAdjList().remove(cell);
					}
				}
			}
		}
	}
	
	/*
	 * finds adjacency list for walkways
	 */
	private void calcWalkwayAdjacencies() {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				//check walkways
				//if the adjacent cell is a room or a closet do not add to the adjacency list
				//else add the cell to adjacency list.
				if (grid[row][col].getWalkway()) {
					if (row > 0) {
						if (grid[row - 1][col].isRoom() || grid[row - 1][col].getCloset()) {
							System.out.print("");
						}
						else {
							grid[row][col].addAdj(grid[row - 1][col]);
						}
					}
					if (row < numRows - 1) {
						if (grid[row + 1][col].isRoom() || grid[row + 1][col].getCloset()) {
							System.out.print("");
						}
						else {
							grid[row][col].addAdj(grid[row + 1][col]);
						}
					}
					if (col > 0) {
						if (grid[row][col - 1].isRoom() || grid[row][col - 1].getCloset()) {
							System.out.print("");
						}
						else {
							grid[row][col].addAdj(grid[row][col - 1]);
						}
					}
					if (col < numColumns - 1) {
						if (grid[row][col + 1].isRoom() || grid[row][col + 1].getCloset()) {
							System.out.print("");
						}
						else {
							grid[row][col].addAdj(grid[row][col + 1]);
						}
					}

				}
				
				// next check Doorways and add room centers to their adjacency list
				if (grid[row][col].isDoorway()) {
					DoorDirection currDirection = grid[row][col].getDoorDirection();
					BoardCell neighbor = new BoardCell(0, 0);
					//check the direction of the door and get the room corresponding to it
					switch (currDirection) {
					case LEFT:
						neighbor = grid[row][col - 1];
						break; 
					case RIGHT:
						neighbor = grid[row][col + 1];
						break;
					case UP:
						neighbor = grid[row - 1][col];
						break;
					case DOWN:
						neighbor = grid[row + 1][col];
						break;
					}
					//get the initial of that room
					char roomInitial = neighbor.getInitial();
					//create a new room variable to check
					Room nextRoom = new Room("E", null, null);
					//find the room initial in the room map
					for (Map.Entry<Character, Room> entry : roomMap.entrySet()) {
						//then get the room corresponding to that initial
						if (entry.getKey() == roomInitial) {
							nextRoom = entry.getValue();
							break;
						}
					}
					//add the room center of that room to the current doorway's adjacency list
					BoardCell adjRoomCenter = nextRoom.getCenterCell();
					grid[row][col].addAdj(adjRoomCenter);
				}
			}
		}
	}
	
	/*
	 * finds adjacency list for room centers
	 */
	private void calcRoomCenterAdjacencies() {
		//loop through all of the cells
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				//find room center cells
				if (grid[row][col].isRoomCenter()) {
					//check all cells again
					for (int i = 0; i < numRows; i++) {
						for (int j = 0; j < numColumns; j++) {
							//if the cell is a doorway
							if (grid[i][j].isDoorway()) {
								//get the adjacency list of that doorway
								Set<BoardCell> set = grid[i][j].getAdjList();
								//if the adjacent cell in the list is equal to that room center cell 
								//then add that doorway to the room center adjacency list
								for (BoardCell cell : set) {
									if (cell.getRow() == row && cell.getCol() == col) {
										grid[row][col].addAdj(grid[i][j]);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/*
	 * finds adjacency list for rooms with secret passages
	 */
	private void calcSecretPassageAdjacencies() {
		//loop through all the cells
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				//if the cell is a s Secret Passage then
				if (grid[row][col].isSecretPassage()) {
					//get the initial of the room it connects to
					char secretChar = grid[row][col].getSecretPassage();
					//get the center cell of that corresponding room
					BoardCell correspondingCell = roomMap.get(secretChar).getCenterCell();
					//get the initial of the room you are currently in
					char thisRoomInitial = grid[row][col].getInitial();
					// get your current room's room center
					BoardCell thisRoomCell = roomMap.get(thisRoomInitial).getCenterCell();
					//add the corresponding room center to the adjacency list of current room center.
					thisRoomCell.addAdj(correspondingCell);
				}
			}
		}
	}

	/*
	 * calculates legal targets for a move from startCell of length path length
	 */
	public void calcTargets(BoardCell startCell, int pathLength) {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}

	public void findAllTargets(BoardCell thisCell, int numSteps) {
		// Iterates through the adjacency list for the given cell
		for (BoardCell adjCell : thisCell.getAdjList()) {

			// Only continues recursive function if the cell has not been visited and is not occupied
			if (!visited.contains(adjCell) && (!adjCell.getOccupied()|| adjCell.isRoomCenter())) {

				visited.add(adjCell);

				// Stops recursion if out of steps or in a room
				if (numSteps == 1 || adjCell.isRoom()) {
					targets.add(adjCell);
				} else {
					findAllTargets(adjCell, numSteps - 1);
				}

				visited.remove(adjCell);
			}
		}
	}
	
	
	/********************************
	 * ACCUSATIONS AND SUGGESTIONS
	 *********************************/
	
	/*
	 * check an accusation made to see if it matches the solution
	 */
	public boolean checkAccusation(Card weapon, Card room, Card player){
		//if the theAnswer's cards are the same as the accused, return true
		if (theAnswer.getPerson().equals(player) && theAnswer.getRoom().equals(room) && theAnswer.getWeapon().equals(weapon)) {
			return true;
		}
		return false;
	}
	
	/*
	 * return the first card disputed in the suggestion 
	 */
	public Card handleSuggestion(Card person, Card weapon, Card room, ArrayList<Player> players, Player suggestingPlayer) {
		this.suggestingPlayer = suggestingPlayer;
		for (Player p : allPlayers) {
			
			if (p.getName().equals(person.getName())) {
		
				p.setLocation(suggestingPlayer.getLocation());
				repaint();
			}
		}
		
		//go through all the players in order
		for (Player p : players) {
			//if they are the one suggestion, they cannot disprove so you move on.
			if (p == suggestingPlayer) {
				continue;
			}
			//if we haven't disproved any card, then check if a player can disprove
			else {
				//return the first card that can be disproved
				if(p.disproveSuggestion(person, weapon, room) == person) {
					disprovingPlayer = p;
					return person;
				}
				else if (p.disproveSuggestion(person, weapon, room) == weapon) {
					disprovingPlayer = p;
					return weapon;
				}
				else if (p.disproveSuggestion(person, weapon, room) == room) {
					disprovingPlayer = p;
					return room;
				}
				
			}
		}
		
		
		//if no cards can be disproved, return null.
		return null;
	}
	
	/**
	 * paintComponent: paints the board using graphics. 
	 * Calls functions from board cell and players
	 */
	public void paintComponent(Graphics g) {
		ArrayList<BoardCell> cells = new ArrayList<BoardCell>();

		//get the width and height of the board only
		boardHeight = gameFrame.getHeight() - controlPanel.getHeight();
		boardWidth = gameFrame.getWidth() - cardPanel.getWidth();

		super.paintComponent(g);
		
		//Calculate width and height of each cell
		cellWidth = boardWidth / numColumns;
		cellHeight = boardHeight / numRows;
		
		//add the cells to an array list of all cells
		for (int row = 0; row < numRows; row ++) {
			for (int col = 0; col < numColumns; col ++) {
				cells.add(grid[row][col]);
			}
		}
		
		//draw the board cells
		for (BoardCell b : allCells) {
			if (targets.contains(b) && b.isRoomCenter() && isHumanTurn()) {
				//add the rooms with the same initial to the target list
				for (BoardCell c : allCells) {
					if (c.getInitial() == b.getInitial()) {
						targets.add(c);
						c.draw(g, cellWidth, cellHeight, this);
					}
				}
				targets.add(b);
				b.draw(g, cellWidth, cellHeight, this);
			}
			else {
				b.draw(g, cellWidth, cellHeight, this);
			}
			if (!isHumanTurn()) {
				targets.clear();
			}
			
		}
		
		//draw the players
		for (Player p : allPlayers) {
			int playerCol = p.getLocation().getCol();
			int playerRow = p.getLocation().getRow();
			int counter = 1;
			
			if (p.getLocation().isRoomCenter() && p.getLocation().getOccupied()) {
				
				for (Player player: allPlayers) {
					if (player.getLocation() == p.getLocation()) {
						if (allPlayers.indexOf(player) < allPlayers.indexOf(p)) {
							counter ++;
						}
					}
				}		
			}
			else {
				counter = 0;
			}
			p.draw(g, playerCol, playerRow, cellWidth, cellHeight, counter);
			counter = 0;
		}
		
		//draw the rooms
		for (Map.Entry<Character, Room> entry: roomMap.entrySet() ) {
			if (!(entry.getKey() == 'W') && !(entry.getKey() == 'X')) {
				int labelCol = roomMap.get(entry.getKey()).getLabelCell().getCol() * cellWidth;
				int labelRow = roomMap.get(entry.getKey()).getLabelCell().getRow() * cellHeight;
				roomMap.get(entry.getKey()).drawLabel(g, labelCol, labelRow);
			}

		}
		
	}
	
	@Override
	/**
	 * mouseClicked: checks for user mouse clicks 
	 */
	public void mouseClicked(MouseEvent e) {
		//make sure it is the humans turn when they click
		if (human.isTurn() == false) {
			JOptionPane.showMessageDialog(this, "It is not your turn");
		}
		else {
			//get the point they clicked on and convert to cell
			BoardCell clickedCell = null;
			Point p = e.getPoint();
			int row = ((p.y/cellHeight) - 2);
			int col = (p.x/cellWidth);

			//ensure it is in bounds
			if(row <= numRows && col <= numColumns) {
				clickedCell = grid[row][col];
			}
			
			
			//if the clicked cell is one of the targets, then move the player to that cell
			if (targets.contains(clickedCell)) {
				makingAccusation = false;
				if (clickedCell.isRoom()) {
					Room r = roomMap.get(clickedCell.getInitial());
					clickedCell = r.getCenterCell();
				}
				clickedCells.add(clickedCell);
				currentPlayer.setLocation(clickedCell);
				targets.clear();
				repaint();
				human.setMoved(true);
				if (clickedCell.isRoomCenter()) {
					String roomName = null;
					Character currentRoomInitial = clickedCell.getInitial();
					
					for (Map.Entry<Character, Room> entry: roomMap.entrySet() ) {
						if ((entry.getKey() == currentRoomInitial)) {
							roomName = entry.getValue().getName();
						}

					}
					clickedCell.setOccupied(true);
					setMakingSuggestion(true);
					makeSuggestionHuman(roomName);
				}
				
			}
			//if not show error message
			else {
				JOptionPane.showMessageDialog(this, "You cannot move in that spot");
			}
		}
	}
	
	/**
	 * handleNextButton: tells the computer what to do 
	 * when the next button is pressed. 
	 */
	public void handleNextButton(int currentPlayerIndex) {
		//update current player
		setCurrentPlayer(this.getPlayers().get(currentPlayerIndex));
		//roll the dice
		this.dieRoll = rollDie();
		int row = currentPlayer.getLocation().getRow();
		int col = currentPlayer.getLocation().getCol();
		//calculate targets
		calcTargets(this.getCell(row, col), this.dieRoll);
		
		//check if human or computer
		if (currentPlayer.getType().equals("Human")) {
			human.setMoved(false);
			human.setTurn(true);
			repaint();
			currentPlayer.doMove(this.getTargets(), this);
			
		}
		else if (currentPlayer.getType().equals("Computer")) {
			setMakingAccusation(true);
			human.setTurn(false);
			//TODO: MAKE ACCUSATION?
			
			
			//see if the computer player can make an accusation
			//this will only occur if the size of the seen cards + the 3 cards in their hand is equal to the size of all cards minus the 3 cards in the solution
			if (currentPlayer.getSeenCards().size() == allCards.size() - 3) {
				for (Card c : allCards) {
					if (!(currentPlayer.getCards().contains(c)) && !(currentPlayer.getSeenCards().contains(c))) {
						if (c.getType() == CardType.PERSON) {
							accusedPerson = c;
						}
						else if (c.getType() == CardType.ROOM) {
							accusedRoom = c;
						}
						else if (c.getType() == CardType.WEAPON) {
							accusedWeapon = c;
						}
					}
				}
				
				//check the accusation made by the computer
				if (checkAccusation(accusedWeapon, accusedRoom, accusedPerson)) {
					JOptionPane.showMessageDialog(this, "Someone else found the right solution. You lose!");
					System.exit(0);
				}
			}
			else {
				currentPlayer.setLocation(currentPlayer.doMove(this.getTargets(), this));
				repaint();
				//handle the suggestion by the computer
				if (currentPlayer.getLocation().isRoomCenter()) {
					Solution computerSuggestion = currentPlayer.createSuggestion(this);

					Card computerSuggestedRoom = computerSuggestion.getRoom();
					Card computerSuggestedPerson = computerSuggestion.getPerson();
					Card computerSuggestedWeapon = computerSuggestion.getWeapon();
					
					Card disprovedCard = null;
					disprovedCard = handleSuggestion(computerSuggestedPerson, computerSuggestedWeapon, computerSuggestedRoom, allPlayers, currentPlayer);
					
					if (disprovedCard == null) {
						controlPanel.setGuessResult("No new clue", currentPlayer);
					}
					else {
						for (Card c : allCards) {
							if (disprovedCard == c) {
								currentPlayer.addSeenCard(disprovedCard);
								controlPanel.setGuessResult("Suggestion disproved by " + disprovingPlayer.getName(), disprovingPlayer);
							}
						}
					}
					
					controlPanel.setGuess((computerSuggestedPerson.getName() + ", " + computerSuggestedWeapon.getName() + ", " + computerSuggestedRoom.getName()), currentPlayer);

				}
			}
			
		}		
	}
	
	/**
	 * handleMakeAccusationButton: tells the game what to do 
	 * when the make accusation button is pressed
	 */
	public void handleMakeAccusationButton() {
		if (human.isTurn() == false) {
			JOptionPane.showMessageDialog(this, "You can only make an accusation on your turn");
		}
		else {
			accusationDialog.setTitle("Make a suggestion");
			accusationDialog.setVisible(true);
			accusationDialog.setSize(300, 150);
			accusationDialog.setModal(true);
			accusationDialog.setLayout(new GridLayout(4,2));
			
			JLabel roomLabel = new JLabel("Room");
			accusedRoomCombo = new JComboBox<String>();
			for (Card c : roomCards) {
				accusedRoomCombo.addItem(c.getName());
			}
			
			JLabel peopleLabel = new JLabel("Person");
			accusedPeopleCombo = new JComboBox<String>();
			for (Card c : peopleCards) {
				accusedPeopleCombo.addItem(c.getName());
			}
			
			JLabel weaponLabel = new JLabel("Weapon");
			accusedWeaponCombo = new JComboBox<String>();
			for (Card c : weaponCards) {
				accusedWeaponCombo.addItem(c.getName());
			}
			
			accusedPeopleCombo.addActionListener(this);
			accusedRoomCombo.addActionListener(this);
			accusedWeaponCombo.addActionListener(this);
			
			JButton submitButton = new JButton("Submit");
			JButton cancelButton = new JButton("Cancel");
			submitButton.addActionListener(this);
			cancelButton.addActionListener(this);
			
			accusationDialog.add(roomLabel);
			accusationDialog.add(accusedRoomCombo);
			accusationDialog.add(peopleLabel);
			accusationDialog.add(accusedPeopleCombo);
			accusationDialog.add(weaponLabel);
			accusationDialog.add(accusedWeaponCombo);
			accusationDialog.add(submitButton);
			accusationDialog.add(cancelButton);
			
		}
		
	}
	
	public void makeSuggestionHuman(String roomName) {	
		suggestionDialog = new JDialog();
		suggestionDialog.setTitle("Make a suggestion");
		suggestionDialog.setVisible(true);
		suggestionDialog.setSize(300, 150);
		suggestionDialog.setModal(true);
		suggestionDialog.setLayout(new GridLayout(4,2));
		
		JLabel roomLabel = new JLabel("Room");
		JLabel roomField = new JLabel(roomName);
		
		
		//add all the player cards to combo box
		JLabel peopleLabel = new JLabel("Person");
		for (Card c : peopleCards) {
			people.addItem(c.getName());
		}
		
		//add all the weapon cards to combo box
		JLabel weaponLabel = new JLabel("Weapon");
		for (Card c : weaponCards) {
			weapon.addItem(c.getName());
		}
		
		people.addActionListener(this);
		weapon.addActionListener(this);
		
		
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
		submitButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		suggestionDialog.add(roomLabel);
		suggestionDialog.add(roomField);
		suggestionDialog.add(peopleLabel);
		suggestionDialog.add(people);
		suggestionDialog.add(weaponLabel);
		suggestionDialog.add(weapon);
		suggestionDialog.add(submitButton);
		suggestionDialog.add(cancelButton);
		
		for (Card c : roomCards) {
			if (c.getName() == roomName) {
				suggestedRoom = c;
			}
		}

	}
	
	public void makeSuggestionComputer() {
		if (currentPlayer.getType().equals("Computer")) {
			System.out.println(currentPlayer.createSuggestion(this));
			
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		//add combo boxes
		if (makingSuggestion) {
			if (e.getSource() == people) {
				for (Card c : peopleCards) {
					if (c.getName() == people.getSelectedItem().toString()) {
						suggestedPerson = c;
					}
				}
			}
			else if (e.getSource() == weapon) {
				for (Card c : weaponCards) {
					if (c.getName() == weapon.getSelectedItem().toString()) {
						suggestedWeapon = c;
					}
				}
			}
		}
		
		else if (makingAccusation) {
			if (e.getSource() == accusedRoomCombo) {
				for (Card c : roomCards) {
					if (c.getName() == accusedRoomCombo.getSelectedItem().toString()) {
						accusedRoom = c;
					}
				}
			}
			else if (e.getSource() == accusedWeaponCombo) {
				for (Card c : weaponCards) {
					if (c.getName() == accusedWeaponCombo.getSelectedItem().toString()) {
						accusedWeapon = c;
					}
				}
			}
			else if (e.getSource() == accusedPeopleCombo) {
				for (Card c : peopleCards) {
					if (c.getName() == accusedPeopleCombo.getSelectedItem().toString()) {
						accusedPerson = c;
					}
				}
			}
		}
		
		//check for button presses
		String buttonName = e.getActionCommand();
		switch(buttonName) {
		case "Submit": 
			if (makingSuggestion == true) {
				if (suggestionDialog == null) {
					JOptionPane.showMessageDialog(this, "You must input a suggestion");
				}
				else {
					Card disprovedCard = null;
					disprovedCard = handleSuggestion(suggestedPerson, suggestedWeapon, suggestedRoom, allPlayers, human);
					if (disprovedCard == null) {
						controlPanel.setGuess((suggestedPerson + ", " + suggestedWeapon + ", " + suggestedRoom), suggestingPlayer);
						controlPanel.setGuessResult("No new clue", currentPlayer);
					}
					else {
						String cardName = null;
						for (Card c : allCards) {
							if (disprovedCard == c) {
								cardName = c.getName();
								human.addSeenCard(disprovedCard);
								cardPanel.updatePanels();
								controlPanel.setGuess((suggestedPerson + ", " + suggestedWeapon + ", " + suggestedRoom), suggestingPlayer);
								controlPanel.setGuessResult("Suggestion disproved by " + disprovingPlayer.getName(), disprovingPlayer);
							}
						}
					}
				}
				suggestionDialog.dispose();
				setMakingSuggestion(false);
			}
			else if (makingAccusation == true) {
				accusationDialog.dispose();
				System.out.println(theAnswer);
				if (checkAccusation(accusedWeapon, accusedRoom, accusedPerson)) {
					JOptionPane.showMessageDialog(this, "Youre accusation: \n" + accusedRoom + "\n" + accusedPerson + "\n" + accusedWeapon + "\n Your accusation was correct!! YOU WIN");
					System.exit(0);
				}
				else {
					JOptionPane.showMessageDialog(this, "Youre accusation: \n" + accusedRoom + "\n" + accusedPerson + "\n" + accusedWeapon + "\n YOUR'E WRONG! Better luck next time!");
					System.exit(0);
				}
				setMakingAccusation(false);
			}
			break;
		case "Cancel":
			//if cancel is pressed, close the dialog message
			suggestionDialog.dispose();
			accusationDialog.dispose();
			break;
		}
		
		
	}

	public int rollDie() {
		Random random = new Random();
		this.dieRoll = random.nextInt(6) + 1;
		return dieRoll;
	}
	

	/********************************
	 * GETTERS AND SETTERS
	 *********************************/

	public BoardCell getCell(int row, int col) {
		// create new cell with row and column
		return grid[row][col];
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}
	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public Room getRoom(BoardCell cell2) {
		char initial = cell2.getInitial();
		return roomMap.get(initial);
	}

	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		return grid[i][j].getAdjList();
	}
	
	public ArrayList<Player> getPlayers() {
		return allPlayers;
	}
	
	public ArrayList<Card> getCards() {
		return allCards;
	}

	public ArrayList<Card> getWeaponCards() {
		return weaponCards;
	}

	public ArrayList<Card> getRoomCards() {
		return roomCards;
	}

	public ArrayList<Card> getPeopleCards() {
		return peopleCards;
	}
	
	public ArrayList<Card> getDealtCards() {
		return dealtCards;
	}

	public Solution getSolution() {
		return theAnswer;
	}
	
	public void setSolution(Card person, Card weapon, Card room) {
		this.theAnswer = new Solution(person, weapon, room);
	}

	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}

	public void setSeenCards(ArrayList<Card> seenCards) {
		this.seenCards = seenCards;
	}
	
	public Map<Character, Room> getRoomMap() {
		return roomMap;
	}

	public HumanPlayer getHuman() {
		return human;
	}

	public void setHuman(HumanPlayer human) {
		this.human = human;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getDieRoll() {
		return dieRoll;
	}

	public void setDieRoll(int dieRoll) {
		this.dieRoll = dieRoll;
	}
	
	public boolean isHumanTurn() {
		return humanTurn;
	}

	public void setHumanTurn(boolean humanTurn) {
		this.humanTurn = humanTurn;
	}
	
	public Set<BoardCell> getAllCells() {
		return allCells;
	}
	
	public boolean isMakingAccusation() {
		return makingAccusation;
	}

	public void setMakingAccusation(boolean makingAccusation) {
		this.makingAccusation = makingAccusation;
	}

	public boolean isMakingSuggestion() {
		return makingSuggestion;
	}

	public void setMakingSuggestion(boolean makingSuggestion) {
		this.makingSuggestion = makingSuggestion;
	}
	
	public GameControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(GameControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public KnownCardPanel getCardPanel() {
		return cardPanel;
	}

	public void setCardPanel(KnownCardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	public ClueGame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(ClueGame gameFrame) {
		this.gameFrame = gameFrame;
	}
	

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	
}
