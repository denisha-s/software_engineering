
public abstract class Person {
	private String myName;
	private String occupation;
	
	public Person(String myName, String occupation) {
		super();
		this.myName = myName;
		this.occupation = occupation;
	}
	public void askName(){
		System.out.println("What is your name?");
	}
	public void giveName(){
		System.out.println(myName);
	}
	public void whatIDo(){
		System.out.println(occupation);
	}
	abstract public void askQuestion();
	abstract public void answerQuestion();
}

