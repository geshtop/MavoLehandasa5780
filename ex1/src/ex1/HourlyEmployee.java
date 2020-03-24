package ex1;


public class HourlyEmployee  extends Employee {
	private Integer hours;
	private Float wage;
	
	
	HourlyEmployee(){
		super();
		this.hours = 0;
		this.wage =(float) 0;
	}
	HourlyEmployee(String _FirstName, String _LastName, String _ID, Integer _hours, Float _wage ){
		super(_FirstName, _LastName, _ID);
		this.hours = _hours;
		this.wage = _wage;
		
	}
	
	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer _hours) throws Exception {
		if(_hours < 1) throw new Exception("The hours need to be greater than zero");

		this.hours = _hours;
	}

	public Float getWage() {
		
		return wage;
	}

	public void setHours(Float _wage) throws Exception {
		if(_wage < 0) throw new Exception("The _wage need to be greater than zero");
		this.wage = _wage;
	}

	
	@Override
	public String toString() {
		return  super.toString() + " hours:" +  this.hours + " wage: " + this.wage;
	}

	

	 @Override
	    public boolean equals(Object o) { 
	  
	        // If the object is compared with itself then return true   
	        if (o == this) { 
	            return true; 
	        } 
	      
	        /* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof HourlyEmployee)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        HourlyEmployee c = (HourlyEmployee) o; 
	          
	        // Compare the data members and return accordingly  
	        Employee parent = (Employee) c;
	        return  hours.equals(c.hours)
	                &&   wage.equals(c.hours)
	                && super.equals(parent);
	    } 
	
	
	@Override
	Float earnings() {
	return this.hours * this.wage;	
	}
}


