/**
 * A class to represent Minimum Heap for HuffmanNode
 * @author Alena Tochilkina
 */

public class HuffmanHeap {

	/** A field to store items in the heap*/
	private HuffmanNode[] items;
	/** A field to store maximum number of items in the heap*/
	private int maxItems;
	/** A field to store current number of items in the heap*/
	private int numItems;

	/**
	 * A constructor for HuffmanHeap
	 * @param maxSize is the maximum number of items in the heap*
	 * */
	public HuffmanHeap(int maxSize) {
		this.items = new HuffmanNode[maxSize];
		this.maxItems = (maxSize);
		this.numItems = 0;
	}

	/**
	 * A method to get the all items stored in the heap
	 * @return HuffmanNode[] This returns the items stored in the heap
	 */
	public HuffmanNode[] getItems() {
		return items;
	}
	/**
	 * A method to get the maximum number of items stored in the heap
	 * @return int This returns the maximum number of items stored in the heap
	 */
	public int getMaxItems() {
		return maxItems;
	}
	/**
	 * A method to get the current number of items stored in the heap
	 * @return int This returns the current number of items stored in the heap
	 */
	public int getNumItems() {
		return numItems;
	}

	/**
	 *A method to double the size of items array ones the numItems reaches max capacity
	 */
	public void toResize() {
		HuffmanNode[] tempArray = new HuffmanNode[items.length];
		//copy items of the original array into the tempArray
		for (int j = 0; j < items.length; j++) {
			tempArray[j] = items[j];
		}
		//double the size of the original array
		items  = new HuffmanNode[items.length * 2];

		//copy the items from the tempArray back into original
		for(int k = 0; k < tempArray.length; k++) {
			items[k] = tempArray[k];
		}
	}

	/**
	 *A method to insert an item in the binary heap
	 *@param item is the item being inserted
	 */
	public void insert(HuffmanNode item) {
		//resize array if necessary
		if (numItems == maxItems)
			toResize();
		//insert item as the last element of the tree/array
		items[numItems] = item;
		numItems++;
		//place the inserted item into the right spot in the tree/array
		siftUp(numItems - 1);
	}

	/**
	 *A method to remove the root from the binary heap
	 *@return HuffmanNode This returns the item that was removed
	 */
	public HuffmanNode removeMin() {

		HuffmanNode toRemove;

		//checking if the array is not empty
		if (getNumItems() == 0) {
			toRemove = null;
			System.out.println("Removing From Empty Array");
		}

		else {
			toRemove = items[0];
			//replaces the root with the smallest element
			items[0] = items[numItems - 1];
			numItems--;
			siftDown(0);
		}

		return toRemove;
	}

	/**
	 *A method to “sift-down” (properly organize) the tree
	 * Source: Lecture 12 slides
	 *@param i is the index of the item that is being sifted-down
	 */
	public void siftDown(int i) {
		HuffmanNode toSift = items[i];
		//stores the value of current item’s index
		int cursor = i;
		//stores the value of item’s right child
		int child = 2 * i + 1;
		
		while (child < numItems) {
			HuffmanNode leftChild = items[child];
			HuffmanNode rightChild = items[child + 1];
			//checks whether left child exists
			if (child + 1 < numItems) {
				
				//determines which child’s value is the greatest
				if(leftChild.compareTo(rightChild) < 0) 
					child = child + 1;
			}

			//exits the loop if the item we need to sift is greater than its greatest child
			if(toSift.compareTo(items[child]) >= 0)
				break;
			
			//swap nodes in case if a child is greater than the parent
			items[cursor] = items[child];
			items[child] = toSift;
			//move the cursor to the swapped child in order to repeat the sifting
			cursor = child;
			//change the value for the new left child
			child = 2 * cursor + 1;
		}
		items[cursor] = toSift;
	}

	/**
	 *A method to “sift-up” (properly place) the item in the binary heap
	 *@param i is the index of the item that is being sifted-up
	 */
	public void siftUp(int i) {
		HuffmanNode toSift = items[i];
		int cursor = i;
		//if i is equal to 0, the item is the root and has no parents
		if ( i != 0) {
			//determine the index of the parent
			int parent = (i-1)/2;
			while (parent >= 0) {
				//stops the node for current parent
				HuffmanNode parentNode = items[parent];

				//checks whether item is smaller than the parent
				if(toSift.compareTo(parentNode) <= 0)
					break;

				//if the item is greater than the parent, swaps nodes
				items[cursor] = items[parent];
				items[parent] = toSift;

				//move the cursor to the swapped parent in order to repeat the sifting
				cursor = parent;

				//change the value for the new parent
				parent = (cursor - 1)/2;
			}
		}
		items[cursor] = toSift;
	}
}


