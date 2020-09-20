import java.util.Arrays;

/**
 * A class that includes the mergeSort method
 * @author Alena Tochilkina (axt557)
 */
public class MergeSort {

    /**
     * A method to sort the array of integers in ascending order
     * Implements Merge Sort technique
     * @param arr is the array of integers to be sorted
     */
    public static void mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        int end = arr.length;
        mergeSortHelper(arr, temp, 0, end);
    }

    /**
     * A method to find the smallest of the two numbers
     * @param a is the first number
     * @param b is the second number
     * @return int This returns the smallest of the two numbers
     */
    private static int min(int a, int b) {
        int min;
        if (a < b) min = a;
        else min = b;
        return min;
    }

    /**
     * A method to merge two sorted sub-arrays
     * Takes the smallest number of the two arrays, and places it into the temporary array
     * Source: Lecture 19, Slide 15
     * @param arr is the array which contains the sub-arrays
     * @param temp is the temporary array where the subarrays are merged into
     * @param leftStart is the start index of the 1st sub-array
     * @param leftEnd is the end index of the 1st sub-array
     * @param rightStart is the start index of the 2nd sub-array
     * @param rightEnd is the start index of the 2nd sub-array
     */
    private static void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        //the index into the 1st sub-array
        int i = leftStart;
        //the index into the 2nd sub-array
        int j = rightStart;
        //the index into the temp array
        int k = leftStart;
        //checking the bounds and whether one of the arrays has reached the last element
        while(i <= leftEnd && j <= rightEnd) {
            if(arr[i] < arr[j])
                //move k and i by 1
                temp[k++] = arr[i++];
            else
                //move k and j by 1
                temp[k++] = arr[j++];
        }
        //copy the rest of the elements in the 1st sub-array into the temp
        for( ; i <= leftEnd; i++) {
            temp[k++] = arr[i];
        }
        //copy the rest of the elements in the 2nd sub-array into the temp
        for( ; j <= rightEnd; j++) {
            temp[k++] = arr[j];
        }
    }

    /**
     * A helper method to mergeSort
     * @param arr is the array to be sorted
     * @param temp is the temporary array for merging
     * @param start is the start index for sorting (the beginning of the array)
     * @param end is the end index for sorting ( the last element of the array)
     */
    private static void mergeSortHelper(int[] arr, int[] temp, int start, int end) {
        //a variable to determine the flip of original arr and temp (number of passes)
        int round = 0;
        //dividing the array into blocks of appropriate size for each pass
        //pass 1: sub-array size is 1
        //pass 2: sub-array size is 2
        //pass 3: sub-array size is 4 and so on
        for (int n = 1; n < end; n = n * 2) {
            //increment the number of passes
            round++;
            //creating the indices for sub-arrays
            for (int i = start; i < end; i += n * 2) {
                int leftStart = i;
                //if leftEnd is larger than the size of the array, use the last array index as the leftEnd
                int leftEnd = min(i + n - 1, end - 1);
                int rightStart = i + n;
                //if rightEnd is larger than the size of the array, use the last array index as the rightEnd
                int rightEnd = min(i + 2 * n - 1, end - 1);
                //if the pass is odd, use original array as input and temp as output
                if ((round % 2) != 0)
                    merge(arr, temp, leftStart, leftEnd, rightStart, rightEnd);
                //if the pass is even, use temp array as an input and "obsolete" original array as an output
                else
                    merge(temp, arr, leftStart, leftEnd, rightStart, rightEnd);
            }
        }
        //if the last pass was odd and the data in original array is "obsolete"
        //copy the sorted temp array into the original one
        if ((round % 2) != 0) {
            for(int i = 0; i < end; i++) {
                arr[i] = temp[i];
            }
        }
    }
}
