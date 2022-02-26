package trains;
import java.util.ArrayList;

import idObjects.*;

/**
 * database of railcars
 */
public class RailVehicleDatabase extends IdObjectDatabase<RailVehicle>{

	private static final long serialVersionUID = 1401286648355017072L;

	public void printInfoAll() {
		for(RailVehicle i: array) {
			i.printInfo();
		}
	}
	
	public void deleteRV(RailVehicle car) { 	//deletes railcar from database
		if (car.getTrain() != null) {
			car.getTrain().remove(car);
		}
		this.remove(car);
	}
	
	public void deleteDepotRVs() {				//deletes railcars that are not attached to trains
		ArrayList<RailVehicle> toDelete = new ArrayList<RailVehicle>();
		for(RailVehicle RV : array) {
			if(RV.getTrain() == null) {
				toDelete.add(RV);
			}
		}
		
		for(RailVehicle RV: toDelete) {
			deleteRV(RV);
		}
	
	}
	
}
