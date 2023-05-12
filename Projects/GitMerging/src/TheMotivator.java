import java.util.ArrayList;

public class TheMotivator {
	private ArrayList<String> meals = new ArrayList<String>();
	
	public void feedback(int score) {
		if (score == 100)
			System.out.println("You're awesome");
		else if (score > 90)
			System.out.println("That's great");
		else if (score > 60)
			System.out.println("That's good ");
		else
			System.out.println("Well, what can I say?");
	}

	public void otherComments() {
		System.out.println("To Do List:");
		System.out.println("You should study for the next test. It's on Wednesday.");
	}
	
	public void createMeals() {
		meals.add("Sushi");
		meals.add("Pasta");
	}
	
	public void dailyMeal() {
		System.out.println("Lunch Meal:");
		
		for (String meal : meals)
			System.out.println(meals);
	}
	
	public static void main(String[] args) {
		TheMotivator tm = new TheMotivator();
		tm.feedback(100);
		tm.otherComments();
		tm.createMeals();
		tm.dailyMeal();
	}
}