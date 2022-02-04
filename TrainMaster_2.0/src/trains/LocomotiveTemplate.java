package trains;

public class LocomotiveTemplate extends RailVehicleTemplate {

	private static final long serialVersionUID = -5853970899209340496L;
	int power;
	
	LocomotiveTemplate(String name,int mass,int maxspeed, int length, int power) {
		super(name,mass,maxspeed,length);
		this.power = power;
	}
}
