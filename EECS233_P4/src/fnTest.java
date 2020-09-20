import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A class to test the functionality of the merge sort
 * @author Alena Tochilkina (axt557)
 */
public class fnTest {

    /** A variable to store the array of integers*/
    private static int[] array;

    /**
     * A method to get the total number of numbers in the file
     * - With the assumption that file contains ONLY one integer number per line
     * @param inputFileName is the name of the file which contains numbers to be sorted
     * @return int This returns the number of integer numbers in the file
     */
    private static int countLines(String inputFileName) {
        int count = 0;
        Path path = Paths.get(inputFileName);
        try {
            Scanner sc = new Scanner(path);
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
        catch(IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
        return count;
    }

    /**
     * A method which
     *      1. reads the file with integers and puts them into the array
     *      2. sorts the array with merge sort
     * The method was done with the assumption that the input file ONLY contains one integer number per line
     * @param inputFileName is the name of the file which contains numbers to be sorted
     */
    private static void readFileIntoArray (String inputFileName) {
        Path path = Paths.get(inputFileName);
        array = new int[countLines(inputFileName)];
        try {
            Scanner sc = new Scanner(path);
            int i = 0;
            while (sc.hasNextLine()) {
                array[i] = Integer.parseInt(sc.nextLine());
                i++;
            }
            sc.close();
            //sort the array
            MergeSort.mergeSort(array);
        }
        catch(FileNotFoundException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
        catch(IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }

    /**
     * A method to print out the merged array into the file
     * - If the file exists, the method overrides the information there
     * - If file doesnt exist, the method creates the new file
     * @param outputFileName is the name of the output file
     */
    private static void printSortedArray(String outputFileName) {
        try {
            File output = new File(outputFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            for(int i = 0; i < array.length; i++) {
                writer.write(array[i] + "\n");
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println("File Error");
        }
    }

    /**
     * A method to test the functionality of merge sort
     * @param inputFileName is the name of the file which contains the array to be sorted
     * @param outputFileName is the name of the file where the sorted array is outputted
     */
    public static void functionalityTest(String inputFileName, String outputFileName) {
        readFileIntoArray(inputFileName);
        printSortedArray(outputFileName);
    }

    public static void main(String[] args) {

        functionalityTest(args[0], args[1]);

        //Please refer below for the example of the test file names used to test this program
//         functionalityTest("inTestFile.txt", "outTestFile.txt");
    }


}
