package com.example.aston_intensive.HashMap;

/**
 * Pair is the entity we'll be using as an element of {@link src.main.java.com.example.aston_intensive.HashMap.GenericHashMap}
 * @param <K> key
 * @param <V> value
 */
public class Pair <K, V>{
    /** */
    private K key;
    private V value;
    private Pair<K, V> nextPair;

    public Pair(K key, V value, Pair<K, V> nextPair) {
        this.key = key;
        this.value = value;
        this.nextPair = nextPair;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Pair<K, V> getNextPair() {
        return nextPair;
    }

    public void setNextPair(Pair<K, V> nextPair) {
        this.nextPair = nextPair;
    }
}
