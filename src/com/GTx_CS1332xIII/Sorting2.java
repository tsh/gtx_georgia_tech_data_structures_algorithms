package com.GTx_CS1332xIII;


import java.util.Queue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting2 {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1){
            return;
        }
        int mid = arr.length / 2;
        T[] left = sliceOf(arr, 0, mid);
        T[] right = sliceOf(arr, mid, arr.length);

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length){
            if (comparator.compare(left[i], right[j]) > 0 ){
                arr[i+j] = right[j];
                j++;
            } else {
                arr[i+j] = left[i];
                i++;
            }
        }
        while (i < left.length){
            arr[i+j] = left[i];
            i++;
        }
        while (j < right.length){
            arr[i+j] = right[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

        int maxDigitNumber = Math.abs(arr[0]);
        for (int j : arr) {
            if (j==Integer.MIN_VALUE){
                // abs doesn't work on min value
                maxDigitNumber = Integer.MAX_VALUE;
            }
            if (Math.abs(j) > maxDigitNumber) {
                maxDigitNumber = Math.abs(j);
            }
        }

        int NUMBER_OF_BUCKETS = 19; // 10->radix + 9->to shift negative numbers
        List<Queue<Integer>> buckets = new ArrayList<Queue<Integer>>(NUMBER_OF_BUCKETS);
        for (int i=0; i < NUMBER_OF_BUCKETS; i++){
            buckets.add(new LinkedList<Integer>());
        }

        for (int divisor =1; maxDigitNumber/divisor > 0; divisor *=10) {

            for (int el : arr) {
                int digit = el / divisor;
                buckets.get((digit % 10) + 9).add(el);
            }

            // gather results
            int i = 0;
            for (Queue<Integer> q : buckets) {
                while (!q.isEmpty()) {
                    arr[i] = q.remove();
                    i++;
                }
            }
        }
    }

    private static <T> T[] sliceOf(T[] arr, int start, int end)
    {
        T[] slice = (T[]) new Object[end - start];
        for(int x = 0; x <= end-start-1; x++) {
            slice[x] = arr[x + start];
        }
        return slice;
    }
}
