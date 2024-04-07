package handwriting.graph;

import java.util.*;

public class TopologicalOrderDFS {

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

        int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    //排序操作
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        //节点及深度的映射关系
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();

        //循环所有节点，并递归计算节点深度
        for (DirectedGraphNode node : graph) {
            calculateDeep(node, order);
        }

        //拿到所有接地那及其深度信息，进行排序
        List<Record> records = new ArrayList<>();

        for (Map.Entry<DirectedGraphNode, Record> entry : order.entrySet()) {
            records.add(entry.getValue());
        }

        records.sort(new DeepComparator());

        //遍历输出
        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        for (Record record : records) {
            res.add(record.node);
        }

        return res;
    }

    public static class DeepComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    public static Record calculateDeep(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order) {

        //如果已经计算过深度，直接返回节点信息
        if (order.containsKey(node)) {
            return order.get(node);
        }
        int deep = 0;

        //遍历相邻节点递归进行深度计算
        for (DirectedGraphNode neighbor : node.neighbors) {
            deep = Math.max(deep, calculateDeep(neighbor, order).deep);
        }

        //构建当前接节点的深度信息
        Record record = new Record(node, deep + 1);
        order.put(node, record);
        return record;
    }

}
