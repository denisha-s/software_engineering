/*
 * ClueGame class: This class extends JPanel and is part of a bigger project for the game "Clue". 
 * The purpose of this class is to create a panel that displays the cards a player has and the cards they have seen.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 4/10/2023
 * Sources: None
 */

package clueGame;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ClueGame extends JFrame {
	//attributes
	private static Board board;
	private static Player human;
	GameControlPanel controlPanel;
	KnownCardPanel cardPanel;
	
	/**
	 * Constructor for the panel
	 * @throws BadCon
	 * figFormatException 
	 * @throws IOException
	 */
	public ClueGame( ) throws BadConfigFormatException, IOException {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();	
		human = board.getHuman();
		board.setCurrentPlayer(human);
		addMouseListener(board);
		board.setHumanTurn(true);
		
		System.out.println(board.getSolution());

		
		cardPanel = new KnownCardPanel(human); // create the cards panel
		controlPanel = new GameControlPanel(board);
		
		board.setCardPanel(cardPanel);
		board.setControlPanel(controlPanel);;
		board.setGameFrame(this);
		
		add(board, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		add(cardPanel, BorderLayout.EAST);
		

		
		setSize(550,550);	// set size of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		setVisible(true); // make it visible
		
		int row = human.getLocation().getRow();
		int col = human.getLocation().getCol();
		board.getHuman().setTurn(true);
		board.getHuman().setMoved(false);
		board.rollDie();
		//calculate targets
		board.calcTargets(board.getCell(row, col), board.getDieRoll());
		
		JOptionPane.showMessageDialog(null , "You are Selena. \n Can you find the solution \n before the computers?", "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE); //displays welcome to clue game

		board.repaint();
		
		controlPanel.setTurn(board.getCurrentPlayer(), board.getDieRoll());
	}

	/**
	 * main: created the frame and adds the panels. some testing is being done in here for now
	 * @throws BadConfigFormatException 
	 * @throws IOException 
	 */
	public static void main (String[] args) throws BadConfigFormatException, IOException {		
		//Create clue game object
		ClueGame game = new ClueGame();
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
	
}