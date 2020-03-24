package ex1;
//Geula Shoshan 011826658 g@geshtop.com
//Tamar Sebbag 855054 tamarseb@gmail.com
public class Payroll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[] employeeArr =  new Employee[3];
		HourlyEmployee he = new HourlyEmployee("Geula", "Shoshan", "12345", 20,(float) 100);
		CommissionEmployee ce = new CommissionEmployee("Tamar", "Sebbag", "12222", 25,(float) 2000);
		BasePlusCommissionEmployee bpc = new BasePlusCommissionEmployee("Shafir", "Cohen", "44444", 15,(float) 3000,(float) 7000);
		employeeArr[0] = he;
		employeeArr[1] = ce;
		employeeArr[2] = bpc;

		 for (int i = 0; i < employeeArr.length; i++) {
			 
			 System.out.println(employeeArr[i].toString() + " earnings: " +  String.format("%.02f",   employeeArr[i].earnings()));
		 }
		       

	}

}
