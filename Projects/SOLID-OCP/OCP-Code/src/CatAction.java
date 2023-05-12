public class CatAction implements PetAction {
	private String petName;

    public CatAction(String petName) {
        this.petName = petName;
    }

    public void performAction() {
        System.out.println("Watch " + petName + " sleep");
        System.out.println("Give " + petName + " some catnip");
        System.out.println("Meow");
        System.out.println();
    }
}

