// Natalie Assaad
// Novemeber 15, 2018
// Queue functions

package stacksAndQueues;

import java.util.ArrayList;

public class Queue<E> {
	ArrayList<E> queue;
	
	public Queue() {
		queue = new ArrayList<E>();
	}

	//add an item to the “back” of the queue
	public void enqueue(E element) {
		queue.add(0, element);
	}

	//remove and return the item from the “front” of the queue
	public E dequeue(int num) {
		boolean empty = isEmpty();
		if (!empty) {
			E newValue = queue.remove(num);
			return newValue;
		}
		return null;
	}

	//return the item from the “front” of the queue (but don’t remove it from queue)
	public E peek() {
		boolean empty = isEmpty();
		if (!empty) {
			int index = queue.size() - 1;
			E newValue = queue.get(index);
			return newValue;
		}
		return null;
	}
	
	public E printIndex(int chosenIndex) {
		boolean empty = isEmpty();
		if (!empty) {
			E chosenValue = queue.get(chosenIndex);
			System.out.println(chosenValue);
			return chosenValue;
		}
		return null;
	}

	//check to see if queue is empty
	public boolean isEmpty() {
		int size = queue.size();
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	//check to see if queue is empty
	public boolean isEnoughPlayers() {
		int size = queue.size();
		if (size > 1) {
			return true;
		}
		return false;
	}
	
	//check to see if queue is empty
	public boolean isOnePlayer() {
		int size = queue.size();
		if (size == 1) {
			return true;
		}
		return false;
	}

	public int size() {
		boolean empty = isEmpty();
		if (!empty) {
			return queue.size();
		}
		return 0;
	}

	public E get(int i) {
		boolean empty = isEmpty();
		if (!empty) {
			return queue.get(i);
		}
		return null;
	}
		
}