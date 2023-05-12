package levelPieces;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import gameEngine.Moveable;

public class LostMoney extends GamePiece implements Moveable {
	
	private int location;
	private char symbol;
	
	public LostMoney(){
		super('$', "Lost Money", 19);
		location = 19;
		symbol = '$';
	}
	public LostMoney(char symbol, String description, int location){
		super(symbol, description, location);
		this.location = location;
		this.symbol = symbol;
	}

	@Override
	public InteractionResult interact(Drawable[] pieces, int playerLocation) {
		// TODO Auto-generated method stub
		if(playerLocation == this.getLocation()) {
			return InteractionResult.GET_POINT;
			
		} else {
			return InteractionResult.NONE;
		}
	}


	public int getLocation() {
		return location;
	}


	public void setLocation(int location) {
		this.location = location;
	}


	public String getSymbol() {
		// TODO Auto-generated method stub
		return Character.toString(symbol);
	}


	public void move(Drawable[] gameBoard, int playerLocation) {
		/*
		 * EVERY TURN, THE MONEY MOVES TO A RANDOM LOCATION
		 */
		int index = 0;
		int maxIndex = GameEngine.BOARD_SIZE - 1;
		int minIndex = 0;
		
		
		index = (int) Math.floor(Math.random()*(maxIndex - minIndex + 1) + minIndex);			//CALCULATES AN INDEX IN RANGE OF BOARD LENGTH
		while(gameBoard[index] != null){
			index = (int) Math.floor(Math.random()*(maxIndex - minIndex + 1) + minIndex);		//ENSURES THE SPACE IS EMPTY
		}
		
		int tempLoc = this.getLocation();
		this.setLocation(index);								//MOVE THE LOST MONEY.
		gameBoard[this.getLocation()] = this;
		gameBoard[tempLoc] = null;
	
		
	}
}
