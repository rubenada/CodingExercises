package org.rubenada.misc.arrays;

import java.util.Arrays;

/**
 * Sort an almost sorted array, where only two elements are swapped.
 */
public class SortAlmostSortedArray {

    public static void main(String args[]) {
        int[] array1 = {1, 5, 3, 3, 3, 4, 4, 5, 2, 5, 6, 6};
        int[] array2 = {6, 7, 9, 8, 8};
        int[] array3 = {2, 1, 3, 3, 4, 6, 9};
        int[] array4 = {2, 1};
        int[] array5 = {2, 2, 1};

        System.out.println("sort(" + Arrays.toString(array1)+ ") = " + Arrays.toString(sort(array1)));
        System.out.println("sort(" + Arrays.toString(array2)+ ") = " + Arrays.toString(sort(array2)));
        System.out.println("sort(" + Arrays.toString(array3)+ ") = " + Arrays.toString(sort(array3)));
        System.out.println("sort(" + Arrays.toString(array4)+ ") = " + Arrays.toString(sort(array4)));
        System.out.println("sort(" + Arrays.toString(array5)+ ") = " + Arrays.toString(sort(array5)));

    }

    public static int[] sort (int[] array) {
        if (array == null || array.length == 0)
            return null;

        int leftPos = 0;
        int rightPos = array.length-1;

        while (array[leftPos] <= array[leftPos+1])
            leftPos++;
        while (leftPos > 0 && array[leftPos] == array[leftPos-1]) // handle duplicates
            leftPos--;

        while (array[rightPos] >= array[rightPos-1])
            rightPos--;
        while (rightPos < array.length-1 && array[rightPos] == array[rightPos+1]) // handle duplicates
            rightPos++;

        int temp = array[leftPos];
        array[leftPos] = array[rightPos];
        array[rightPos] = temp;
        return array;
    }
}
