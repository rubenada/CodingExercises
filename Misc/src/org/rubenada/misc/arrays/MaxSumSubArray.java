package org.rubenada.misc.arrays;

import java.util.Arrays;

/**
 * Given an array of integers, find the maximum sum in a subarray
 */
public class MaxSumSubArray {

    public static void main(String args[]) {
        int[] array1 = {-2, -3, 4, -1, -2, 1, 5, -3};
        int[] array2 = {-2, -3, -1};
        int[] array3 = {2, 3, 5, 7};

        System.out.println("maxSubArraySum(" + Arrays.toString(array1)+ ") = " + maxSubArraySum(array1));
        System.out.println("maxSubArraySum(" + Arrays.toString(array2)+ ") = " + maxSubArraySum(array2));
        System.out.println("maxSubArraySum(" + Arrays.toString(array3)+ ") = " + maxSubArraySum(array3));

        System.out.println("maxSubArrayProduct(" + Arrays.toString(array1)+ ") = " + maxSubArrayProduct(array1));
        System.out.println("maxSubArrayProduct(" + Arrays.toString(array2)+ ") = " + maxSubArrayProduct(array2));
        System.out.println("maxSubArrayProduct(" + Arrays.toString(array3)+ ") = " + maxSubArrayProduct(array3));
    }

    public static Integer maxSubArraySum (int[] array) {
        if (array == null || array.length == 0)
            return null;

        int max = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for (int i = 0; i < array.length; i++) {
            maxEndingHere += array[i];
            max = Math.max(max, maxEndingHere);
            maxEndingHere = Math.max(maxEndingHere, 0);
        }
        return max;
    }

    // equivalent but with product
    public static Integer maxSubArrayProduct (int[] array) {
        if (array == null || array.length == 0)
            return null;

        int max = Integer.MIN_VALUE;
        int maxEndingHere = 1;

        for (int i = 0; i < array.length; i++) {
            maxEndingHere *= array[i];
            max = Math.max(max, maxEndingHere);
            maxEndingHere = Math.max(maxEndingHere, 1);
        }
        return max;
    }

}
