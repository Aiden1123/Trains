package trains;
import java.util.ArrayList;

import idObjects.*;

public class RailVehicleDatabase extends IdObjectDatabase<RailVehicle>{

	public void printInfoAll() {
		for(RailVehicle i: array) {
			i.printInfo();
		}
	}
	
	public void deleteRV(RailVehicle car) {
		car.getTrain().remove(car);
		this.remove(car);
	}
	
	public void deleteDepotRVs() {
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
