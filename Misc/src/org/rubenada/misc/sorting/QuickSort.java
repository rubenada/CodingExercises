package org.rubenada.misc.sorting;

import java.util.Random;

public class QuickSort {

    public static void quickSort(Object[] array) {
        if (array.length < 2)
            return;
        quickSort(array, 0, array.length-1);
    }

    private static void quickSort(Object[] array, int ini, int end) {
        if (end <= ini)
            return;

        int pivotPos = partitionEnd(array, ini, end);
        quickSort(array, ini, pivotPos-1);
        quickSort(array, pivotPos+1, end);
    }

    // picks end as pivot
    private static int partitionEnd(Object[] array, int ini, int end) {
        int i = ini-1;
        Object pivot = array[end];
        for (int j=ini; j<end; j++) {
            if ( ((Comparable<Object>)array[j]).compareTo(pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        i++;
        swap(array, i, end);
        return i;
    }

    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main (String[] args) {
        Integer[] array = new Integer[40];
        Random random = new Random();
        for (int i = 0; i<array.length; i++)
            array[i] = random.nextInt(99);

        System.out.print("Array: " );
        for (int i = 0; i<array.length; i++)
            System.out.print(array[i]+" ");
        System.out.println();

        quickSort(array);

        System.out.print("Sorted: " );
        for (int i = 0; i<array.length; i++)
            System.out.print(array[i]+" ");
        System.out.println();
    }
}
