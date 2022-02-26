package trainMaster;

import namedObjects.*;
import java.util.ArrayList;

/**
 * Used for managing train lines
 */
public class TrainLineDatabase extends UniqueNameDatabase<TrainLine>{

	private static final long serialVersionUID = 2848675711554495419L;
	
	public void printNamesStations() {		//prints names of lines
		for(TrainLine i: array) {
			System.out.println(i.getName());
			i.printStations();
			System.out.println();
		}
	}
	
	public void printNamesExchanges() {		//prints names and exchanges of lines
		for(TrainLine i: array) {
			System.out.println(i.getName());
			i.printExchanges();
			System.out.println();
		}	
	}
	
	public void deleteTrainLine(TrainLine line) {	//deletes train line
		
		while(line.getTrains().size() > 0) {		//remove trains from line
			line.removeTrain(line.getTrain());
		}
		
		for(Station station: line.getStations()) {							//delete connections with this line				
			ArrayList<Connection> toDelete = new ArrayList<Connection>();
			for(Connection connection: station.getConnections()) {
				if (connection.getLine().equals(line)) {
					toDelete.add(connection);
				}
			}
			station.getConnections().removeAll(toDelete);			
			station.getLines().remove(line);								//delete line from the station
		}
		
		
		for(Exchange exchange : line.getExchanges()) {						//delete exchanges
			ArrayList<Exchange> toDelete = new ArrayList<Exchange>();
			for(Exchange otherExchange: exchange.getLine().getExchanges()) {
				if (otherExchange.getLine().equals(line)) {
					toDelete.add(otherExchange);
				}
			}
			exchange.getLine().getExchanges().removeAll(toDelete);
		}
		
		line.getExchanges().clear();		//empty exchanges array
		line.getStations().clear();			//empty stations array
		
		this.delete(line);					//remove line from database
		
	}
	
}
