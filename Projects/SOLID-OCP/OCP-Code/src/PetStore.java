import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetStore {
	private List<Pet> pets = new ArrayList<>();
	private Map<PetType, PetAction> petActions = new HashMap<>();
	
	public PetStore() {
		petActions.put(PetType.CAT, new CatAction());
		petActions.put(PetType.DOG, new DogAction());
	}
	
	public void performAction(Pet pet) {
		PetAction action = petActions.get(pet.getPetType());
		action.performAction(pet);
	}
	
	public void addPet(Pet pet) {
		pets.add(pet);
	}
	
	public void listPets() {
		for(Pet pet : pets) {
			System.out.println(pet);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		PetStore myStore = new PetStore();
		
		myStore.addPet(new Pet("Buttons", PetType.CAT ));
		myStore.addPet(new Pet("Boxer", PetType.DOG ));
		myStore.addPet(new Pet("Dax", PetType.CAT ));
		myStore.addPet(new Pet("Spot", PetType.DOG ));
		
		myStore.listPets();
		
		for(Pet pet : myStore.pets) {
			myStore.performAction(pet);
		}
	}
	
	private interface PetAction {
		public void performAction(Pet pet);
	}
	
	private class CatAction implements PetAction {
		public void performAction(Pet pet) {
			System.out.println("Watching " + pet.getPetName() + " sleep");
			System.out.println("Giving " + pet.getPetName() + " some catnip");
			System.out.println("Hearing meow from " + pet.getPetName());
			System.out.println();
		}
	}
	
	private class DogAction implements PetAction {
		public void performAction(Pet pet) {
			System.out.println("Throwing a frisbee to " + pet.getPetName());
			System.out.println("Giving " + pet.getPetName() + " a bone");
			System.out.println("Hearing woof from " + pet.getPetName());
			System.out.println();
		}
	}
}