package trains;

public class EmuTemplate extends RailVehicleTemplate {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5411853387759420337L;
	int capacity;
	int power;
	
	
	
	public EmuTemplate(String name,int mass,int maxSpeed, int length, int capacity, int power) {
		super(name,mass,maxSpeed,length);
		this.capacity = capacity;
		this.power = power;
	}
	
	public void printInfo() {
		System.out.printf("Capacity: %d People\nPower: %d kW\nMaxVelocity: %d km/h\nLength: %d meters\nMass: %d Tonnes\n", capacity, power, maxSpeed,length,mass);
	}
	
	public void incrementTrainStats(Train train) {
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setCapacity(train.getCapacity() + capacity);
		train.setPower(train.getPower() + power);
	}
	
	public void decrementTrainStats(Train train) {
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setCapacity(train.getCapacity() - capacity);
		train.setPower(train.getPower() - power);
	}
}
