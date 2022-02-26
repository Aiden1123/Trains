package trains;

/**
 * Locomotive with an engine, cannot carry people on its own; 
 */
public class Locomotive extends RailVehicle {

	private static final long serialVersionUID = 8179642765244237978L;
	
	public Locomotive(LocomotiveTemplate model) {
		super(model);
	}
}
