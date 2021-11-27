package com.GTx_CS1332xI;


import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayQueue.
 */
public class ArrayQueue<T> {

    /*
     * The initial capacity of the ArrayQueue.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayQueue.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayQueue() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the back of the queue.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array and reset front to 0.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        if (size == backingArray.length){
            resize();
        }

        Integer back = (front + size) % backingArray.length;
        backingArray[back] = data;
        size ++;
    }

    private void resize(){
        T[] newArray = (T[]) new Object[backingArray.length * 2];
        int j = front;
        for (int i=0; i<size; i++ ){
            if (j == backingArray.length){
                j=0;
            }
            newArray[i] = backingArray[j];
            j++;
        }
        backingArray = newArray;
        front = 0;
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you dequeue from with null.
     *
     * If the queue becomes empty as a result of this call, do not reset
     * front to 0.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        if (front == backingArray.length){
            front = 0;
        }
        T data = backingArray[front];
        backingArray[front] = null;
        front++;
        size--;
        return data;
    }

    /**
     * Returns the backing array of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
/*
[Test Failure: enqueue] [-0.48] : Unexpected content after enqueueing one element and triggering first resize, with front at index 0.
	Expected : [0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, null, null, null, null, null, null, null, null]
	Actual : [9a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a]

[Test Failure: enqueue] [-0.48] : Unexpected content after enqueueing one element and triggering second resize, with front at index 0.
	Expected : [0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a, 12a, 13a, 14a, 15a, 16a, 17a, 18a, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]
	Actual : [18a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a, 12a, 13a, 14a, 15a, 16a, 17a]

[Test Failure: enqueue] [-0.48] : Unexpected content after enqueueing one element and triggering first resize, with front at index 3.
	Expected : [0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, null, null, null, null, null, null, null, null]
	Actual : [6a, 7a, 8a, 9a, 1a, 2a, 3a, 4a, 5a]

[Test Failure: validFront] [-0.48] : Front variable did not update correctly when testing the following method(s): enqueue, dequeue.
 */