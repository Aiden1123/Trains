package trainMaster;

import java.io.Serializable;

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
}
