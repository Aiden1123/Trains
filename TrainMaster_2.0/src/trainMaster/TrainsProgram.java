package trainMaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import trains.*;

public class TrainsProgram {
	
	static TrainLineDatabase lines;
	static StationDatabase stations;
	static RailVehicleTemplateDatabase RVmodels;
	static RailVehicleDatabase RVs;
	static TrainDatabase trains;
	static TrainTemplateDatabase trainTemplates;
	
	private static void executeScript(String filename) {
	    try {
	        File myObj = new File(filename);
	        Scanner myReader = new Scanner(myObj);
	        
	        while (myReader.hasNextLine()) {
	          menu(myReader.nextLine().split(" "));
	        }
	        
	        myReader.close();
	      } 
	    
	    catch (FileNotFoundException e) {
	    	System.out.println("File " + filename + " has not been found");
	    }
	    
	    catch (Exception e) {
	        System.out.println("Unknown error occured while reading from " +  filename);
	        e.printStackTrace();
	      }
	}
	
	private static void unknownInstruction(String word) {
		System.out.println("Unknown instruction: " + word + " Use help to list available instructions");
	}
	
	private static void wrongToken(String word) {
		System.out.println(word + " is not defined for this instruction");
	}
	
	private static void menu(String[] instruction) {

			try {
			
			switch(instruction[0]) {
			
				case "exec":
					executeScript(instruction[1]);
					break;
					
				case "routeC":
					if (!(stations.find(instruction[1]) == null) && !(stations.find(instruction[2]) == null)) {
						RouteFinder.FewestExchangesPretty(stations.find(instruction[1]), stations.find(instruction[2]));
					}
					else {
						System.out.println("At least one of the provided station does not exist");
					}
					break;
				
				case "route":
					if (!(stations.find(instruction[1]) == null) && !(stations.find(instruction[2]) == null)) {
						RouteFinder.LowestDistance(stations.find(instruction[1]), stations.find(instruction[2]));
					}
					else {
						System.out.println("At least one of the provided station does not exist");
					}
					break;
					
				case "add": 
					switch(instruction[1]) {
						case "line":
							for(int i=2;i<instruction.length;i++) {
								if (lines.nameExists(instruction[i])) {
									System.out.println(instruction[i] + " Name already taken!");
									break;
								}
								lines.add(new TrainLine(instruction[i]));
							}
							break;
						case "station":
							for(int i=2;i<instruction.length;i++) {
								if (stations.nameExists(instruction[i])) {
									System.out.println(instruction[i] + " Name already taken!");
									break;
								}
								stations.add(new Station(instruction[i]));
							}
							break;
						case "carriage":
							if (!(RVmodels.nameExists(instruction[2]))) {
								System.out.println("Invalid model name");
								break;
							}
							try {
							RVs.add(new Carriage((CarriageTemplate)RVmodels.find(instruction[2])));
							} catch(ClassCastException e) {
								System.out.println(RVmodels.find(instruction[2]) + "is not a carriage!");
							}
							break;
						case "emu":
							if (!(RVmodels.nameExists(instruction[2]))) {
								System.out.println("Invalid model name");
								break;
							}
							try {
							RVs.add(new Emu((EmuTemplate)RVmodels.find(instruction[2])));
							} catch(ClassCastException e) {
								System.out.println(RVmodels.find(instruction[2]) + "is not an EMU!");
							}
							break;
						case "loco":
							if (!(RVmodels.nameExists(instruction[2]))) {
								System.out.println("Invalid model name");
								break;
							}
							try {
							RVs.add(new Locomotive((LocomotiveTemplate)RVmodels.find(instruction[2])));
							} catch(ClassCastException e) {
								System.out.println(RVmodels.find(instruction[2]) + "is not a locomotive!");
							}
							break;					
							
						case "template":
							switch(instruction[2]) {
								case "carriage": 
									RVmodels.add(new CarriageTemplate(instruction[3],Integer.parseInt(instruction[4]),
																					Integer.parseInt(instruction[5]), 
																					Integer.parseInt(instruction[6]), 
																					Integer.parseInt(instruction[7])));
									break;
								case "emu":
									RVmodels.add(new EmuTemplate(instruction[3],Integer.parseInt(instruction[4]),
																					Integer.parseInt(instruction[5]), 
																					Integer.parseInt(instruction[6]), 
																					Integer.parseInt(instruction[7]),
																					Integer.parseInt(instruction[8])));
									break;
								case "loco":
									RVmodels.add(new LocomotiveTemplate(instruction[3],Integer.parseInt(instruction[4]),
																					Integer.parseInt(instruction[5]), 
																					Integer.parseInt(instruction[6]), 
																					Integer.parseInt(instruction[7])));
									break;
								case "train":
									trainTemplates.add(new TrainTemplate(instruction[3]));
									break;
								
								default:
									wrongToken(instruction[2]);
									break;
							}
							break;
						case "train":
							trains.add(new Train());
							break;
						
						default:
							unknownInstruction(instruction[0] + " " +  instruction[1]);
							break;
						
					}
					break;
				
				case "delete":
				
					switch(instruction[1]) {
						case "train":
							if (instruction[2].equals("depot")) {
								trains.removeDepotTrains();
								break;
							}
							if (instruction[2].matches("[0-9.]+") && trains.idTaken(Integer.parseInt(instruction[2]))) {
								trains.removeTrain(trains.find(Integer.parseInt(instruction[2])));
								break;
							}
							System.out.println("Incorrect Id Provided");
							break;
							
						case "car":
							if (instruction[2].equals("depot")) {
								RVs.deleteDepotRVs();
								break;
							}
							if (instruction[2].matches("[0-9.]+") && RVs.idTaken(Integer.parseInt(instruction[2]))) {
								RVs.deleteRV(RVs.find(Integer.parseInt(instruction[2])));
								break;
							}
							System.out.println("Incorrect Id provided");
							break;
							
						case "trainTemplate":
							if (trainTemplates.nameExists(instruction[2])) {
								trainTemplates.delete(trainTemplates.find(instruction[2]));
							}
							else {
								System.out.println("Incorrect name provided");
							}
							break;
							
						case "carTemplate":
							if (RVmodels.nameExists(instruction[2])) {
								RVmodels.delete(RVmodels.find(instruction[2]));
							}
							else {
								System.out.println("Incorrect name provided");
							}
							break;
							
						case "line":
							if (lines.nameExists(instruction[2])) {
								lines.deleteTrainLine(lines.find(instruction[2]));
							}
							else {
								System.out.println("Incorrect name provided");
							}
							break;
							
						case "station":
							if (stations.nameExists(instruction[2])) {
								stations.deleteStation(stations.find(instruction[2]));
							}
							else {
								System.out.println("Incorrect name provided");
							}
							break;
							
						default:
							unknownInstruction(instruction[0] + " " + instruction[1]);
							break;
					}
				
					break;
				
				case "append":
					if (!lines.nameExists(instruction[1])) {
						System.out.println("Invalid line name");
						break;
					}
					
					for(int i=2;i<instruction.length;i+=2) {
					
						if (stations.nameExists(instruction[i]) && instruction[i+1].matches("[0-9.]+")) {
							lines.find(instruction[1]).addStation(stations.find(instruction[i]), Integer.parseInt(instruction[i+1]));
						}
						
						else {
							if (instruction[i+1].matches("[0-9.]+")) {
								Station aux = new Station(instruction[i]); 
								stations.add(aux);
								lines.find(instruction[1]).addStation(aux, Integer.parseInt(instruction[i+1]));
							}
							
							else {
								System.out.println("Incorrect distance provided");
							}
						}

					}
					break;

				case "prepend":
					if (!lines.nameExists(instruction[1])) {
						System.out.println("Invalid line name");
						break;
					}
					
					for(int i=2;i<instruction.length;i+=2) {
					
						if (stations.nameExists(instruction[i]) && instruction[i+1].matches("[0-9.]+")) {
							lines.find(instruction[1]).prependStation(stations.find(instruction[i]), Integer.parseInt(instruction[i+1]));
						}
						
						else {
							if (instruction[i+1].matches("[0-9.]+")) {
								Station aux = new Station(instruction[i]); 
								stations.add(aux);
								lines.find(instruction[1]).prependStation(aux, Integer.parseInt(instruction[i+1]));
							}
							else {
								System.out.println("Incorrect distance provided");
							}
						}

					}
					break;
					
				case "disengage":
					if (!lines.nameExists(instruction[1])) {
						System.out.println("Invalid line name");
						break;
					}
					
					if (!instruction[2].matches("[0-9.]+") || !instruction[3].matches("[0-9.]+")) {
						System.out.println("Invalid index or distance provided");
						break;						
					}
					
					lines.find(instruction[1]).deleteStation(Integer.parseInt(instruction[2]),Integer.parseInt(instruction[3]));
					break;
					
				case "attach":
					if(instruction[1].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[1])))) {
						for(int i=2;i<instruction.length;i++) 
							if (instruction[i].matches("[0-9.]+") && (RVs.idTaken(Integer.parseInt(instruction[i])))) {
								trains.find(Integer.parseInt(instruction[1])).add(RVs.find(Integer.parseInt(instruction[i])));
							}
							else if (RVmodels.nameExists(instruction[i])) {
								RailVehicle aux = new RailVehicle(RVmodels.find(instruction[i]));
								RVs.add(aux);
								trains.find(Integer.parseInt(instruction[1])).add(aux);
							}
							else {
								System.out.println("car not found");
							}
					}
					else {
						System.out.println("Incorrect Id provided");
					}
					break;
				
