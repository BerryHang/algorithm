package handwriting.graph;

import java.util.*;

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

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        Map<DirectedGraphNode, Integer> map = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            map.put(node, 0);
        }

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.get(neighbor) + 1);
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList();

        for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            DirectedGraphNode poll = queue.poll();
            res.add(poll);

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
