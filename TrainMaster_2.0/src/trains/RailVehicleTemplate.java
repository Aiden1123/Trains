package trains;
import namedObjects.*;

abstract public class RailVehicleTemplate extends NamedObject{
	
	private static final long serialVersionUID = -9189385608865751817L;
	int mass;
	int maxspeed;
	int length; 
	
	abstract public void printInfo();
	
	RailVehicleTemplate(String name, int mass, int maxspeed, int length) {
		super(name);
		this.mass = mass;
		this.maxspeed = maxspeed;
		this.length = length;
	}
}
