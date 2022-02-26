package trains;
import java.util.ArrayList;

import namedObjects.*;

/**
 * Template of a train
 */
public class TrainTemplate extends NamedObject{


	private static final long serialVersionUID = -3763309597150352652L;

	int mass;		//1000kg
	int power;		//kWh
	int maxSpeed;	//km/h
	int length;
	int capacity;
	
	ArrayList<RailVehicleTemplate> cars = new ArrayList<RailVehicleTemplate>();
	
	public TrainTemplate(String name) {
		super(name);
		cars = new ArrayList<RailVehicleTemplate>();
		mass = 0;
		power = 0;
		maxSpeed = 0;
		length = 0;
		capacity = 0;
	}
	
	private void updatemaxSpeed() {
		if (cars.isEmpty()) {
			maxSpeed = 0;
		}
		
		for(RailVehicleTemplate car: cars) {
			if (maxSpeed==0) {
				maxSpeed = car.getmaxSpeed();
			}
			else if (maxSpeed > car.getmaxSpeed()) {
				maxSpeed = car.getmaxSpeed();
			}
		}
	}
	
	public void add(RailVehicleTemplate car) {
		cars.add(car);
		car.incrementTrainStats(this);
		updatemaxSpeed();
	}
	
	public void remove(RailVehicleTemplate car) {
		if (cars.contains(car)) {
			cars.remove(car);
			car.decrementTrainStats(this);
			updatemaxSpeed();
		}
	}
	
	public void printStats() {
		System.out.printf("Capacity: %d People\nPower: %d kW\nMaxVelocity: %d km/h\nLength: %d meters\nMass: %d Tonnes\n", capacity, power, maxSpeed,length,mass);
	}
	
	public void printInfo() {
		System.out.println(this.getName());
		for(RailVehicleTemplate car: cars) {
			System.out.println(car.getName());
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

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
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

	public ArrayList<RailVehicleTemplate> getCars() {
		return cars;
	}

	public void setCars(ArrayList<RailVehicleTemplate> cars) {
		this.cars = cars;
	}
	
}
