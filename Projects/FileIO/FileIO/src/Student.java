
public class Student implements Comparable<Student>{
	
	private String name;
	private int score;
	
	public Student(String name, int score) {
	this.name = name;
	this.score = score;
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		//if we want to compare scores:
		if(this.score < o.score) {
			return -1;
		}else if(this.score > o.score) {	
			return 1;						//Method to sort the students.
		}else {
			return 0;
		}	
	}

	public Student() {
		super();
		this.name = "";
		this.score = 0;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public void setScore(int score) {
		// TODO Auto-generated method stub
		this.score = score;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return name + " " + score;
	}
}