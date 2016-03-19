/**
 * ProductionWorker sub-class of Employee
 * 
 * @authors Abraham Vargas, Jim Halter, Greg Basile
 * 
 */
public class ProductionWorker extends Employee {
	private double hourlyRate; // value between $6.00 - $50.00
	private static final double MIN_HOURLY_RATE = 6;
	private static final double MAX_HOURLY_RATE = 50;

	/**
	 * Default Constructor
	 */
	public ProductionWorker() {
		super();
		this.hourlyRate = MIN_HOURLY_RATE;
		this.weeklyEarnings = this.hourlyRate * 40;
	}

	/**
	 * Class Constructor
	 * 
	 * @param name
	 *            of employee
	 * @param year
	 *            employee hired
	 * @param earnings
	 *            weekly of employee
	 * @param id
	 *            of employee
	 * @param rate
	 *            hourly of employee
	 */
	public ProductionWorker(String name, int year, String id,
							double rate) 
	{
		super(name, year, rate*40, id);
		if (rate >= MIN_HOURLY_RATE && rate <= MAX_HOURLY_RATE) {
			this.hourlyRate = rate;
		} else {
			this.hourlyRate = MIN_HOURLY_RATE;
		}		
	}
	
	// Returns hourly rate
	public double getHourlyRate() {
		return hourlyRate;
	}

	// Changes hourly rate if between 6 and 50
	public void setHourlyRate(double rate) {
		if (rate >= MIN_HOURLY_RATE && rate <= MAX_HOURLY_RATE) {
			this.hourlyRate = rate;
		} else {
			this.hourlyRate = MIN_HOURLY_RATE;
		}
	}
	
	// toString method
	public String toString() {
		
		return "Production Worker: " + super.toString() + "\tHourly rate: " 
				+ this.hourlyRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(hourlyRate);
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
		ProductionWorker other = (ProductionWorker) obj;
		if (Double.doubleToLongBits(hourlyRate) != Double
				.doubleToLongBits(other.hourlyRate))
			return false;
		return true;
	}	
}
