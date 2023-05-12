import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class ScoreTrakker {

	private ArrayList <Student> students = new ArrayList<Student>();
	private String[] files = {"scores.txt", "badscore.txt", "nofile.txt" };
	
	public void loadDataFile(String a) throws FileNotFoundException {
		// make a new file reader
		FileReader reader = new FileReader(a);
		// pass file reader to scanner
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String readScore = "";			//Create a string and int to store the score
			int intScore = 0;
			
			Student newStudent = new Student();
			String name = "";				//Input the first name of the student, 
			name = name + in.next();
			name = name + " ";
			name = name + in.next();		//Input the second name of the student
			newStudent.setName(name);
			in.nextLine();					//Skip a line and read in the score
			readScore = in.next();
			try {			
				intScore = Integer.parseInt(readScore);		//Try to read the score into an integer
				newStudent.setScore(intScore);
				students.add(newStudent);				//If successful, create a student with the score.
				}
				catch(NumberFormatException e){
					System.out.println("Incorrect format for " + name + " not a valid score: " + readScore);
					System.out.println();			//If not successful, then print an error and continue
					continue;
				}
		}
	}
		
	public void printInOrder() {
		Collections.sort(students);
		System.out.println("Student Score List");
		for (Student student : students) {				//Print each student from the sorted list
			System.out.println(student.toString());
		}
		System.out.println();
	}
	
	public void processFiles() {
		for(String fileName : files) {
			students.clear();				//Clear the list before preparing a file
			try {
				loadDataFile(fileName);
				this.printInOrder();
			} 
			catch (FileNotFoundException e) {
				System.out.println("Can't open file: " + fileName);		//If the file doesnt exist, then print an error.
				System.out.println();
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreTrakker newTrakker = new ScoreTrakker();	//Process the files in the ScoreTrakker list
		newTrakker.processFiles();
	}

}