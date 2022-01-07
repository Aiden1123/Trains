package trainMaster;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveDatabases implements Serializable{
	
	private static final long serialVersionUID = 63472L;
	TrainLineDatabase lines;
	StationDatabase stations;
	
	SaveDatabases(TrainLineDatabase lines, StationDatabase stations) {
		this.lines = lines;
		this.stations = stations;
	}
	
	public void writeToFile(String filename) {
		try {
			FileOutputStream o = new FileOutputStream(new File(filename));
			ObjectOutputStream output = new ObjectOutputStream(o);
			output.writeObject(this);
			o.close();
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error while writing to file");
		}
	}
	
	static public SaveDatabases readFromFile(String filename) {
		SaveDatabases aux=null;
		
		try {
			FileInputStream in = new FileInputStream(new File(filename));
			ObjectInputStream input = new ObjectInputStream(in);
			
			try {
				aux = (SaveDatabases) input.readObject();
			}
			catch (IOException e) {e.printStackTrace();}
			catch (ClassNotFoundException e) {e.printStackTrace();}
			
			in.close();
			input.close();
		
		} catch (IOException e) {
			System.out.println("error while reading accounts from file");
			
		}
		
		return aux;
	}

	public TrainLineDatabase getLines() {
		return lines;
	}

	public StationDatabase getStations() {
		return stations;
	}
}
