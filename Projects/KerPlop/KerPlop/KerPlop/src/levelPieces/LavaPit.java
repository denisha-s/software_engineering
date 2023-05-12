package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;

public class LavaPit extends GamePiece implements gameEngine.Drawable {

	private final int location;
	private char symbol;
	
	public LavaPit() {
		/*
		 * DEFAULT CONSTRUCTOR IS THE FIRST LEVEL
		 */
		super('L', "Lava Pit", 17);
		location = 17;
		symbol = 'L';
	}
	public LavaPit(char symbol, String description, int location) {
		/*
		 * PARAMETERIZED CONSTRUCTOR ARE SUBSEQUENT LEVELS
		 */
		super(symbol, description, location);
		this.location = location;
		this.symbol = symbol;
		// TODO Auto-generated constructor stub
	}
	
	@Override
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
	


	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		// IF LANDED ON, KILL THE PLAYER
		if(playerLocation == getLocation() - 1 || playerLocation == getLocation() + 1 || playerLocation == getLocation() - 2 || playerLocation == getLocation() + 2) {
			return InteractionResult.KILL;
		} else {
			return InteractionResult.NONE;
		}
	}

}
