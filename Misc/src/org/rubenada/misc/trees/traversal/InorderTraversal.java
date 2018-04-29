package org.rubenada.misc.trees.traversal;

import java.util.Stack;

public class InorderTraversal {

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

    public static void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root + " ");
        inorder(root.right);
    }

    public static void iterativeInorder(Node root) {
        Stack<Node> stack =  new Stack<>();
        Node current = root;

        while (!stack.isEmpty() || current!=null) {
            if (current!=null) {
                stack.push(current);
                current = current.left;
            }
            else {
                current = stack.pop();
                System.out.print(current + " ");
                current = current.right;
            }
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


        System.out.println("Recursive Inorder Traversal:");
        inorder(root);
        System.out.println();
        System.out.println("Iterative Inorder Traversal:");
        iterativeInorder(root);
        System.out.println();
    }
}
