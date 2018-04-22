package org.rubenada.misc.trees;


/**
 * Given a binary tree and two nodes belonging to the tree, find their lowest common ancestor
 */
public class LowestCommonAncestorBinary {

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


    public static Node lowestCommonAncestor(Node root, Node a, Node b) {
        if (root == null)
            return null;

        if (root == a || root == b)
            return root;

        Node lcsLeft = lowestCommonAncestor(root.left, a, b);
        Node lcsRight = lowestCommonAncestor(root.right, a, b);

        if (lcsLeft != null && lcsRight != null)
            return root;

        return (lcsLeft != null ? lcsLeft : lcsRight);
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
        Node one = root;
        Node two = root.left;
        Node three = root.right;
        Node four = root.left.left;
        Node five = root.left.right;
        Node six = root.right.left;
        Node seven = root.right.right;

        System.out.println("lowestCommonAncestor(1,2)=" + lowestCommonAncestor(root, one, two));
        System.out.println("lowestCommonAncestor(2,7)=" + lowestCommonAncestor(root, two, seven));
        System.out.println("lowestCommonAncestor(4,5)=" + lowestCommonAncestor(root, four, five));
        System.out.println("lowestCommonAncestor(4,3)=" + lowestCommonAncestor(root, four, three));
        System.out.println("lowestCommonAncestor(7,6)=" + lowestCommonAncestor(root, seven, six));
        System.out.println("lowestCommonAncestor(2,5)=" + lowestCommonAncestor(root, two, five));
        System.out.println("lowestCommonAncestor(4,4)=" + lowestCommonAncestor(root, four, four));
    }
}
