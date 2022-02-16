package trains;
import idObjects.*;

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
	
}
