import java.util.Arrays;
/**
 * @author Alena Tochilkina
 */
/**********************************************************************************************************************/
public class ShellSort {

    public static void shellSort(int[] arr) {
        int incr = 1;
        while(2*incr <= arr.length) incr = 2*incr; //starting inner = floor(log arr.length)
        incr = incr - 1;
        while(incr >= 1){
            for(int i = incr; i < arr.length; i++){
                int toInsert = arr[i];
                int j;
                for(j=i; j>incr-1 && toInsert < arr[j-incr]; j=(j-incr)){
                    arr[j] = arr[j-incr];
                }
                arr[j] = toInsert;
            }
            incr= incr/2;
        }
    }
/**********************************************************************************************************************/
    public static void main(String[] args) {
//        int[] arr = generateRandomArray(20);
        int[] arr = {25, 1, 23, 22, 5, 6, 7, 9,  24, 23, 22, 21, 20, 19, 18, 17, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
