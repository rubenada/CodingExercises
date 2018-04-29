package org.rubenada.misc.trees.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class ByLevelTraversal {

    static class Node {
        int data;
        Node left;
        Node right;
        Node(int d) {
            data = d;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    public static void levelTraversal(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current + " ");
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
    }


    public static void main(String[] args) {
        /*
               1
              / \
             2  3
            /\  /\
           4 5 6 7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("By Level Traversal:");
        levelTraversal(root);
        System.out.println();
    }
}
