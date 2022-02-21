package trainMaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import trains.*;

public class SaveDatabases implements Serializable{
	
	private static final long serialVersionUID = 63472L;
	TrainLineDatabase lines;
	StationDatabase stations;
	RailVehicleDatabase RVs;
	RailVehicleTemplateDatabase RVTemplates;
	TrainDatabase trains;
	TrainTemplateDatabase trainTemplates;
	
	SaveDatabases(TrainLineDatabase lines, StationDatabase stations,RailVehicleDatabase RVs, 
				  RailVehicleTemplateDatabase RVTemplates, TrainDatabase trains, TrainTemplateDatabase trainTemplates) {
		
		this.lines = lines;
		this.stations = stations;
		this.RVs = RVs;
		this.RVTemplates = RVTemplates;
		this.trains = trains;
		this.trainTemplates = trainTemplates;
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
		
		} 
		catch (FileNotFoundException e) {
			System.out.println("File " + filename + "has not been found");
		}
		catch (IOException e) {
			System.out.println("error while reading from file " + filename);
			
		}
		return aux;
	}

	public TrainLineDatabase getLines() {
		return lines;
	}

	public StationDatabase getStations() {
		return stations;
	}

	public RailVehicleDatabase getRVs() {
		return RVs;
	}

	public RailVehicleTemplateDatabase getRVTemplates() {
		return RVTemplates;
	}

	public TrainDatabase getTrains() {
		return trains;
	}

	public TrainTemplateDatabase getTrainTemplates() {
		return trainTemplates;
	}
}
