package org.rubenada.misc.trees;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given preorder traversal of a binary search tree, construct the BST.
 */
public class PreorderToBST {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            data = d;
        }
    }

    public static Node preorderToBST(int[] array) {
        if (array == null || array.length == 0)
            return null;
        return _preorderToBST(array, new AtomicInteger(0), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static Node _preorderToBST(int[] array, AtomicInteger index, int min, int max) {
        if (index.intValue() >= array.length)
            return null;
        Node node = null;
        int value = array[index.intValue()];
        if (value >= min && value <= max) {
            node = new Node(value);
            index.incrementAndGet();
            node.left  = _preorderToBST(array, index, min, value);
            node.right = _preorderToBST(array, index, value, max);
        }
        return node;
    }

    // auxiliary method to print inorder traversal of a binary tree
    private static void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public static void main(String[] args) {
        int array[] = {10, 5, 1, 5, 5, 7, 8, 9, 40, 50, 49, 55};
        Node root = preorderToBST(array);
        printInorder(root);
    }
}
