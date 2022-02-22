package namedObjects;

import java.util.ArrayList;
import java.io.Serializable;

/** Array with NamedObjects
 *  Each object in the array has a different name
 *  so it is not possible to add a new object if its name
 *  is already present in the array
 */
public class UniqueNameDatabase<T extends NamedObject> implements Serializable {
		
	private static final long serialVersionUID = -264959355041262260L;
	protected ArrayList<T> array;
	
	public UniqueNameDatabase() {
		array = new ArrayList<T>();
	}
	
	public Boolean nameExists(String name) {	//checks if name exists in the array
												//returns true if name is taken and false otherwise
		for(T i: array) {
			if(i.getName().equals(name)) {
				return true;
			}  
		}
		return false;
	}
	
	public void add(T new_object) {
		
		/**adds object to the array if it's name is not already present*/
		
		if (this.nameExists(new_object.getName())) {	//check if name is taken
			System.out.println("Name already taken!");
			return;
		}
		
		this.array.add(new_object);
	}
	
	public T find(String target) {	//Searches for an object with given name
									//returns object if it was found and null otherwise
		for(T i: array) {
			if (i.getName().equals(target))
				return i;
		}
		
		return null;
		
	}
	
	public void delete(T object) { 	//Removes object from array
		this.array.remove(object);
	}
	
	public void printNames() {	//Prints names of objects in the array
		for(T i: array) {
			System.out.println(i.getName());
		}
	}

	public ArrayList<T> getArray() {
		return array;
	}
	
	
	
}
