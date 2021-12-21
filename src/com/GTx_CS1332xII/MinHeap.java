package com.GTx_CS1332xII;

import java.util.Arrays;
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
        // array starts from 1 + 1 for new data element
        if (backingArray.length == size + 2) {
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
        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        size--;
        // downheap
        int i = 1;
        int leftChildIndex = i*2;
        int rightChildIndex = i*2+1;
        if (backingArray[i].compareTo(backingArray[leftChildIndex]) < 0 ||
            backingArray[i].compareTo(backingArray[rightChildIndex]) < 0){
            if (backingArray[leftChildIndex].compareTo(backingArray[rightChildIndex]) < 0){
                swap(leftChildIndex, i);
            }
            else {
                swap(rightChildIndex, i);
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

/*
============================================================
MinHeap.java successfully compiled.
============================================================
Tests Passed: 2 / 29

[Test Failure: remove] [-0.34] : NoSuchElementException not thrown when attempting to remove from an empty MinHeap.

[Test Failure: remove] [-0.34] : This remove test was inconclusive due to: java.lang.NullPointerException

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 14, 7, null, null, null, null, null, null, null, null, null]
	Expected : [null, 7, 14, null, null, null, null, null, null, null, null, null, null]
	Actual : [null, 7, 14, 7, null, null, null, null, null, null, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 21, 28, 35, 42, null, null, null, null, null]
	Expected : [null, 7, 21, 14, 42, 28, 35, null, null, null, null, null, null]
	Actual : [null, 42, 7, 14, 21, 28, 35, 42, null, null, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 21, 28, 35, 42, 49, 56, null, null, null]
	Expected : [null, 7, 21, 14, 49, 28, 35, 42, 56, null, null, null, null]
	Actual : [null, 56, 7, 14, 21, 28, 35, 42, 49, 56, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 42, 28, 35, 21, null, null, null, null, null]
	Expected : [null, 7, 21, 14, 42, 28, 35, null, null, null, null, null, null]
	Actual : [null, 21, 7, 14, 42, 28, 35, 21, null, null, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 21, 28, 35, 42, 56, 49, null, null, null]
	Expected : [null, 7, 21, 14, 49, 28, 35, 42, 56, null, null, null, null]
	Actual : [null, 49, 7, 14, 21, 28, 35, 42, 56, 49, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 14, 7, 42, 35, 28, 21, 56, 49, null, null, null]
	Expected : [null, 7, 14, 21, 42, 35, 28, 49, 56, null, null, null, null]
	Actual : [null, 49, 14, 7, 42, 35, 28, 21, 56, 49, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 14, 7, 28, 21, 56, 49, 42, 35, null, null, null]
	Expected : [null, 7, 14, 35, 28, 21, 56, 49, 42, null, null, null, null]
	Actual : [null, 35, 14, 7, 28, 21, 56, 49, 42, 35, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 14, 7, 28, 21, 56, 49, null, null, null, null, null]
	Expected : [null, 7, 14, 49, 28, 21, 56, null, null, null, null, null, null]
	Actual : [null, 49, 14, 7, 28, 21, 56, 49, null, null, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 21, 28, 35, 42, 56, 49, 63, null, null]
	Expected : [null, 7, 21, 14, 49, 28, 35, 42, 56, 63, null, null, null]
	Actual : [null, 63, 7, 14, 21, 28, 35, 42, 56, 49, 63, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 28, 21, 35, 49, 42, 56, 63, 70, null]
	Expected : [null, 7, 21, 14, 28, 63, 35, 49, 42, 56, 70, null, null]
	Actual : [null, 70, 7, 14, 28, 21, 35, 49, 42, 56, 63, 70, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 35, 28, 42, 21, null, null, null, null, null]
	Expected : [null, 7, 21, 14, 35, 28, 42, null, null, null, null, null, null]
	Actual : [null, 21, 7, 14, 35, 28, 42, 21, null, null, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 14, 7, 35, 28, 42, 21, null, null, null, null, null]
	Expected : [null, 7, 14, 21, 35, 28, 42, null, null, null, null, null, null]
	Actual : [null, 21, 14, 7, 35, 28, 42, 21, null, null, null, null, null]

[Test Failure: validSize] [-0.34] : Size variable could not be validated for the following method(s) due to early test failure(s): add, remove.

[Test Failure: validData] [-0.34] : Returned data could not be validated for the following method(s) due to early test failure(s): remove.

[Test Failure: compareTo] [-0.34] : Correct compareTo() usage could not be validated for the following method(s) due to early test failure(s): remove, add.
 */
