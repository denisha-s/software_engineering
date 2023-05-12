
public class Workaholic extends Person {
	
	private String[] workResponse = {"I didn't sleep at all last night.", "I am on the grind!", "I wanna go home", "You should have seen this Karen who was here."};
	private String[] workQues = {"What time do you work tommorow?", "Want to guess how many hours I've worked this week?", "Are you as tired as I am?", "Who is your least favorite manager?"};
	private String[] thingsIDo = {"I hate my life", "I work all day today :(", "I hate my boss"};
	
	public Workaholic(String myName, String occupation) {
		super(myName, occupation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestion() {
		// TODO Auto-generated method stub
		int maxIndex = 3;
		int minIndex = 0;
		int randomIndex = (int) Math.floor(Math.random()*(maxIndex - minIndex + 1) + minIndex);
		System.out.println(workQues[randomIndex]);
		
	}

	@Override
	public void answerQuestion() {
		// TODO Auto-generated method stub
		int maxIndex = 3;
		int minIndex = 0;
		int randomIndex = (int) Math.floor(Math.random()*(maxIndex - minIndex + 1) + minIndex);
		System.out.println(workResponse[randomIndex]);
	}
	
	@Override
	public void whatIDo() {
		super.whatIDo();
		int maxIndex = 2;
		int minIndex = 0;
		int randomIndex = (int) Math.floor(Math.random()*(maxIndex - minIndex + 1) + minIndex);
		System.out.println(thingsIDo[randomIndex]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Workaholic newPerson = new Workaholic("My name is Patty", "I am a non-stop worker");
		newPerson.askName();
		newPerson.giveName();
		newPerson.whatIDo();
		newPerson.askQuestion();
		newPerson.answerQuestion();
		
	}


}
