package trainMaster;

import namedObjects.*;
import java.util.ArrayList;
import trains.*;

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
	
	public void addStation(Station station, int distance) {
		this.stations.add(station);
		station.addLine(this,distance);
	}
	
	public void prependStation(Station station, int distance) {
		this.stations.add(0,station);
		
		station.getConnections().add(new Connection(this.stations.get(1),this, distance, 0));
		this.stations.get(1).getConnections().add(new Connection(station,this, distance, 1));
		
		for(int i = 1;i < stations.size();i++) {
			for(Connection connection: stations.get(i).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == i-1) {
					connection.setStationIndex(i);
				}
			}
		}
		
		station.addLineWithoutConnection(this);
	}
	
	public void addStation(Station station, int index, int distance_a, int distance_b) {

		if (index == stations.size()) {
			System.out.println("Invalid index");
			return;			
		}
		
		try {
			this.stations.add(index,station);
		} 
		catch(IndexOutOfBoundsException e) {
			System.out.println("Invalid index");
			return;
		}
		
		for(Connection connection: this.stations.get(index-1).getConnections()) {
			if (connection.getLine().equals(this) || connection.getStationIndex() == index-1) {
				this.stations.get(index-1).getConnections().remove(connection);
			}
		}
		
		for(Connection connection: this.stations.get(index+1).getConnections()) {
			if (connection.getLine().equals(this) || connection.getStationIndex() == index) {
				this.stations.get(index-1).getConnections().remove(connection);
			}
		}
		
		this.stations.get(index-1).getConnections().add(new Connection(this.stations.get(index),this, distance_a, index-1));
		
		this.stations.get(index).getConnections().add(new Connection(this.stations.get(index-1),this, distance_a, index));
		this.stations.get(index).getConnections().add(new Connection(this.stations.get(index+1),this, distance_b, index));
		
		this.stations.get(index+1).getConnections().add(new Connection(this.stations.get(index),this, distance_b, index+1));
		
		station.addLineWithoutConnection(this);
		
		for(int i = index+1;i < stations.size();i++) {
			for(Connection connection: stations.get(i).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == i-1) {
					connection.setStationIndex(connection.getStationIndex()+1);
				}
			}
		}
	}
	
	public void deleteStation(int index,int distance) {
		
		if (index < 0 || index >= stations.size()) {
			System.out.println("Incorrect index provided");
			return;
		}
		
		if (stations.size()==1) {
			
		}
		
		else if (index == stations.size()-1) {
			for(Connection connection: stations.get(index).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index) {
					stations.get(index).getConnections().remove(connection);
					break;
				}
			}
			
			for(Connection connection: stations.get(index-1).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index-1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index-1).getConnections().remove(connection);
					break;
				}
			}
		}
		
		else if (index == 0) {
			for(Connection connection: stations.get(index).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index) {
					stations.get(index).getConnections().remove(connection);
					break;
				}
			}
			
			for(Connection connection: stations.get(index+1).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index+1) {
					stations.get(index+1).getConnections().remove(connection);
					break;
				}
			}
			
			for(int i=0;i<stations.size();i++) {
				for(Connection connection: stations.get(i).getConnections()) {
					if (connection.getLine().equals(this) && connection.getStationIndex()==i) {
						connection.setStationIndex(i-1);
					}
				}
			}
		}
		
		else {
			ArrayList<Connection> toDelete = new ArrayList<Connection>();
			for(Connection connection: stations.get(index).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index) {
					toDelete.add(connection);
				}
			}
			stations.get(index).getConnections().removeAll(toDelete);
			
			for(Connection connection: stations.get(index+1).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index+1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index+1).getConnections().remove(connection);
					break;
				}
			}
			for(Connection connection: stations.get(index-1).getConnections()) {
				if (connection.getLine().equals(this) && connection.getStationIndex() == index-1 && connection.getStation().equals(stations.get(index))) {
					stations.get(index-1).getConnections().remove(connection);
					break;
				}
			}
			
			stations.get(index-1).getConnections().add(new Connection(stations.get(index+1),this,distance,index-1));
			stations.get(index+1).getConnections().add(new Connection(stations.get(index-1),this,distance,index));

			for(int i=index+1;i<stations.size();i++) {
				for(Connection connection: stations.get(i).getConnections()) {
					if (connection.getLine().equals(this) && connection.getStationIndex() == i) {
						connection.setStationIndex(connection.getStationIndex()-1);
					}
				}
			}
		
		}
		
		Station stationToDelete = stations.get(index);
		stations.remove(index);
		ArrayList<Exchange> exchangesToDelete = new ArrayList<Exchange>();
		
		for(Exchange exchange: exchanges) {
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

	public void printStations() {
		int i=0;
		for(Station station: stations) {
			System.out.println(Integer.toString(i++) + ": " + station.getName());
		}
	}
	
	public void printExchanges() {
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
