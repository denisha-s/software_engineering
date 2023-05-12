
public class Dog extends Person{
	
	private String[] dogResponse = {"Woof woof!"};
	private String[] dogQues = {"Bark bark?"};
	private String[] thingsIDo = {"Bow wow"};
	

	public Dog(String myName, String occupation) {
		super(myName, occupation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void whatIDo() {
		super.whatIDo();
		int iDo = 0;
		System.out.println(thingsIDo[iDo]);
	}
	
	@Override
	public void askQuestion() {
		// TODO Auto-generated method stub
		int ques = 0;
			System.out.println(dogQues[ques]);
		
	}

	@Override
	public void answerQuestion() {
		// TODO Auto-generated method stub
		int res = 0;
		System.out.println(dogResponse[res]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog newDog = new Dog("Ruff", "Arf");
		newDog.askName();
		newDog.giveName();
		newDog.whatIDo();
		newDog.askQuestion();
		newDog.answerQuestion();
	}

}
