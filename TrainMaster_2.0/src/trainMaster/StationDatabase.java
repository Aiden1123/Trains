package trainMaster;

import namedObjects.*;

/**
 * Object used for managing stations
 */
public class StationDatabase extends UniqueNameDatabase<Station> {

	private static final long serialVersionUID = 5882169962585915820L;

	public void printNamesLines() {			//prints names and lines of each station
		for(Station i: array) {
			System.out.println(i.getName());
			i.printLines("\t");
		}
	}
	
	public void deleteStation(Station station) {		//deletes station from database, updates all necessary distances to 10000 m
		while(!(station.getLines().isEmpty())) {		//remove station from all lines
			station.getLines().get(0).deleteStation(station.getLines().get(0).getStations().indexOf(station), 10000);
		}
		this.delete(station);							//remove station from database
	}
}
