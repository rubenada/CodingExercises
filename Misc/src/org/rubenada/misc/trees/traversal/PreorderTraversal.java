package org.rubenada.misc.trees.traversal;

import java.util.Stack;

public class PreorderTraversal {

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

    public static void preorder(Node root) {
        if (root == null)
            return;

        System.out.print(root + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void iterativePreorder(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current + " ");
            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
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

        System.out.println("Recursive Preorder Traversal:");
        preorder(root);
        System.out.println();
        System.out.println("Iterative Preorder Traversal:");
        iterativePreorder(root);
        System.out.println();
    }
}
