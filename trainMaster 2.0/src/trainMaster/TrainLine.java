package trainMaster;

import java.util.ArrayList;

public class TrainLine extends NamedObject {

	private static final long serialVersionUID = -6343023013881887764L;
	ArrayList<Station> stations;
	ArrayList<Exchange> exchanges;
	
	TrainLine(String name) {
		super(name);
		this.stations = new ArrayList<Station>();
		this.exchanges = new ArrayList<Exchange>();
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
		for(Exchange i: exchanges) {
			System.out.printf("%s: %s\n",i.getStation().getName(),i.getLine().getName());
		}
	}

	public void printExchanges(String message) {
		for(Exchange i: exchanges) {
			System.out.printf("%s%s: %s\n",message,i.getStation().getName(),i.getLine().getName());
		}
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public ArrayList<Exchange> getExchanges() {
		return exchanges;
	}

}
