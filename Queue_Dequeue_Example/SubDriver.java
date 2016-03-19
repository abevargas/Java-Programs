package Lab6;
import java.io.*;
/**
 * Purpose: Data Structure and Algorithms Lab 6 Problem 2
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
public class SubDriver{
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static Deque deque = new Deque();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean run = true;

		while(run){
			System.out.println("Select an option from the following menu:\n\t" +
					"1. Insert item at back of queue.\n\t" +
					"2. Insert item at front of queue.\n\t" +
					"3. Remove item from front of queue.\n\t" +
					"4. Remove item from back of queue.\n\t" +
					"5. Display front item of queue.\n\t" +
					"6. Display last item of queue.\n\t" +
					"7. Clear  queue.\n\t" +
					"8. Exit.\n" +
					"Make your menu selection now: ");

			try{
				int selection = Integer.parseInt((r.readLine().trim()));		

				switch(selection){
				case 1:  queue();
				break;
				case 2:	 queueFront();
				break;
				case 3:	 dequeue();
				break;
				case 4:	 dequeueBack();
				break;
				case 5:	 peek();
				break;
				case 6:	 peekBack();
				break;
				case 7:	 deque.dequeueAll();
				System.out.println();
				break;
				case 8:	 run = false;
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
			deque.enqueue(item);
		}catch(IOException e){}
		System.out.println("Item inserted at back of queue.\n");
	}

	/**
	 * Insert item at front of queue
	 */
	private static void queueFront() {
		Object item;
		System.out.println("Enter item: ");
		try{
			item = r.readLine();
			deque.insertFirst(item);
		}catch(IOException e){}
		System.out.println("Item inserted at front of queue.\n");
	}

	/**
	 * Remove item from front of queue
	 */
	private static void dequeue() {
		try{
			System.out.println("Item " + deque.dequeue() + " removed from front of queue.\n");
		}catch(QueueException e){
			System.out.println("Error: Queue is empty.\n");
		}
	}

	/**
	 * Remove item from back of queue
	 */
	private static void dequeueBack() {
		try{
			System.out.println("Item " + deque.removeLast() + " removed from back of queue.\n");
		}catch(ExtendedQueueException e){
			System.out.println("Error: Queue is empty.\n");
		}
	}

	/**
	 * Display front item of queue
	 */
	private static void peek() {
		try{
			System.out.println("The item in front of the queue is " + deque.peek() + ".\n");
		}catch(QueueException e){
			System.out.println("Error: Queue is empty.\n");
		}
	}

	/**
	 * Display last item of queue
	 */
	private static void peekBack() {
		try{
			System.out.println("The item in front of the queue is " + deque.peekLast() + ".\n");
		}catch(ExtendedQueueException e){
			System.out.println("Error: Queue is empty.\n");
		}
	}
}
