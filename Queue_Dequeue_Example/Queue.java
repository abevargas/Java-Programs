package Lab6;
import Lab4.MyListReferenceBased;
/**
 * Purpose: Data Structure and Algorithms Lab 6 Pre-Lab:Problem 1
 *Author: Abraham Vargas
 *Version: 2.3 (Draft/Final)
 *Status: Complete and thoroughly tested/Incomplete/ Barely started
 *Last update: 02/27/13
 *Submitted:  03/5/13
 *Comment:
 */
public class Queue implements QueueInterface {
	MyListReferenceBased queue = new MyListReferenceBased();

	@Override
	public boolean isEmpty() {
		if(queue.isEmpty())
			return true;
		return false;
	}

	@Override
	public void enqueue(Object newItem) throws QueueException {
		try{
			queue.add(queue.size(),newItem);
		}catch(Exception e){
			throw new QueueException("Item could not be queued.");
		}
	}

	@Override
	public Object dequeue() throws QueueException {
		if(!queue.isEmpty()){
			Object item = queue.get(0);
			queue.remove(0);
			return item;
		}
		else
			throw new QueueException("Queue is empty");
	}

	@Override
	public void dequeueAll() {
		if(!queue.isEmpty())
			queue.removeAll();
	}

	@Override
	public Object peek() throws QueueException {
		if(!queue.isEmpty())
			return queue.get(0);		
		else
			throw new QueueException("Queue is empty");
	}
}
