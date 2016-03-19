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

public interface StackInterface {
	public boolean isEmpty();
	// Determines whether the stack is empty.
	// Precondition: None.
	// Postcondition: Returns true if the stack is empty;
	// otherwise returns false.

	public void popAll();
	// Removes all the items from the stack.
	// Precondition: None.
	// PostCondition: Stack is empty.

	public void push(Object newItem) throws StackException;
	// Adds an item to the top of a stack.
	// Precondition: newItem is the item to be added.
	// Postcondition: If insertion is successful, newItem
	// is on the top of the stack.
	// Exception: Some implementations may throw 
	// StackException when newItem cannot be placed on
	// the stack.

	public Object pop() throws StackException;
	// Removes the top of a stack.
	// Precondition: None.
	// Postcondition: If the stack is not empty, the item 
	// that was added most recently is removed from the
	// stack.
	// Exception: Throws StackException if the stack is 
	// empty.

	public Object peek() throws StackException;
	// Retrieves the top of a stack.
	// Precondition: None.
	// Postcondition: If the stack is not empty, the item
	// that was added most recently is returned. The 
	// stack is unchanged. 
	// Exception: Throws StackException if the stack is 
	// empty.
}  // end StackInterface