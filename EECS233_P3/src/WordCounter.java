import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.File;

public class WordCounter {

    /** A field that stores a hashtable*/
    private static HashTableSI hashTable = new HashTableSI();

    /**
     * A method to scan in the file and fill in the hashtable with words and their frequencies
     * @param inputFileName is the name of the file we are scanning in
     */
    private static void readFileIntoTable(String inputFileName) {
        Path path = Paths.get(inputFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));

            String line;
            //put every character in the file into the hash map
            while ((line = reader.readLine()) != null) {
                //ensures there are no white spaces in the lines of the table
                if (!line.trim().equals("")) {
                    //removes all unnecessary punctuation
                        line = line.replaceAll("[^a-zA-Z0-9]", " ");
                        String[] words = line.split(" ");
                    for (String word : words) {
                        //ensures that were are not processing white spaces as words
                        if(word == null || word.trim().equals(""))
                            continue;
                        //if the character is in the map already, increment the frequency by 1
                        String s = word.toLowerCase();
                        if (hashTable.containsKey(s))
                            hashTable.put(s, hashTable.get(s) + 1);
                            //if not, put it in the map and set its the frequency equal to 1
                        else
                            hashTable.put(s, 1);
                    }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }

    /**
     * A method to output the string representation of the hashtable in a separate file
     * - if the file exists, it is overrided with current file
     * - if the file does not exist, the new file is created
     * @param outputFileName is the name of the output file
     */
    private static void printTable(String outputFileName) {
        File outputFile = new File(outputFileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(hashTable.toString());
            writer.close();
        }
        catch (IOException e){
            System.out.println("File Error");
        }
    }

    /**
     * A method to count the frequency of every word in the file and produce a separate output file with the table of words and their frequencies
     * @param inputFileName is the name of the input file
     * @param outputFileName is the name of the output file
     */
    public static void wordCount(String inputFileName, String outputFileName) {
        readFileIntoTable(inputFileName);
        printTable(outputFileName);
    }

    /**
     * The main method of the WordCounter
     * - The first argument is the input file name
     * - The second argument is the output file name
     */
    public static void main(String[] args) {

        wordCount(args[0], args[1]);

        //For an example of calling the "toy" and actual test files, please refer below
//       wordCount("P3Tester.txt", "P3Tester_Output.txt");
//       wordCount("TheCaptainsDaughter_ASPushkin.txt", "TheCaptainsDaughter_ASPushkin_Output.txt");
    }
}
