import java.util.NoSuchElementException;

/**
 * A class to represent a linked list representation of the phone book
 * @author Alena Tochilkina (axt557)
 * Feb 14, 2020
 */
public class PhBLinkedList implements PhoneBook{
	
	/**
	 * Stores the first node of the linked list
	 */
	private Node firstNode;
	
	/**
	 * A constructor for PhBLinkedList class
	 */
	public PhBLinkedList() {
		this.firstNode = null;
	}
	
	/**
	 * A method to retries the first node of the list 
	 * O(1)
	 * @return Node This returns the first node of the list
	 */
	public Node getFirstNode() {
		return firstNode;
	}

	/**
	 * A method to set the first node of the list 
	 * O(1)
	 * @param node is the node to be set as the first node of the list 
	 */
	public void setFirstNode(Node node) {
		firstNode = node;
	}

	/**
	 * A method to count the number of elements in the linked list
	 * O(N)
	 * @return int is the number of elements in the list
	 */
	public int size() {

		Node ptr = getFirstNode();
		
		int count = 0; 
		
		while(ptr != null) {
			count++;
			ptr = ptr.getNext();
		}
		return count;
	}

	/**
	 * A method to insert a person into a linked list at a specific location
	 * O(N)
	 * @param i is the index of whether the element should be inserted
	 * @param person is the person to be inserted into a linked list
	 */
	public void insert(int i, Person person) {
		
		Node ptr;
		//if the list is empty, person becomes the first node of the list
		if (size() == 0) {
			setFirstNode(new Node(person, null));
		}
		//if index of insertion is larger than the num. of elements, insert the element to the end of the list 
		else if(size() < i + 1) {
			
			ptr = getFirstNode();
			
			for (int j = 0; j < size() - 1; j++) {
				ptr.getNext();
			}
			
			ptr.setNext(new Node(person, null));	
		}
		
		//in other cases, go until the element before i and set the next to be equal to a new node with person 
		else {
			
			//if i is 0, set the second node as the first node
			if(i == 0) {
				Node save = new Node(person, getFirstNode());
				setFirstNode(save);
			}

			//all the other cases
			else {
				ptr = getFirstNode();
				
				//reach the index before the one where the person need to be inserted
				for (int j = 0; (j < i - 1); j++) {
					ptr = ptr.getNext();
				}

				ptr.setNext(new Node(person, ptr.getNext()));
			}
		}
	}


	/**
	 * A method to remove an element at a specific location
	 * O(N)
	 * @param i is the index at which the elements is deleted
	 * @return Person This returns the person that gets removed from a specific location
	 */
	public Person remove(int i) {
		
		Node ptr; 
		
		Person save = null;
		
		int count = 0;
		
		//if the list is empty
		if (size() == 0) {
			throw new NullPointerException("the list is empty, remove operation cannot proceed");
		}
		
		//if the size is 1, make the first node to be equal to null
		else if (size() == 1) {
			save = getFirstNode().getPerson();
			setFirstNode(null);
		}
		
		//if removing from index 0, set the second node to be a first node and set the first node to null
		else if (i == 0) {
			Node n = getFirstNode();
			setFirstNode(n.getNext()); 
			save = n.getPerson();
			
		}
		
		//if the number of elements is greater than the index, return null
		else if(size() < i) 
			return null;
		
		//else loop until the element before index, save the person set next to  the two elements ahead
		else {
			
			ptr = getFirstNode();
			//count until index before the one we need to remove
			while (ptr != null && count < i - 1) { 
				ptr = ptr.getNext();
				count++;
			}
			
			//save the person
			save = ptr.getNext().getPerson();
			//move the pointer two nodes ahead
			ptr.setNext(ptr.getNext().getNext());
			
		}
		//return the person removed at an index
		return save;	
	}

	/**
	 * A method for searching for the person in the linked list
	 * O(N)
	 * @param i is the index of the person in the linked list
	 * @return Person This returns the person at index i
	 */
	public Person lookup(int i) {
		
		Node ptr = getFirstNode();
		
		int count = 0; 
		
		//if i is larger that the number of people in the list
		if (size() < i)
			throw new NoSuchElementException("the index is too large");
		
		else {
			//go to the index of the person
			while(ptr != null && i > count ) {
				ptr = ptr.getNext();
				count++;
			}
		}
		
		return ptr.getPerson();
	}
	
	/**
	 * A method to retrieve an iterator for linked list class
	 * O(1)
	 * @return PhBIterator This returns an iterator for phone book 
	 */
	public PhBIterator phBIterator() {
		return new PhBListIterator(getFirstNode());
	}
	
	/**
	 * A method to print people's information from the linked list
	 * O(N)
	 */
	public void print() {
		
		for (Node ptr = getFirstNode(); ptr != null; ptr = ptr.getNext()) {
			System.out.println(ptr.getPerson().printInfo());
		}
		
	}
	
	/**
	 * A class to represent a node of a linked list
	 * @author Alena Tochilkina
	 */
	protected class Node {
		
		/**
		 * Stores a person 
		 */
		private Person person;
		
		/**
		 * Stores the next node value
		 */
		private Node next;
		
		/**
		 * A constructor for Node class
		 * @param person is the person in the list
		 * @param next is the next node of the linked list
		 */
		private Node (Person person, Node next) {
			this.person = person;
			this.next = next;
		}

		/**
		 * A method to retrieve a person from the list
		 * O(1)
		 * @return Person This returns the person stored in the list
		 */
		public Person getPerson() {
			return person;
		}

		/**
		 * A method to set the person value to the list
		 * O(1)
		 * @param person is the person stored in the list
		 */
		public void setPerson(Person person) {
			this.person = person;
		}

		/**
		 * A method to retrieve the next node
		 * O(1)
		 * @return Node This returns the next node in the linked list 
		 */
		public Node getNext() {
			return next;
		}

		/**
		 * A method used to set the next node of the linked list
		 * O(1)
		 * @param next is the next node of the linked list
		 */
		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	/**
	 * A class to represent the iterator for the linked lists
	 * @author Alena Tochilkina
	 *
	 */
	private class PhBListIterator implements PhBIterator {

		/**
		 * Stores the node pointer
		 */
		private Node ptr;
		
		/**
		 * A constructor for the PhBListIterator class
		 * @param firstNode is the first node of the linked lists
		 */
		public PhBListIterator(Node firstNode) {
			this.ptr = firstNode;
		}
		
		/**
		 * A method to retrieve the node pointer
		 * O(1)
		 * @return Node This returns the node pointer
		 */
		public Node getPtr() {
			return ptr;
		}

		/**
		 * A method to set a node pointer
		 * O(1)
		 * @param ptr is the node pointer
		 */
		public void setPtr(Node ptr) {
			this.ptr = ptr;
		}
	
		/**
		 * A method to determine whether the end of the list was reached
		 * O(1)
		 * @return boolean This returns true is end of the list was not reached, false otherwise
		 */
		public boolean hasNext() {
			return this.ptr.getNext() != null;
		}

		/**
		 * A method to retrieve the person store in the next node
		 * O(1)
		 * @return Person This returns the person store in the next node
		 */
		public Person next() {
			
			if (ptr == null)
				throw new NoSuchElementException();
			
			else {
				Person p = getPtr().getPerson();
				this.setPtr(getPtr().getNext());
				return p;
			}
		}
	}
}
