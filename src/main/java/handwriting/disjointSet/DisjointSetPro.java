package handwriting.disjointSet;

public class DisjointSetPro {

    //保存所有的节点信息
    int[] help;

    //保存节点和代表节点的映射关系
    int[] parent;

    //保存代表节点对应的结合大小
    int[] size;

    int sizes;

    int column;

    //初始化并查集数据，默认初始化时，每个节点都是独立的集合，代表节点都是自己，并且集合大小都为1
    public DisjointSetPro(char[][] grid) {

        int row = grid.length;
        column = grid[0].length;

        parent = new int[row * column];
        size = new int[row * column];
        help = new int[row * column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (grid[i][j] == '1') {
                    int index = i * column + j;
                    parent[index] = index;
                    size[index] = 1;
                    sizes++;
                }

            }
        }
    }

    //查询某个节点的代表节点
    private int findFather(int index) {

        //保存查找父级的路径
        int i = 0;

        //当前找到的节点不是代表节点时，不断循环  只有代表节点的 下标和对应下标存的值是相同的 最终会找到代表节点
        while (index != parent[index]) {
            help[i++] = index;
            index = parent[index];
        }

        //将节点和代表节点的映射关系进行更新，以便下次查询速度更快
        for (i--; i >= 0; i--) {
            parent[help[i]] = index;
        }

        //返回代表接节点
        return index;
    }

    //判断两个节点是否在同一个集合内
    public boolean isSameSet(int row1, int column1, int row2, int column2) {
        return findFather(row1 * column + column1) == findFather(row2 * column + column2);
    }

    //合并两个集合
    public void union(int row1, int column1, int row2, int column2) {

        //查询要合并的两个节点是否是相同的代表节点，如果相同，那么不需要合并
        int aHead = findFather(row1 * column + column1);
        int bHead = findFather(row2 * column + column2);

        //如果不相同
        if (aHead != bHead) {

            //获取两个节点对应的集合大小
            if (size[aHead] > size[bHead]) {
                size[aHead] += size[bHead];
                parent[bHead] = aHead;
            } else {
                size[bHead] += size[aHead];
                parent[aHead] = bHead;
            }

            sizes--;
        }

    }

    //返回某个节点所在集合的大小
    public int size(int row, int column) {

        int index = row * this.column + column;

        //找到当前节点的代表节点
        while (index != parent[index]) {
            index = parent[index];
        }

        //获取该节点的大小
        return size[index];
    }

    //返回整个数据集合有几个独立的集合
    public int size() {
        return sizes;
    }
}
