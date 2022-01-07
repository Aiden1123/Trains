package namedObjects;

import java.util.ArrayList;
import java.io.Serializable;

public class UniqueNameDatabase<T extends NamedObject> implements Serializable {
	
	private static final long serialVersionUID = -264959355041262260L;
	protected ArrayList<T> array;
	
	public UniqueNameDatabase() {
		array = new ArrayList<T>();
	}
	
	public Boolean nameExists(String name) {
		for(T i: array) {
			if(i.getName().equals(name)) {
				return true;
			}  
		}
		return false;
	}
	
	public void add(T new_object) {
		
		if (this.nameExists(new_object.getName())) {
			System.out.println("Name already taken!");
			return;
		}
		
		this.array.add(new_object);
	}
	
	public T find(String target) {
		for(T i: array) {
			if (i.getName().equals(target))
				return i;
		}
		return null;
	}
	
	public void delete(T object) {
		this.array.remove(object);
	}
	
	public void printNames() {
		for(T i: array) {
			System.out.println(i.getName());
		}
	}

	public ArrayList<T> getArray() {
		return array;
	}
	
	
	
}
