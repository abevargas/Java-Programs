/**
 * Purpose: Data Structure and Algorithms Lab 5 Problem 1
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/26/13
 *Submitted:  02/26/13
 *Comment:
 */
package Lab5;
import java.io.*;

public class Driver{
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static MyStack stack = new MyStack();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean run = true;

		while(run){
			System.out.println("Select an option from the following menu:\n\t" +
					"1. Push item onto stack.\n\t" +
					"2. Pop item from stack.\n\t" +
					"3. Display top item from stack.\n\t" +
					"4. Clear stack.\n\t" +
					"5. Exit.\n" +
					"Make your menu selection now: ");

			try{
				int selection = Integer.parseInt((r.readLine().trim()));		

				switch(selection){
				case 1:  push();
				break;
				case 2:	 pop();
				break;
				case 3:	 peek();
				break;
				case 4:	 stack.popAll();
				System.out.println();
				break;
				case 5:	 run = false;
				break;
				default: System.out.println("Selection entered is out of range!\n");
				break;
				}}catch(IOException e){				
				}
		}// end while
		System.out.println("Exiting program...Good Bye");
	}

	/**
	 * Pushes an item onto the stack
	 */
	private static void push(){
		Object item;

		System.out.println("You are now pushing an item onto the stack.\n\t" +
				"Enter item: ");
		try{
			item = r.readLine().trim();

			stack.push(item);
			System.out.println("Item " + item + " pushed onto the stack.\n");
		}catch(Exception e){
		}
	}

	/**
	 * Pops an item off the stack
	 */
	private static void pop(){
		if(stack.isEmpty()){
			System.out.println("Stack is empty\n");
			return;
		}

		System.out.println("Item " + stack.pop() + " popped off the stack.\n");
	}

	/**
	 * Peeks at the top item on the stack
	 */
	private static void peek(){
		if(stack.isEmpty()){
			System.out.println("Stack is empty\n");
			return;
		}

		System.out.println("Item " + stack.peek() + " is on top of the stack.\n");
	}
}
