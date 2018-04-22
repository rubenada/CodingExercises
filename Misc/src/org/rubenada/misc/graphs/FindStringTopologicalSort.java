package org.rubenada.misc.graphs;

import java.util.*;

/**
 * Problem taken from: https://www.codewars.com/kata/recover-a-secret-string-from-random-triplets
 *
 * There is a secret string which is unknown to you. Given a collection of random triplets from the string, recover the original string.
 * A triplet here is defined as a sequence of three letters such that each letter occurs somewhere before the next in the given string,
 * e.g. "whi" is a triplet for the string "whatisup".
 * As a simplification, you may assume that no letter occurs more than once in the secret string.
 * You can assume nothing about the triplets given to you other than that they are valid triplets and that they contain sufficient information
 * to deduce the original string. In particular, this means that the secret string will never contain letters that do not occur in one of the triplets given to you.
 */
public class FindStringTopologicalSort {

    public static String calculateString (char[][] triplets) {

        // convert triplets in directed graphs, get topological sort, that will be our answer
        Graph<Character> graph = new Graph<>();

        for (char[] triplet : triplets) {
            for (int i = 0; i < triplet.length - 1; i++) {
                char a = triplet[i];
                char b = triplet[i + 1];
                // insert into graphs: a --> b
                graph.addEdge(a, b);

            } // end for i
        } // end for triplet

        // get topological sort of the graphs
        List<Character> sol = graph.getTopologicalSort();

        // get equivalent String
        StringBuilder res = new StringBuilder();
        for (Character c : sol)
            res.append(c);

        return res.toString();
    }


    // auxiliary class Graph
    public static class Graph<T> {
        private List<Node<T>> nodes;

        public Graph() {
            nodes = new ArrayList<>();
        }

        public Node<T> addNode(T t) {
            for (Node<T> n : nodes) {
                if (n.getInfo().equals(t))
                    return n; //NOOP
            }

            Node<T> node = new Node<T>(t);
            nodes.add(node);
            return node;
        }

        public boolean addEdge(T t1, T t2) {
            Node<T> n1 = addNode(t1);
            Node<T> n2 = addNode(t2);
            n1.addEdge(n2);
            return true;
        }


        public List<T> getTopologicalSort() {
            List<T> l = new LinkedList<>();
            Set<Node<T>> visited = new HashSet<>();

            while (visited.size() != nodes.size()) {
                Node n = null;
                for (int i = 0; i < nodes.size(); i++) {
                    n = nodes.get(i);
                    if (!visited.contains(n))
                        break;
                    else
                        n = null;
                }
                if (n == null)
                    return null; // should never happen

                visit(n, visited, l);
            }

            return l;
        }

        private void visit(Node<T> n, Set<Node<T>> visited, List<T> l) {
            if (visited.contains(n))
                return;

            for (Node<T> m : n.getAdj())
                visit(m, visited, l);

            visited.add(n);
            l.add(0, n.getInfo());
        }
    }

    // auxiliary class Node
    public static class Node<T> {
        private T info;
        private List<Node<T>> adj;

        public Node(T t) {
            this.info = t;
            this.adj = new ArrayList<>();
        }

        public T getInfo() {
            return info;
        }

        public List<Node<T>> getAdj() {
            return adj;
        }

        public boolean addEdge(Node<T> n2) {
            for (Node<T> i : adj) {
                if (i.getInfo().equals(n2.getInfo()))
                    return false; //NOOP
            }
            adj.add(n2);
            return true;
        }
    }


    public static void main (String[] args) {
        char[][] triplets = {
                {'t','u','p'},
                {'w','h','i'},
                {'t','s','u'},
                {'a','t','s'},
                {'h','a','p'},
                {'t','i','s'},
                {'w','h','s'}
        };

        System.out.println("calculateString = " + calculateString(triplets));
    }

}