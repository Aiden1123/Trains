package trainMaster;

public class Connection {
	
	Station station;
	TrainLine line;
	int distance; //m
	
	Connection(Station station, TrainLine line, int distance) {
		this.station = station;
		this.line = line;
		this.distance = distance;
	}
	
	public void printInfo() {
		System.out.println("Direction: " + station.getName() + "\nline: " + line.getName() + "\ndistance: " + Integer.toString(distance) + " m");
	}
	
	public Station getStation() {
		return station;
	}
	public TrainLine getLine() {
		return line;
	}
	
}
