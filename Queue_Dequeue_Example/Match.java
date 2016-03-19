package Lab6;
/**
 * Purpose: Data Structure and Algorithms Lab 6 Problem 3
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
import java.io.*;
import java.util.ArrayList;

public class Match {
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static ArrayList<Character> line = new ArrayList();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean run = true;

		while(run){
			System.out.print("Enter character: ");
			try{
				char c = r.readLine().charAt(0);
				System.out.println(c);
				if(c != '*')
					line.add(c);
				else{
					compare();
					line.clear();
					System.out.println("Try again? y/n");
					if(r.readLine().charAt(0) != 'y'){
						System.out.println("Exiting...Goodbye.");
						break;
					}
				}
			}catch(IOException e){}
		} //end while
	} // end main

	/**
	 * Default Constructor
	 */
	public Match(){

	}

	/**
	 * Compare the given inputs
	 */
	static void compare(){
		if(line.isEmpty()){
			System.out.println("\t>>Error: Input was empty.");
			return;
		}
		if(!line.contains('-')){
			System.out.println("\t>>No Minus");
			return;
		}

		int minusPos = line.indexOf('-');

		if(line.size()-1 == 0) //only minus input
			System.out.println("\t>>Same Length and Same Content, No Palindrome");
		else if(line.size() / 2 < minusPos || minusPos == line.size()-1)
			System.out.println("\t>>Left Longer");
		else if(line.size() / 2 > minusPos)
			System.out.println("\t>>Right Longer");

		else{ // same length and content
			boolean content = true;
			boolean palindrome = true;

			for(int i = 0; i < minusPos; i++){
				if(line.get(i) != line.get(minusPos+1+i))
					content = false;
				if(line.get(i) != line.get(minusPos-1-i) && i < minusPos)
					palindrome = false;
				if(content == false){
					System.out.println("\t>>Same Length, Different Content");
					return;
				}
			}
			if(palindrome) // also a palindrome
				System.out.println("\t>>Same Length and Same Content, Palindrome");
			else // Not a palindrome
				System.out.println("\t>>Same Length and Same Content");
		}
	}// end compare
}
