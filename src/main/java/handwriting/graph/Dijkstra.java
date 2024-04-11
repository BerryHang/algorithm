package handwriting.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dijkstra {

    //暴力破解
    public static HashMap<Node, Integer> dijkstra(Node from) {

        //定义结果集的map集合，并先把起始点放进去
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);

        //存放已经处理好的节点列表
        HashSet<Node> set = new HashSet<>();

        //获取当前状态下距离最小的点
        Node minNode = getMinDistanceNode(distanceMap, set);

        //如果节点不为空，证明还有可到的点没有计算
        while (minNode != null) {

            //获取当前点目前的距离信息
            int minDistance = distanceMap.get(minNode);

            //遍历当前点的所有边，判断其到相邻点的距离
            for (Edge edge : minNode.edges) {

                //如果当前距离表中没有数据，代表之前不可达，直接添加数据，如果存在，更新节点的距离  比较之前存储的距离和通过当前边来到次点的距离 取小值
                if (!distanceMap.containsKey(edge.to)) {
                    distanceMap.put(edge.to, edge.weight + minDistance);
                } else {
                    distanceMap.put(edge.to, Math.min(distanceMap.get(edge.to), edge.weight + minDistance));
                }

            }
            //标记当前节点已经处理，并获取下一个最小的点
            set.add(minNode);
            minNode = getMinDistanceNode(distanceMap, set);
        }

        return distanceMap;
    }

    //获取当前最小的点
    public static Node getMinDistanceNode(HashMap<Node, Integer> distanceMap, HashSet<Node> set) {

        //定义最小节点和最短距离
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        //遍历距离表中没有处理过，且当前距离最小的点
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!set.contains(entry.getKey()) && entry.getValue() < minDistance) {
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }

        return minNode;
    }

    public static class CustomHeap {

        Node[] nodes;
        Map<Node, Integer> indexMap = new HashMap<>();
        Map<Node, Integer> distanceMap = new HashMap<>();

        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public CustomHeap(int size) {
            this.size = size;
            nodes = new Node[size];
        }

        public NodeRecord pop() {

            Node node = nodes[0];
            Integer distance = distanceMap.get(nodes[0]);
            swap(0, size - 1);

            indexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return new NodeRecord(node, distance);
        }

        public void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {

                int smallIndex = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;

                smallIndex = distanceMap.get(nodes[smallIndex]) < distanceMap.get(nodes[index]) ? smallIndex : index;

                if (smallIndex == index) {
                    return;
                }

                swap(index, smallIndex);
                index = smallIndex;

                left = index * 2 + 1;
            }
        }

        //从堆的下方向上调整
        public void heapInsert(int index) {
            //如果自己比父节小就和父节点交换，直到父节点比自己小或者已经到堆顶
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //交换两个节点，不仅交换数组中位置，还有更新下标映射关系的map
        public void swap(int index1, int index2) {
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
            indexMap.put(nodes[index1], index1);
            indexMap.put(nodes[index2], index2);
        }

        //对堆节点进行操作，已经存在的节点进行更新，没有的节点进行新增
        public void operateNode(Node node, int distance) {

            //更新已存在的节点
            if (indexMap.containsKey(node) && indexMap.get(node) != -1) {
                distanceMap.put(node, distance);
                //这里的距离只能越更新越小，因此只向上调整
                heapInsert(indexMap.get(node));
            }

            //新增不存在的接节点
            if (!indexMap.containsKey(node)) {
                indexMap.put(node, size);
                nodes[size] = node;
                distanceMap.put(node, distance);
                heapInsert(size++);
            }

        }

    }

    //封装节点信息，包含节点和出发节点到当前节点的距离
    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static HashMap<Node, Integer> dijkstraByHeap(Node from, int size) {

        //定义结果集的map集合，并先把起始点放进去
        HashMap<Node, Integer> resultMap = new HashMap<>();

        //根据节点数量创建小顶对，并将出发节点添加到堆中
        CustomHeap customHeap = new CustomHeap(size);
        customHeap.operateNode(from, 0);

        //当堆中不为空时，进行操作
        while (!customHeap.isEmpty()) {

            //弹出堆顶元素
            NodeRecord pop = customHeap.pop();
            //遍历其所有的边并更新最短距离
            for (Edge edge : pop.node.edges) {
                customHeap.operateNode(edge.to, pop.distance + edge.weight);
            }

            //添加到结果集中
            resultMap.put(pop.node, pop.distance);
        }

        return resultMap;
    }

}
