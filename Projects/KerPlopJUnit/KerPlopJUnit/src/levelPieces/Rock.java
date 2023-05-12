package levelPieces;

public class Rock implements gameEngine.Drawable {
	
	int location = 5;
	char symbol = 'R';
	
	public Rock() {
		/*
		 * DEFAULT CONSTRUCTOR IS THE FIRST LEVEL
		 */
		super();
		this.location = 5;
		this.symbol = 'R';
		// TODO Auto-generated constructor stub
	}
	public Rock(char symbol, String description, int location) {
		/*
		 * PARAMETERIZED CONSTRUCTOR MAKES SUBSEQUENT LEVELS
		 */
		super();
		this.location = location;
		this.symbol = symbol;
		// TODO Auto-generated constructor stub
	}
	public int getLocation(){
		return location;
	}
	
	public void setLocation(int location){
		this.location = location;
	}
	

	public void draw() {
		// TODO Auto-generated method stub
		System.out.print(symbol);
	}


	public String getSymbol() {
		// TODO Auto-generated method stub
		return Character.toString(symbol);
	}


}
