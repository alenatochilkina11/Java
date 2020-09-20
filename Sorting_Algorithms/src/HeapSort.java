//
///**
// * @author Alena Tochilkina
// */
///**********************************************************************************************************************/
//public class HeapSort {
//
//    /**
//     * Complexity:
//     * O(N) --> turn array into a heap
//     * O(log(N)) --> remove the largest remaining item
//     * N above removals
//     * OVERALL: O(Nlog(N))
//     */
//    public static void heapSort(int[] arr){
//        int numItems = arr.length;
//        heapSortHelper(arr, numItems);
//    }
//
//    /**
//     * The Idea:
//     * 1. Turn the array into max-on-top heap
//     * 2. Remove max element and place it in the end of the array
//     *      --> which is empty because of the previous removal
//     */
//    public static void heapSortHelper(int[] arr, int numItems) {
//        //build max-on-top heap
//        buildHeap(arr, numItems);
//        //designate the end of the array as the index of insertion
//        int endUnsorted = numItems - 1;
//        while(endUnsorted > 0){
//            int largestRemaining = removeMax(arr);
//            arr[endUnsorted] = largestRemaining;
//            //decrement the index of insertion
//            endUnsorted--;
//        }
//    }
//}
///**********************************************************************************************************************/