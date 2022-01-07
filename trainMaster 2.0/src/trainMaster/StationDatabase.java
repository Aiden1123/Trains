package trainMaster;

public class StationDatabase extends UniqueNameDatabase<Station> {

	private static final long serialVersionUID = 5882169962585915820L;

	public void printNamesLines() {
		for(Station i: array) {
			System.out.println(i.getName());
			i.printLines("\t");
		}
	}
}
