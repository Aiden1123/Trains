package trainMaster;

public class TrainLineDatabase extends UniqueNameDatabase<TrainLine>{

	private static final long serialVersionUID = 2848675711554495419L;
	
	public void printNamesStations() {
		for(TrainLine i: array) {
			System.out.println(i.getName());
			i.printStations("\t");
		}
	}
	
	public void printNamesExchanges() {
		for(TrainLine i: array) {
			System.out.println(i.getName());
			i.printExchanges("\t");
		}	
	}
	
}
