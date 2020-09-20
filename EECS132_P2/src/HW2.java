
public class HW2 {

	  /* 1. This method says whether letters in the input string are written in alphabetical order
	   * the method ignores symbols that are not letters
	   * the method ignores capitalization of letters
	   */
	  public static boolean isAlphabeticalOrder(String s) { 
	    StringBuilder newString = new StringBuilder();
	    String newString2 = newString.toString();
	    //Step 1. this loop removes non-letter characters from the string and capitlizes all letters
	    for (int i = 0; i < s.length(); i++) {
	      if('A' <= s.charAt(i) && s.charAt(i) <= 'Z')
	        newString.append((char) (s.charAt(i) + ('a'-'A')));
	      else if ('a' <= s.charAt(i) && s.charAt(i) <= 'z')
	        newString.append(s.charAt(i));
	    }
	    //Step 2. this creates new string that only consists of uppercase letters
	    String newStr = newString.toString();
	    //Step 3. this loop compares every character of the new string to the next character
	    for( int j = 0; j < newStr.length() - 1; j++) {
	      if(newStr.charAt(j) > newStr.charAt(j+1))
	        return false;
	    }
	    return true;
	  }
	  
	  /* 2. This method removes requested first number(s) of occurences of a 
	   * certain character from the input string
	   */
	  public static String removeNchars(String s, int numberOfOccurances, char charToRemove) {
	    StringBuilder newString = new StringBuilder();
	    //stores the amount of encounters of the letter we want to remove
	    int numberOfOccurancesRemoved = 0;
	    //Step 1. scans the loop until the end of it 
	    for (int i = 0; i < s.length(); i++) {
	      //Step 2. sets the counter to track number of occurances removed
	      //only goes until number of occurances removed is less than the input number of occurances
	      if (s.charAt(i) == charToRemove && numberOfOccurancesRemoved < numberOfOccurances) {
	        numberOfOccurancesRemoved++;
	      }
	      //Step 3. appends the rest of the string
	      else 
	        newString.append(s.charAt(i));
	    }
	    return newString.toString();
	  }
	  
	  // 3. This method removes all occuracnces of requested string from the input string
	  public static String removeString(String s, String toRemove) {
	    StringBuilder newString = new StringBuilder();
	    //Step 1. scans the input string to check if the 1st character is equal to the 1st character of the removing string
	    //appends the character, if true 
	    for (int i = 0; i < s.length(); i++) {
	      if (s.charAt(i) != toRemove.charAt(0))
	       newString.append(s.charAt(i));
	      else {
	       //a variable that states whether all letters in the removing string are equal to the certain #of letters in s
	       boolean check = true;
	       int j;
	       //Step 2. when the first characters are not equal, moves the string we want to remove along
	       //checks if all letters of the string to remove are equal to a certain portion of the input string
	       //sets up a boolean variable if the previous statement is not fully satisfied to false
	       for (j = 1; j < toRemove.length() && check ; j++) {
	         if(i+j >= s.length() || s.charAt(i+j) != toRemove.charAt(j))
	           check = false; 
	       }
	       //Step 3. if there are instances of the string to remove, shifts index for the input sring to appropriate place
	       if (check)
	         i += (j - 1);
	       //Step 4. if the strings are not fully equal, appends the letter to the new string
	       else 
	         newString.append(s.charAt(i));
	      }
	    }
	    return newString.toString();
	  }
	  
	  /* 4. This method moves all instances of requested characters one space to the right
	   * if the instances are clamped together, removes the block one space to the right
	   */
	  public static String moveAllXsRight(char toMove, String s) {
	    StringBuilder newString = new StringBuilder();
	    //Step 1. runs though the entire loop
	    for( int i = 0; i < s.length(); i++ ) {
	      //Step 2. sets an inner string builder that appends only all instances of the character we want ot move
	      //starts only from the moment first instance is encountered
	      //ends when it reaches the first character that is not equal to the character we want ot remove
	      StringBuilder newString2 = new StringBuilder();
	      for( int j = i; j < s.length() - 1 && s.charAt(j) == toMove; j++ ) {
	        newString2.append(s.charAt(j));
	      }
	      String a = newString2.toString();
	      //Step 3. re-established the index so the outer for loop could catch up 
	      i += a.length();
	      //Step 4. appends (1) the letter right after the last of the cplamped characters we want to remove and then
	      //(2) the clamped characters we want to remove
	      newString.append(s.charAt(i) + a);
	      //Step 5. special case for the last character of the string because there is no need to check it 
	      if (i == s.length())
	        newString.append(i);
	    }
	    return newString.toString();
	  }
	  
	  /* 5. Moves switches the requested characters on the board of 2D arrays with the one right below it
	   * if the switch is not possible, the requasted character stays in its place.
	   * The switch is not possible if the place the character is supposed to be moved does not exists OR
	   * the requested character is at the "bottom" of the row
	   */
	  public static void moveAllXsdown(char toMove, char[][] arr) {
	    //Step 1. starts scaning from the second column at the bottom and the most right character in the row
	    for(int i = arr.length - 2; i >= 0; i--) {
	      for(int j = arr[i].length -1; j > 0; j--) {
	        //Step 2. if board's character is equal to the character we want to remove AND the coulmn right above the character exist
	        //switch the character with the one right above it
	        if (arr[i][j] == toMove && j < arr[i+1].length) {
	          arr[i][j] = arr[i+1][j];
	          arr[i+1][j] = toMove;
	        }
	      }
	    }
	  }
	 
	  /* 6. This method moves the first occurance of the character we want to remove
	   * to the furthest possible left bottom place of the 2D array.
	   */
	  public static void moveXDownLeft(char toMove, char[][] arr){
	    // stores i values
	    int p = 0; 
	    //stores j values
	    int q = 0;
	    //keeps trac of j when moving alone the diagonal
	    //it is set to -1000 because this number is definitely out of range of the array so it will not interfere
	    int nextDiagJ = -1000;
	    //states whether the character we want to remove if found or not
	    boolean charFound = false;
	    //runs through the outer loop (columns)
	    for(int i = 0; i < arr.length; i++) {
	      //runs through the inner loop (rows)
	      for(int j = 0; j < arr[i].length; j++) {
	        char currentChar = arr[i][j];
	        //looks for the character we want to remove 
	        if(!charFound) {
	          if(currentChar == toMove) {
	            //stores character's coordinates
	            p = i;
	            q = j;
	            charFound = true;
	            //sets the starter point to go to the diagonal
	            nextDiagJ = j;
	          }
	        }
	        //swaps the coordinates for the character we want to remove on diagonal
	        else if (j == nextDiagJ) {
	          arr[p][q] = arr[i][j];
	          arr[i][j] = toMove;
	          p = i;
	          q = j;
	      }
	    }
	      //decrements j by 1, so the swaping continues on the diagonal with step 1
	      if (nextDiagJ != -1000)
	        nextDiagJ--;
	    }
	  }
}
