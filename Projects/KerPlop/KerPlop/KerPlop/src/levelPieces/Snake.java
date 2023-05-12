package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;
import gameEngine.Moveable;

import java.util.Random;

public class Snake extends GamePiece implements Moveable {
	
	private int location;
	private char symbol;

	public Snake(char symbol, String label, int location) {
		super(symbol, label, location);
		this.location = location;
		this.symbol = symbol;
		
	}


	public void draw() {
		// TODO Auto-generated method stub
		System.out.print(symbol);
	}



	public String getSymbol() {
		// TODO Auto-generated method stub
		return Character.toString(symbol);
	}

	public int getLocation() {
		return location;
	}
	
	
	public InteractionResult interact(Drawable[] pieces, int playerLocation){
		/*
		 * IF A PLAYER LANDS ON THE SNAKE, THEN REUTURN HIT.
		 */
		for(int i=0; i<pieces.length; i++){
			if(pieces[i] ==  null){
				continue;
			}
			Drawable temp = pieces[i];
			
			if(temp instanceof Snake){
				if(i==playerLocation){
					return InteractionResult.HIT;
				}
			}
		}
		return InteractionResult.NONE;
	}
	
	// moves piece randomly within 2 spaces
	public void move(Drawable[] gameBoard, int playerLocation){
		int distance = 0;
		int maxIndex = 2;			//calculate index that is within 2 spaces.
		int minIndex = -2;
		distance = (int) Math.floor(Math.random()*(maxIndex - minIndex + 1) + minIndex);
		if((this.getLocation() + distance > gameBoard.length) || this.getLocation() + distance < 0){
			distance = -distance;
		}
		Drawable replacement = gameBoard[getLocation() + distance];
		int tempLoc = this.getLocation();
		this.setLocation(getLocation() + distance);			//Move the snake to a location.
		gameBoard[tempLoc] = replacement;
		gameBoard[this.getLocation()] = this;
	}


	public void setLocation(int location) {
		this.location = location;
	}
}
