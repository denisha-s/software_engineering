//public class Control {
//	public static void main(String[] args) {
//		LightBulb lightBulb = new LightBulb() ;
//		ElectricPowerSwitch bulbSwitch = new ElectricPowerSwitch( lightBulb ) ;
//		
//		bulbSwitch.press() ;
//		bulbSwitch.press() ;
//	}
//}

public class Control {
    public static void main(String[] args) {
        Switchable lightBulb = new LightBulb();
        Switchable fan = new Fan();
        ElectricPowerSwitch bulbSwitch = new ElectricPowerSwitch(lightBulb);
        ElectricPowerSwitch fanSwitch = new ElectricPowerSwitch(fan);

        bulbSwitch.press();
        bulbSwitch.press();

        fanSwitch.press();
        fanSwitch.press();
    }
}
