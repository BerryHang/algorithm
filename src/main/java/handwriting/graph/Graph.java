package handwriting.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    //图的点的集合
    Map<Integer, Node> nodeMap;

    //图的边的集合
    List<Edge> edges;

    public Graph() {
        nodeMap = new HashMap<>();
        edges = new ArrayList<>();
    }

}
