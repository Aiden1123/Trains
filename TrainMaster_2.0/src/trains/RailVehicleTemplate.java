package trains;
import namedObjects.*;

public class RailVehicleTemplate extends NamedObject{
	
	private static final long serialVersionUID = -9189385608865751817L;
	int mass;
	int maxspeed;
	int length; 
	
	RailVehicleTemplate(String name) {
		super(name);
	}
}
