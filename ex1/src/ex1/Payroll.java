package ex1;

public class Payroll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[] employeeArr =  new Employee[3];
		HourlyEmployee he = new HourlyEmployee("Geula", "Shoshan", "11111", 20,(float) 100);
		CommissionEmployee ce = new CommissionEmployee("Tamar", "Sebbag", "12222", 25,(float) 2000);
		BasePlusCommissionEmployee bpc = new BasePlusCommissionEmployee("Shafir", "Cohen", "44444", 15,(float) 3000,(float) 7000);
		employeeArr[0] = he;
		employeeArr[1] = ce;
		employeeArr[2] = bpc;

		 for (int i = 0; i < employeeArr.length; i++) {
			 
			 System.out.println(employeeArr[i].toString() + " earnings: " + employeeArr[i].earnings().toString());
		 }
		       

	}

}