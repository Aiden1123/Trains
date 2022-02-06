package trains;

public class CarriageTemplate extends RailVehicleTemplate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1548244048043337491L;
	int capacity;
	
	public void printInfo() {
		System.out.printf("Capacity: %d People\nMaxVelocity: %d km/h\nLength: %d meters\nMass: %d Tonnes\n", capacity,maxspeed,length,mass);
	}

	public CarriageTemplate(String name,int mass,int maxspeed, int length, int capacity) {
		super(name,mass,maxspeed,length);
		this.capacity=capacity;
	}
}
