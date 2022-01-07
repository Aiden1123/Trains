package trainMaster;

import java.util.ArrayList;

public class RouteFinder {
	public static void FewestExchanges(Station start, Station dest) {

		if(start.equals(dest)) {
			System.out.println("Start and destination is the same");
			return;
		}
		
		ArrayList<TrainLine> lines = new ArrayList<TrainLine>();
		ArrayList<TrainLine> prev_line = new ArrayList<TrainLine>();
		
		for(TrainLine i: start.getLines()) {
			lines.add(i);
			prev_line.add(null);
		}
		
		int i=0;
		
		while(i<lines.size()) {
			
			if (lines.get(i).getStations().contains(dest)) {
				String res = null;
				TrainLine previousLine = prev_line.get(i);
				TrainLine nextLine = lines.get(i);
				
				res = "Get on " + lines.get(i).getName() + "\n\t Get off at " + dest.getName() + "\n";
				while(!(previousLine == null)) {
		
					Station transfer = null;
					
					for(Exchange j: previousLine.getExchanges()) {
						if (j.getLine().equals(nextLine)) {
							transfer = j.getStation();
							break;
						}
					}
		
					if (transfer == null) {
						System.out.println("An error has occured");
						return;
					}
					
					res = "Get on " + previousLine.getName() + "\n\t Get off at " + transfer.getName() + "\n" + res;
					nextLine = previousLine;
					previousLine = prev_line.get(lines.indexOf(nextLine));
				}
				System.out.println(res);
				return;
			}
			
			else {
				for(Exchange j: lines.get(i).getExchanges()) {
					if (!lines.contains(j.getLine())) {
						lines.add(j.getLine());
						prev_line.add(lines.get(i));
					}
				}
			}
			i++;
		}
		
		
		System.out.println("Route has not been found");
		return;
		
	}

	public static void FewestExchangesStops(Station start, Station dest) {

		if(start.equals(dest)) {
			System.out.println("Start and destination is the same");
			return;
		}
		
		ArrayList<TrainLine> lines = new ArrayList<TrainLine>();
		ArrayList<Exchange> lineExchange = new ArrayList<Exchange>();
		
		for(TrainLine i: start.getLines()) {
			lines.add(i);
			lineExchange.add(null);
		}
		
		int i=0;
		
		while(i<lines.size()) {
			
			if (lines.get(i).getStations().contains(dest)) {
				String res = null;
				Exchange exchange = lineExchange.get(i);
				TrainLine nextLine = lines.get(i);
				Station before;
				
				if (exchange == null) {
					before = start;
				}
				else {
					before = exchange.getStation();
				}
				
				
				res = "Get on " + lines.get(i).getName() + "\n\t Ride " + Math.abs(lines.get(i).getStations().indexOf(before) - lines.get(i).getStations().indexOf(dest)) + " stops to " + dest.getName() + "\n";
				
				while(!(exchange == null)) {
					
					if (lineExchange.get(lines.indexOf(exchange.getLine()))==null) {
						before=start;
					}
					else {
						before = lineExchange.get(lines.indexOf(exchange.getLine())).getStation();
					}
					
					res = "Get on " + exchange.getLine().getName() + "\n\t Ride " + Math.abs(exchange.getLine().getStations().indexOf(before) - exchange.getLine().getStations().indexOf(exchange.getStation())) + " stops to " + exchange.getStation().getName() + "\n" + res;
					nextLine = exchange.getLine();
					exchange = lineExchange.get(lines.indexOf(nextLine));
				}
				
				System.out.println(res);
				return;
			}
			
			else {
				for(Exchange j: lines.get(i).getExchanges()) {
					if (!lines.contains(j.getLine())) {
						lines.add(j.getLine());
						lineExchange.add(new Exchange(j.getStation(), lines.get(i)));
					}
				}
			}
			i++;
		}
		
		
		System.out.println("Route has not been found");
		return;
		
	}
}
