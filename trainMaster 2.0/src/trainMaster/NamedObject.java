package trainMaster;

import java.io.Serializable;

public class NamedObject implements Serializable {

	private static final long serialVersionUID = 346987913768539955L;
	String name;

	NamedObject(String new_name) {
		name = new_name;
	}

	public String getName() {
		return name;
	}
}
