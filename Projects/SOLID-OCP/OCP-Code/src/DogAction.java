
public class DogAction implements PetAction{
	 private String petName;

	    public DogAction(String petName) {
	        this.petName = petName;
	    }

	    public void performAction() {
	        System.out.println("Throw a frisbee to " + petName);
	        System.out.println("Give " + petName + " a bone");
	        System.out.println("Woof");
	        System.out.println();
	    }
}
