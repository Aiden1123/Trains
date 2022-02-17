package trainMaster;

import namedObjects.*;

public class StationDatabase extends UniqueNameDatabase<Station> {

	private static final long serialVersionUID = 5882169962585915820L;

	public void printNamesLines() {
		for(Station i: array) {
			System.out.println(i.getName());
			i.printLines("\t");
		}
	}
	
	public void deleteStation(Station station) {
		while(!(station.getLines().isEmpty())) {
			station.getLines().get(0).deleteStation(station.getLines().get(0).getStations().indexOf(station), 10000);
		}
	}
}
