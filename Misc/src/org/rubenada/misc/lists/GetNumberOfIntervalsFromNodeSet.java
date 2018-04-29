package org.rubenada.misc.lists;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a set of nodes belonging to a singly linked list, compute the number of intervals that these nodes represent
 * Example: given the list A -> B -> C -> D -> E  and the set {B,A,E,C} the number of intervals is 2: {A-C} and {E}
 */
public class GetNumberOfIntervalsFromNodeSet {

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


    public static int getNumberOfIntervals (Set<Node> set) {
        Set<Node> endOfInterval = new HashSet<>();
        for (Node node : set) {
            Node current = node;
            while (current.next != null && set.contains(current.next))
                current = current.next;
            endOfInterval.add(current);
        }
        return endOfInterval.size();
    }


    public static void main(String args[]) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n0.next = n1; n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5; n5.next = n6; n6.next = null;

        Set<Node> set1 = new HashSet<>();
        set1.add(n1); set1.add(n0);
        set1.add(n6);
        set1.add(n4); set1.add(n3);

        Set<Node> set2 = new HashSet<>();
        set2.add(n1); set2.add(n2); set2.add(n3);
        set2.add(n5);

        Set<Node> set3 = new HashSet<>();

        Set<Node> set4 = new HashSet<>();
        set4.add(n4);

        System.out.println("Number of intervals " + Arrays.toString(set1.toArray()) + " = " + getNumberOfIntervals(set1));
        System.out.println("Number of intervals " + Arrays.toString(set2.toArray()) + " = " + getNumberOfIntervals(set2));
        System.out.println("Number of intervals " + Arrays.toString(set3.toArray()) + " = " + getNumberOfIntervals(set3));
        System.out.println("Number of intervals " + Arrays.toString(set4.toArray()) + " = " + getNumberOfIntervals(set4));
    }

}
