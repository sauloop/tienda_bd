package info.pablogiraldo.tienda_bd.model;

public class User implements Comparable<User> {

	private int id;
	private String name;
	private String pass;

	public User() {

	}

	public User(int id, String name, String pass) {
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int compareTo(User compareuser) {
		int compareids = ((User) compareuser).getId();
		return compareids - this.id;
	}
}
