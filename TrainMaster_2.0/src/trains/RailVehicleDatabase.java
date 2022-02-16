package trains;
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
	
}
