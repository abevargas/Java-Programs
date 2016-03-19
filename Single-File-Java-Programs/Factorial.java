package Lab7;
import java.util.Scanner;
/**
 * Purpose: Data Structure and Algorithms Lab 7 Problem 2
 *Author: Abraham Vargas
 *Version: 2.3 (Complete)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
public class Factorial {
	static Scanner s = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Enter a number to see all the numbers that come before it: ");
		int n = s.nextInt();
		fact(n);
		System.out.println(fact(n));
	}
	
	public static String fact(int n){
		if(n == 0)
			return "0";
		else
			return fact(n-1) + n;
	}
}
