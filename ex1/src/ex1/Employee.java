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

	

	 @Override
	    public boolean equals(Object o) { 
	  
	        // If the object is compared with itself then return true   
	        if (o == this) { 
	            return true; 
	        } 
	      
	        /* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof Employee)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        Employee c = (Employee) o; 
	          
	        // Compare the data members and return accordingly  
	        return  FirstName.equals(c.FirstName)
	                &&   LastName.equals(c.LastName)
	                && ID.equals(c.ID);
	    } 
	
}