package org.rubenada.misc.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array containing only 0s and 1s, print all sub-arrays which contain equal number of 0s and 1s
 */
public class AllSubArrayEqual0s1s {

    public static void main(String args[]) {
        int[] array0 = {0, 1, 0, 0, 1, 0, 0, 1, 1};
        int[] array1 = {0, 1, 1, 1, 0, 0, 0, 1, 1, 0};
        int[] array2 = {0, 1, 0, 1, 1, 1, 1, 1, 1, 0};
        int[] array3 = {1, 1, 1, 0};
        int[] array4 = {0, 0, 0, 0, 0, 0, 0};
        int[] array5 = {1, 1};
        int[] array6 = {1};
        int[] array7 = {0};

        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array0)+ ") = "); subarraysEqual1s0s(array0); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array1)+ ") = "); subarraysEqual1s0s(array1); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array2)+ ") = "); subarraysEqual1s0s(array2); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array3)+ ") = "); subarraysEqual1s0s(array3); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array4)+ ") = "); subarraysEqual1s0s(array4); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array5)+ ") = "); subarraysEqual1s0s(array5); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array6)+ ") = "); subarraysEqual1s0s(array6); System.out.println();
        System.out.print("subarraysEqual1s0s(" + Arrays.toString(array7)+ ") = "); subarraysEqual1s0s(array7); System.out.println();
    }

    public static void subarraysEqual1s0s (int[] array) {
        if (array == null || array.length == 0)
            return;

        int sum = 0; // accumulated sum (considering 0s as -1, and 1s as 1)
        Map<Integer, List<Integer>> mapSumPositions = new HashMap<>();  // map: sum -> positions where it appeared that sum

        // to ease calculations for ranges starting at 0 (i.e. sum 0), consider add -1 as position for sum 0
        List<Integer> list0 = new LinkedList<>();
        list0.add(-1);
        mapSumPositions.put(0, list0);

        for (int i=0; i<array.length; i++) {
            sum = sum + (array[i]==0?-1:1);

            List<Integer> list = mapSumPositions.get(sum);
            if (list == null) { // this is the first time we see this sum, add it to the map
                list = new LinkedList<>();
                list.add(i);
                mapSumPositions.put(sum, list);
            }
            else { // we have seen this sum before in previous positions, all pairs [previous_position - i] is a solution
                for (int pos : list)
                    System.out.print("[" + (pos+1) + "-" + i + "]");
                list.add(i);
            }
        }
    }
}
