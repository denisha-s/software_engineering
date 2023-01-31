
public class PublicSafety {
	private Station universityOffice;
	private Station cityOffice;
	
	public PublicSafety(String university, String city) {
		Station universityStation = new Station(university);
		Station cityStation = new Station(city);
		universityOffice = universityStation;
		cityOffice = cityStation;
	}
	
	public void doHire(boolean isCityWorker) {
		if(isCityWorker == true) {
			cityOffice.hireDetective();
		} else {
			universityOffice.hireDetective();
		}
	}
	
	public void printDetectiveLists() {
		cityOffice.printWorkers();
		universityOffice.printWorkers();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
