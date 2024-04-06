package handwriting.graph;

//图中的边
public class Edge {

    //边的权重
    int wight;

    //边的起始节点
    Node from;

    //边的结束节点
    Node to;
    public Edge(int wight, Node from, Node to) {
        this.wight = wight;
        this.from = from;
        this.to = to;
    }
}
