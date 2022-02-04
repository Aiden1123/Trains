package idObjects;
import java.util.ArrayList;
import java.util.Random;

public class IdObjectDatabse<T extends IdObject> {
	
	ArrayList<T> array = new ArrayList<T>();
	
	boolean idTaken(int id) {
		for(T i: array) {
			if (i.getId()==id) {
				return true;
			}
		}
		return false;
	}

	int add(T object) {
		Random rand = new Random();
		int id;
		do {
			id = rand.nextInt(100000);
		} while(idTaken(id));
		object.setId(id);
		array.add(object);
		return id;
	}
	
	void remove(T object) {
		array.remove(object);
	}
	
	void remove(int id) {
		T aux = find(id);
		if (aux==null) {
			return;
		}
		array.remove(aux);
	}
	
	T find(int id) {
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
	
	
}
