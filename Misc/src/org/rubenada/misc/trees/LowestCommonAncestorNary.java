package org.rubenada.misc.trees;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a n-ary tree and a collection of nodes belonging to the tree, find their lowest common ancestor
 */
public class LowestCommonAncestorNary {

    static class Node {
        int data;
        Set<Node> children;
        Node(int d) {
            data = d;
            children = new HashSet<>();
        }
        void addChild (Node n) {
            children.add(n);
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }


    public static Node lowestCommonAncestor(Node root, Collection<Node> nodes) {
        if (root == null)
            return null;

        for (Node n: nodes)
            if (root == n)
                return root;

        Node lcsChild = null;
        for (Node child : root.children) {
            Node lcs = lowestCommonAncestor(child, nodes);
            if (lcs != null) {
                if (lcsChild == null)
                    lcsChild = lcs;
                else
                    return root; // we have at least two children with partial lcs solution, the lcs is the current root
            }
        }
        if (lcsChild != null)
            return lcsChild;

        return null; // should not happen
    }


    public static void main(String[] args) {
        /*
                 1
              / |  \
             2  3   4
            /\  |  /| \
           5 6  7 8 9  10
                   / \  |
                  11 12 13
         */

        Node one = new Node(1);
        Node two = new Node(2);
        Node three  = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);
        Node twelve = new Node(12);
        Node thirteen = new Node(13);
        Node root = one;
        one.addChild(two);one.addChild(three);one.addChild(four);
        two.addChild(five);two.addChild(six);
        three.addChild(seven);
        four.addChild(eight);four.addChild(nine);four.addChild(ten);
        nine.addChild(eleven);nine.addChild(twelve);
        ten.addChild(thirteen);
        System.out.println("lowestCommonAncestor("+Arrays.toString(one.children.toArray())+")=" + lowestCommonAncestor(root, one.children));
        Set<Node> set = new HashSet<>();
        set.addAll(two.children);set.addAll(three.children);
        System.out.println("lowestCommonAncestor("+Arrays.toString(set.toArray())+")=" + lowestCommonAncestor(root, set));
        set.clear();
        set.add(eight);set.add(four);set.add(ten);
        System.out.println("lowestCommonAncestor("+Arrays.toString(set.toArray())+")=" + lowestCommonAncestor(root, set));
        set.clear();
        set.add(eight);set.add(twelve);set.add(thirteen);
        System.out.println("lowestCommonAncestor("+Arrays.toString(set.toArray())+")=" + lowestCommonAncestor(root, set));

    }
}
