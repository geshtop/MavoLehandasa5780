package ex1;
public abstract class Employee {
	Employee() {
		FirstName = "Plony";
		LastName = "Almony";
		ID = "0";
	}

	Employee(String _FirstName, String _LastName, String _ID) {
		FirstName = _FirstName;
		LastName = _LastName;
		ID = _ID;
	}

	private String FirstName;
	private String LastName;
	private String ID;

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String _FirstName) {
		this.FirstName = _FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String _LastName) {
		this.LastName = _LastName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String _ID) {
		this.ID = _ID;
	}

	@Override
	public String toString() {
		return FirstName + " " + LastName + " " + ID;
	}

}