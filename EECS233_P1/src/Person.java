
/**
 * A class to represent the data stored in a Phone Book
 * @author Alena Tochilkina (axt557)
 * Feb 14, 2020
 */
public class Person {
	
	/**
	 * Stores a person's ID number
	 */
	private String personID;
	
	/**
	 * Stores a person's phone number
	 */
	private String phoneNum;
	
	/**
	 * A constructor for Person class 
	 * @param personID a person's ID number
	 * @param phoneNum a person's phone number
	 */
	public Person (String personID, String phoneNum) {
		this.personID = personID;
		this.phoneNum = phoneNum;
	}

	/**
	 * A  method that returns a person's ID
	 * O(1)
	 * @return String This returns person's ID
	 */
	public String getPersonID() {
		return personID;
	}

	/**
	 * A method to return a person's phone number
	 * O(1)
	 * @return String This returns a person's phone number
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	
	/**
	 * A method to print a person's information (ID number and phone number)
	 * O(1)
	 * @return String This returns a person's ID number and phone number
	 */
	public String printInfo() {
		return getPersonID() + " " + getPhoneNum();
	}

}
