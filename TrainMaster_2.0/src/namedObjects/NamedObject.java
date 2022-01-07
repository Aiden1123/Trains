package namedObjects;

import java.io.Serializable;

public class NamedObject implements Serializable {

	private static final long serialVersionUID = 346987913768539955L;
	protected String name;

	public NamedObject(String new_name) {
		name = new_name;
	}

	public String getName() {
		return name;
	}
}
