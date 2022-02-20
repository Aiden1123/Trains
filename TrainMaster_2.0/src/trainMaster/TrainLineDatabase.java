package trainMaster;

import namedObjects.*;
import java.util.ArrayList;

public class TrainLineDatabase extends UniqueNameDatabase<TrainLine>{

	private static final long serialVersionUID = 2848675711554495419L;
	
	public void printNamesStations() {
		for(TrainLine i: array) {
			System.out.println(i.getName());
			i.printStations();
			System.out.println();
		}
	}
	
	public void printNamesExchanges() {
		for(TrainLine i: array) {
			System.out.println(i.getName());
			i.printExchanges();
			System.out.println();
		}	
	}
	
	public void deleteTrainLine(TrainLine line) {
		
		while(line.getTrains().size() > 0) {
			line.removeTrain(line.getTrain());
		}
		
		for(Station station: line.getStations()) {
			ArrayList<Connection> toDelete = new ArrayList<Connection>();
			for(Connection connection: station.getConnections()) {
				if (connection.getLine().equals(line)) {
					toDelete.add(connection);
				}
			}
			station.getConnections().removeAll(toDelete);
			station.getLines().remove(line);
		}
		
		
		for(Exchange exchange : line.getExchanges()) {
			ArrayList<Exchange> toDelete = new ArrayList<Exchange>();
			for(Exchange otherExchange: exchange.getLine().getExchanges()) {
				if (otherExchange.getLine().equals(line)) {
					toDelete.add(otherExchange);
				}
			}
			exchange.getLine().getExchanges().removeAll(toDelete);
		}
		
		line.getExchanges().clear();
		line.getStations().clear();
		
		this.delete(line);
		
	}
	
}
