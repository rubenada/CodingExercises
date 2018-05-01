package org.rubenada.misc.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of integers and a number k, return a list with the k most frequent numbers of the array
 * Example: array=[1,3,4,4,5,1]; k=2; output=1,4
 * In case there are less than k distinct elements, return all distinct elements
 * In case two elements have the same frequency, return first the element that appeared first in the array
 */
public class FindKMostFrequentElementsInArray {

    public static List<Integer> findMostFrequent (int[] array, int k) {
        if (array==null || array.length==0 || k<=0)
            return null;

        Map<Integer,Integer> valueToFreqMap = new LinkedHashMap<>(); // map: int value -> frequency of that value
        for (int number : array) {
            Integer freq = valueToFreqMap.get(number);
            freq = freq == null ? 1 : freq+1;
            valueToFreqMap.put(number,freq);
        }

        Map<Integer,List<Integer>> freqToValuesMap = new TreeMap<>(new InverseComparator()); // map: frequency : list of values with that frequency
        for (int number : valueToFreqMap.keySet()) {
            Integer freq = valueToFreqMap.get(number);
            List<Integer> list = freqToValuesMap.get(freq);
            if (list == null) {
                list = new LinkedList<>();
                list.add(number);
                freqToValuesMap.put(freq,list);
            }
            else
                list.add(number);
        }

        int i = 0;
        List<Integer> resultList = new LinkedList<>();
OUT:    for (int freq : freqToValuesMap.keySet()) {
            for (int number : freqToValuesMap.get(freq)) {
                resultList.add(number);
                i++;
                if (i==k)
                    break OUT;
            }
        }

        return resultList;
    }

    private static class InverseComparator implements Comparator<Integer> {
        @Override
        public int compare (Integer i1, Integer i2) {
            return -Integer.compare(i1,i2);
        }
    }

    public static void main(String args[]) {
        int[] array0 = {1, 3, 3, 1, 4, 4, 6, 5, 7, 5, 5};
        int[] array1 = {1, 3, 3, 1, 4, 4, 6};
        int[] array2 = {1, 1, 1, 1, 1};
        int[] array3 = {7, 5, 3};

        for (int k=1; k<=4; k++) {
            System.out.println("findMostFrequent(" + Arrays.toString(array0) + ", " + k + ") = " + Arrays.toString(findMostFrequent(array0, k).toArray()));
            System.out.println("findMostFrequent(" + Arrays.toString(array1) + ", " + k + ") = " + Arrays.toString(findMostFrequent(array1, k).toArray()));
            System.out.println("findMostFrequent(" + Arrays.toString(array2) + ", " + k + ") = " + Arrays.toString(findMostFrequent(array2, k).toArray()));
            System.out.println("findMostFrequent(" + Arrays.toString(array3) + ", " + k + ") = " + Arrays.toString(findMostFrequent(array3, k).toArray()));
        }
    }

}
