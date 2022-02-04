package idObjects;
import java.io.Serializable;

public class IdObject implements Serializable{

	private static final long serialVersionUID = -7132064532635976228L;
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
