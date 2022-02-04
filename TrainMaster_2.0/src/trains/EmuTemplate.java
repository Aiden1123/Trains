package trains;

public class EmuTemplate extends RailVehicleTemplate {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5411853387759420337L;
	int capacity;
	int power;
	
	EmuTemplate(String name,int mass,int maxspeed, int length, int capacity, int power) {
		super(name,mass,maxspeed,length);
		this.capacity = capacity;
		this.power = power;
	}
}
