package trains;

/**
 * model of locomotive
 */
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
	
	public void incrementTrainStats(Train train) {			//updates train stats while attaching loco to train
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setPower(train.getPower() + power);
	}
	
	public void decrementTrainStats(Train train) {			//updates train stats while detaching loco from train
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setPower(train.getPower() - power);
	}
	
	public void incrementTrainStats(TrainTemplate train) {	//updates train template stats while attaching loco to train template
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setPower(train.getPower() + power);
	}
	
	public void decrementTrainStats(TrainTemplate train) {	//updates train template stats while detaching loco from train template
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setPower(train.getPower() - power);
	}
}
