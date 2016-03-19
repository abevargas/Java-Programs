package Lab5;

/**
 * Purpose: Data Structure and Algorithms Lab 5 Problem 2
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/26/13
 *Submitted:  02/26/13
 *Comment:
 */
import java.util.Scanner;
import java.util.ArrayList;

public class BagDriver{
	private static Scanner sc = new Scanner(System.in);
	private static MyStack bag = new MyStack();
	private static MyStack sampleBag = new MyStack();
	private static ArrayList<Double> weights = new ArrayList<Double>();
	private static ArrayList<Double> sampleWeights = new ArrayList<Double>();
	private static double weight = 0;
	private static int size = 0;
	private static int sampleSize = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean run = true;

		while(run){
			System.out.println("Select an option from the following menu:\n\t" +
					"1. Place a layer of item(s) in the bag.\n\t" +
					"2. Remove a layer of item(s) from the bag.\n\t" +
					"3. Display the weight of the bag.\n\t" +
					"4. Display the number of layers in the bag.\n\t" +
					"5. Display the number of items and the weight of the sample bag.\n\t" +
					"6. Remove an item from the sample bag.\n\t" +
					"7. Empty the sample bag.\n\t" +
					"8. Exit.\n" +
					"Make your menu selection now: ");


			int selection = sc.nextInt();		

			switch(selection){
			case 1:  push();
			break;
			case 2:	 pop();
			break;
			case 3:	 getWeight();
			break;
			case 4:	 getLayers();
			break;
			case 5:	 getSample();
			break;
			case 6:	 removeSample();
			break;
			case 7:  emptySample();
			break;
			case 8:  run = false;
			break;
			default: System.out.println("Selection entered is out of range!\n");
			break;
			}
		}// end while
		System.out.println("Exiting program...Good Bye");
	}

	/**
	 * Places a layer of items in the bag
	 */
	private static void push(){
		Object numItems;
		double thisWeight;		

		System.out.println("Enter number of items to place in bag: ");
		numItems = sc.nextInt();
		System.out.println("Enter weight of item (lb): ");
		thisWeight = sc.nextDouble();
		try{
			weights.add(thisWeight);
		}catch(Exception e){

		}
		weight += thisWeight * Double.parseDouble(numItems.toString());

		bag.push(numItems);

		System.out.println(numItems + " items weighing " + thisWeight +
				" lbs have been placed in the bag.\n");
		size++;
	}

	/**
	 * Removes the top layer of items
	 */
	private static void pop(){		
		if(bag.isEmpty()){
			System.out.println("Error: Empty bag.\n");
			return;
		}

		Object temp = bag.pop();
		double tempWeight = Double.parseDouble(weights.remove(weights.size()-1).toString());
		weight = weight - tempWeight * Double.parseDouble(temp.toString());

		System.out.println(temp + " items have been removed from the bag.\n" +
				"Would you like to store a sample from this layer (Y/N)?");
		try{
			if(sc.next().toUpperCase().equals("Y")){
				sampleBag.push(temp);
				sampleWeights.add(tempWeight);
				System.out.println("Sample stored.\n");
				sampleSize++;
			}
			else
				System.out.println();
		}catch(Exception e){
		}
		size--;
	}

	/**
	 * Displays the weight of the bag.
	 */
	private static void getWeight(){
		if(bag.isEmpty())
			System.out.println("The bag is empty: Weight is 0 lbs.\n");		
		else
			System.out.println("The weight of the bag is " + weight + " lbs.\n");
	}

	/**
	 * Displays the number of layers in the bag.
	 */
	private static void getLayers(){
		if(bag.isEmpty())
			System.out.println("The bag is empty: No layers.\n");
		else
			System.out.println("The number of layers in this bag is " + size + ".\n");
	}

	/**
	 * Displays the number of items and the weight of the sample bag.
	 */
	private static void getSample(){
		double sampleWeight = 0;
		if(sampleBag.isEmpty())
			System.out.println("The sample bag is empty: No items.\n");
		else{
			for(int i = 0; i < sampleWeights.size(); i++){
				sampleWeight += sampleWeights.get(i);
			}
			System.out.println("The number of items in the sample bag is " + sampleSize + ".");
			System.out.println("The sample bag weighs " + sampleWeight + " lbs.\n");
		}
	}

	/**
	 * Removes an item from the sample bag.
	 */
	private static void removeSample(){
		if(sampleBag.isEmpty())
			System.out.println("Error: Empty sample bag.\n");
		else{
			System.out.println("An item weighing " + sampleWeights.remove(sampleSize-1) + " lbs has been removed from the sample bag.\n");			
			sampleBag.pop();
			sampleSize--;
		}
	}

	/**
	 * Empty sample bag
	 */
	private static void emptySample(){
		if(sampleBag.isEmpty())
			System.out.println("Error: Empty sample bag.\n");
		else{
			sampleBag.popAll();
			sampleWeights.clear();
			sampleSize = 0;
			System.out.println("The sample bag has been emptied.\n");
		}
	}
}