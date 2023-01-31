import java.util.ArrayList;
import java.util.Arrays;

public class CareFree extends Person {

	private ArrayList<String> careResponse = new ArrayList<String>(
		    Arrays.asList("Honestly, it doesnt matter to me.", "Whatever sounds good to you!", "To be honest, I am chillin'"));
	private ArrayList<String> careQuestion = new ArrayList<String>(
		    Arrays.asList("Hey dude, how's it hangin?", "Did you bring your dog?", "Where's the beer?"));
	private ArrayList<String> thingsIDo = new ArrayList<String>(
		    Arrays.asList("I forgot what day it is", "I have no plans today", "I'm not worrying about it"));
	private static int currentIndex = 0;
	private final int maxIndex = careResponse.size() - 1;
	
	
	public CareFree(String myName, String occupation) {
		super(myName, occupation);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CareFree person1 = new CareFree("My name is Patrick", "I am a professional sleeper");
		CareFree person2 = new CareFree("Patty", "I am the same person");
		
		person1.askName();
		person1.giveName();
		person1.whatIDo();
		person1.askQuestion();
		person2.answerQuestion();

	}

	@Override
	public void askQuestion() {
		// TODO Auto-generated method stub
		System.out.println(careQuestion.get(currentIndex));
		if(currentIndex == maxIndex) {
			currentIndex = 0;
		} else {
			currentIndex++;
		}
	}

	@Override
	public void answerQuestion() {
		// TODO Auto-generated method stub
		System.out.println(careResponse.get(currentIndex));
		if(currentIndex == maxIndex) {
			currentIndex = 0;
		} else {
			currentIndex++;
		}
		
	}
	
	@Override
	public void whatIDo() {
		// TODO Auto-generated method stub
		super.whatIDo();
		System.out.println(thingsIDo.get(currentIndex));
	if(currentIndex == maxIndex) {
			currentIndex = 0;
		} else {
			currentIndex++;
		}	
		
	}

}