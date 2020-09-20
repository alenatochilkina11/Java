import java.util.Random;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to test the dependence of running time on the size of the input array when performing the merge sort
 * @author Alena Tochilkina (axt557)
 */
public class runtime {

    /**
     * A method to generate the array random integers
     * @param inputArray is the array to be filled with random integers
     */
    private static void generateRandomArray(int[] inputArray) {
        Random rd = new Random();
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = rd.nextInt();
        }
    }

    /**
     * Is the method to determine the median runtime for array of the same size
     * The sorting is performed 3 times on 3 random arrays of the same size and the median runtime is chosen
     * @param inputArray is the array to be filled with random integers and then sorted
     * @return long This returns the median running time of merge sort (N=3)
     */
    private static long getMedianRuntime(int[] inputArray) {
        //array to store the runtimes
        long[] runningTimeVals = new long[3];
        for(int i = 0; i < 3; i++) {
            //generate the random array
            generateRandomArray(inputArray);
            //start the time
            long startTime = System.nanoTime();
            //merge the array
            MergeSort.mergeSort(inputArray);
            //stop the time and subtract the start time
            long estimatedTime = System.nanoTime() - startTime;
            runningTimeVals[i] = estimatedTime;
        }
        //sort the resulting array of running times and take the middle element
        Arrays.sort(runningTimeVals);
        return runningTimeVals[1];
    }


    /**
     * A method to perform the runtime test on 12 different arrays and print the median runtime for array of each size
     * The test is run 3 times of each arrays of 4 different sizes
     * @param outputFileName is the name of the file where the result is printed out to
     */
    public static void testRuntime(String outputFileName) {
        File outputFile = new File(outputFileName);
        //the testing sizes of the arrays
        int[] testSamples = {250000, 500000, 1000000, 1250000};
        //create a single array which the gets re-filled with different values
        int[] arr;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (int i = 0; i < testSamples.length; i++) {
                arr = new int[testSamples[i]];
                writer.write("Array Size: " + testSamples[i] + " -- " + "Running Time: " + getMedianRuntime(arr) + "\n");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("File Error");
        }
    }

    /**
     * The main method of runtime class
     * Calls the program which tests the running time of the merge sort method and outputs the result into the file
     */
    public static void main(String[] args) {
        testRuntime("RunningTimeTest.txt");
    }
}
