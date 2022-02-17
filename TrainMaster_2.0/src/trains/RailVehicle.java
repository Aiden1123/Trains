package trains;
import idObjects.*;

public class RailVehicle extends IdObject {

	private static final long serialVersionUID = -9040645256763290347L;

	RailVehicleTemplate model;
	Train train;
	
	public RailVehicle(RailVehicleTemplate model) {
		this.model = model;
		this.train = null;
	}
	
	public void printInfo() {
		System.out.printf("id: %d\nmodel: ",id);
		System.out.println(model.getName());
		model.printInfo();
		System.out.println("Current location: " + ((train == null) ? "Depot" : "appended to train: " + Integer.toString(train.getId())));
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public RailVehicleTemplate getModel() {
		return model;
	}
}
