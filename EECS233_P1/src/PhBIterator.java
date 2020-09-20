/**
 * A class to represent the iterator for phone book
 * @author Alena Tochilkina (axt557)
 * Feb 14, 2020
 */
public interface PhBIterator {
	
	/**
	 * A method to check whether the end of the list was reached
	 * @return boolean This method returns true if the end of list is reached
	 */
	boolean hasNext();
	
	/**
	 * A method to return the next person in the list
	 * @return Person This returns the next person in the list
	 */
	Person next();

}
