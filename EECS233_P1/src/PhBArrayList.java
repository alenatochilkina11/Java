import java.util.NoSuchElementException;

/**
 * A class to represent an array representation of phone book
 * @author Alena Tochilkina (axt557)
 * Feb 14, 2020
 */
public class PhBArrayList implements PhoneBook{

	/**
	 * This stores an array of people
	 */
	private Person[] arrayList;

	/**
	 * This stores the size of the array
	 */
	private int numOfPeople;

	/**
	 * A constructor for PhBArrayList class
	 */
	public PhBArrayList() {
		this.arrayList = new Person[20];
		numOfPeople = 0;
	}

	/**
	 * A method to retrieve the array of people
	 * O(1)
	 * @return Person[] This returns the array of people
	 */
	public Person[] getArrayList() {
		return arrayList;
	}

	/**
	 * A method to retrieve the number of people stored in the array
	 * O(1)
	 * @return int This returns the number of people stored in the array
	 */
	public int size() {
		return numOfPeople;
	}

	/**
	 * A method to increment the size of the array by 1
	 * O(1)
	 */
	public void increment() {
		numOfPeople++;
	}

	/**
	 * A method to decrement the size of the array by 1
	 * O(1)
	 */
	public void decrement() {
		numOfPeople--;
	}

	/**
	 * A method to resize the the array (multiply the size by 2)
	 * O(N)
	 */
	public void toResize() {

		Person[] tempArray = new Person[getArrayList().length];

		for (int j = 0; j < getArrayList().length; j++) {
			tempArray[j] = getArrayList()[j];
		}

		arrayList = new Person[getArrayList().length * 2];

		for(int k = 0; k < tempArray.length; k++) {
			getArrayList()[k] = tempArray[k];
		}
	}

	/**
	 * A method to insert a person into the phone book at a specific index
	 * O(N)
	 * @param i is the index where to insert the person
	 * @param person is the person to insert in the list
	 */
	public void insert(int i, Person person) {

		//if the number of people in the array is greater then the length of the array
		if (size() >= getArrayList().length)		
			toResize();

		//if i is greater than the number of people in the array
		if (i >= size()) {
			//insert to the end of the array
			arrayList[size()] = person;
		}

		//in the middle cases
		else {

			//shift array from the element before i to the right
			for (int j = size() - 1; j >= i; j--) {
				arrayList[j+1] = arrayList[j];
			}
			//occupy the empty slot before i
			arrayList[i-1] = person;
		}
		//increase the number of people in the array by 1
		increment();

	}

	/**
	 * A method to remove a person from a specific location of the list
	 * O(N)
	 * @param i is the index from where remove the person
	 * @return Person This returns the removed person
	 */
	public Person remove(int i) {

		Person save = null;

		//if i is greater that the size, do nothing
		if(i > size())
			;

		//if the array is empty
		else if(size() == 0) {
			throw new NoSuchElementException("the list is empty");
		}

		//in all other cases
		else {
			//save the person in the position we are removing from
			save = arrayList[i-1];
			//shift the array to the right 
			for(int j = i-1; j < size(); j++) {
				arrayList[j] = arrayList[j+1];
			}
		}
		//decrease the number of people in the array by 1
		decrement();

		return save;
	}


	/**
	 * A method to lookup the person by its index in the array
	 * O(1)
	 * @param i is the index of the person
	 * @return Person This returns the person at the given index
	 */
	public Person lookup(int i) {
		//if index is greater than the size of the array, throw an exception
		if (i > size())
			throw new NoSuchElementException("the index is too large");

		return arrayList[i];
	}

	/**
	 * A method to print the list with people's information
	 * O(N)
	 */
	public void print() {

		PhBIterator iter = phBIterator();

		while(iter.hasNext()) {
			System.out.println(iter.next().printInfo());
		}
	}

	/**
	 * A method to return an iterator for array list
	 * O(1)
	 * @return PhBIterator This returns the iterator for array list
	 */
	public PhBIterator phBIterator() {

		return new PhBArrayIterator();

	}

	/**
	 * A class to represent the array iterator
	 * @author Alena Tochilkina
	 */
	private class PhBArrayIterator implements PhBIterator { 

		/**
		 * Stores the index for iteration
		 */
		private int index;

		/**
		 * A constructor for PhBArrayIterator class
		 */
		public PhBArrayIterator() {
			this.index = 0;
		}

		/**
		 * A method to retrieve the index
		 * O(1)
		 * @return int This returns the index of iteration
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * A method to set the index
		 * O(1)
		 * @param index is the index of iteration
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * A method to check whether we reached the end of the array
		 * O(1)
		 * @return boolean This returns true if the end is not reached, and false otherwise
		 */
		public boolean hasNext() {
			return getIndex() < size();
		}

		/**
		 * A method to retrieve the next person in the array
		 * O(1) 
		 * @return Person This returns the next person in the array
		 */
		public Person next() {

			Person save = null;

			if (getIndex() < size()) {
				save = arrayList[getIndex()];
				setIndex(getIndex() + 1);
			}

			else 
				throw new NullPointerException();

			return save;
		}
	}

}
