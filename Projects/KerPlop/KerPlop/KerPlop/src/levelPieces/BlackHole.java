package levelPieces;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import gameEngine.Player;

public class BlackHole extends GamePiece implements gameEngine.Drawable {
	
	private int location;
	private char symbol;

	public BlackHole() {
		/*
		 * DEFAULT CONSTRUCTOR IS LOCATIONS FOR FIRST LEVEL
		 */
		super('O', "Black hole", 3);
		location = 3;
		symbol = 'O';
		
	}
	
	
	public BlackHole(char symbol, String description, int location) {
		/*
		 * PARAMETERIZED CONSTRUCTOR IS FOR SUBSEQUENT LEVELS
		 */
		super(symbol, description, location);
		this.location = location;
		this.symbol = symbol;
	}

	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.print(symbol);
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

	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		// ADVANCES LEVEL IF THE PLAYER IS DIRECTLY NEXT TO THE BLACKHOLE.
		if(playerLocation == getLocation() - 1 || playerLocation == getLocation() + 1) {
			return InteractionResult.ADVANCE;
		} else {
			return InteractionResult.NONE;
		}
	}
}

