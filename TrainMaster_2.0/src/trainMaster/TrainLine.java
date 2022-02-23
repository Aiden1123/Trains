package trainMaster;

import namedObjects.*;
import java.util.ArrayList;
import trains.*;

/**
 * Train line object holds information about line's trains, station and possible transfers
 * It is assumed that trains move both ways: 
 * starting from stations with index 0 to the station with highest index
 * and vice versa in another direction
 */
public class TrainLine extends NamedObject {

	private static final long serialVersionUID = -6343023013881887764L;
	ArrayList<Station> stations;
	ArrayList<Exchange> exchanges;
	ArrayList<Train> trains;
	
	TrainLine(String name) {
		super(name);
		this.stations = new ArrayList<Station>();
		this.exchanges = new ArrayList<Exchange>();
		this.trains = new ArrayList<Train>();
	}
	
	public void addTrain(Train train) {		//adds train to line
		if (!(trains.contains(train))) {
			train.setLine(this);
			trains.add(train);
		}
	}
	
	public void removeTrain(Train train) {	//removes train from line
		if (trains.contains(train)) {
			trains.remove(train);
			train.setLine(null);
		}
	}
	
	public void printTrains() {				//prints trains servicing line
		for(Train train: trains) {
			System.out.println(train.getId());
		}
	}
	
	public void addStation(Station station, int distance) {		//adds station to the end of line
		if (station != null) {
			this.stations.add(station);
			station.addLine(this,distance);
		}
	}
	
	public void prependStation(Station station, int distance) {	//adds stations at the beginning of the line
		this.stations.add(0,station);															//add station
		
		station.getConnections().add(new Connection(this.stations.get(1),this, distance, 0));	//add new connections
		this.stations.get(1).getConnections().add(new Connection(station,this, distance, 1));
		
		for(int i = 1;i < stations.size();i++) {												//update other connections on the line
			for(Connection connection: stations.get(i).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == i-1) {
					connection.setStationIndex(i);
				}
			}
		}
		
