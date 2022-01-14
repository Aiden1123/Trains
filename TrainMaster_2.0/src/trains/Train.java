package trains;
import namedObjects.*;
import java.util.*;

public class Train extends NamedObject{

	private static final long serialVersionUID = 4892684671273988857L;
	
	int mass;		//1000kg
	int power;		//kWh
	int maxSpeed;	//km/h
	int runningCost;
	int brakePower;
	int length;
	
	int train_id;
	String model;
	
	ArrayList<Integer> idDatabase = new ArrayList<Integer>();
	ArrayList<RailVehicle> cars = new ArrayList<RailVehicle>();
	
	Train(String name) {
		super(name);
	}

}
