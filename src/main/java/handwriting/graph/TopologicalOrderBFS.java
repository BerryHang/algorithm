package handwriting.graph;

import java.util.*;

//广度优先实现图的拓扑排序
public class TopologicalOrderBFS {

    //图中的节点信息
    public static class DirectedGraphNode {

        //节点的值
        public int label;

        //节点的相邻节点
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    //对图进行拓扑排序
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        //创建一个Map key保存节点信息  value 代表节点的入度
        Map<DirectedGraphNode, Integer> map = new HashMap<>();

        //先将点一次添加到map中，并初始化入度为0
        for (DirectedGraphNode node : graph) {
            map.put(node, 0);
        }

        //遍历所有节点的相邻节点信息，并累加入度次数
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.get(neighbor) + 1);
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList();

        //将所有入度为0的节点添加到队列中
        for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        //遍历队里
        while (!queue.isEmpty()) {
            //从队列中弹出数据
            DirectedGraphNode poll = queue.poll();
            res.add(poll);

            //并对其所有的相邻节点，遍历，入度次数减一，如果为0，添加到队列中
            for (DirectedGraphNode neighbor : poll.neighbors) {
                map.put(neighbor, map.get(neighbor) - 1);
                if (map.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return res;
    }

}
