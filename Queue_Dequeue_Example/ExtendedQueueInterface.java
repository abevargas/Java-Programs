package Lab6;
/**
 * Purpose: Data Structure and Algorithms Lab 6 Problem 2
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
public interface ExtendedQueueInterface {

	public void insertFirst(Object newItem) throws ExtendedQueueException;
	public Object removeLast() throws ExtendedQueueException;
	public Object peekLast() throws ExtendedQueueException;
}  // end ExtendedQueueInterface
