package trains;
import java.util.ArrayList;

import namedObjects.*;


public class TrainTemplate extends NamedObject{


	private static final long serialVersionUID = -3763309597150352652L;

	int mass;		//1000kg
	int power;		//kWh
	int maxSpeed;	//km/h
	int length;
	
	ArrayList<RailVehicle> cars = new ArrayList<RailVehicle>();
	
	TrainTemplate(String name) {
		super(name);
	}
	
}
