package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * CardPanel class: This class extends JPanel and is part of a bigger project for the game "Clue". 
 * The purpose of this class is to create a panel that displays the cards a player has and the cards they have seen.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 4/5/2023
 * Sources: None
 */

public class KnownCardPanel extends JPanel{
	private ArrayList<JTextField> roomHandCards = new ArrayList<JTextField>();
	private ArrayList<JTextField> roomSeenCards = new ArrayList<JTextField>();
	private ArrayList<JTextField> peopleHandCards = new ArrayList<JTextField>();
	private ArrayList<JTextField> peopleSeenCards = new ArrayList<JTextField>();
	private ArrayList<JTextField> weaponHandCards = new ArrayList<JTextField>();
	private ArrayList<JTextField> weaponSeenCards = new ArrayList<JTextField>();
	
	public static Board board;
	private static Player human;

	private ArrayList<Card> inHandCards = new ArrayList<Card>();
	private Set<Card> seenCards = new HashSet<Card>();



	/**
	 * Constructor for the panel, it does 90% of the work
	 * @throws BadConfigFormatException 
	 * @throws IOException 
	 */
	public KnownCardPanel(Player human) throws BadConfigFormatException, IOException  {
		this.human = human;
		updatePanels();
	}
	
	// Method that creates the known cards panel
	private void createKnownCardsPanel() {
		//JPanel this = new JPanel();
		this.removeAll();
		this.setSize(750, 750);
		this.setLayout(new GridLayout(3,1));
		this.setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
		//add people panel
		JPanel subPanel = createPeoplePanel();
		this.add(subPanel);
		//add room panel
		subPanel = createRoomsPanel();
		this.add(subPanel);
		//add weapon panel
		subPanel = createWeaponsPanel();
		this.add(subPanel);
	}
	
	
	/**
	 * Method that creates the panel for the people cards
	 * @return JPanel representing the panel for the people cards
	 */
	private JPanel createPeoplePanel () {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		
		panel.setSize(180, 250);
		
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(2,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		
		// Create an ArrayList of JTextFields to hold the inHand people cards
		peopleHandCards.clear();
		for (Card card : inHandCards) {
			if (card.getType() == CardType.PERSON) {
				if (card.getName().length() < 10) {
					peopleHandCards.add(new JTextField(card.getName() + "     "));
				}
				else {
					peopleHandCards.add(new JTextField(card.getName()));
				}
				
			}
		}
		
		if (peopleHandCards.size() == 0) {
			peopleHandCards.add(new JTextField("None"));
		}
		
		//add the in Hand Panel
		subPanel = createInHandPanel(peopleHandCards.size());
		panel.add(subPanel);
		
		for (JTextField card : peopleHandCards) {
			card.setSize(15, 5);
			subPanel.add(card);

		}
			
		// Create an ArrayList of JTextFields to hold the seen people cards
		peopleSeenCards.clear();
		for (Card card : seenCards) {
			if (card.getType() == CardType.PERSON) {
				if (card.getName().length() < 10) {
					peopleSeenCards.add(new JTextField(card.getName() + "     "));
				}
				else {
					peopleSeenCards.add(new JTextField(card.getName()));
				}			}
		}
		
		if (peopleSeenCards.size() == 0) {
			peopleSeenCards.add(new JTextField("None"));
		}
		
		//add the seen panel
		subPanel = createSeenPanel(peopleSeenCards.size());
		panel.add(subPanel);
		
		for (JTextField card : peopleSeenCards) {
			card.setSize(15, 5);
			subPanel.add(card);

		}
		
		//make sure JTextFields are not editable
		for (JTextField field : peopleHandCards) {
			field.setEditable(false);
		}
		for (JTextField field : peopleSeenCards) {
			field.setEditable(false);
		}
		
		return panel;
	}
		
	/**
	 * Creates a JPanel that displays the player's hand and seen cards for rooms.
	 * @return The JPanel that displays the player's room cards.
	 */
	private JPanel createRoomsPanel () {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		
		panel.setSize(180, 250);
		
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(2,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		//add hand cards
		roomHandCards.clear();
		for (Card card : inHandCards) {
			if (card.getType() == CardType.ROOM) {
				if (card.getName().length() < 10) {
					roomHandCards.add(new JTextField(card.getName() + "     "));
				}
				else {
					roomHandCards.add(new JTextField(card.getName()));
				}			}
		}
		
		if (roomHandCards.size() == 0) {
			roomHandCards.add(new JTextField("None"));
		}
		
		//add the in Hand Panel
		subPanel = createInHandPanel(roomHandCards.size());
		panel.add(subPanel);
		
		for (JTextField card : roomHandCards) {
			card.setSize(15, 5);
			subPanel.add(card);

		}
			
		//add seen cards	
		roomSeenCards.clear();
		for (Card card : seenCards) {
			if (card.getType() == CardType.ROOM) {
				if (card.getName().length() < 10) {
					roomSeenCards.add(new JTextField(card.getName() + "     "));
				}
				else {
					roomSeenCards.add(new JTextField(card.getName()));
				}			}
		}
		
		if (roomSeenCards.size() == 0) {
			roomSeenCards.add(new JTextField("None"));
		}
		
		//add the seen panel
		subPanel = createSeenPanel(roomSeenCards.size());
		panel.add(subPanel);
		
		for (JTextField card : roomSeenCards) {
			card.setSize(15, 5);
			subPanel.add(card);

		}
		
		//make sure JTextFields are not editable
		for (JTextField field : roomHandCards) {
			field.setEditable(false);
		}
		for (JTextField field : roomSeenCards) {
			field.setEditable(false);
		}
		return panel;
	}
	
	/**
	 * Creates a panel displaying the weapons that the player knows about.
	 * @return JPanel The panel displaying the player's known weapons.
	 */
	private JPanel createWeaponsPanel () {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		
		panel.setSize(180, 250);
		
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(2,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		//add hand cards
		weaponHandCards.clear();
		for (Card card : inHandCards) {
			if (card.getType() == CardType.WEAPON) {
				if (card.getName().length() < 10) {
					weaponHandCards.add(new JTextField(card.getName() + "     "));
				}
				else {
					weaponHandCards.add(new JTextField(card.getName()));
				}			}
		}
		
		if (weaponHandCards.size() == 0) {
			weaponHandCards.add(new JTextField("None"));
		}
		
		//add the in Hand Panel
		subPanel = createInHandPanel(weaponHandCards.size());
		panel.add(subPanel);
		
		for (JTextField card : weaponHandCards) {
			card.setSize(15, 5);
			subPanel.add(card);

		}
			
		//add seen cards
		weaponSeenCards.clear();
		for (Card card : seenCards) {
			if (card.getType() == CardType.WEAPON) {
				if (card.getName().length() < 10) {
					weaponSeenCards.add(new JTextField(card.getName() + "     "));
				}
				else {
					weaponSeenCards.add(new JTextField(card.getName()));
				}			}
		}
		
		if (weaponSeenCards.size() == 0) {
			weaponSeenCards.add(new JTextField("None"));
		}
		
		//add the seen panel
		subPanel = createSeenPanel(weaponSeenCards.size());
		panel.add(subPanel);
		
		
		for (JTextField card : weaponSeenCards) {
			card.setSize(15, 5);
			subPanel.add(card);

		}
		
		//make sure JTextFields are not editable
		for (JTextField field : weaponHandCards) {
			field.setEditable(false);
		}
		for (JTextField field : weaponSeenCards) {
			field.setEditable(false);
		}
		return panel;
	}
	
	/**
     * Create a panel to display the cards in the player's hand 
     * @param numRows sets the number of rows to display the cards in
     * @return a panel displaying the cards in the player's hand
     */
	private JPanel createInHandPanel(int numRows) {
	 	JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(numRows,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "In Hand"));

		return panel;
	}
	
    /**
     * Create a panel to display the cards the player has seen
     * @param numRows sets the number of rows to display the cards in
     * @return a panel displaying the cards the player has seen
     */
	private JPanel createSeenPanel(int numRows) {
	 	JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(numRows,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Seen"));
		
		return panel;
	}
	
	/**
     * when the seen cards are updated, make sure to redraw the 
     * panel with updates
    */
	protected void updatePanels() {
		//get the human's cards
		//setLayout(new GridLayout(1,0));
		inHandCards = human.getCards();
		seenCards = human.getSeenCards();
		//create the panel
		this.createKnownCardsPanel();
        // revalidate the panel to reflect the changes
		this.revalidate();
	}
	
	/**
	 * Main to test the panel
	 * 
	 * @param args command-line arguments
	 * @throws IOException if an I/O error occurs
	 * @throws BadConfigFormatException if the game configuration is invalid
	 */
	public static void main(String[] args) throws BadConfigFormatException, IOException {
		// setup board and player
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		
		human = board.getPlayers().get(0);

		//create the panel
		KnownCardPanel panel = new KnownCardPanel(human);  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(180, 750);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		frame.setTitle("Clue Game"); //set title
		
		//test to see if the seen cards will update after a "Round" 
		human.addSeenCard(new Card("RoomTest", CardType.ROOM));
		human.addSeenCard(new Card("RoomTest2", CardType.ROOM));
		human.addSeenCard(new Card("WeaponTest", CardType.WEAPON));
		human.addSeenCard(new Card("PersonTest", CardType.PERSON));

	    //get input to test, will act as a "round"
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Type something and click enter to see the undates seen cards:");
	    String test = reader.readLine();
	    System.out.println(test);
	    
	    //update after the "round"
	    panel.updatePanels();	
	}
}

