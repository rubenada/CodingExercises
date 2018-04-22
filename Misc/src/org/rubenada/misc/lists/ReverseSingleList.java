package org.rubenada.misc.lists;

/**
 * Given an singly linked list, reverse it
 */
public class ReverseSingleList {

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

    // reverse list and return new header
    public static Node reverse (Node header) {
        if (header == null)
            return null;

        Node current = header;
        Node prev = null;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        header = prev;
        return header;
    }

    private static void printList (Node header) {
        Node current = header;
        while (current != null) {
            System.out.print(current);
            System.out.print(" ");
            current = current.next;
        }
    }

    public static void main(String args[]) {
        Node header = new Node (0);
        Node current = header;
        for (int i=1; i<=9; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        System.out.print("Original List: { "); printList(header); System.out.println("}");

        header = reverse(header);
        System.out.print("Reverse List: { "); printList(header); System.out.println("}");

        header = new Node (15);
        System.out.print("Original List: { "); printList(header); System.out.println("}");

        header = reverse(header);
        System.out.print("Reverse List: { "); printList(header); System.out.println("}");
    }

}
