package org.rubenada.misc.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array containing only 0s and 1s, find the largest sub-array which contain equal number of 0s and 1s
 */
public class MaxSubArrayEqual0s1s {

    public static void main(String args[]) {
        int[] array1 = {0, 1, 1, 1, 0, 0, 0, 1, 1, 0};
        int[] array2 = {0, 1, 0, 1, 1, 1, 1, 1, 1, 0};
        int[] array3 = {1, 1, 1, 0};
        int[] array4 = {0, 0, 0, 0, 0, 0, 0};
        int[] array5 = {1, 1};
        int[] array6 = {1};
        int[] array7 = {0};

        System.out.print("maxRange1s0s(" + Arrays.toString(array1)+ ") = "); maxRange1s0s(array1);
        System.out.print("maxRange1s0s(" + Arrays.toString(array2)+ ") = "); maxRange1s0s(array2);
        System.out.print("maxRange1s0s(" + Arrays.toString(array3)+ ") = "); maxRange1s0s(array3);
        System.out.print("maxRange1s0s(" + Arrays.toString(array4)+ ") = "); maxRange1s0s(array4);
        System.out.print("maxRange1s0s(" + Arrays.toString(array5)+ ") = "); maxRange1s0s(array5);
        System.out.print("maxRange1s0s(" + Arrays.toString(array6)+ ") = "); maxRange1s0s(array6);
        System.out.print("maxRange1s0s(" + Arrays.toString(array7)+ ") = "); maxRange1s0s(array7);
    }

    public static void maxRange1s0s (int[] array) {
        if (array == null || array.length == 0)
            return;

        int maxRange = 0;
        int endOfMaxRange = -1;
        int sum = 0; // accumulated sum (considering 0s as -1, and 1s as 1)
        Map<Integer,Integer> mapPositions = new HashMap<>(); // map: sum -> position where it first appeared that sum

        for (int i=0; i<array.length; i++) {
            sum = sum + (array[i]==0 ? -1 : 1);
            if (sum == 0) {
                // if sum==0, it means that 0..i is a range with same 0s and 1s, and by definition, the largest we have seen so far
                endOfMaxRange = i;
                maxRange = i+1;
            }
            else {
                Integer previousPosition = mapPositions.get(sum);
                if (previousPosition == null) // first time we have this sum, save it
                    mapPositions.put(sum,i);
                else {
                    // we have seen this sum before, it means that between previousPosition+1 .. i there are the same #1s and #0s
                    // check if this range is largest than the current maxRange
                    if (i-previousPosition > maxRange) {
                        maxRange = i-previousPosition;
                        endOfMaxRange = i;
                    }
                }
            }
        }

        if (maxRange == 0)
            System.out.println("No solution");
        else
            System.out.println("Largest range: " + (endOfMaxRange-maxRange+1) + "-" + endOfMaxRange + " (size " + maxRange + ")");
    }
}
