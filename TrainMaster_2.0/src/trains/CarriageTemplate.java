package trains;

public class CarriageTemplate extends RailVehicleTemplate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1548244048043337491L;
	int capacity;
	
	

	CarriageTemplate(String name,int mass,int maxspeed, int length, int capacity) {
		super(name,mass,maxspeed,length);
		this.capacity=capacity;
	}
}
