package org.rubenada.misc.arrays;

import java.util.Arrays;

/**
 * Given an array like [0,1,2,3,4,5] and an index like 3, make the array [3,4,5,0,1,2] in place
 */
public class RotateArray {

    public static int[] rotate (int[] array, int index) {
        if (array == null || array.length == 0)
            return array;
        if (index <= 0 || index > array.length-1)
            return array;

        int n = array.length;
        int value = array[index];
        int currentIndex = index;
        for (int i=0; i<n; i++) {
            currentIndex -= index;
            if (currentIndex < 0)
                currentIndex = n + currentIndex;
            int temp = array[currentIndex];
            array[currentIndex] = value;
            value = temp;
        }
        return array;
    }

    public static void main(String args[]) {
        int[] array1 = {0, 1, 2, 3, 4, 5, 6};
        int[] array2 = {7, 9, 5, 6, 3, 2};
        int[] array3 = {7, 5, 3, 1};

        System.out.println("rotate(" + Arrays.toString(array1)+ ", 2) = " +  Arrays.toString(rotate(array1, 2)));
        System.out.println("rotate(" + Arrays.toString(array2)+ ", 0) = " +  Arrays.toString(rotate(array2, 0)));
        System.out.println("rotate(" + Arrays.toString(array3)+ ", 1) = " +  Arrays.toString(rotate(array3, 1)));
    }

}
