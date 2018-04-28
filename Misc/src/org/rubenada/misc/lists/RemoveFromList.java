package org.rubenada.misc.lists;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an singly linked list, remove certain elements
 */
public class RemoveFromList {

    static class Node {
        int value;
        Node next;

        Node (int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // remove all nodes whose value is greater than k
    public static Node removeGreaterThan (Node header, int k) {
        // move header while its value is greater than
        while (header != null && header.value > k)
            header = header.next;

        if (header == null)
            return null;

        // remove nodes from the rest of the list
        Node current = header;
        while (current.next != null) {
            if (current.next.value > k)
                current.next = current.next.next;
            else
                current = current.next;
        }
        return header;
    }

    // remove all nodes (in a sorted list) whose value is k
    public static Node removeFromSortedList (Node header, int k) {

        // auxiliary dummy node whose next is the header of the list
        Node aux = new Node(0);
        aux.next = header;
        Node current = aux;

        while (current.next != null) {
           if (current.next.value == k)
               current.next = current.next.next;
           else if (current.next.value > k)
               break;
           else // current.next.value < k
            current = current.next;
        }

        return aux.next;
    }


    // remove nodes with duplicated values
    public static Node removeDuplicates (Node header) {
        if (header == null)
            return null;
        Set<Integer> set = new HashSet<>();
        Node current = header;
        set.add(current.value);
        while (current.next != null) {
            if (set.contains(current.next.value))
                current.next = current.next.next;
            else {
                set.add(current.next.value);
                current = current.next;
            }
        }
        return header;
    }




    public static void main(String args[]) {
        Node header = new Node (0);
        Node current = header;
        for (int i=1; i<=9; i++) {
            current.next = new Node(i%4);
            current = current.next;
        }
        current = header;
        System.out.print("Original List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        header = removeGreaterThan(header, 1);
        current = header;
        System.out.print("removeGreaterThan 1 List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");


        header = new Node (0);
        current = header;
        for (int i=1; i<=9; i++) {
            current.next = new Node(i%4);
            current = current.next;
        }
        current = header;
        System.out.print("Original List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        header = removeDuplicates(header);
        current = header;
        System.out.print("removeDuplicates List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");


        header = new Node (0);
        current = header;
        for (int i=1; i<=9; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        current = header;
        System.out.print("Original List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        header = removeFromSortedList(header, 3);
        current = header;
        System.out.print("removeFromSortedList (3) List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
    }

}
