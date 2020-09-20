/**
 * A class to represent the node of the Huffman Tree
 * @author Alena Tochilkina
 */

public class HuffmanNode {

	/**A field to store the character in the node*/
	private Character inChar;
	/**A field to store the frequency of the character in the node*/
	private Integer frequency;
	/**A field to store the left child of the node*/
	private HuffmanNode left;
	/**A field to store the right child of the node*/
	private HuffmanNode right;

	/**
	 * A constructor for HuffmanNode class
	 * @param inChar is the character in the node
	 * @param frequency is the frequency of the character in the node
	 * @param left is the left child of the node
	 * @param right is the right child of the node
	 */
	public HuffmanNode(Character inChar, int frequency, HuffmanNode left, HuffmanNode right) {
		this.inChar = inChar;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}

	/**
	 * A method to get the character stored in the node
	 * @return Character This returns the character stored in the node
	 */
	public Character getInChar() {
		return inChar;
	}

	/**
	 * A method to get the frequency of the character stored in the node
	 * @return Integer This returns the frequency of the character stored in the node
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * A method to get the left child of the node
	 * @return HuffmanNode is the left child of the node
	 */
	public HuffmanNode getLeft() {
		return left;
	}
	/**
	 * A method to get the right child of the node
	 * @return HuffmanNode is the right child of the node
	 */
	public HuffmanNode getRight() {
		return right;
	}

	/**
	 * A method to compare two huffman nodes by their frequencies
	 * @param n is the huffman node we a comparing to
	 * @return int This returns
	 * -1 if the node is greater than n
	 * 0 if the node is equal to n
	 * 1 if the node is smaller than n
	 */
	public int compareTo(HuffmanNode n) {
		return n.getFrequency().compareTo(this.getFrequency());
	}

	/**
	 * A method to determine if the node is a leaf node
	 * @return boolean This returns true is the node is leaf and false otherwise
	 */
	public boolean isLeaf() {
		//the leaf node does not have children
		return ((this.getLeft() == null) && (this.getRight() == null));
	}

}
