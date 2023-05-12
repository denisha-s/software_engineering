
public class Burner {
	public enum Temperature {BLAZING, HOT, WARM, COLD};
	public final static int TIME_DURATION = 2;
	
	private Setting mySetting;
	private int timer;
	private Temperature myTemperature;
	
	public Temperature getMyTemperature() {
		return myTemperature;
	}
	
	public Burner(){
		super();
		timer = 0;                              // set default timer to 0, burner is off and cold, initializes burner to that state
		myTemperature = Temperature.COLD;
		mySetting = Setting.OFF;
	}

	public void updateTemperature() {
		// TODO Auto-generated method stub
		switch(timer) {							//If the timer is 0, then the burner will not need to be changed
		case 0:
			
		case 2:
			timer--;	
			break;								//If the timer is 2, then decrease the timer to 1.
		case 1:									//If the timer is 1, then we need to move the temperature towards the current setting.
			switch(myTemperature) {
			case COLD:
				switch(mySetting) {
				case OFF:
					timer = 0;
					break;
				case LOW:
					myTemperature = Temperature.WARM;
					timer = 0;
					break;
				default:
					myTemperature = Temperature.WARM;
					timer = 2;
					break;				
				}
			case WARM:
				if(mySetting == Setting.OFF) {			//If the setting is OFF and the stove is warm, then we make it cold
					myTemperature = Temperature.COLD;
					timer = 0;
					break;
				} else if (mySetting == Setting.MEDIUM || mySetting == Setting.HIGH) {	//Else if the stove is warm, it needs to get hotter
					myTemperature = Temperature.HOT;
					timer = 2;
					break;
				} else if (mySetting == Setting.LOW){
					myTemperature = Temperature.WARM;
					timer = 0;				
					break; 						//If the setting matches the temperature, set timer = 0;
				}
			case HOT:										//Same logic as above for HOT, except different settings
				if(mySetting == Setting.HIGH) {
					myTemperature = Temperature.BLAZING;			//If the setting is HIGH, we need to move to BLAZING
					timer = 0;	
					break;
				} else if (mySetting == Setting.OFF || mySetting == Setting.LOW) {	//Else if the setting is lower than medium, we need to lower temperature
					myTemperature = Temperature.WARM;	
					timer = 2;
					break;
				} else {					//Else the settings are already the same
					timer = 0;
					break;
				}
			case BLAZING:
				if(mySetting == Setting.HIGH) {				//If the setting is HIGH, we are at the right temperature already.
					timer = 0;
					break;
				} else  if (mySetting == Setting.MEDIUM){									//Else, we need to lower the temperature.
					myTemperature = Temperature.HOT;	
					timer = 0;
					break;
				} else {
					myTemperature = Temperature.HOT;
					timer = 2;
				}
			}
		}
		
	}


	public void plusButton() {
		// TODO Auto-generated method stub
		switch(mySetting) {
		case OFF:
			mySetting = Setting.LOW;
			timer = TIME_DURATION;
			break;
		case LOW:
			mySetting = Setting.MEDIUM;
			timer = TIME_DURATION;
			break;
		case MEDIUM:
			mySetting = Setting.HIGH;
			timer = TIME_DURATION;
			break;
		case HIGH:
			timer = TIME_DURATION;
			break;
		}
	}

	public void minusButton() {
		// TODO Auto-generated method stub
		switch(mySetting) {
		case OFF:
			timer = TIME_DURATION;					//Timer = 0 means that we are at the correct temperature for the setting.
			break;
		case LOW:
			mySetting = Setting.OFF;
			timer = TIME_DURATION;
			break;
		case MEDIUM:
			mySetting = Setting.LOW;
			timer = TIME_DURATION;
			break;
		case HIGH:
			mySetting = Setting.MEDIUM;
			timer = TIME_DURATION;
			break;
		}
	}
	
	public void display(){
		String statusTemp = "";
		switch (myTemperature) {
		case COLD:
			statusTemp = "cooool";
			break;
		case WARM:
			statusTemp = "warm";
			break;
		case HOT:
			statusTemp = "CAREFUL";
			break;
		case BLAZING:
			statusTemp = "VERY HOT! DON'T TOUCH";
		default: 
			break;                                    // default will never happen
		}
		System.out.println(mySetting + "....." + statusTemp); 
	}

}
