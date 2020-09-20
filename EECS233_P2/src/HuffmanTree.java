import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileReader;
import java.util.*;

/**
 * A Class to represent the Huffman Tree and the Encoding produced from reading the tree
 * @author Alena Tochilkina
 */
public class HuffmanTree {
	/** A field to store stack of Strings */
	private static Stack<String> stack = new Stack<>();
	/** A field to store the map with character encodings */
	private static  HashMap<Character, String> encodingMap = new HashMap<>();
	/** A field to store the map with character frequencies */
	private static  HashMap<Character, Integer> frequencyTable = new HashMap<>();
	/** A field to store the amount of space saved with huffman encoding in percentage*/
	private static double savedSpace;

	/**
	 * A method to get the encoding map
	 * @return HashMap<Character, String> This returns the map with character encodings
	 */
	public static HashMap<Character, String> getEncodingMap() {
		return HuffmanTree.encodingMap;
	}
	/**
	 * A method to get the frequency map
	 * @return HashMap<Character, Integer> This returns the map with character frequencies
	 */
	public static HashMap<Character, Integer> getFrequencyTable(){
		return HuffmanTree.frequencyTable;
	}

	/**
	 * A method to set the saved space amount
	 * @param savedSpace is the space saved with huffman encoding in percentage
	 */
	public static void setSavedSpace(double savedSpace) {
		HuffmanTree.savedSpace = savedSpace;
	}

	/**
	 * A mehtod to get the saved space amount
	 * @return double This returns the space saved with huffman encoding in percentage
	 */
	public static double getSavedSpace(){
		return HuffmanTree.savedSpace;
	}

	/**
	 * A method to scan the input file, calculate frequency of each character in the file,
	 * 														and put the values in the frequency table
	 * @param fileName is the name of the input file
	 */
	public static void readFileIntoMap (String fileName) {

		Path path = Paths.get(fileName);

		try {

			BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));

			String line;

