package ex1;

public class BasePlusCommissionEmployee extends CommissionEmployee {
	private Float baseSalary;
	BasePlusCommissionEmployee(String _FirstName, String _LastName, String _ID, Integer _commision, Float _grossSales , Float _baseSalary ){
		super(_FirstName, _LastName, _ID,_commision, _grossSales  );
		this.baseSalary = _baseSalary;
		
	}
	BasePlusCommissionEmployee(){
		super();
		this.baseSalary =(float) 0;
	}
	
	

	public Float getBaseSalary() {
		
		return baseSalary;
	}

	public void setBaseSalary(Float _baseSalary) throws Exception {
		if(_baseSalary < 0) throw new Exception("The baseSalary need to be greater than zero");
		this.baseSalary = _baseSalary;
	}

	
	@Override
	public String toString() {
		return  super.toString() + " baseSalary:" +  this.baseSalary;
	}

	

	 @Override
	    public boolean equals(Object o) { 
	  
	        // If the object is compared with itself then return true   
	        if (o == this) { 
	            return true; 
	        } 
	      
	        /* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof BasePlusCommissionEmployee)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        BasePlusCommissionEmployee c = (BasePlusCommissionEmployee) o; 
	          
	        // Compare the data members and return accordingly  
	        CommissionEmployee parent = (CommissionEmployee) c;
	        return  baseSalary.equals(c.baseSalary)
	                && super.equals(parent);
	    } 
	
	
	
	//more test
	//new test from eclipse
	@Override
	Float earnings() {
		return super.earnings() + baseSalary;
		
	}

}
