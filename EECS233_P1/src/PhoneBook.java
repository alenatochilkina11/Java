/**
 * A class to represent the phone book with people's IDs and phone numbers
 * @author Alena Tochilkina (axt557)
 * Feb 14, 2020
 */
public interface PhoneBook {
	
	/**
	 * A method to retrieve the number of people stored in the phone book
	 * @return int This returns the number of people stored in the phone book
	 */
	int size();
	
	/**
	 * A method to insert a person into a phone book at a specific location (O(n))
	 * @param i is the index of whether the element should be inserted
	 * @param person is the person to be inserted into a phone book
	 */
	void insert(int i, Person person);
	
	/**
	 * A method to remove an element at a specific location from phone book
	 * @param i is the index at which the elements is deleted
	 * @return Person This returns the person that gets removed from a specific location
	 */
	Person remove(int i);
	
	/**
	 * A method for searching for the person in the phone book
	 * @param i is the index of the person in the phone book
	 * @return Person This returns the person at index i
	 */
	Person lookup(int i);
	
	/**
	 * A method to retrieve an iterator for phone book class
	 * @return PhBIterator This returns an iterator for phone book 
	 */
	PhBIterator phBIterator();
	
	/**
	 * A method to print people's information from the phone book
	 */
	void print();
	
}
