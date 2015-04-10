import java.util.ArrayList;
/**
 * 
 * @authors Abraham Vargas
 *
 */
public class EmployeeTest {
	 public static void main(String args[])
	    {
	        ArrayList <Employee> workers = new ArrayList <Employee> ();
	        
	        for(int i = 0; i < 20; i++)
	        {
	            workers.add(Employee.getRandomEmployee());
	        }
	        
	        for(Employee e: workers)
	        {
	            System.out.println(e);
	        }
	        Employee worker = new ProductionWorker();
	        System.out.println(worker);
	        Employee supervisor = new ShiftSupervisor();
	        System.out.println(supervisor);
	    }
}
