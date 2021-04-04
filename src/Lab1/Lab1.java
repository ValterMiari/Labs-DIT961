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
        int index = Math.max(random.nextInt(high), low);
        swap(array, index, low);
        int pivot = array[low];
        int l = low + 1; int h = high;

        while (l <= h) {
            while (array[l] < pivot)
                l++;
            while (array[h] > pivot)
                h--;
            if (l < h) {
                swap(array, l, h);
                l++; h--;
            }
        }
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
        throw new UnsupportedOperationException();
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        throw new UnsupportedOperationException();
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right) {
        throw new UnsupportedOperationException();
    }
}
