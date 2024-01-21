package com.example.aston_intensive.HashMap;

/**
 * HashMap custom implementation
 * @param <K> data type that will be used for keys
 * @param <V> data type that will be used for values
 */
public class GenericHashMap<K, V> {

    /** HashMap default capacity */
    private static final int DEFAULT_CAPACITY = 16;

    /** HashMap default load factor */
    private static final double LOAD_FACTOR = 0.75;

    public Pair<K, V>[] getArray() {
        return array;
    }

    /** Linked list of Pair objects*/
    private Pair<K, V>[] array;

    /** To count the number of filled buckets to extend map if the load factor is reached */
    private int filledBuckets;

    /** Creates HashMap with default initial capacity (16 buckets)
     * */
    GenericHashMap() {
        this.array = new Pair[DEFAULT_CAPACITY];
    }

    /** Creates HashMap with specified capacity
     * @param capacity - buckets quantity
     * */
    GenericHashMap(int capacity) {
        this.array = new Pair[capacity];
    }


    /** Inserts a specific key and the value it is mapping to into a bucket.
     * If an existing key is passed then the previous value gets replaced by the new value.
     * If a new Pair is passed, then the Pair gets inserted as a whole.
     *
     * @param key: key that needs to be inserted into the bucket.
     * @param value: value that the above key would map into.
     * */
    public void put(K key, V value) {
        if ((double) filledBuckets / (double) array.length >= LOAD_FACTOR) {
            extend();
        }
        int bucket = getBucket(key);
        if (array[bucket] == null) { //if our linked list is empty
            array[bucket] = new Pair<>(key, value, null);
            filledBuckets++;
        } else { //if our linked list is not empty
            Pair<K, V> previousPair = null;
            Pair<K, V> currentPair = array[bucket];
            while (currentPair != null) {
                if (currentPair.getKey().equals(key)) {
                    currentPair.setValue(value);
                    break;
                }
                previousPair = currentPair;
                currentPair = currentPair.getNextPair();
            }
            if (previousPair != null) {
                previousPair.setNextPair(new Pair(key, value, null)); // to the end of LL
            }
        }
    }

    /**
     * Retrieves the value mapped by a key
     * @param key - the key whose associated value is supposed to be retrieved.
     * @return the value associated with the key in the parameter
     */

    public V get(K key) {
        V value = null;
        int bucket = getBucket(key);
        Pair<K, V> head = array[bucket];
        while (head != null) {
            if (head.getKey().equals(key)) {
                value = head.getValue();
                break;
            }
            head = head.getNextPair();
        }
        return value;
    }


    /** Finds exact bucket number
     * @param key is used to find bucket number
     * @return the bucket number
     * */
    public int getBucket(K key) {
        int hash = key.hashCode();
        if (hash<0){  // otherwise indexOutOfBoundExc
            hash = hash*(-1);
        }
        return hash % array.length;
    }

    /** Extends HashMap when the number of filled buckets exceeds the load factor (more than 75%)
     * */
    public void extend() {
        GenericHashMap<K, V> newGenericMap = new GenericHashMap<>(array.length * 2);
        for (int i = 0; i < array.length; i++) { // по бакетам нашего array
            if (array[i] != null) {
                Pair<K, V> tempPair = array[i];
                newGenericMap.put(tempPair.getKey(), tempPair.getValue());
                while (tempPair.getNextPair() != null) {
                    newGenericMap.put(tempPair.getNextPair().getKey(), tempPair.getNextPair().getValue());
                    tempPair = tempPair.getNextPair();
                }
            }
            this.array = newGenericMap.array;
        }
    }
}
