package trains;
import namedObjects.*;

public class RailVehicleTemplateDatabase extends UniqueNameDatabase<RailVehicleTemplate>{


	private static final long serialVersionUID = 4262112496469416569L;

	public void printNames() {
		for(RailVehicleTemplate i: array) {
			System.out.println(i.getName());
		}
	}
	
	public void printInfoAll() {
		for(RailVehicleTemplate i: array) {
			System.out.println(i.getName());
			i.printInfo();
			System.out.println("--------");
		}
	}	
}
