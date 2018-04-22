package org.rubenada.misc.lists;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an singly linked list where each node has an extra pointer to a random node in the list, perform a deep copy
 */
public class ListDeepCopy {

    static class Node {
        Object value;
        Node next;
        Node random;

        Node (Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return " [ " + value.toString() + " (r=" + (random == null ? null : random.value) + ") ] ";
        }
    }

    public static Node copy (Node header) {
        // translation map: node i the original list and its corresponding node in copy list
        Map<Node, Node> originalToCopyMap = new HashMap<>();

        Node copyHeader = new Node(10 + (Integer)header.value); // add 10 for debugging, to see better the copy
        Node current = header;
        Node copyCurrent = copyHeader;
        originalToCopyMap.put(header, copyHeader);

        // 1st pass: do the list copy
        while (current.next != null) {
            copyCurrent.next = new Node(10 + (Integer) current.next.value);  // add 10 for debugging, to see better the copy
            originalToCopyMap.put(current.next, copyCurrent.next);
            current = current.next;
            copyCurrent = copyCurrent.next;
        }

        // 2nd pass: set the random pointers in the copy list
        current = header;
        copyCurrent = copyHeader;
        while (current != null) {
            copyCurrent.random = originalToCopyMap.get(current.random);
            current = current.next;
            copyCurrent = copyCurrent.next;
        }

        return copyHeader;
    }

    public static void main(String args[]) {
        Node header = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        header.next = n1; header.random = n2;
        n1.next = n2; n1.random = n3;
        n2.next = n3; n2.random = n2;
        n3.next = n4; n3.random = null;
        n4.next = null; n4.random = n2;

        System.out.print("Original List: {");
        for (Node current = header; current != null; current = current.next)
            System.out.print(current);
        System.out.println("}");

        Node copy = copy(header);
        System.out.print("Copy List: {");
        for (Node current = copy; current != null; current = current.next)
            System.out.print(current);
        System.out.println("}");
    }

}
