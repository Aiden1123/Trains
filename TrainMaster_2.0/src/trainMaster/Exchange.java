package trainMaster;

import java.io.Serializable;

/**Exchange object holds data about possible exchange points */
public class Exchange implements Serializable {
	
	private static final long serialVersionUID = 2574470783904638714L;
		Station station;
		TrainLine line;
		
		Exchange(Station station, TrainLine line) {
			this.station = station;
			this.line = line;
		}

		public Station getStation() {
			return station;
		}

		public TrainLine getLine() {
			return line;
		}
		
		public void printInfo() {
			System.out.println("Station: " + station.getName());
			System.out.println("Line: " + line.getName());
		}
}
