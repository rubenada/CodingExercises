package org.rubenada.misc.lists;

/**
 * Given a linked list, put all the odd elements in lexicographical order list
 * in front of the even elements in lexicographical order in place.
 */
public class SortList {

    static class Node {
        Integer value;
        Node next;

        Node (int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "["+value+"]";
        }
    }

    public static Node sort (Node header) {
        Node oddList = new Node(0);
        Node evenList = new Node(0);

        Node current = header;
        while (current != null) {
            Node next = current.next;
            if (current.value % 2 != 0)
                insertNodeInList(oddList, current);
            else
                insertNodeInList(evenList, current);
            current = next;
        }

        current = oddList;
        while (current.next != null)
            current = current.next;

        current.next = evenList.next;
        return oddList.next;
    }

    private static void insertNodeInList(Node auxHeader, Node node) {
        Node current = auxHeader;

        while (current.next != null && String.valueOf(current.next.value).compareTo(String.valueOf(node.value)) < 0) {
            current = current.next;
        }
        Node next = current.next;
        current.next = node;
        node.next = next;
    }

    public static void main(String args[]) {
        Node header = new Node (0);
        Node current = header;
        for (int i=2; i<=22; i++) {
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
        header = sort(header);
        current = header;
        System.out.print("sorted List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");


        header = new Node (0);
        current = header;
        for (int i=2; i<=22; i+=2) {
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
        header = sort(header);
        current = header;
        System.out.print("sorted List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");


        header = new Node (1);
        current = header;
        for (int i=3; i<=22; i+=2) {
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
        header = sort(header);
        current = header;
        System.out.print("sorted List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");

        header = new Node (4);
        current = header;
        System.out.print("Original List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        header = sort(header);
        current = header;
        System.out.print("sorted List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");


        header = new Node (1);
        current = header;
        System.out.print("Original List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        header = sort(header);
        current = header;
        System.out.print("sorted List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");


        header = null;
        current = header;
        System.out.print("Original List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        header = sort(header);
        current = header;
        System.out.print("sorted List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");

    }

}
