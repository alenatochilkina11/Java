import java.util.Arrays;
/**
 * @author Alena Tochilkina
 */
/**********************************************************************************************************************/
public class MergeSort {

    /**
     * Divide-and-Concur algorithm
     * Complexity (Bast Case, Worst Case, Average Case) --> O(Nlog(N))
     */
    public static void mergeSort(int[] arr) {
        int end = arr.length - 1;
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, temp, 0, end);
    }

    /**
     * Recursive helper function
     * 1. Split array in the middle until the size of sub-array is 1 element
     * 2. merge sub-arrays together
     */
    public static void mergeSortHelper(int[] arr, int[] temp, int start, int end){
        if(start == end)
            return;
        int middle = (start + end)/2;
        mergeSortHelper(arr, temp, start, middle);
        mergeSortHelper(arr, temp, middle + 1, end);
        merge(arr, temp, start, middle, middle + 1, end);
    }

    /**
     * Merging 2 sub-arrays together
     * Takes the smallest elements and puts them in-front
     * Important: copy the elements from tem back into the original array in the end
     */
    public static void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart;
        int j = rightStart;
        int k = leftStart;
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
        //copy the elements from temp back into the original array
        for(i = leftStart; i <= rightEnd; i++) {
            arr[i] = temp[i];
        }
    }
/**********************************************************************************************************************/

    public static void main(String[] args) {
//        int[] arr = generateRandomArray(20);
        int[] arr = {25, 1, 23, 22, 5, 6, 7, 9,  24, 23, 22, 21, 20, 19, 18, 17, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
