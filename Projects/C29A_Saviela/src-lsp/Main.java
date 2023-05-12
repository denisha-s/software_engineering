public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnimalTrainer trainer = new AnimalTrainer();
		Animal bird = new Bird();
		Animal penguin = new Penguin();

		trainer.trainAnimal(bird);
		trainer.trainAnimal(penguin);
	}
}

