package Lab1;

import java.util.Random;

public class Lab1 {
    /**
     * Sorting algorithms
     **/
    public static Random random = new Random();

    // Insertion sort.
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int checkVal = array[i];
            int j;

            for (j = i-1; j >= 0; j--) {
                if (array[j] > checkVal) {
                    array[j + 1] = array[j];
                }
                else {
                    break;
                }
            }
            array[j + 1] = checkVal;
        }
    }

    // Quicksort.

    public static void quickSort(int[] array) {
        int low = 0;
        int high = array.length - 1;
        quickSort(array, low, high);
    }

    // Quicksort part of an array
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int low, int high) {
        // Random index in scope
        int index = Math.max(random.nextInt(high), low);
        // Swap the lowest index in scope with the random index
        swap(array, index, low);
        // Assign the random element from the array to the variable pivot
        int pivot = array[low];
        // The left pointer "l" moves one step forward because of the pivot element being the first in scope
        int l = low + 1, h = high;

        while (l <= h) {
            while (l <= high && array[l] < pivot)
                l++;
            while (array[h] > pivot)
                h--;
            if (l <= h) {
                swap(array, l, h);
                l++;
                h--;
            }
        }
        // Swap the pivot element with the right pointer "h" such that the pivot element gets placed correctly
        swap(array, low, h);
        return h;
    }

    // Swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }

    // Mergesort.

    public static int[] mergeSort(int[] array) {
        if (array.length < 1)
            return array;
        int first = 0;
        int last = array.length - 1;
        return mergeSort(array, first, last);
    }

    public static int[] mergeSort(int[] array, int first, int last) {

        if (first == last) {
            return new int[]{array[first]};
        }
        int m = first + (last - first) / 2;
        int[] left = mergeSort(array, first, m);
        int[] right = mergeSort(array, m + 1, last);
        return merge(left, right);

    }

    public static int[] merge(int[] left, int[] right) {

        int i = 0;
        int j = 0;
        int n = 0;
        int[] merged = new int[left.length + right.length];

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[n] = left[i];
                i++;
            } else {
                merged[n] = right[j];
                j++;
            }
            n++;
        }

        while (i < left.length) {
            merged[n++] = left[i++];
        }
        while (j < right.length) {
            merged[n++] = right[j++];
        }
        return merged;
    }
}
