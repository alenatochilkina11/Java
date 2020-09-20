import java.util.ArrayList;
import java.util.LinkedList;

public class HashTableSI {
    /**
     * A field to store the array of entries
     */
    private ArrayList<LinkedList<Entry>> table;
    /**
     * A filed to store the default capacity of the HashTable
     * To make sure that the size of the table never becomes a multiple of 31,
     * the default capacity is 2^2 (then the size only doubles when resizing the table)
     */
    private final int defaultCapacity = 4;
    /** A field to store the size of the array of entries*/
    private int capacity;
    /** A field to store the number of elements stored in the hashtable*/
    private int numElements;

    /**
     * A constructor for HashTableSI class
     */
    public HashTableSI() {
        this.capacity = defaultCapacity;
        this.table = new ArrayList<>(defaultCapacity);
        numElements = 0;
        for (int i = 0; i < defaultCapacity; i++) {
            table.add(null);
        }
    }
    /**
     * A method to resize the table once the number of elements reaches the load factor
     */
    private void resize() {
        //double the capacity
        capacity = capacity * 2;
        //rehash the entire table (since the capacity has changed)
        ArrayList<LinkedList<Entry>> tempTable = rehash();
        //setting the original table to be equal to the new, rehashed table with doubled capacity
        table = tempTable;
    }

    /**
     * A method to rehash the table when the size of table changes
     * primarily only used in resize() method for simplicity of the code
     * @return ArrayList<LinkedList<Entry>> This returns the new hash table after rehashing the old one
     */
    private ArrayList<LinkedList<Entry>> rehash() {
        //creating the new array with the new capacity
        ArrayList<LinkedList<Entry>> tempTable = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tempTable.add(null);
        }
        //because capacity changes, all of the elements in the previous array should have a different hash
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i) != null) {
                for (Entry entry : table.get(i)) {
                    //creating new hash for every single entry in the original table
                    int rowIndex = hash(entry.key);
                    LinkedList<Entry> row = tempTable.get(rowIndex);
                    //if the row is empty, make a new linked list in it
                    if (row == null) {
                        row = new LinkedList<>();
                        tempTable.set(rowIndex, row);
                    }
                    row.add(entry);
                }
            }
            //deleting all the elements in the original table
            table.set(i, null);
        }
        return tempTable;
    }

    /**
     * A method to produce the hash index of the elements put into hashtable
     * @param key is the key of the element put into the hashtable
     * @return int This returns the the index of the elements put into hashtable, so 0 <= index < capacity
     */
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * A method to get the number of elements currently stored in the hash table
     * @return int This returns the current number of elements in the array
     */
    private int tableSize() {
        return numElements;
    }

    /**
     * A method to determine whether a key has already been put in the hashtable
     * @param key is the key of the element put into the hash table
     * @return boolean This returns whether a key has already been put in the hashtable
     */
    public boolean containsKey(String key) {
        int rowIndex = hash(key);
        return (rowSearch(rowIndex, key) != null);
    }

    /**
     * A method to search for an element in a specific row of the hashtable
     * @param rowIndex is the index of the "bucket" in the hashtable
     * @param key is the key of the element stored the hash table
     * @return Entry This returns:
     * - the Entry with the key specified in the input
     * - null if the key is not found or input key is null
     */
    private Entry rowSearch(int rowIndex, String key) {
        if (key == null)
            return null;
        //get the row specified by rowIndex
        LinkedList<Entry> row = table.get(rowIndex);
        //if the row is empty, it doesn't have the element we're looking for
        if (row == null)
            return null;
        //searching for the entry in the linked list
        for (Entry entry : row) {
            //if found, return the entry
            if (entry.key.equals(key))
                return entry;
        }
        //else, return null
        return null;
    }

    /**
     * A method to insert or update an Entry for the specified row and key
     * @param rowIndex is the index of the "bucket" in the hashtable
     * @param entry is the entry we need to insert
     */
    private void rowInsert(int rowIndex, Entry entry) {
        //if key is null, insertion can't proceed
        if (entry == null) {
            throw new IllegalArgumentException("Inserting Non-Specified Entry");
        }
        LinkedList<Entry> row = table.get(rowIndex);
        //if row is empty, make it hold empty linked list for later insertion of the element
        if(row == null) {
            row = new LinkedList<>();
            table.set(rowIndex, row);
        }
        //looking up if the key already exists
        Entry sameKey = rowSearch(rowIndex, entry.key);
        //if it doesn't insert the new element in the end of the linked list
        if (sameKey == null) {
            table.get(rowIndex).add(entry);
            numElements++;
            if (tableSize() > capacity )
                resize();
        }
        //if the key already exists, update the value
        else {
            sameKey.value = entry.value;
        }
    }

    /**
     * A method to put the the entry (key-value pair) into the hashtable
     * - if the key is not in the table already, insert a new element
     * - if the key is in the table, update its value
     * @param key is the key of the element put into the hashtable
     * @param value is the value of the element put into the hashtable
     */
    public void put(String key, Integer value) {
        Entry entry = new Entry(key, value);
        int rowIndex = hash(key);
        rowInsert(rowIndex, entry);
    }

    /**
     * A method to look up the value by its key
     * @param key is the key of the element stored into the hashtable
     * @return Integer This returns:
     * - the value of the element we're looking up if it exists in the hashtable
     * - null if there's not such key in the table
     */
    public Integer get(String key) {
        int rowIndex = hash(key);
        Entry entry = rowSearch(rowIndex, key);
        if (entry != null)
            return entry.value;
        return null;
    }

    /**
     * A method that scans the entire hashtable and produces its string representation
     * @return String This returns the string representation of the hashtable
     */
    public String toString() {
        double avgCollisions = ((double)numElements)/((double)capacity);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            //ignores the null keys
            if (table.get(i) == null)
                continue;

            else {
                for (Entry entry : table.get(i)) {
                    sb.append(entry.toString() + "\n");
                }
            }
        }
        sb.append("\n\n" + "Average Length of Collision Lists is " + avgCollisions);
        return sb.toString();
    }

    /**
     * A class to represent the entry in the hash table
     * Key: String
     * Value: Integer
     */
    protected class Entry {

        /** A field that stores the key of the entry*/
        private String key;
        /** A field that stores the value of the entry*/
        private Integer value;

        /**
         * A constructor of the class Entry
         * @param word is the key of the entry
         * @param frequency is the value of the entry
         */
        protected Entry(String word, Integer frequency) {
            this.key = word;
            this.value = frequency;
        }

        /**
         * A method to represent the Entry as the String
         * @return String This returns the String representation of the Entry
         */
        public String toString() {
            return "(" + key + " " + value + ")";
        }
    }
}


