package org.rubenada.misc.lists;

/**
 * Given an singly linked list, find the kth-to-last element
 */
public class ListKthToLast {

    static class Node {
        Object value;
        Node next;

        Node (Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    // we will consider that the last node is the k=0, the second-to-last is k=1, etc.
    public static Node getKthToLast (Node header, int k) {
        if (k<0) return null; // or exception

        // we'll keep two pointers, slow will remain in the header of the list, fast will be move k steps forward;
        // then we will move both step by step, until fast reaches the end of the list, at that moment slow will be the requested node.
        Node slow = header;
        Node fast = header;
        for (int i=0; i<k; i++) {
            if (fast == null)
                return null; // or exception, there are less than the requested k in the list
            fast = fast.next;
        }
        if (fast == null)
            return null; // or exception, there are less than the requested k in the list

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String args[]) {
        Node header = new Node (0);
        Node current = header;
        for (int i=1; i<=9; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        current = header;
        System.out.print("List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        System.out.println("getKthToLast(0) = " +  getKthToLast(header, 0)); // should be the last
        System.out.println("getKthToLast(1) = " +  getKthToLast(header, 1));
        System.out.println("getKthToLast(2) = " +  getKthToLast(header, 2));
        System.out.println("getKthToLast(5) = " +  getKthToLast(header, 5));
        System.out.println("getKthToLast(9) = " +  getKthToLast(header, 9));  // should be the first
        System.out.println("getKthToLast(10) = " +  getKthToLast(header, 10)); // should be null
        System.out.println("getKthToLast(11) = " +  getKthToLast(header, 11)); // should be null
        System.out.println("getKthToLast(12) = " +  getKthToLast(header, 12)); // should be null

        System.out.println("-----------");
        header = new Node ('a');
        current = header;
        for (char c='b'; c<='c'; c++) {
            current.next = new Node(c);
            current = current.next;
        }
        current = header;
        System.out.print("List: { ");
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("}");
        System.out.println("getKthToLast(0) = " +  getKthToLast(header, 0)); // should be the last
        System.out.println("getKthToLast(1) = " +  getKthToLast(header, 1));
        System.out.println("getKthToLast(2) = " +  getKthToLast(header, 2)); // should be the first
        System.out.println("getKthToLast(3) = " +  getKthToLast(header, 3)); // should be null
        System.out.println("getKthToLast(4) = " +  getKthToLast(header, 4)); // should be null
    }

}
