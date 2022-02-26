package idObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**Array which holds IdObjects
 * While adding object to the array, the object is assigned unique Id
 * (within this array)
 */
public class IdObjectDatabase<T extends IdObject> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4813397834416730342L;
	protected ArrayList<T> array;
	
	public IdObjectDatabase() {
		array = new ArrayList<T>();
	}
	
	public boolean idTaken(int id) {	//Checks if object with this id exists in array
										//returns true if id is taken and false otherwise
		for(T i: array) {
			if (i.getId()==id) {
				return true;
			}
		}
		return false;
	}

	public int add(T object) {			//adds object to the array and assigns it unique id
										//returns assigned id
		Random rand = new Random();
		int id;
		
		do {							//generate unique id
			id = rand.nextInt(100000);
		} while(idTaken(id));
		
		object.setId(id);				//initialise values
		array.add(object);
		return id;
	}
	
	public void remove(T object) {	//Removes object from array		
		array.remove(object);
	}
	
	public void remove(int id) {	//Removes object with specified id
		
		T aux = find(id);			//find object
		if (aux==null) {			//check if object was found
			return;
		}
		array.remove(aux);			//delete object
	}
	
	public T find(int id) {			//Searches for object with specified id
									//returns object if found and null otherwise
		for(T i: array) {
			if (i.getId()==id) {
				return i;
			}
		}
		return null;
	}

	public ArrayList<T> getArray() {
		return array;
	}
	
	public void printIDs() {	//prints IDs of objects to terminal

		for(T i:array) {
			System.out.println(i.getId());
		}
	}
	
}
