package com.GTx_CS1332xII;

import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        if (key == null){
            throw new IllegalArgumentException();
        }

        double lf = (double) (size + 1) / table.length;
        if (lf > MAX_LOAD_FACTOR){
            resizeBackingTable((2 * table.length) + 1);
        };
        int hash = key.hashCode();
        int compression = hash % table.length;

        V ret;
        if (table[compression]  == null){
            ExternalChainingMapEntry<K,V> kv = new ExternalChainingMapEntry<K,V>(key, value);
            table[compression] = kv;
            ret = null;
            size++;
        } else {
            ExternalChainingMapEntry<K, V> kv = table[compression];
            ret = addToChain(kv, key, value, compression);
        }
        return ret;
    }

    private V addToChain(ExternalChainingMapEntry<K,V> kv, K key, V value, int compression){
        ExternalChainingMapEntry<K,V> cur = kv;
        while (true) {
            if (cur == null){
                break;
            }
            if (cur.getKey() == key){
                cur.setValue(value);
                return value;
            }
            cur = kv.getNext();
        }
        ExternalChainingMapEntry<K,V> newKV = new ExternalChainingMapEntry<K,V> (key, value);
        newKV.setNext(kv);
        table[compression] = newKV;
        size++;
        return value;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }

        Integer cellNumber = key.hashCode() % table.length;
        ExternalChainingMapEntry<K,V> cur = table[cellNumber];
        if (cur == null) {
            throw new NoSuchElementException();
        }
        V returnValue = findAndRemove(cur, key);
        if (returnValue != null){
            size--;
            return returnValue;
        }
        throw new NoSuchElementException();
    }

    private V findAndRemove(ExternalChainingMapEntry<K,V> chain, K key){
        Tupple t = recurFindAndRemove(chain, key);
        return t.returnValue;
    }

    private Tupple recurFindAndRemove (ExternalChainingMapEntry<K,V> chain, K key){
        Tupple t = new Tupple(null, null);
        if (chain == null){
            return t;
        }
        if (chain.getKey() == key){
            t.chainElement = chain.getNext();
            t.returnValue = chain.getValue();
        } else {
            t = recurFindAndRemove(chain.getNext(), key);
            chain.setNext(t.chainElement);
        }
        return t;
    }

    class Tupple {
        /* class to return multiple arguments from a function */
        ExternalChainingMapEntry<K,V> chainElement;
        V returnValue;

        public Tupple(ExternalChainingMapEntry<K,V> chainElement_, V returnValue_){
               chainElement = chainElement_;
               returnValue = returnValue_;
        }
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        ExternalChainingMapEntry<K,V>[] oldTable = table;
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];
        for (ExternalChainingMapEntry<K, V> oldTableKV : oldTable) {
            if (oldTableKV == null) {
                continue;
            }
            ExternalChainingMapEntry<K,V> newTableCurElem = oldTableKV;
            while (newTableCurElem != null) {
                Integer cell = newTableCurElem.getKey().hashCode() % table.length;
                if (table[cell] == null) {
                    ExternalChainingMapEntry<K, V> newChain = new ExternalChainingMapEntry<K, V>(newTableCurElem.getKey(), newTableCurElem.getValue());
                    table[cell] = newChain;
                } else {
                    addToChain(table[cell], newTableCurElem.getKey(), newTableCurElem.getValue(), cell);
                }
                newTableCurElem = oldTableKV.getNext();
            }

        }
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}