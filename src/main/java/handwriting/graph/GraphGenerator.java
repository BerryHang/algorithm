package handwriting.graph;

import java.util.HashSet;
import java.util.Set;

public class GraphGenerator {
    static Set<Integer> node = new HashSet<>();

    public static void main(String[] args) {
        int times = 1000;
        int wight = 1000;
        int nodes = 5;
        for (int i = 0; i < times; i++) {
            int[][] matrix = generator(wight, nodes);
            Graph graph = generatorGraph(matrix);
        }
    }

    public static int[][] generator(int wight, int nodes) {
        Set<String> set = new HashSet<>();
        nodes = (int) ((Math.random() * nodes) + 2);
        int edges = (int) (Math.random() * (nodes * (nodes - 1)) + 1);

        int[][] matrix = new int[edges][3];

        for (int i = 0; i < matrix.length; i++) {

            int from = (int) (Math.random() * nodes + 1);
            int to = (int) (Math.random() * nodes + 1);

            while (set.contains(from + "" + to) || from == to) {
                from = (int) (Math.random() * nodes + 1);
                to = (int) (Math.random() * nodes + 1);
            }

            set.add(from + "" + to);
            node.add(from);
            node.add(to);
            matrix[i][0] = (int) (Math.random() * wight + 1);
            matrix[i][1] = from;
            matrix[i][2] = to;
        }
        return matrix;
    }

    public static Graph generatorGraph(int[][] matrix) {
        Graph graph = new Graph();

        //循环每一组边的数据
        for (int i = 0; i < matrix.length; i++) {

            int wight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            //查询当前节点有没有进行初始化，如果没有初始化进行初始化操作
            if (!graph.nodeMap.containsKey(from)) {
                graph.nodeMap.put(from, new Node(from));
            }
            if (!graph.nodeMap.containsKey(to)) {
                graph.nodeMap.put(to, new Node(to));
            }

            //拿出对应接地那
            Node fromNode = graph.nodeMap.get(from);
            Node toNode = graph.nodeMap.get(to);

            //时候节点信息和权重创建边信息
            Edge edge = new Edge(wight, fromNode, toNode);

            //边的起始接地那的出度累加 并添加边信息和下一个节点信息
            fromNode.out++;
            fromNode.next.add(toNode);
            fromNode.edges.add(edge);

            //对边的结束节点的入度进行累加
            toNode.in++;

            graph.edges.add(edge);
        }

        return graph;
    }

}
