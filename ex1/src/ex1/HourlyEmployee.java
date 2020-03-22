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
	

}
