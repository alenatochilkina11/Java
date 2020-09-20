import java.util.Arrays;
/**
 * @author Alena Tochilkina
 */
/**********************************************************************************************************************/
public class InsertionSort {

    /**
     * - Always comparing is the value to the left is greater than the current key value
     *  1. the first element has no left value - start with element 1
     *  2. set the key value to i (i = 1) and set the index for comparison (j)
     *                                                           to be one element less than 1 (the element to the left
     *  3. while elements to the left are larger than key value, swap them
     *  Complexity:
     *          Worst & Average Case: O(N^2)
     *          Best Case: O(N) --> array is sorted
     *
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int toInsert = arr[i];
            int j = i - 1;
            for(j=i; j>0 && toInsert<arr[j-1]; j--){
                //important: we are swapping "backwards" to the left
                arr[j] = arr[j-1];
            }
            arr[j] = toInsert;

        }
    }

/**********************************************************************************************************************/

    public static void main(String[] args) {
//        int[] arr = generateRandomArray(20);
        int[] arr = {25, 1, 23, 22, 5, 6, 7, 9,  24, 23, 22, 21, 20, 19, 18, 17, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
