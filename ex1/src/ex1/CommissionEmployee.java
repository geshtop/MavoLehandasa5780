package ex1;

public class CommissionEmployee extends Employee {
	private Float grossSales;
	private Integer commision;
	CommissionEmployee(String _FirstName, String _LastName, String _ID, Integer _commision, Float _grossSales ){
		super(_FirstName, _LastName, _ID);
		this.commision = _commision;
		this.grossSales = _grossSales;
		
	}
	CommissionEmployee(){
		super();
		this.commision = 0;
		this.grossSales =(float) 0;
	}
	
	public Integer getCommision() {
		return commision;
	}

	public void setHours(Integer _commision)  {
		if(commision < 0 || commision > 100)  throw new IllegalArgumentException("The commision need to be between 0-100");

		this.commision = _commision;
	}

	public Float getGrossSales() {
		
		return grossSales;
	}

	public void setGrossSales(Float _grossSales)  {
		if(_grossSales < 0) throw new IllegalArgumentException("The grossSales need to be greater than zero");
		
		this.grossSales = _grossSales;
	}

	
	@Override
	public String toString() {
		return  super.toString() + " grossSales:" + String.format("%.02f", this.grossSales)   + " commision: " + this.commision;
	}

	

	 @Override
	    public boolean equals(Object o) { 
	  
	        // If the object is compared with itself then return true   
	        if (o == this) { 
	            return true; 
	        } 
	      
	        /* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof CommissionEmployee)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        CommissionEmployee c = (CommissionEmployee) o; 
	          
	        // Compare the data members and return accordingly  
	        Employee parent = (Employee) c;
	        return  commision.equals(c.commision)
	                &&   grossSales.equals(c.grossSales)
	                && super.equals(parent);
	    } 
	
	
	
	//more test
	//new test from eclipse
	@Override
	Float earnings() {
		return (float) commision/100*grossSales;
		
	}

}
