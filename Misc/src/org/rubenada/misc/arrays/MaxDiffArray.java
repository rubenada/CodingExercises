package org.rubenada.misc.arrays;

import java.util.Arrays;

/**
 * Maximum difference between two elements such that larger element appears after the smaller number.
 * If array contains elements in decreasing order, return 0
 */
public class MaxDiffArray {

    public static void main(String args[]) {
        int[] array1 = {2, 3, 10, 6, 4, 13, 1};
        int[] array2 = {7, 9, 5, 6, 3, 2};
        int[] array3 = {7, 5, 3, 1};

        System.out.println("maxDiff(" + Arrays.toString(array1)+ ") = " + maxDiff(array1));
        System.out.println("maxDiff(" + Arrays.toString(array2)+ ") = " + maxDiff(array2));
        System.out.println("maxDiff(" + Arrays.toString(array3)+ ") = " + maxDiff(array3));
    }

    public static Integer maxDiff (int[] array) {
        if (array == null || array.length == 0)
            return null;
        int maxDiff = 0;
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            maxDiff = Math.max(maxDiff, array[i]-min);
            min = Math.min(min, array[i]);
        }
        return maxDiff;
    }
}
