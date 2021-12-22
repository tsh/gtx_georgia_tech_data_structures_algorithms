package com.GTx_CS1332xII;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        if (backingArray.length == size + 1) {
            expandArray();
        }
        size++;
        backingArray[size] = data;
        upheap(size);
    }

    private void upheap(int startIndex){
        for (int i=startIndex; i>1; i=i/2) {
            int parentIndex = i / 2;
            if (backingArray[parentIndex].compareTo(backingArray[i]) > 0) {
                swap(i, parentIndex);
            } else {
                break;
            }
        }
    }

    private void expandArray(){
        T[] tmpArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i=1; i < backingArray.length; i++){
            tmpArray[i] = backingArray[i];
        }
        this.backingArray = tmpArray;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        // downheap
        int i = 1;

        while (true){
            int leftChildIndex = i*2;
            int rightChildIndex = i*2+1;
            if (rightChildIndex > size) {break;}
            if (backingArray[leftChildIndex] == null && backingArray[rightChildIndex] != null){
                swap(rightChildIndex, i);
                i = rightChildIndex;
                break;
            }
            if (backingArray[rightChildIndex] == null && backingArray[leftChildIndex] != null){
                swap(leftChildIndex, i);
                i = leftChildIndex;
                break;
            }
            if (backingArray[i].compareTo(backingArray[leftChildIndex]) > 0 ||
                backingArray[i].compareTo(backingArray[rightChildIndex]) > 0){
                if (backingArray[leftChildIndex].compareTo(backingArray[rightChildIndex]) < 0){
                    swap(leftChildIndex, i);
                    i = leftChildIndex;
                }
                else {
                    swap(rightChildIndex, i);
                    i = rightChildIndex;
                }
            }
        }
        return removed;
    }

    private void swap(int index1, int index2) {
        T tmp = backingArray[index1];
        backingArray[index1] = backingArray[index2];
        backingArray[index2] = tmp;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

