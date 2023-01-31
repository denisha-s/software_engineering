
public class Detective {
	private String detectiveName;
	private int badgeNumber;
	
	
	@Override
	public String toString() {
		return "Detective [detectiveName=" + detectiveName + ", badgeNumber=" + badgeNumber + "]";
	}

	public String getDetectiveName() {
		return detectiveName;
	}

	public void setDetectiveName(String detectiveName) {
		this.detectiveName = detectiveName;
	}

	public int getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	
	public Detective(String DetectiveName, int badgeNumber) {
		this.detectiveName = DetectiveName;
		this.badgeNumber = badgeNumber;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
