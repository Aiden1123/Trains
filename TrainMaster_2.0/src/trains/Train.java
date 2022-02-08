package trains;
import idObjects.*;
import trainMaster.*;
import java.util.*;

public class Train extends IdObject {

	private static final long serialVersionUID = 4892684671273988857L;
	
	int mass;		//1000kg
	int power;		//kWh
	int maxSpeed;	//km/h
	int length;
	int capacity;
	
	TrainLine line;
	
	ArrayList<RailVehicle> cars;
	
	public Train() {
		cars = new ArrayList<RailVehicle>();
		mass = 0;
		power = 0;
		maxSpeed = 0;
		length = 0;
		capacity = 0;
		line = null;
	}
	
	private void updatemaxSpeed() {
		if (cars.isEmpty()) {
			maxSpeed = 0;
		}
		
		for(RailVehicle car: cars) {
			if (maxSpeed==0) {
				maxSpeed = car.getModel().getmaxSpeed();
			}
			else if (maxSpeed > car.getModel().getmaxSpeed()) {
				maxSpeed = car.getModel().getmaxSpeed();
			}
		}
	}
	
	public void add(RailVehicle car) {
		if (car.getTrain()!=null) {
				car.getTrain().remove(car);
			}
		car.setTrain(this);
		cars.add(car);
		car.getModel().incrementTrainStats(this);
		updatemaxSpeed();
	}
	
	public void remove(RailVehicle car) {
		car.setTrain(null);
		cars.remove(car);
		car.getModel().decrementTrainStats(this);
		updatemaxSpeed();
	}
	
	public void printStats() {
		System.out.printf("Capacity: %d People\nPower: %d kW\nMaxVelocity: %d km/h\nLength: %d meters\nMass: %d Tonnes\n", capacity, power, maxSpeed,length,mass);
	}
	
	public void printInfo() {
		System.out.println("Train\t" + Integer.toString(id));
		for(RailVehicle car: cars) {
			System.out.println(car.getModel().getName() + "\t" + Integer.toString(car.getId()));
		}
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getmaxSpeed() {
		return maxSpeed;
	}

	public void setmaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public TrainLine getLine() {
		return line;
	}

	public void setLine(TrainLine line) {
		this.line = line;
	}

	public ArrayList<RailVehicle> getCars() {
		return cars;
	}

	public void setCars(ArrayList<RailVehicle> cars) {
		this.cars = cars;
	}
}
