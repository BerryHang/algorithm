package handwriting.graph;

import java.util.*;

public class TopologicalOrderDFS2 {

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

    //保存节点和深度的对应关系
    public static class Record {

        DirectedGraphNode node;

        long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    //排序操作
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        //节点及深度的映射关系
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();

        //循环所有节点，并递归计算节点深度
        for (DirectedGraphNode node : graph) {
            calculateNodes(node, order);
        }

        //拿到所有节点那及其可达节点数信息，进行排序
        List<Record> records = new ArrayList<>();

        for (Record record : order.values()) {
            records.add(record);
        }

        records.sort(new NodesComparator());

        //遍历输出
        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        for (Record record : records) {
            res.add(record.node);
        }

        return res;
    }

    public static class NodesComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes - o2.nodes > 0 ? -1 : 1);
        }
    }

    public static Record calculateNodes(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order) {

        //如果已经计算过深度，直接返回节点信息
        if (order.containsKey(node)) {
            return order.get(node);
        }
        long nodes = 0;

        //遍历相邻节点递归进行可以到达的节点数总和
        for (DirectedGraphNode neighbor : node.neighbors) {
            nodes += calculateNodes(neighbor, order).nodes;
        }

        //构建当前接节点的节点数信息
        Record record = new Record(node, nodes + 1);
        order.put(node, record);
        return record;
    }

}
