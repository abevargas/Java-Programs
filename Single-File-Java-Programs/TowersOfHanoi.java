package Lab7;
/**
 * Purpose: Data Structure and Algorithms Lab 7 Pre-Lab Problem 1
 *Author: Abraham Vargas
 *Version: 2.3 (Incomplete)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
import java.util.Scanner;

public class TowersOfHanoi {
	static Scanner scan = new Scanner(System.in);
	static char firstPole = 'A';
	static char secondPole = 'B';
	static char thirdPole = 'C';
	static int moves = 0;
	static int disks;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("How many disks would you like to solve for? ");
		disks = scan.nextInt();
		solve(disks);
	}

	/**
	 * Default Constructor
	 */
	public TowersOfHanoi(){
	}

	/**
	 * Solve the tower
	 * @param disks given number of disks to sort
	 */
	public static void solve(int disks){
		if(disks <= 0)
			System.out.println("There are no disks.");
		else if(disks == 1)
			System.out.println("There is only one disk. Nothing to sort.");
		else
			/*TODO*/;
	}
	
	/**
	 * Moves the disks
	 */
	private static void move(char firstPole, char secondPole){
		
		moves++;
	}
}
