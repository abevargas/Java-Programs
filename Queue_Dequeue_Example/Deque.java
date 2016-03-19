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
public class Deque extends Queue implements ExtendedQueueInterface {

	@Override
	public void insertFirst(Object newItem) throws ExtendedQueueException {
		try{
			queue.add(0, newItem);
		}catch(Exception e){
			throw new ExtendedQueueException("Error: Item not inserted.");
		}
	}

	@Override
	public Object removeLast() throws ExtendedQueueException {
		try{
			Object item = queue.get(queue.size()-1);
			queue.remove(queue.size()-1);
			return item;
		}catch(Exception e){
			throw new ExtendedQueueException("Error: Queue is empty.");
		}
	}

	@Override
	public Object peekLast() throws ExtendedQueueException {
		try{
			return queue.get(queue.size()-1);
		}catch(Exception e){
			throw new ExtendedQueueException("Error: Queue is empty.");
		}
	}

}
