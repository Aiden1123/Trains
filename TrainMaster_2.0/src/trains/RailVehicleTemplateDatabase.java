package trains;
import namedObjects.*;


/**
 * Database of railway vehicle models
 */
public class RailVehicleTemplateDatabase extends UniqueNameDatabase<RailVehicleTemplate>{

	private static final long serialVersionUID = 4262112496469416569L;

	public void printInfoAll() {			//prints information about all models in database
		for(RailVehicleTemplate i: array) {
			System.out.println(i.getName());
			i.printInfo();
			System.out.println("--------");
		}
	}
}
