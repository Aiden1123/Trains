package trainMaster;

public class Connection {
	
	Station station;
	TrainLine line;
	int distance; //m
	int stationIndex;
	
	Connection(Station station, TrainLine line, int distance, int stationIndex) {
		this.station = station;
		this.line = line;
		this.distance = distance;
		this.stationIndex = stationIndex;
	}
	
	public void printInfo() {
		System.out.println("Direction: " + station.getName() + "\nline: " + line.getName() + "\ndistance: " + Integer.toString(distance) + " m" + "Index: " + Integer.toString(stationIndex));
	}
	
	public Station getStation() {
		return station;
	}
	public TrainLine getLine() {
		return line;
	}
	public int getDistance() {
		return distance;
	}

	public int getStationIndex() {
		return stationIndex;
	}

	public void setStationIndex(int stationIndex) {
		this.stationIndex = stationIndex;
	}
	
}
