package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;

public class Gold extends GamePiece{
	
	private int location;
	private char symbol;
	
	public Gold(){
		/*
		 * DEFAULT CONSTRUCTOR IS THE FIRST LEVEL
		 */
		super('G', "Gold", 14);
		this.location = 14;
		this.symbol = 'G';
	}
	
	public Gold(char symbol, String description, int location) {
		/*
		 * PARAMETERIZED CONSTRUCTOR ARE SUBSEQUENT LEVELS
		 */
		super(symbol, description, location);
		this.location = location;
		this.symbol = symbol;
		// TODO Auto-generated constructor stub
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

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public InteractionResult interact(Drawable[] pieces, int playerLocation) {
		// ADVANCE THE PLAYER IF LANDED ON.
		if(this.getLocation() == playerLocation) {
			return InteractionResult.ADVANCE;
		}		
		return InteractionResult.NONE;
	}
}
