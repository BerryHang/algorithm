package handwriting.graph;

import java.util.*;

public class Prim {

    public static List<Edge> primMST(Graph graph) {

        //创建优先队列，按照边的权重进行排序，权重小的靠前
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new WeightComparator());

        //保存已经到过的点的集合
        HashSet<Node> set = new HashSet<>();

        //满足要求的结果集
        List<Edge> res = new ArrayList<>();

        //遍历图中的节点
        for (Node node : graph.nodeMap.values()) {

            //如果当前节点没有遍历到过
            if (!set.contains(node)) {
                //添加此节点
                set.add(node);

                //并将节点相邻的边添加到优先队列中
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }

                //从队列中弹出一个权重小的边
                while (!priorityQueue.isEmpty()) {
                    Edge poll = priorityQueue.poll();
                    Node toNode = poll.to;
                    //如果这条边的终点没有被添加过，则当前边是满足要求的，添加到结果集中
                    //并将点相邻的边都添加到队列中，往复执行
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        res.add(poll);

                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            //正常在一个连通图中，不遍历完所有的节点是不会走到这里的，如果是森林的情况下可以正常进入下一个循环，这里添加break语句只会遍历一个联通图
            //break;
        }

        return res;
    }

    public static class WeightComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight = o2.weight;
        }
    }

    /**
     * prim算法求最小生成树的权重只和
     *
     * @param graph 二维数字  graph[i][j] 代表 i 点到j点的距离  二维数组为一个正方形的矩阵，对角线都是自己到自己的距离为0
     *              因此是按照对角线对称的，只需要使用矩阵的一半就好了
     * @return
     */
    public static int primMST(int[][] graph) {

        int size = graph.length;
        //保存出发点到其他各个
        int[] distances = new int[size];
        boolean[] visited = new boolean[size];

        //以第一个点为出发点，计算出第一个点到所有点的距离，并标记第一个点已经为最小距离
        visited[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }

        int sum = 0;

        //从第二个元素开始
        for (int i = 1; i < size; i++) {

            int minDistance = Integer.MAX_VALUE;
            int minIndex = -1;
            //遍历没有所有没有被标记的元素中距离最短的
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    minIndex = j;
                }
            }

            //如果最小的下标是-1 代表没有能够到达的节点了
            if (minIndex == -1) {
                return sum;
            }

            //把当前距离最小的下标标记为已经访问过，并更新距离和
            visited[minIndex] = true;
            sum += minDistance;

            //循环节点，看从当前最小节点出发是否有需要更新距离的
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }

        return sum;
    }

}