				case "detach":
					if(instruction[1].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[1])))) {
						for(int i=2;i<instruction.length;i++) 
							if (instruction[i].matches("[0-9.]+") && (RVs.idTaken(Integer.parseInt(instruction[i])))) {
								trains.find(Integer.parseInt(instruction[1])).remove(RVs.find(Integer.parseInt(instruction[i])));
							}
							else {
								System.out.println("Incorrect Id provided");
							}							
					}
					else {
						System.out.println("Incorrect Id provided");
					}
					break;
					
				case "attachModel":
					if(trainTemplates.nameExists(instruction[1])) {
						for(int i=2;i<instruction.length;i++) 
							if (RVmodels.nameExists(instruction[i])) {
								trainTemplates.find(instruction[1]).add(RVmodels.find(instruction[i]));
							}
					}
					else {
						System.out.println("Incorrect name provided");
					}
					break;
				
				case "detachModel":
					if(trainTemplates.nameExists(instruction[1])) {
						for(int i=2;i<instruction.length;i++) 
							if (RVmodels.nameExists(instruction[i])) {
								trainTemplates.find(instruction[1]).remove(RVmodels.find(instruction[i]));
							}
							else {
								System.out.println("Incorrect name provided");
							}
					}
					else {
						System.out.println("Incorrect name provided");
					}
					break;
				
				case "attachTemplate":
					if(instruction[1].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[1])))) {
						if(trainTemplates.nameExists(instruction[2])) {
							for(RailVehicleTemplate carTemplate: trainTemplates.find(instruction[2]).getCars()) {
								RailVehicle aux = new RailVehicle(carTemplate);
								RVs.add(aux);
								trains.find(Integer.parseInt(instruction[1])).add(aux);
							}
						}
						else {
							System.out.println("Incorrect train template provided");							
						}
					}
					else {
						System.out.println("Incorrect train Id provided");
					}
					break;
					
				case "buildTemplate":
					if(trainTemplates.nameExists(instruction[1])) {
						Train train = new Train();
						for(RailVehicleTemplate carTemplate: trainTemplates.find(instruction[1]).getCars()) {
							RailVehicle aux = new RailVehicle(carTemplate);
							RVs.add(aux);
							train.add(aux);
						}
						trains.add(train);
					}
					else {
						System.out.println("Incorrect train template provided");							
					}
					break;
					
				case "insert":
					if (!(instruction[3].matches("[0-9.]+") && instruction[4].matches("[0-9.]+") && instruction[5].matches("[0-9.]+"))) {
						System.out.println("Incorrect index or distances provided");
						break;
					}
						
					if (lines.nameExists(instruction[1]) && stations.nameExists(instruction[2])) {
						lines.find(instruction[1]).addStation(stations.find(instruction[2]),Integer.parseInt(instruction[3]),Integer.parseInt(instruction[4]),Integer.parseInt(instruction[5]));
					}
					
					else if (lines.nameExists(instruction[1]) && !stations.nameExists(instruction[2])) {
						Station aux = new Station(instruction[2]); 
						stations.add(aux);
						lines.find(instruction[1]).addStation(aux,Integer.parseInt(instruction[3]),Integer.parseInt(instruction[4]),Integer.parseInt(instruction[5]));
					}
					
					else {
						System.out.println("Incorrect station or line name provided");
					}
					break;
					
				case "assign":
					if(lines.nameExists(instruction[1])) {
						for(int i=2;i<instruction.length;i++) {
							if (instruction[i].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[i])))) {
								lines.find(instruction[1]).addTrain(trains.find(Integer.parseInt(instruction[i])));
							}
							else {
								System.out.println("Incorrect train Id provided");
							}
						}
					}
					else {
						System.out.println("Incorrect line name");
					}
					break;
					
				case "withdraw":
					if(lines.nameExists(instruction[1])) {
						for(int i=2;i<instruction.length;i++) {
							if (instruction[i].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[i])))) {
								lines.find(instruction[1]).removeTrain(trains.find(Integer.parseInt(instruction[i])));
							}
							else {
								System.out.println("Incorrect train Id provided");
							}
						}
					}
					else {
						System.out.println("Incorrect line name");
					}
					break;
				
				case "assignTemplate":
					if(lines.nameExists(instruction[1])) {
						for(int i=2;i<instruction.length;i++) {
							if (trainTemplates.nameExists(instruction[i])) {
								Train train = new Train();
								for(RailVehicleTemplate carTemplate: trainTemplates.find(instruction[i]).getCars()) {
									
									RailVehicle aux = new RailVehicle(carTemplate);
									RVs.add(aux);
									train.add(aux);
								
								}
								trains.add(train);
								lines.find(instruction[1]).addTrain(train);
							}
							else {
								System.out.println("Train template not found");
							}
						}
					}
					else {
						System.out.println("Incorrect line name");
					}
					break;

				case "test":
					if (instruction[1].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[1])))) {
						Train train = trains.find(Integer.parseInt(instruction[1]));
						int testDistances[] = {1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 250000};
						for(int distance : testDistances) {
							System.out.println(Integer.toString(distance) + ": " + Long.toString(RouteFinder.CalculateTime(train,distance)));
						}
					}
					else {
						System.out.println("Incorrect train provided");
					}
					break;
					
				case "print":
					switch(instruction[1]) {
						case "line":
							if (lines.nameExists(instruction[2])) {
								lines.find(instruction[2]).printStations();
							} else {
								System.out.println("Line not found");
							}
							break;
							
						case "lineTrains":
							if (lines.nameExists(instruction[2])) {
								lines.find(instruction[2]).printTrains();
							} else {
								System.out.println("Line not found");
							}
							break;
							
						case "lineExchanges":
							if (lines.nameExists(instruction[2])) {
								lines.find(instruction[2]).printExchanges();
							} else {
								System.out.println("Line not found");
							}
							break;
							
						case "station":
							if (stations.nameExists(instruction[2])) {
								stations.find(instruction[2]).printLines();
							} else {
								System.out.println("Station not found");
							}
							break;

						case "stationConnections":
							if (stations.nameExists(instruction[2])) {
								stations.find(instruction[2]).printConnections();
							} else {
								System.out.println("Station not found");
							}
							break;
							
						case "lines":
							lines.printNames();
							break;
						
						case "linesInfo":
							lines.printNamesStations();
							break;
							
						case "stations":
							stations.printNames();
							break;
						
						case "stationsInfo":
							stations.printNamesLines();
							break;
						
						case "linesExchanges":
							lines.printNamesExchanges();
							break;
						
						case "RVmodels":
							RVmodels.printInfoAll();
							break;
						
						case "RVnames":
							RVmodels.printNames();
							break;
						
						case "RVs":
							RVs.printInfoAll();
							break;
						
						case "trains":
							trains.printIDs();
							break;
						
						case "trainCars":
							if(instruction[2].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[2])))) {
								trains.find(Integer.parseInt(instruction[2])).printInfo();
							} else {
								System.out.println("Incorrect Id provided");
							}
							break;
						
						case "trainStats":
							if(instruction[2].matches("[0-9.]+") && (trains.idTaken(Integer.parseInt(instruction[2])))) {
								trains.find(Integer.parseInt(instruction[2])).printStats();
							} else {
								System.out.println("Incorrect Id provided");
							}
							break;
						
						case "templateStats":
							if (trainTemplates.nameExists(instruction[2])) {
								trainTemplates.find(instruction[2]).printStats();
							} else {
								System.out.println("Train template not found");
							}
							break;
						
						case "templateCars":
							if (trainTemplates.nameExists(instruction[2])) {
								trainTemplates.find(instruction[2]).printInfo();
							} else {
								System.out.println("Train template not found");
							}
							break;
							
						case "templates":
							trainTemplates.printNames();
							break;
							
						case "default":
							unknownInstruction(instruction[0] + " " + instruction[1]);
					
					}
					break;
				
				case "quit":
					break;
					
				case "save": 
					{
						SaveDatabases save = new SaveDatabases(lines, stations, RVs,RVmodels, trains, trainTemplates);
						save.writeToFile(instruction[1]);
					}
					break;
					
				case "load": 
					{	
						SaveDatabases load = SaveDatabases.readFromFile(instruction[1]);
						lines = load.getLines();
						stations = load.getStations();
						RVs = load.getRVs();
						RVmodels = load.getRVTemplates();
						trains = load.getTrains();
						trainTemplates = load.getTrainTemplates();
						
					}
					break;
					
				case "help":
					
					if (instruction.length == 1) {
						System.out.println("Use one of the instructions from below or use help <instruction>");
						System.out.println("---Basic---");
						System.out.println("add");
						System.out.println("delete");
						System.out.println("print");
						System.out.println("---Manage line's stations---");
						System.out.println("append");	
						System.out.println("prepend");
						System.out.println("insert");
						System.out.println("disengage");
						System.out.println("---Manage line's trains---");
						System.out.println("assign");
						System.out.println("assignTemplate");
						System.out.println("withdraw");
						System.out.println("---Manage train templates---");
						System.out.println("attachModel");
						System.out.println("detachModel");
						System.out.println("---Manage trains---");
						System.out.println("attach");
						System.out.println("detach");
						System.out.println("buildTemplate");
						System.out.println("attachTemplate");
						System.out.println("---Other---");						
						System.out.println("test");
						System.out.println("exec");
						System.out.println("route");
						System.out.println("routeC");
						System.out.println("---Saving and loading---");							
						System.out.println("load");
						System.out.println("save");
						System.out.println("---quit---");	
						System.out.println("quit");
					}
					
					else {
						switch(instruction[1]) {
						
							case "add":
								System.out.println("creates new object");
								System.out.println("add line <name>\t//adds line");
								System.out.println("add station <name>\t//adds station");
								System.out.println("add carriage <model>\t//adds carriage");
								System.out.println("add emu <model>\t//adds electric multiple unit");
								System.out.println("add loco <model>\t//adds locomotive");
								System.out.println("add template carriage <name>\t//add carriage model");
								System.out.println("add template emu <name>\t//add EMU model");
								System.out.println("add template loco <name>\t//add locomotive model");
								System.out.println("add template train <name>\t//add train template");
								System.out.println("add train\t//adds train");
								break;
								
							case "print":
								System.out.println("prints information about specified object(s)");
								System.out.println("print line <name>\t//print line's stops");
								System.out.println("print lineTrains <name>\t//print line's trains");
								System.out.println("print lineExchanges <name>\t//print line's exchanges");
								System.out.println("print station <name>\t//print station's lines");
								System.out.println("print stationConnections <name>\t//print station's connections");
								System.out.println("print lines\t//print lines' names");
								System.out.println("print linesInfo\t//print lines and their stations");
								System.out.println("print linesExchanges\t//print lines and their exchanges");
								System.out.println("print stations\t//print stations' names");
								System.out.println("print stationsInfo\t//print stations and their lines");
								System.out.println("print RVmodels\t//print car models' specifications");
								System.out.println("print RVnames\t//print car models' names");
								System.out.println("print RVs\t//print cars");
								System.out.println("print trains\t//print trains's IDs");
								System.out.println("print templates\t//print train templates' names");
								System.out.println("print trainCars <id>\t//print train's cars");
								System.out.println("print trainStats <id>\t//print train's specifications");
								System.out.println("print templateCars <id>\t//print train template's cars");
								System.out.println("print templateCars <id>\t//print train template's specifications");
								break;						
							
							case "delete":
								System.out.println("deletes object");
								System.out.println("delete train depot\t//delete all trains which are not running on lines");
								System.out.println("delete train <id>\t//delete train");
								System.out.println("delete car depot\t//delete all cars which are not attached to trains");
								System.out.println("delete car <id>\t//delete car");
								System.out.println("delete trainTemplate <name>\t//delete train template");
								System.out.println("delete carTemplate <name>\t//delete car model");
								System.out.println("delete line <name>\t//delete line");
								System.out.println("delete station <name>\t//delete station");
								break;
								
							case "append":
								System.out.println("adds stations to the end of the line");
								System.out.println("append <line> [<station> <distance>]");
								break;
								
							case "prepend":
								System.out.println("adds stations to the beginning of the line");
								System.out.println("prepend <line> [<station> <distance>]");
								break;
								
							case "insert":
								System.out.println("adds stations at specified place between start and end in the line");
								System.out.println("prepend <line> <station> <index> <distanceToPreviousStation> <distanceToNextStation>");
								break;
								
							case "disengage":
								System.out.println("removes station from the line");
								System.out.println("remove <line> <stationIndex> <distance>");
								break;
								
							case "assign":
								System.out.println("adds train(s) to the line");
								System.out.println("assign <line> [<trainId>]");
								break;
								
							case "assignTemplate":
								System.out.println("creates train(s) from template and adds them to the line");
								System.out.println("assignTemplate <line> [<trainTemplate>]");
								break;
								
							case "withdraw":
								System.out.println("removes train(s) from the line");
								System.out.println("withdraw <line> [<trainId>]");
								break;
								
							case "attachModel":
								System.out.println("attaches car model(s) to train template");
								System.out.println("attachModel <trainTemplate> [<carModel>]");	
								break;
								
							case "detachModel":
								System.out.println("removes car model(s) from train template");
								System.out.println("detachModel <trainTemplate> [<carModel>]");
								break;
								
							case "attach":
								System.out.println("attaches car(s) to train");
								System.out.println("attach <trainId> [<carModel>]");
								System.out.println("attach <trainId> [<carId>]");
								break;
								
							case "detach":
								System.out.println("removes car(s) from train");
								System.out.println("detach <trainId> [<carId>]");
								break;
								
							case "buildTemplate":
								System.out.println("creates a train from template");
								System.out.println("buildTemplate <trainTemplate>");
								break;
								
							case "attachTemplate":
								System.out.println("attaches cars from template to train");
								System.out.println("attachTemplate <trainId> <trainTemplate>");
								break;
								
							case "test":
								System.out.println("tests how much train should travel certain distance");
								System.out.println("test <trainId>");
								break;
								
							case "exec":
								System.out.println("executes instructions specified in file");
								System.out.println("exec <file>");
								break;
								
							case "route":
								System.out.println("searches for the shortest route between 2 stations");
								System.out.println("route <start> <end>");
								break;
								
							case "routeC":
								System.out.println("searches for the route with fewest exchanges between 2 stations");
								System.out.println("routeC <start> <end>");
								break;
								
							case "save":
								System.out.println("saves current databases to file");
								System.out.println("save <file>");
								break;
								
							case "load":
								System.out.println("loads databases from file");
								System.out.println("load <file>");
								break;
								
							default:
								System.out.println("There is no instruction: " + instruction[1]);
								break;
						}
					}
					
					break;
					
				default:
					unknownInstruction(instruction[0]);
					break;
			}
			
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Incorrect instruction");
			}
	}
	
	public static void main(String[] args) {

		lines = new TrainLineDatabase();
		stations = new StationDatabase();
		RVmodels = new RailVehicleTemplateDatabase();
		RVs = new RailVehicleDatabase();
		trains = new TrainDatabase();
		trainTemplates = new TrainTemplateDatabase();
		Scanner sc=new Scanner(System.in);  
		String[] instruction;
		
		System.out.println("TrainMaster 2.0");
		
		while(!((instruction = sc.nextLine().split(" "))[0].equals("quit"))) {
			menu(instruction);
		}
	
		sc.close();
		System.out.println("Program closed");
	}
}
