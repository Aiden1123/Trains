package trainMaster;

import namedObjects.*;
import java.util.ArrayList;
import trains.*;

public class TrainLine extends NamedObject {

	private static final long serialVersionUID = -6343023013881887764L;
	ArrayList<Station> stations;
	ExchangeDatabase exchanges;
	ArrayList<Train> trains;
	
	TrainLine(String name) {
		super(name);
		this.stations = new ArrayList<Station>();
		this.exchanges = new ExchangeDatabase();
		this.trains = new ArrayList<Train>();
	}
	
	public void addTrain(Train train) {
		if (!(trains.contains(train))) {
			train.setLine(this);
			trains.add(train);
		}
	}
	
	public void removeTrain(Train train) {
		if (trains.contains(train)) {
			trains.remove(train);
			train.setLine(null);
		}
	}
	
	public void printTrains() {
		for(Train train: trains) {
			System.out.println(train.getId());
		}
	}
	
	public void addStation(Station station) {
		this.stations.add(station);
		station.addLine(this);
	}
	
	public void addStation(Station station, int index) {
		
		try {
			this.stations.add(index,station);
		} 
		catch(IndexOutOfBoundsException e) {
			this.stations.add(station);
		}
		
		station.addLine(this);
	}
	
	public void deleteStation(Station station) {
		this.stations.remove(station);
		station.deleteLine(this);
	}
	
	public void deleteStation(int index) {
		
		try {
			Station a = this.stations.get(index);
			this.stations.remove(index);
			a.deleteLine(this);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Invalid index");
		}
	}

	public void printStations() {
		int i=0;
		for(Station station: stations) {
			System.out.println(Integer.toString(i++) + ": " + station.getName());
		}
	}
	
	public void printStations(String message) {
		int i=0;
		for(Station station: stations) {
			System.out.print(message);
			System.out.println(Integer.toString(i++) + ": " + station.getName());
		}
	}
	
	public void printExchanges() {
		for(Exchange i: exchanges.getExchanges()) {
			System.out.printf("%s: %s\n",i.getStation().getName(),i.getLine().getName());
		}
	}

	public void printExchanges(String message) {
		for(Exchange i: exchanges.getExchanges()) {
			System.out.printf("%s%s: %s\n",message,i.getStation().getName(),i.getLine().getName());
		}
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public ArrayList<Exchange> getExchanges() {
		return exchanges.getExchanges();
	}

}
