import java.util.Scanner;

public class Station {

	private String stationName;
	private int numberOfDetectives = 0;
	final int MAX_DETECTIVES = 5;
	static int MAX_BADGE_NUMBER = 1;
	
	private Detective workers[];
	
	public Station() {
		super();
		this.workers = null;
		workers = new Detective[5];
		// TODO Auto-generated constructor stub
	}

	public Station(String stationName) {
		super();
		this.stationName = stationName;
		workers = new Detective[5];
	}

	public void hireDetective() {
		if(numberOfDetectives >= MAX_DETECTIVES) {
			System.out.println("Can't hire any more detectives for " + stationName);
			return;
		}
		Scanner myObj = new Scanner(System.in);
		System.out.print("New hire for " + stationName + "...Enter detective name: ");
		String detectiveName = myObj.nextLine();
		Detective newHire = new Detective(detectiveName, MAX_BADGE_NUMBER);
		MAX_BADGE_NUMBER = MAX_BADGE_NUMBER + 1;
		System.out.println("Name is: " + newHire.getDetectiveName());
		workers[numberOfDetectives] = newHire;
		numberOfDetectives++;
		
		
		
	}
	
	public void printWorkers() {
		System.out.println("List of detectives for " + stationName);
		for(int i = 0; i < workers.length; i++) {
			if(workers[i] == null) {
				return;
			}
			System.out.println("Detective [Badge=" + workers[i].getBadgeNumber() + ", Name=" + workers[i].getDetectiveName() + "]");
		}
		System.out.println("");
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Station newStation = new Station();
		newStation.hireDetective();
		
	}

}
