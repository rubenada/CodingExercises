package org.rubenada.misc.arrays;

import java.util.*;

/**
 * Given an array of integers, all of them appearing an even number of times, except for one that appears an odd number of times,
 * find that number
 */
public class GetOddElementInArray {

    public static void main(String args[]) {
        int[] array = new int[] {5, 1, 2, 3, 2, 5, 5, 1, 2, 2, 5, 3, 5};

        System.out.println("getOddElement = " + getOddElement(array));
    }

    public static Integer getOddElement (int[] array) {
        Set<Integer> oddSet = new HashSet<>();
        Set<Integer> evenSet = new HashSet<>();

        for (int i : array) {
            if (oddSet.contains(i)) {
                oddSet.remove(i);
                evenSet.add(i);
            }
            else if (evenSet.contains(i)){
                evenSet.remove(i);
                oddSet.add(i);
            }
            else
                oddSet.add(i);
        }

        if (oddSet.isEmpty())
            return null;

        return oddSet.iterator().next();
    }

}
