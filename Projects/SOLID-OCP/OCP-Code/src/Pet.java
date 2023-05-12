
public class Pet {
	public String petName;
	public PetType petType;
	
	public Pet(String petName, PetType petType) {
		this.petName = petName;
		this.petType = petType;
	}
	public String getPetName() {
		return petName;
	}

	public PetType getPetType() {
		return petType;
	}
	
	@Override
	public String toString() {
		return petName + " - " + petType.toString();
	}

}
