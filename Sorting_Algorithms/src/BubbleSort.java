import java.util.Arrays;
/**
 * @author Alena Tochilkina
 */
/**********************************************************************************************************************/
public class BubbleSort {

    /**
     * 1. start with the first element\
     * 2. if current element is greater than the next one -> swap
     * 3. repeat for number of passes = (arr.length - 2)
     * 4. Complexity:
     *              - Average & Worst Case: O(N^2)
     *              - Best Case: O(N)
     */
    public static void bubbleSort(int[] arr){
        int i, j, temp = 0;
        for(i = 0; i < arr.length - 1; i++) {
            //important: the every pass, the last elements will be sorted
            for(j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
/**********************************************************************************************************************/

    public static void main(String[] args) {
//        int[] arr = generateRandomArray(20);
        int[] arr = {25, 1, 23, 22, 5, 6, 7, 9,  24, 23, 22, 21, 20, 19, 18, 17, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
