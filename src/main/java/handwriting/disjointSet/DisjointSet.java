package handwriting.disjointSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DisjointSet<T> {

    //保存所有的节点信息
    Map<T, Node<T>> nodes;

    //保存节点和代表节点的映射关系
    Map<Node<T>, Node<T>> parentMap;

    //保存代表节点对应的结合大小
    Map<Node<T>, Integer> sizeMap;

    //初始化并查集数据，默认初始化时，每个节点都是独立的集合，代表节点都是自己，并且集合大小都为1
    public DisjointSet(List<T> values) {
        this.nodes = new HashMap<>();
        this.parentMap = new HashMap<>();
        this.sizeMap = new HashMap<>();

        for (T value : values) {
            Node<T> node = new Node<>(value);
            nodes.put(value, node);
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    //查询某个节点的代表节点
    private Node findFather(Node node) {

        //保存查找父级的路径
        Stack<Node> stack = new Stack<>();

        //当前找到的节点不是代表节点时，不断循环  只有代表节点的 key 和 value 是相同的 最终会找到代表节点
        while (node != parentMap.get(node)) {
            stack.push(node);
            node = parentMap.get(node);
        }

        //将节点和代表节点的映射关系进行更新，以便下次查询速度更快
        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), node);
        }

        //返回代表接节点
        return node;
    }

    //判断两个节点是否在同一个集合内
    public boolean isSameSet(T a, T b) {
        return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    //合并两个集合
    public void union(T a, T b) {

        //查询要合并的两个节点是否是相同的代表节点，如果相同，那么不需要合并
        Node aHead = findFather(nodes.get(a));
        Node bHead = findFather(nodes.get(b));

        //如果不相同
        if (aHead != bHead) {

            //获取两个节点对应的集合大小
            Integer aSize = sizeMap.get(aHead);
            Integer bSize = sizeMap.get(bHead);

            Node big = aSize > bSize ? aHead : bHead;
            Node small = aSize > bSize ? bHead : aHead;

            //将小的集合挂在大的集合下
            parentMap.put(small, big);

            //更新大集合的大小
            sizeMap.put(big, aSize + bSize);
            //移除小集合的大小信息
            sizeMap.remove(small);

        }

    }

    //返回某个节点所在集合的大小
    public int size(Node node) {

        //找到当前节点的代表节点
        while (node != parentMap.get(node)) {
            node = parentMap.get(node);
        }

        //获取该节点的大小
        return sizeMap.get(node);
    }

    //返回整个数据集合有几个独立的集合
    public int size() {
        return sizeMap.size();
    }
}
