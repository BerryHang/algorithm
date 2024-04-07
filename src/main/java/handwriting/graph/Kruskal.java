package handwriting.graph;

import java.util.*;

public class Kruskal {

    //并查集对象
    public static class UnionFind {

        //保存节点和父节点关系
        public Map<Node, Node> parentMap;

        //保存节点大小
        public Map<Node, Integer> nodeSize;

        //判断两个节点在不在一个集合里
        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        //查询某个节点所在集合的代表节点
        public Node findFather(Node node) {

            //保存查询代表节点时经过的路径
            Stack<Node> stack = new Stack<>();

            //当节点的代表节点不是自己时，一直往上找
            while (node != parentMap.get(node)) {
                stack.push(node);
                node = parentMap.get(node);
            }

            //对路径上的节点设置代表节点
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), node);
            }

            //返回代表节点
            return node;
        }

        //合并两个节点
        public void union(Node a, Node b) {

            if (a == null || b == null) {
                return;
            }

            //查询两个节点的代表节点
            Node aFather = findFather(a);
            Node bFather = findFather(b);

            //如果两个节点的代表节点不同时，证明不在一个集合内，可以合并
            if (aFather != bFather) {

                //获取节点大小，始终把小的节点挂到大的上
                int aSize = nodeSize.get(aFather);
                int bSize = nodeSize.get(aFather);

                if (aSize > bSize) {
                    //改变小集合的父节点
                    parentMap.put(b, aFather);
                    //改变大集合的大小
                    nodeSize.put(aFather, aSize + bSize);
                    //移除小节点的大小
                    nodeSize.remove(bFather);
                } else {
                    parentMap.put(a, bFather);
                    nodeSize.put(bFather, aSize + bSize);
                    nodeSize.remove(aFather);
                }

            }
        }

        //初始化所有接节点
        public void init(Collection<Node> nodes) {
            for (Node node : nodes) {
                parentMap.put(node, node);
                nodeSize.put(node, 1);
            }
        }

        //无参构造，不是必要的
        public UnionFind() {
            this.parentMap = new HashMap<>();
            this.nodeSize = new HashMap<>();
        }
    }

    //比较器，边权重小的排在前面
    public static class WeightComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    //使用kruskal算法求最小生成树
    public static Set<Edge> kruskalMST(Graph graph) {

        //初始化并查集对象，并初始化节点数据
        UnionFind unionFind = new UnionFind();
        unionFind.init(graph.nodeMap.values());

        //将所有的边放到优先队列中，权重小的排在前面
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new WeightComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }

        Set<Edge> set = new HashSet<>();

        //当优先级队列不为空时
        while (!priorityQueue.isEmpty()) {

            //弹出一条边，判断两个端点在不在一个集合内，不在时把边添加到结果集中，并合并两个端点
            Edge edge = priorityQueue.poll();
            Node from = edge.from;
            Node to = edge.to;

            if (!unionFind.isSameSet(from, to)) {
                set.add(edge);
                unionFind.union(from, to);
            }

        }

        return set;
    }

}
