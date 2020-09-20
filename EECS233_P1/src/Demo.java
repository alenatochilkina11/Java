

/**
 * A class to demonstrate project 1
 * @author Alena Tochilkina (axt557)
 * Feb 14, 2020
 */
public class Demo {

	/**
	 * A method used to compare two Phone Books and delete all the duplicates from one of the,
	 * O(N^2)
	 * @param list1 data from Phone Book number one
	 * @param list2 data from Phone Book number two
	 */
	public static void removeDuplicates(PhoneBook list1, PhoneBook list2) {
		
		PhBIterator iter1 = list1.phBIterator();
		
		int index = 0;
		
		//Case for the first and middle elements
		while(iter1.hasNext()) {
			boolean removed = false;
			Person save1 = iter1.next();
	        PhBIterator iter2 = list2.phBIterator();
			
			while(iter2.hasNext()) {
				 
				Person save2 = iter2.next();
				if (save1.getPersonID().equals(save2.getPersonID())) {
					list1.remove(index);
					removed = true;
				}
			}
			
			if (!removed)
				index++;
		}
		
		//Case for the last element
		Person save1 = iter1.next();
		
		if(save1 != null) {
			PhBIterator iter2 = list2.phBIterator();
			
			while(iter2.hasNext()) {
				 
				Person save2 = iter2.next();
				if (save1.getPersonID().equals(save2.getPersonID())) {
					list1.remove(index);
				}
			}
		}
	}
	
	/**
	 * Main method is used to launch the program
	 * @param args input arguments
	 */
	public static void main(String[] args) {
		
		Person p1 = new Person("1", "9164573348");
		Person p2 = new Person("2", "9856674545");
		Person p3 = new Person("3", "9166767302");
		Person p4 = new Person("4", "9857618485");
		Person p5 = new Person("5", "9191919191");
		Person p6 = new Person("6", "9144444444");
		
		PhoneBook list = new PhBLinkedList();
		PhoneBook array = new PhBArrayList();
		
		list.insert(1, p1);
		list.insert(0, p2);
		list.insert(0, p3);
		list.insert(0, p4);
		list.insert(0, p6);
		

		array.insert(1, p4);
		array.insert(2, p5);
		array.insert(3, p2);
		array.insert(4, p1);

		System.out.println("The original list is: ");
		list.print();
		System.out.println("The original array is: ");
		array.print();
		
		removeDuplicates(list, array);
		
		System.out.println("The modified list is: ");
		list.print();
		System.out.println("The modified array is: ");
		array.print();
		
	}
}
