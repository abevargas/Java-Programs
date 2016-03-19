import java.util.ArrayList;
import java.util.Random;
/**
 * Shift class contains information
 * about work day shifts.
 * 
 * @author Abraham Vargas, Jim Halter, Greg Basile
 *
 */
public class Shift {
					   
	// Class Fields
	private ArrayList <Employee> workShift = new ArrayList<Employee>();	// 1 supervisor per 5 workers
	private int shift;	// 1-Day 2-Night
	private boolean goalsMet;	// true if goals met during shift
	private Day day;
	public final static double PROB_GOALS_MET = 0.55;
	Random r = new Random();
	
	public Shift()
	{
		// Random shift supervisor and 5 production workers
		this.workShift.add(new ShiftSupervisor());
		for(int i = 0; i < 5; i++)
		{
		this.workShift.add(new ProductionWorker());
		}					
		
		// Random goalsMet value
		this.goalsMet = (r.nextDouble() < PROB_GOALS_MET ? true: false);
				
		// Random shift
		this.shift = r.nextInt(2) + 1;
	}

	public Shift(ArrayList <Employee> workShift, Day day, int shift, boolean goalsMet)
	{
		this.workShift = workShift;
		this.day = day;
		this.goalsMet = goalsMet;
		// Checks shift
		if(shift == 1 || shift == 2)
		{
			this.shift = shift;
		}
		else
		{
			this.shift = 1;
		}
		
	}
					   
	public Day getDay()
	{
		return day;
	}
	
	public String getShift()
	{
		String shift_;
		shift_ = (shift == 1 ?  "Day":  "Night");
		
		return shift_;
	}
}
