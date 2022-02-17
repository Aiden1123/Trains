package trains;
import idObjects.*;
import java.util.ArrayList;

public class TrainDatabase extends IdObjectDatabase<Train>{

	public void removeTrain(Train train) {
		if (train.getLine()!=null) {
			train.getLine().removeTrain(train);
		}
		while(train.getCars().size() > 0) {
			train.remove(train.getCars().get(0));
		}
		this.remove(train);
	}
	
	public void removeDepotTrains() {
		ArrayList<Train> toDelete = new ArrayList<Train>();
		for(Train train : array) {
			if(train.getLine() == null) {
				toDelete.add(train);
			}
		}
		
		for(Train train: toDelete) {
			removeTrain(train);
		}
	
	}
	
	
}
