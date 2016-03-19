package Lab6;
import java.io.*;
/**
 * Purpose: Data Structure and Algorithms Lab 6 Pre-Lab:Problem 1
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
public class Driver{
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static Queue queue = new Queue();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean run = true;

		while(run){
			System.out.println("Select an option from the following menu:\n\t" +
					"1. Insert item at back of queue.\n\t" +
					"2. Remove item from front of queue.\n\t" +
					"3. Display front item of queue.\n\t" +
					"4. Clear  queue.\n\t" +
					"5. Exit.\n" +
					"Make your menu selection now: ");

			try{
				int selection = Integer.parseInt((r.readLine().trim()));		

				switch(selection){
				case 1:  queue();
				break;
				case 2:	 dequeue();
				break;
				case 3:	 peek();
				break;
				case 4:	 queue.dequeueAll();
				System.out.println();
				break;
				case 5:	 run = false;
				break;
				default: System.out.println("Selection entered is out of range!\n");
				break;
				}}catch(IOException e){}
		}// end while
		System.out.println("Exiting program...Good Bye.");
	}

	/**
	 * Insert item at back of queue
	 */
	private static void queue() {
		Object item;
		System.out.println("Enter item: ");

		try{
			item = r.readLine();
			queue.enqueue(item);
			System.out.println("Item inserted at back of queue.\n");
		}catch(IOException e){
			System.out.println("Error: Item not inserted.\n");
		}
	}
	/**
	 * Remove item from front of queue
	 */
	private static void dequeue() {
		try{
			System.out.println("Item " + queue.dequeue() + " was removed from the front of the queue.\n");
		}catch(QueueException e){
			System.out.println("Error: Queue is empty.\n");
		}
	}
	/**
	 * Display front item of queue
	 */
	private static void peek() {
		try{
			System.out.println("The item in front of the queue is " + queue.peek() + ".\n");
		}catch(QueueException e){
			System.out.println("Error: Queue is empty.\n");
		}
	}
}
