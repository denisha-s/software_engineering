package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * GameControlPanel class: This class create a panel that displays the current turn, roll, guess, 
 * and guess result in a game of Clue. The panel is divided into two sections, with the top section containing the turn 
 * and roll information and buttons for making accusations and advancing to the next turn. The bottom section contains the 
 * current guess and the result of the previous guess.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 4/4/2023
 * Sources: None
 */

public class GameControlPanel extends JPanel implements ActionListener{
	
	private JTextField turnField = new JTextField();
	private JTextField rollAmount = new JTextField();
	private static Board board;
	private JTextField guess = new JTextField();
	private JTextField guessResult = new JTextField();
	private int currentPlayerIndex = 0;
	
	/**
	 * Constructor for the panel, it does 90% of the work
	 */
	public GameControlPanel(Board board)  {
		// Create a layout with 2 rows
				this.board = board;
				setLayout(new GridLayout(2,0));
				JPanel panel = createTopPanel();
				add(panel);
				panel = createBottomPanel();
				add(panel);
	}
	
	// Create the top panel, which contains the turn field, roll field, and buttons
	private JPanel createTopPanel() {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,4));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Top"));
		JButton agree = new JButton("Make Accusation");
		JButton disagree = new JButton("NEXT!");
		agree.addActionListener(this);
		disagree.addActionListener(this);
		subPanel = createTurnPanel();
		panel.add(subPanel);
		subPanel = createRollPanel();
		panel.add(subPanel);
		panel.add(agree);
		panel.add(disagree);
		return panel;
	}
	
	// Create the bottom panel, which contains the guess and guess result labels
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Bottom"));
		JPanel subPanel = createGuessPanel();
		panel.add(subPanel);
		subPanel = createGuessResultPanel();
		panel.add(subPanel);
		return panel;
	}
	
	// Create the turn panel, which displays whose turn it is
	private JPanel createTurnPanel() {
	 	JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(2,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Turn"));
		
		JLabel turnLabel = new JLabel("Whose Turn?");
		panel.add(turnLabel);
		panel.add(turnField);
		return panel;
	}
	
	// Create the roll panel, which displays the roll amount
	private JPanel createRollPanel() {
	 	JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Roll"));
		
		JLabel rollLabel = new JLabel("Roll:");
		panel.add(rollLabel);
		panel.add(rollAmount);
		
		return panel;
	}
	
	// Create the guess panel, which displays the current guess
	private JPanel createGuessPanel() {
	 	JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,1));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		guess.setEditable(false);
		panel.add(guess);
		
		return panel;
	}
	
	// Creates a panel that displays the current guess.
	private JPanel createGuessResultPanel() {
	 	JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		guessResult.setEditable(false);
		panel.add(guessResult);
		return panel;
	}
 
	// This method sets the current turn information, including player name, color, and roll.
	public void setTurn(Player player, int roll) {
		String name = player.getName();
		this.turnField.setText(name);
		this.turnField.setBackground(stringToColor(player.getColor()));
		this.rollAmount.setText(Integer.toString(roll));
	}
	
	public void setGuess(String guess, Player suggestingPlayer) {
		this.guess.setBackground(stringToColor(suggestingPlayer.getColor()));
		this.guess.setText(guess);
	}
	
	public void setGuessResult(String guessResult, Player disprovingPlayer) {
		this.guessResult.setBackground(stringToColor(disprovingPlayer.getColor()));
		this.guessResult.setText(guessResult);
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		switch(buttonName) {
		case "NEXT!": 
			if (!board.getHuman().isMoved()) {
				JOptionPane.showMessageDialog(this, "You must complete your move before moving onto the next player");
			}
			else {
				//increment player index
				if (currentPlayerIndex < 5) {
					currentPlayerIndex++;
				}
				else {
					currentPlayerIndex = 0;
				}
				//call board method to handle next button
				board.handleNextButton(currentPlayerIndex);
				setTurn(board.getCurrentPlayer(), board.getDieRoll());
			}
			
			break;
		case "Make Accusation":
			if (board.isHumanTurn() && board.isMakingAccusation()) {
				board.setMakingAccusation(true);
				board.handleMakeAccusationButton();
			}
			else {
				JOptionPane.showMessageDialog(this, "You cannot make an accusation now");
			}
			break;
		}
	}
	
	private Color stringToColor (String colorString) {
		Color color = Color.white;
		switch (colorString) {
		case "Pink":
			color = Color.pink;
			break;
		case "Blue":
			color = Color.blue;
			break;
		case "Red":
			color = Color.red;
			break;
		case "Green":
			color = Color.green;
			break;
		case "Orange":
			color = Color.orange;
			break;
		case "Magenta":
			color = Color.magenta;
			break;
		}
		return color;
	}
	
	/**
	 * Main to test the panel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel(board);  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 200);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		frame.setTitle("Clue Game"); //set title
	
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Taylor", "Red", "Computer", new BoardCell(0,0)), 5);

	}
}
