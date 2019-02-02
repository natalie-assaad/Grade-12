// Natalie Assaad
// Novemeber 15, 2018
// Stack functions

package stacksAndQueues;
import java.util.ArrayList;

public class Stack<E> {
	ArrayList<E> stack;
	
	public Stack() {
		stack = new ArrayList<E>();
	}

	//uses the add() method from the ArrayList class to add an element to the end of the ArrayList
	//(that we called stack)
	public void push(E element) {
		stack.add(element);
	}

	// code to pop (i.e. remove and return) an element
	// from the end of the stack
	public E pop() {
		boolean empty = isEmpty();
		if (!empty) {
			int index = stack.size() - 1;
			E newValue = stack.remove(index);
			return newValue;
		}
		return null;
	}

	//will determine the index of the last element in the stack
	public E peek() {
		boolean empty = isEmpty();
		if (!empty) {
			int index = stack.size() - 1;
			E newValue = stack.get(index);

			return newValue;
		}
		return null;
	}
	
	public int size() {
		boolean empty = isEmpty();
		if (!empty) {
			return stack.size();
		}
		return 0;
	}
	
	public E get(int i) {
		boolean empty = isEmpty();
		if (!empty) {
			return stack.get(i);
		}
		return null;
	}

	//checks to see if stack is empty
	public boolean isEmpty() {
		int size = stack.size();
		if (size == 0) {
			return true;
		}
		return false;
	}
		
}
