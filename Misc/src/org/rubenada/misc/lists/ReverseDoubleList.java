package org.rubenada.misc.lists;

/**
 * Given an singly linked list, reverse it
 */
public class ReverseDoubleList {

    static class Node {
        int value;
        Node next;
        Node prev;

        Node (int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "[ " + String.valueOf(value)
                    + " (prev " + (prev == null ? null : prev.value) + ") (next " + (next == null ? null : next.value) + ")]";
        }
    }

    // reverse list and return new header
    public static Node reverse (Node header) {
        if (header == null)
            return null;

        Node current = header;
        Node temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        if (temp != null)
            header = temp.prev;

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
        Node prev = null;
        for (int i=1; i<=5; i++) {
            current.next = new Node(i);
            current.prev = prev;
            prev = current;
            current = current.next;
        }
        current.prev = prev;

        System.out.print("Original List: { "); printList(header); System.out.println("}");
        header = reverse(header);
        System.out.print("Reverse List: { "); printList(header); System.out.println("}");

        header = new Node (15);
        System.out.print("Original List: { "); printList(header); System.out.println("}");
        header = reverse(header);
        System.out.print("Reverse List: { "); printList(header); System.out.println("}");


        header = new Node (99);
        Node n2  = new Node (100);
        header.next = n2;
        n2.prev = header;
        System.out.print("Original List: { "); printList(header); System.out.println("}");
        header = reverse(header);
        System.out.print("Reverse List: { "); printList(header); System.out.println("}");
    }

}
