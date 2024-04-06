package handwriting.graph;

import java.util.ArrayList;
import java.util.List;

//图中的点
public class Node {

    //当前点指向的下一个节点集合
    List<Node> next;

    //点的出度
    int in;

    //点的入度
    int out;

    //该点对应的边
    List<Edge> edges;

    int val;

    public Node(int val) {
        this.val = val;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
