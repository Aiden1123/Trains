package trains;

public class CarriageTemplate extends RailVehicleTemplate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1548244048043337491L;
	int capacity;
	
	public void printInfo() {
		System.out.printf("Capacity: %d People\nMaxVelocity: %d km/h\nLength: %d meters\nMass: %d Tonnes\n", capacity,maxSpeed,length,mass);
	}

	public CarriageTemplate(String name,int mass,int maxSpeed, int length, int capacity) {
		super(name,mass,maxSpeed,length);
		this.capacity=capacity;
	}
	
	public void incrementTrainStats(Train train) {
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setCapacity(train.getCapacity() + capacity);
	}
	
	public void decrementTrainStats(Train train) {
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setCapacity(train.getCapacity() - capacity);
	}
	
	public void incrementTrainStats(TrainTemplate train) {
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setCapacity(train.getCapacity() + capacity);
	}
	
	public void decrementTrainStats(TrainTemplate train) {
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setCapacity(train.getCapacity() - capacity);
	}
}
