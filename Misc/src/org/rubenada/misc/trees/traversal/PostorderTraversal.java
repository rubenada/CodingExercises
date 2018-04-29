package org.rubenada.misc.trees.traversal;

import java.util.Stack;

public class PostorderTraversal {

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

    public static void postorder(Node root) {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root + " ");
    }

    public static void iterativePostorder(Node root) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);
            if (current.left != null)
                stack1.push(current.left);
            if (current.right != null)
                stack1.push(current.right);
        }

        while (!stack2.isEmpty())
            System.out.print(stack2.pop() + " ");
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

        System.out.println("Recursive Postorder Traversal:");
        postorder(root);
        System.out.println();
        System.out.println("Iterative Postorder Traversal:");
        iterativePostorder(root);
        System.out.println();
    }
}
