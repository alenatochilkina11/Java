import java.util.Arrays;
/**
 * @author Alena Tochilkina
 */
/**********************************************************************************************************************/
public class QuickSort {

    /**
     * Divide-and-Concur Algorithm
     * COMPLEXITY:
     *         1. Best Case: O(Nlog(N)) --> almost impossible cuz middle element doesn't guarantee the even division
     *         2. Worst Case: O(N^2) --> in case of very uneven partitioning (mostly sorted arrays)
     *         3. Average Case: O(Nlog(N)) --> proven by smart ppl...
     */
    public static void quickSort(int[] arr) {
        int last = arr.length - 1;
        quickSortHelper(arr, 0, last);
    }

    /**
     * 1. partition the list
     * 2. repeat the same for left and right sub-array until the length of the sub-array is 1
     */
    public static void quickSortHelper(int[] arr, int first, int last) {
        if(first >= last)
            return;
        int split = partition(arr, first, last);
        quickSortHelper(arr, first, split);
        quickSortHelper(arr, split + 1, last);
    }

    /**
     * 1. Choose the middle elements as a pivot
     * 2. Designate i in front of the array and j in the end of the array (out of the array spectrum)
     * 3. move i until an element larger than pivot found
     * 4. move j until an element smaller or equal than pivot is found
     * 5. if i is still in from of j, switch the elements
     * 6. stop once j crosses i (j stops at the pivot)
     * 7. now elements to the right are smaller than pivot and to the left are larger than the pivot
     */
    public static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first+last)/2]; //middle element for pivot
        int temp = 0;
        int i = first - 1;
        int j = last + 1;
        while(i < j){
            do { // increment first to avoid infinite loops
                i++;
            } while (arr[i] < pivot);
            do {
                j--; //decrement first to avoid infinite loops
            } while(arr[j] > pivot);
            if(i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            else
                return j;
        }
        return 0;
    }
/**********************************************************************************************************************/

    public static void main(String[] args) {
//        int[] arr = generateRandomArray(20);
        int[] arr = {25, 1, 23, 22, 5, 6, 7, 9,  24, 23, 22, 21, 20, 19, 18, 17, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
