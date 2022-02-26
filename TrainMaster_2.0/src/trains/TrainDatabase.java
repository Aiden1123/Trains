package trains;
import idObjects.*;
import java.util.ArrayList;

/**
 * Database of trains
 */
public class TrainDatabase extends IdObjectDatabase<Train>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667378522838173622L;

	public void removeTrain(Train train) {			//removes train from database
		if (train.getLine()!=null) {				//remove train from line if it runs on one
			train.getLine().removeTrain(train);
		}
		while(train.getCars().size() > 0) {			//detach cars
			train.remove(train.getCars().get(0));
		}
		this.remove(train);							//remove train from array
	}
	
	public void removeDepotTrains() {				//removes all trains that do not run on  any line
		
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
