package org.rubenada.misc.sorting;

import java.util.Random;

public class MergeSort {


    public static void mergeSort(Object[] array) {
        if (array.length < 2)
            return;

        int mid = array.length / 2;
        Object[] leftArray = new Object[mid];
        Object[] rightArray = new Object[array.length-mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        System.arraycopy(array, mid, rightArray, 0, array.length-mid);

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private static void merge(Object[] array, Object[] leftArray, Object[] rightArray) {
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (((Comparable<Object>)leftArray[leftIndex]).compareTo(rightArray[rightIndex]) <= 0)
                array[index++] = leftArray[leftIndex++];
            else
                array[index++] = rightArray[rightIndex++];
        }

        while (leftIndex < leftArray.length)
            array[index++] = leftArray[leftIndex++];
        while (rightIndex < rightArray.length)
            array[index++] = rightArray[rightIndex++];
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

        mergeSort(array);

        System.out.print("Sorted: " );
        for (int i = 0; i<array.length; i++)
            System.out.print(array[i]+" ");
        System.out.println();
    }
}
