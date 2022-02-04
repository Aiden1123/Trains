package trains;
import idObjects.*;
import trainMaster.*;
import java.util.*;

public class Train extends IdObject {

	private static final long serialVersionUID = 4892684671273988857L;
	
	int mass;		//1000kg
	int power;		//kWh
	int maxSpeed;	//km/h
	int length;
	int capacity;
	
	TrainTemplate model;
	TrainLine line;
	
	ArrayList<RailVehicle> cars = new ArrayList<RailVehicle>();
	
	
}
