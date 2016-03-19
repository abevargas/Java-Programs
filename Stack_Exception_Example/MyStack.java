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
import Lab4.MyListReferenceBased;

public class MyStack implements StackInterface {
	MyListReferenceBased stack;

	public MyStack(){
		stack = new MyListReferenceBased();
	}

	@Override
	public boolean isEmpty() {
		if(stack.isEmpty())
			return true;
		return false;
	}

	@Override
	public void popAll() {
		stack.removeAll();		
	}

	@Override
	public void push(Object newItem) throws StackException {
		try{
			stack.add(stack.size(), newItem);
		}catch(Exception e){
			throw new StackException("Item could not be added to the list.");
		}
	}

	@Override
	public Object pop() throws StackException {
		Object temp;

		if(!stack.isEmpty()){
			temp = stack.get(stack.size()-1);
			stack.remove(stack.size()-1);
		}
		else
			throw new StackException("Stack is empty.");

		return temp;
	}

	@Override
	public Object peek() throws StackException {
		if(!stack.isEmpty())
			return stack.get(stack.size()-1);
		else
			throw new StackException("Stack is empty.");
	}

}
