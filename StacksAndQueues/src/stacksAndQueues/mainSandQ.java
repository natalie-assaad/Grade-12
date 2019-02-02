// Natalie Assaad
// Novemeber 15, 2018
// This code runs stacks and queues to ensure they work

package stacksAndQueues;

public class mainSandQ {

	public static void main(String[] args) {
		
		Stack<String> stack = new Stack<>(); // creates new instance  of stack
		Queue<String> queue = new Queue<String>(); // creates new instance of queue
		
		queue.enqueue("Joe");	// push a string onto the stack
		queue.enqueue("Fred");
		queue.enqueue("Mary");
		queue.enqueue("Sue");
		
		String player = queue.peek(); // checks string at the front of the queue
		System.out.println(player);
		
		player = queue.dequeue(0); // removes string at the front of the queue
		System.out.println(player);
		
		player = queue.peek();          // see what the new first string is
		System.out.println(player);
		
		System.out.println("");
		
		stack.push("Joe");	// push a few names onto the stack
		stack.push("Fred");
		stack.push("Mary");
		stack.push("Sue");
				
		String play = stack.peek();  //see what the last one entered was
		System.out.println(play);
				
		play = stack.pop();        	    // remove the last name entered
		System.out.println(play);
				
		play = stack.peek();          // see what the new last name is
		System.out.println(play);

	}

}
