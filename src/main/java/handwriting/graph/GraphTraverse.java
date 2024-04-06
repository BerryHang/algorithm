package handwriting.graph;

import java.util.*;

public class GraphTraverse {

    public static void main(String[] args) {

        int times = 1000;
        int wight = 1000;
        int nodes = 8;
        for (int i = 0; i < times; i++) {
            int[][] matrix = GraphGenerator.generator(wight, nodes);
            Graph graph = GraphGenerator.generatorGraph(matrix);

            Node node = graph.nodeMap.get(1);
            graphDFS(node);
            System.out.println("");
            graphDFSRecursive(node);
            System.out.println("");
            graphBFS(node);
            System.out.println("");

        }

    }

    public static void graphDFS(Node root) {
        Stack<Node> stack = new Stack();
        HashSet<Node> set = new HashSet<>();
        if (root == null) {
            return;
        }
        stack.add(root);
        set.add(root);
        System.out.print(root.val + " ");
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node node : pop.next) {
                if (!set.contains(node)) {
                    stack.push(pop);
                    stack.push(node);
                    set.add(node);
                    System.out.printf(node.val + " ");
                    break;
                }
            }
        }
    }

    public static void graphDFSRecursive(Node node) {
        if (node == null) {
            return;
        }
        process(node, new HashSet<>());
    }

    public static void process(Node node, Set<Node> set) {

        if (node == null) {
            return;
        }

        if (!set.contains(node)) {
            System.out.printf(node.val + " ");
            set.add(node);
        }

        for (Node next : node.next) {
            if (!set.contains(next)) {
                process(next, set);
            }
        }

    }

    public static void graphBFS(Node node) {
        Queue<Node> queue = new LinkedList();
        HashSet<Node> set = new HashSet<>();
        if (node == null) {
            return;
        }
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                System.out.printf(poll.val + " ");
                for (Node n : poll.next) {
                    if (!set.contains(n)) {
                        queue.add(n);
                        set.add(n);
                    }
                }
            }
        }
    }

}
