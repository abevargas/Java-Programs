package Lab7;
import java.util.Scanner;
/**
 * Purpose: Data Structure and Algorithms Lab 7 Problem 3
 *Author: Abraham Vargas
 *Version: 2.3 (Incomplete)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
public class PascalTriangel {
	static Scanner s = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.print("Enter the first number: ");
		int n = s.nextInt();
		System.out.print("Enter the second number: ");
		int k = s.nextInt();
		System.out.println("Recursive: " + solveR(n,k));
		System.out.println("Iterative: " + solveI(n,k));
		//draw(n,k);
	}

	/**
	 * Problem 3 part A
	 * @param n
	 * @param k
	 * @return
	 */
	private static int solveR(int n, int k){
		if(n >= 0){
			if(k == 0)
				return 1;
			if(n == k)
				return 1;
		}
		if(n > k && k > 0)
			return solveR(n-1, k) + solveR(n-1, k-1);
		else
			return 0;
	}

	/**
	 * Problem 3 part C
	 * @param n
	 * @param k
	 */
	private static void draw(int n, int k) {
		int pasTri[][] = new int[n][k];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				pasTri[i][j] = 0;
			}
		}

		for(int i = 0; i < n; i++) {
			pasTri[i][0] = 1 ;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < k; j++) {
				pasTri[i][j] = pasTri[i-1][j-1] + pasTri[i-1][j];
			}
		}

		for (int i = 0; i < n; i++) {
			for(int j=0;j<=i;j++) {
				System.out.print(pasTri[i][j]+ " ");
			}
			System.out.println();
		}
	} 

	/**
	 * Problem 3 part D
	 * @param n
	 * @param k
	 */
	private static int solveI(int n, int k){
		int value = 0;		
		if(n >= 0){
			if(n > k && k > 0){
				for(int i = n; i >=0; i--){
					for(int a = i, j = k; j >= 0; j--, a--){
						value += solveLeft(a,j);				
					}
				}	
			}
		}
		return value;
	}
	
	private static int solveLeft(int n, int k){
		int value = 0;
		for(int i = n-1, j = k; i >=0 ; i--){
			if(j == 0){
				value += 1;
				break;
			}
			if(i == j)
			{
				value += 1;
				break;
			}						
			value += solveRight(i,j);
		}
		return value;
	}
	
	private static int solveRight(int n, int k){
		int value = 0;
		for(int i = n-1, j = k-1; j >=0 ; j--,i--){
			if(j == 0){
				value += 1;
				break;
			}
			if(i == j)
			{
				value += 1;
				break;
			}	
		}
		return value;
	}
}
