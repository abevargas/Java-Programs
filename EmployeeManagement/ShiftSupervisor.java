/**
 * ShiftSupervor class, sub-class of Employee
 * 
 * @authors Abraham Vargas
 * 
 */
public class ShiftSupervisor extends Employee {
	private double yearlySalary; // value between $40,000 - $80,000
	private int timesGoalsMet; // number of times goals were met
	private static final double MIN_YEARLY_SALARY = 40000;
	private static final double MAX_YEARLY_SALARY = 80000;

	/**
	 * Default Constructor
	 */
	public ShiftSupervisor() {
		super();
		super.getRandomEmployee();
		this.yearlySalary = MIN_YEARLY_SALARY;
		this.timesGoalsMet = 5;
	}

	/**
	 * Class Constructor
	 * 
	 * @param name
	 *            of employee
	 * @param year
	 *            employee hired
	 * @param salary
	 *            of employee
	 * @param goalsMet
	 *            of employee
	 */
	public ShiftSupervisor(String name, int year, double earnings, String id,
						   double salary, int goalsMet) 
	{
		super(name, year, salary/52, id);
		this.yearlySalary = salary;
		this.timesGoalsMet = goalsMet;
	}

	// Return yearly salary
	public double getYearlySalary() {
		return yearlySalary;
	}

	// Return times goals were met
	public int getTimesGoalsMet() {
		return timesGoalsMet;
	}

	// Changes yearly salary
	public void setYearlySalary(double salary) {
		this.yearlySalary = salary;
	}

	// Changes times goals were met
	public void setTimesGoalsMet(int goalsMet) {
		this.timesGoalsMet = goalsMet;
	}
	
	// toString method
	public String toString()
	{
		return "Shift Supervisor: " + super.toString() + "\tYearly salary: " + this.yearlySalary 
				+ "\tGoals met: " + this.timesGoalsMet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + timesGoalsMet;
		long temp;
		temp = Double.doubleToLongBits(yearlySalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShiftSupervisor other = (ShiftSupervisor) obj;
		if (timesGoalsMet != other.timesGoalsMet)
			return false;
		if (Double.doubleToLongBits(yearlySalary) != Double
				.doubleToLongBits(other.yearlySalary))
			return false;
		return true;
	}	
}
