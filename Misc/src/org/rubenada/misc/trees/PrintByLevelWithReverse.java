package org.rubenada.misc.trees;

import java.util.Stack;

/**
 * Print nodes in balanced binary tree level by level and reversing the print order every other level
 */
public class PrintByLevelWithReverse {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            data = d;
        }
    }

    public static void printWithReverse(Node root) {
        if (root == null)
            return;

        Stack<Node> currentStack = new Stack<>();
        boolean leftFirst = true;
        currentStack.push(root);

        while (!currentStack.empty()) {
            Stack<Node> nextStack = new Stack<>();
            do {
                Node node = currentStack.pop();
                System.out.print(node.data + " ");
                if (leftFirst) {
                    if (node.left != null)
                        nextStack.push(node.left);
                    if (node.right != null)
                        nextStack.push(node.right);
                }
                else {
                    if (node.right != null)
                        nextStack.push(node.right);
                    if (node.left != null)
                        nextStack.push(node.left);
                }
            } while (!currentStack.empty());
            System.out.println("");
            currentStack = nextStack;
            leftFirst = !leftFirst;
        }
    }


    public static void main(String[] args) {
        /*
                   1
                /     \
              2         3
            /   \      /  \
           4    5     6    7
          /\   / \   / \   / \
         8 9  10 11 12 13 14 15
       */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(13);
        root.right.right.left = new Node(14);
        root.right.right.right = new Node(15);
        printWithReverse(root);
    }
}
