package trains;
import namedObjects.*;

abstract public class RailVehicleTemplate extends NamedObject{
	
	private static final long serialVersionUID = -9189385608865751817L;
	int mass;
	int maxSpeed;
	int length; 
	
	abstract public void printInfo();
	abstract public void incrementTrainStats(Train train);
	abstract public void decrementTrainStats(Train train);
	abstract public void incrementTrainStats(TrainTemplate train);
	abstract public void decrementTrainStats(TrainTemplate train);
	
	
	RailVehicleTemplate(String name, int mass, int maxSpeed, int length) {
		super(name);
		this.mass = mass;
		this.maxSpeed = maxSpeed;
		this.length = length;
	}

	public int getMass() {
		return mass;
	}

	public int getmaxSpeed() {
		return maxSpeed;
	}

	public int getLength() {
		return length;
	}
	 
}
