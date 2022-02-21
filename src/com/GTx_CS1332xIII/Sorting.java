package com.GTx_CS1332xIII;


import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int i = 0;
            int lastSwappedIndex = 0;
            while (i < stopIndex) {
                if (comparator.compare(arr[i], arr[i+1]) > 0){
                    swap(arr, i, i+1);
                    lastSwappedIndex = i;
                }
                i++;
            }
            stopIndex = lastSwappedIndex;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        for (int i=0; i<arr.length; i++){
            int minIndex = i;
            for (int j=i+1; j< arr.length; j++){
                if (comparator.compare(arr[j], arr[minIndex]) < 0){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        for (int i=1; i<arr.length; i++){
            int j = i;
            while (j > 0 && comparator.compare(arr[j-1], arr[j]) > 0){
                swap(arr, j-1, j);
                j--;
            }
        }
    }

    private static <T> void swap(T[] arr, Integer index1, Integer index2){
        T tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
