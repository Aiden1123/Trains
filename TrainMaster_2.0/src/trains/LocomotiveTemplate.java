package trains;

public class LocomotiveTemplate extends RailVehicleTemplate {

	private static final long serialVersionUID = -5853970899209340496L;
	int power;
	
	public LocomotiveTemplate(String name,int mass,int maxSpeed, int length, int power) {
		super(name,mass,maxSpeed,length);
		this.power = power;
	}
	
	public void printInfo() {
		System.out.printf("Power: %d kW\nMaxVelocity: %d km/h\nLength: %d meters\nMass: %d Tonnes\n", power,maxSpeed,length,mass);
	}
	
	public void incrementTrainStats(Train train) {
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setPower(train.getPower() + power);
	}
	
	public void decrementTrainStats(Train train) {
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setPower(train.getPower() - power);
	}
}
