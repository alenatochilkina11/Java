import java.util.Arrays;
import java.util.Random;
/**
 * @author Alena Tochilkina
 */
/**********************************************************************************************************************/
public class SelectionSort {

    /**
     * 1. record the first element as the minValue
     * 2. find the smallest element (by comparing with minValue
     * 3. record its index and value
     * 4. swap the first element with the minValue element
     * 5. move to the next element
     * Complexity: O(N^2)
     */
    public static void selectionSort(int[] arr) {
        int i, j, minValue, minIndex, temp = 0;
        for( i=0 ; i < arr.length; i++) {
            minValue = arr[i];
            minIndex = i;
            //important: index for j should be the next element after i
            for(j = i + 1 ; j < arr.length; j++) {
                //important: the value should be less or equal to bc otherwise the same numbers won't be sorted
                if(arr[j] <= minValue){
                    minValue = arr[j];
                    minIndex = j;
                }
                //important: swap only if the new minValue is not at current i
                if(minValue < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[minIndex];
                    arr[minIndex] = temp;
                }
            }
        }
    }
/**********************************************************************************************************************/

    public static int[] generateRandomArray(int arrSize) {
        int[] arr = new int[arrSize];
        Random rd = new Random();
        for(int i = 0; i < arrSize; i++) {
            arr[i] = rd.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[] arr = generateRandomArray(20);
        int[] arr = {25, 1, 23, 22, 5, 6, 7, 9,  24, 23, 22, 21, 20, 19, 18, 17, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
