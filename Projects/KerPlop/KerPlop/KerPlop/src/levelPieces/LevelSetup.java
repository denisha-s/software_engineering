package levelPieces;

import gameEngine.Drawable;
import gameEngine.Moveable;
import gameEngine.GameEngine;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelSetup {
	
	Drawable [] board = new Drawable[GameEngine.BOARD_SIZE];				//Game board
	ArrayList<GamePiece> interactingPieces = new ArrayList<GamePiece>();	//list of pieces that can interact
	ArrayList<Moveable> moveable = new ArrayList<Moveable>();
	
	/*
	 * SET ALL BOARD SPACES TO NULL
	 */
	public LevelSetup(){
		super();
		for (int i=0; i<board.length; i++ ){
			board[i] = null;
		}
	}
	
	public void createLevel(int levelNum){
		// can make all index on board null and add pieces to board
		// or use switch case statement
		switch(levelNum) {
		case 1:
			System.out.println("Level 1 Legend:");
			System.out.println("P - Player (you)" + '\n' + "G - Gold (what we are after)" + '\n' + "R - Rock (does nothing)" + '\n'
					+ "L - Lava Pit (can kill you)" + '\n' + "S - Snake (anoying)" + '\n' + "O - Black Hole (randomly transports you)" + '\n'
					+ "$ - Lost Money (special)");

			
			/*
			 * CREATE ALL OBJECTS FOR BOARD
			 */
			Rock newRock = new Rock();
			LavaPit lava = new LavaPit();
			Snake snake = new Snake('S', "Level 1 Snake", 7);		
			BlackHole blackHole = new BlackHole();
			Gold gold = new Gold();
			LostMoney firstPoint = new LostMoney();

			/*
			 * ADD ALL PIECES TO BOARD
			 */
			addPiece(newRock, newRock.getLocation());
			addPiece(lava, lava.getLocation());
			addPiece(snake, snake.getLocation());
			addPiece(blackHole, blackHole.getLocation());
			addPiece(gold, gold.getLocation());
			addPiece(firstPoint, firstPoint.getLocation());
			
			/*
			 * ADD PIECES THAT INTERACT TO THE ARRAYLIST
			 */
			interactingPieces.add(lava);
			interactingPieces.add(snake);
			interactingPieces.add(blackHole);
			interactingPieces.add(gold);
			interactingPieces.add(firstPoint);

			/*
			 * ADD PIECES THAT MOVE TO THE ARRAYLIST
			 */
			moveable.add(snake);
			moveable.add(firstPoint);
			break;
			
		case 2:
			for (int i=0; i<board.length; i++ ){
				board[i] = null;
			}
			interactingPieces = new ArrayList<GamePiece>();
			moveable = new ArrayList<Moveable>();
			System.out.println("Congratulations you have moved to level 2! Legend:");
			System.out.println("P - Player (you)" + '\n' + "G - Gold (what we are after)" + '\n' + "R - Rock (does nothing)" + '\n'
					+ "L - Lava Pit (can kill you)" + '\n' + "S - Snake (anoying)" + '\n' + "O - Black Hole (transports you to next level)" + '\n'
					+ "$ - Lost Money (special)");
			
			/*
			 * CREATE ALL OBJECTS FOR BOARD
			 */
			
			Rock l2Rock = new Rock('R', "Level 2 Rock", 15);
			LavaPit l2Lava = new LavaPit('L', "Level 2 Lava", 2);
			Snake l2Snake = new Snake('S', "Level 2 Snake", 12);
			BlackHole l2BlackHole = new BlackHole('O', "Level 2 Black Hole", 0);
			Gold l2Gold = new Gold('G', "Level 2 Rock", 4);
			LostMoney l2FirstPoint = new LostMoney('$', "Level 2 Rock", 20);
			LostMoney l2SecondPoint = new LostMoney('$', "Another Dropped Dollar", 1);
			
			/*
			 * ADD ALL PIECES TO BOARD
			 */
			addPiece(l2Rock, l2Rock.getLocation());
			addPiece(l2Lava, l2Lava.getLocation());
			addPiece(l2Snake, l2Snake.getLocation());
			addPiece(l2BlackHole, l2BlackHole.getLocation());
			addPiece(l2Gold, l2Gold.getLocation());
			addPiece(l2FirstPoint, l2FirstPoint.getLocation());
			
			/*
			 * ADD PIECES THAT INTERACT TO THE ARRAYLIST
			 */
			interactingPieces.add(l2Lava);
			interactingPieces.add(l2Snake);
			interactingPieces.add(l2BlackHole);
			interactingPieces.add(l2Gold);
			interactingPieces.add(l2FirstPoint);
			
			/*
			 * ADD PIECES THAT MOVE TO THE ARRAYLIST
			 */
			moveable.add(l2Snake);
			moveable.add(l2FirstPoint);
			
		}
		
		
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void addPiece(Drawable newPiece, int index){
		board[index] = newPiece;
		
	}
//
//	@Override
//	public String toString() {
//		String temp = "";
//		for(int i = 0; i <gameBoard.length; i++){
//			temp = temp + "|"+gameBoard[i].getSymbol();
//		}
//		return temp;
//	}
	
	public Drawable[] getBoard(){
		return board;
	}
	
	public ArrayList<Moveable> getMovingPieces(){
		return moveable;
	}
	
	public ArrayList<GamePiece> getInteractingPieces(){
		return interactingPieces;
	}
	
	public int getPlayerStartLoc(){
		return 10;
	}
	
	public static void main(String[] args){

//		GameEngine newGame = new GameEngine();
//		newGame.levelData.createLevel(1);
		
		
		
	
	}

}
