package org.rubenada.misc.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Find the majority element in an array. The majority element is the element that occurs more than half of the size of the array
 */
public class MajorityElementOfArray {

    public static Integer findMajorityElement (int[] array) {
        if (array == null || array.length == 0)
            return null;

        int majorityThreshold = (array.length / 2) + 1;
        Map<Integer, Integer> occurrencesMap = new HashMap<>();
        int maxOccurrences = 0; // max occurrences so far
        for (int i=0; i<array.length; i++) {
            int element = array[i];
            Integer occurrences = occurrencesMap.get(element);
            occurrences = (occurrences == null ? 1 : occurrences + 1);
            if (occurrences >= majorityThreshold)
                return element;
            maxOccurrences = Math.max(maxOccurrences, occurrences);

            // if the element with maxOccurrences + remaining items does not reach threshold, we know for sure there is no majority element
            if (maxOccurrences+(array.length-1-i) < majorityThreshold)
                return null;

            occurrencesMap.put(element, occurrences);
        }
        return null;
    }

    public static void main(String args[]) {
        int[] array1 = {2, 2, 10, 2, 2, 13, 2};
        int[] array2 = {7, 9, 5, 6, 3, 2};
        int[] array3 = {7, 5, 3, 1};

        System.out.println("findMajorityElement(" + Arrays.toString(array1)+ ") = " + findMajorityElement(array1));
        System.out.println("findMajorityElement(" + Arrays.toString(array2)+ ") = " + findMajorityElement(array2));
        System.out.println("findMajorityElement(" + Arrays.toString(array3)+ ") = " + findMajorityElement(array3));
    }

}
