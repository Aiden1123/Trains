package trains;

/**
 * model of electric multiple unit, used for making actual EMUs based on its specifications    
 */
public class EmuTemplate extends RailVehicleTemplate {

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
	
	public void incrementTrainStats(Train train) {			//updates train stats while attaching emu to train
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setCapacity(train.getCapacity() + capacity);
		train.setPower(train.getPower() + power);
	}
	
	public void decrementTrainStats(Train train) {			//updates train stats while detaching emu from train
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setCapacity(train.getCapacity() - capacity);
		train.setPower(train.getPower() - power);
	}
	
	public void incrementTrainStats(TrainTemplate train) {	//updates train template stats while attaching emu to train template
		train.setMass(train.getMass() + mass);
		train.setLength(train.getLength() + length);
		train.setCapacity(train.getCapacity() + capacity);
		train.setPower(train.getPower() + power);
	}
	
	public void decrementTrainStats(TrainTemplate train) {	//updates train template stats while detaching emu from train template
		train.setMass(train.getMass() - mass);
		train.setLength(train.getLength() - length);
		train.setCapacity(train.getCapacity() - capacity);
		train.setPower(train.getPower() - power);
	}
}