		station.addLineWithoutConnection(this);													//add line to the station
	}
	
	public void addStation(Station station, int index, int distance_a, int distance_b) {		//inserts station on specified index between start and end of the line

		if (index >= stations.size() || index <= 0) {											//check if index is ok	
			System.out.println("Invalid index");
			return;			
		}
		
		this.stations.add(index,station);														//add stations
		
		for(Connection connection: this.stations.get(index-1).getConnections()) {				//remove old connections from prev station
			if (connection.getLine().equals(this) || connection.getStationIndex() == index-1) {
				this.stations.get(index-1).getConnections().remove(connection);
			}
		}
		
		for(Connection connection: this.stations.get(index+1).getConnections()) {				//remove old connections from next station
			if (connection.getLine().equals(this) || connection.getStationIndex() == index) {
				this.stations.get(index-1).getConnections().remove(connection);
			}
		}
		
		this.stations.get(index-1).getConnections().add(new Connection(this.stations.get(index),this, distance_a, index-1));	//add new connections
		
		this.stations.get(index).getConnections().add(new Connection(this.stations.get(index-1),this, distance_a, index));
		this.stations.get(index).getConnections().add(new Connection(this.stations.get(index+1),this, distance_b, index));
		
		this.stations.get(index+1).getConnections().add(new Connection(this.stations.get(index),this, distance_b, index+1));
		
		station.addLineWithoutConnection(this);													//add line to the station
		
		for(int i = index+1;i < stations.size();i++) {											//update other connections on the line
			for(Connection connection: stations.get(i).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == i-1) {
					connection.setStationIndex(connection.getStationIndex()+1);
				}
			}
		}
	}
	
	public void deleteStation(int index,int distance) {		//removes station from specified index and sets distance between stations to distance if necessary
		
		if (index < 0 || index >= stations.size()) {			//check if index is valid
			System.out.println("Incorrect index provided");
			return;
		}
																//update connections
		if (stations.size()==1) {												
																//case 1: no other station -> no connections to update
		} else if (index == stations.size()-1) {				//case 2: last station on the line
			for(Connection connection: stations.get(index).getConnections()) {						//remove connection from the last station
				if (connection.getLine().equals(this) && connection.getStationIndex() == index) {
					stations.get(index).getConnections().remove(connection);
					break;
				}
			}
			
			for(Connection connection: stations.get(index-1).getConnections()) {					//remove connection from last but one station
				if (connection.getLine().equals(this) && connection.getStationIndex() == index-1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index-1).getConnections().remove(connection);
					break;
				}
			}
		} else if (index == 0) {								//case 3: first station
			for(Connection connection: stations.get(index).getConnections()) {				//remove connection from first station
				if (connection.getLine().equals(this) && connection.getStationIndex() == index) {
					stations.get(index).getConnections().remove(connection);
					break;
				}
			}
			
			for(Connection connection: stations.get(index+1).getConnections()) {			//remove connection from second station
				if (connection.getLine().equals(this) && connection.getStationIndex() == index+1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index+1).getConnections().remove(connection);
					break;
				}
			}
			
			for(int i=0;i<stations.size();i++) {											//update other connections
				for(Connection connection: stations.get(i).getConnections()) {
					if (connection.getLine().equals(this) && connection.getStationIndex()==i) {
						connection.setStationIndex(i-1);
					}
				}
			}
		} else {														//case 4: station is between start and end
			ArrayList<Connection> toDelete = new ArrayList<Connection>();		//delete connections from the station
			for(Connection connection: stations.get(index).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index) {
					toDelete.add(connection);
				}
			}
			stations.get(index).getConnections().removeAll(toDelete);
			
			for(Connection connection: stations.get(index+1).getConnections()) {			//delete connections from next station
				if (connection.getLine().equals(this) && connection.getStationIndex() == index+1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index+1).getConnections().remove(connection);
					break;
				}
			}
			for(Connection connection: stations.get(index-1).getConnections()) {			//delete connection from prev station
				if (connection.getLine().equals(this) && connection.getStationIndex() == index-1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index-1).getConnections().remove(connection);
					break;
				}
			}
			
			stations.get(index-1).getConnections().add(new Connection(stations.get(index+1),this,distance,index-1));	//add new connections
			stations.get(index+1).getConnections().add(new Connection(stations.get(index-1),this,distance,index));

			for(int i=index+1;i<stations.size();i++) {																	//update other connections on the line
				for(Connection connection: stations.get(i).getConnections()) {
					if (connection.getLine().equals(this) && connection.getStationIndex() == i) {
						connection.setStationIndex(connection.getStationIndex()-1);
					}
				}
			}
		
		}
		
		Station stationToDelete = stations.get(index);
		stations.remove(index);														//remove station
		ArrayList<Exchange> exchangesToDelete = new ArrayList<Exchange>();
		
		for(Exchange exchange: exchanges) {											//remove exchanges
			if (exchange.getStation().equals(stationToDelete)) {
				Exchange ExchangeFromOtherLineToDelete = null;
				for(Exchange otherExchange: exchange.getLine().getExchanges()) {
					if (otherExchange.getLine().equals(this) && otherExchange.getStation().equals(stationToDelete)) {
						if(!(stations.contains(stationToDelete))) {
							ExchangeFromOtherLineToDelete=otherExchange;
							break;
						}
					}
				}
				if (ExchangeFromOtherLineToDelete != null) {
					exchange.getLine().getExchanges().remove(ExchangeFromOtherLineToDelete);
				}
				exchangesToDelete.add(exchange);
			}
		}
		exchanges.removeAll(exchangesToDelete);
		stationToDelete.deleteLine(this);
	}

	public void printStations() {		//prints stations
		int i=0;
		for(Station station: stations) {
			System.out.println(Integer.toString(i++) + ": " + station.getName());
		}
	}
	
	public void printExchanges() {		//prints possible exchanges
		for(Exchange i: exchanges) {
			i.printInfo();
			System.out.println("---------");
		}
	}
	
	public Train getTrain() {
		if (trains.size()>0) {
			return trains.get(0);
		}
		return null;
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public ArrayList<Exchange> getExchanges() {
		return exchanges;
	}

	public ArrayList<Train> getTrains() {
		return this.trains;
	}
	
}