			//put every character in the file into the hash map
			while ((line = reader.readLine()) != null) {
				for (int j = 0; j < line.length(); j++) {
					char c = line.charAt(j);
					//if the character is in the map already, increment the frequency by 1
					if(getFrequencyTable().containsKey(c))
						getFrequencyTable().put(c, getFrequencyTable().get(c) + 1);
					//if not, put it in the map and set its the frequency equal to 1
					else
						getFrequencyTable().put(c, 1);
				}
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A method to merge two Huffman Nodes
	 * @param node1 is the first node
	 * @param node2 is the second node
	 * @return HuffmanNode this returns the single merged node with the
	 * 										frequency being the sum of frequencies of node1 and node2
	 * 										node1 and node2 are the node's references to its left and right children
	 * 										left child must be smaller than the right child
	 */
	public static HuffmanNode merge(HuffmanNode node1, HuffmanNode node2) {

		HuffmanNode newNode;
		int sumFrequency = node1.getFrequency() + node2.getFrequency();
		
		if(node1.getFrequency() > node2.getFrequency())
			newNode = new HuffmanNode(null, sumFrequency, node2, node1);
		//if the frequencies of two nodes are equal and one of the two is a leaf node
		//the leaf node is considered the smaller of the two
		else if(node1.getFrequency() == node2.getFrequency() && node2.isLeaf())
			newNode = new HuffmanNode(null, sumFrequency, node2, node1);
		else if(node1.getFrequency() == node2.getFrequency() && node1.isLeaf())
			newNode = new HuffmanNode(null, sumFrequency, node1, node2);
		else 
			newNode = new HuffmanNode(null, sumFrequency, node1, node2);
		return newNode;
	}

	/**
	 * A method to put all of the characters from frequency table into nodes and then into min heap
	 * @param map is the frequency table with all of the characters present in the file and their frequencies
	 * @return HuffmanHeap This returns the heap, minimum element of which is the smallest node
	 */
	public static HuffmanHeap generateHeap(HashMap<Character, Integer> map){
		
		HuffmanHeap heap = new HuffmanHeap(map.size());

		//record the characters from the frequency table into the array
		Set<Character> keySet = map.keySet();
		ArrayList<Character> listOfChars = new ArrayList<>(keySet);

		//for every character, create the node and put it into heap
		for( int i = 0; i < listOfChars.size(); i++) {
			Character c = listOfChars.get(i);
			int freq = map.get(c);
			HuffmanNode node = new HuffmanNode(c, freq, null, null);
			heap.insert(node);
		}
		return heap;
	}


	/**
	 * A method to create the huffman tree
	 * @param heap is the min heap with the character nodes
	 * @return LinkedList<HuffmanNode> This returns the list, last element of which has the structure of a huffman tree
	 *
	 * I chose to use Linked List in this assignment because then I do not have to declare the size of the list
	 * In every other aspect, linked list is just as good as array list because all I'm doing is adding to the end of the list
	 */
	public static LinkedList<HuffmanNode> generateHuffmanTree(HuffmanHeap heap){
		
		LinkedList<HuffmanNode> list = new LinkedList<>();

		//the heap can have either 1 or 0 elements left, depending on the array
		//the last element will be in the list already
		//we can't remove 2 elements from the 1-element heap, the loop iterates until the heap only has last merged node
		while(heap.getNumItems() != 1) {
		
			HuffmanNode n  = merge(heap.removeMin(), heap.removeMin());
			heap.insert(n);
			list.add(n);
		}
			return list;
	}


	/**
	 * A method to record the encoding for each character in the hash map
	 * 														0 for every left turn
	 * 														1 for every right turn
	 *
	 * @param node is the node which contains the structure of the huffman tree
	 */
	public static void generateEncodingMap(HuffmanNode node) {

		//the base case; if the null node is reached, the method terminates
		if (node == null) {
			//if there are no elements in the s
			if(!stack.isEmpty())
				stack.pop();
			return;
		}

		//every left turn, "0" is pushed into the stack
		stack.push("0");
		generateEncodingMap(node.getLeft());

		//every right turn "1" is pushed into the stack
		stack.push("1");
		generateEncodingMap(node.getRight());

		//if the leaf node is reached, the character in the reached node and stack string is put in the encoding map
		if(node.isLeaf()) {
			encodingMap.put(node.getInChar(), generateStackString());
		}

		//backtracking the node
		if(!stack.isEmpty())
			stack.pop();
		
	}

	/**
	 * A method to create a string out of stack contents
	 * @return String This returns the String encoding
	 */
	protected static String generateStackString() {
		//creates the reversed version of stack and stores it into the list
		ArrayList<String> list = new ArrayList<>(stack);
		StringBuilder sb = new StringBuilder();

		//append every single element of the stack in order
		for(String i : list) {
			sb.append(i);
		}
		return sb.toString();
	}

	/**
	 * A method to encode the file using the huffman encoding and output it into the new file
	 * @param inputFileName is the name of the input file
	 * @param outputFileName is the name of the output file
	 */
	public static void encodeFile(String inputFileName, String outputFileName) {

		try {

			//variables to store bits count for every letter encoded for the later space anallysis
			double fixedCount = 0;
			double huffmanCount = 0;

			String line;

			Path inputFile = Paths.get(inputFileName);

			//if the file exists, writes the output into the existing file
			//if the file doesn't exist, creates the new file with the specified name
			File outputFile = new File(outputFileName);

			BufferedReader reader = new BufferedReader(new FileReader(inputFile.toFile()));

			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

			HashMap<Character, String> encodingTable = getEncodingMap();

			//encode every character in every line of the original document into the output document
			while((line = reader.readLine()) != null){
				for(int i = 0; i < line.length(); i++){
					String letter = encodingTable.get(line.charAt(i));
					writer.write(letter);
					//counting bits
					fixedCount += 8;
					huffmanCount += letter.length();
				}
			}

			setSavedSpace(((fixedCount - huffmanCount)/fixedCount) * 100);

			writer.newLine();
			writer.newLine();
			//print the result of encoding into the encoded file 
			writer.write("File Encoded");
			//print the result of encoding into console
			System.out.println("File Encoded");

			reader.close();
			writer.close();
		}

		catch (IOException e) {
			System.out.println("Input File Error");
			e.printStackTrace();
		}
	}

	/**
	 * A method to represent and output special characters
	 * Credit: Neo Huang
	 * @param s is the input string
	 * @return String This returns the representation of special characters
	 */
	public static String escapeSpecialCharacter(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c >= 32 && c < 127) sb.append(c);
			else sb.append(" [0x" + Integer.toOctalString(c) + "]");
		}
		return sb.toString();
	}

	/**
	 * A method print the huffman encoding protocol into a file
	 * @param outputFileName is the mane of the file that is being generated
	 */
	public static void printEncodingTable(String outputFileName) {

		try {

			//store all of the keys in the frequency table into the array
			Set<Character> keySet = getFrequencyTable().keySet();
			ArrayList<Character> chars = new ArrayList<>(keySet);

			File outputFile = new File(outputFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

			//for every character of the array, find frequency and encoding
			//then write it into the output file
			for (Character c : chars) {
				int frequency = getFrequencyTable().get(c);
				String encoding = getEncodingMap().get(c);
				writer.write(escapeSpecialCharacter(c + ":" + frequency + ":" + encoding));
				writer.newLine();
			}

			writer.close();
		}
		catch(IOException e){
			System.out.println("File Error");
		}
	}

	/**
	 * A method to print the amount of space saved into a file
	 * @param outputFileName is the mane of the file that is being generated
	 */
	public static void printSavedSpace(String outputFileName) {

		try {
			File output = new File(outputFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			writer.write("The space saved is " + getSavedSpace() + "%");
			writer.close();
		}
		catch(IOException e){
			System.out.println("File Error");
		}
	}

	/**
	 * A method to convert text file into binary sequence using Huffman Encoding
	 * @param inputFileName is the name of the input file
	 * @param outputFileName is the name of the output file
	 */
	public static void huffmanCoder(String inputFileName, String outputFileName) {
		//read file and fill the frequency map
		readFileIntoMap(inputFileName);
		HashMap<Character, Integer> map = getFrequencyTable();

		//generate the heap with HuffmanNodes
		HuffmanHeap heap = generateHeap(map);

		//generate the Huffman Tree
		HuffmanNode tree = generateHuffmanTree(heap).getLast();

		//read the Huffman Tree and generate the encoding map
		generateEncodingMap(tree);

		//encode the input file using the encoding map and output it into the output file
		encodeFile(inputFileName, outputFileName);

		//produce the encoding protocol and saved-space protocol files
		printEncodingTable("Encoding_Table.txt");
		printSavedSpace("Saved_Space.txt");
	}

}
