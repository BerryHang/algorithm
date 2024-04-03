package handwriting.disjointSet;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumIslandsII {

    public static void main(String[] args) {

        int times = 10000;
        int row = 10;
        int column = 10;

        for (int i = 0; i < times; i++) {
            char[][] chars = generator(row, column);
            int[][] grid = initGrid(chars);
            List<Integer> res1 = numIslandsII2(chars.length, chars[0].length, grid);
            List<Integer> res2 = numIslandsII(chars.length, chars[0].length, grid);

            if (!compare(res1, res2)) {
                System.out.printf("eerr");
            }
        }


    }

    public static boolean compare(List<Integer> res1, List<Integer> res2) {

        if (res1 == null && res2 == null) {
            return true;
        }


        if (res1 != null && res2 != null) {
            if (res1.size() != res2.size()) {
                return false;
            }

            for (int i = 0; i < res1.size(); i++) {
                if (res1.get(i) != res2.get(i)) {
                    return false;
                }
            }
        }

        if (res1 == null) {
            return false;
        }

        if (res2 == null) {
            return false;
        }

        return true;
    }

    public static int[][] initGrid(char[][] chars) {

        List<Pair<Integer, Integer>> list = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (chars[i][j] == '1') {
                    list.add(new Pair<>(i, j));
                }
            }
        }

        int[][] grid = new int[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            Pair<Integer, Integer> pair = list.get(i);
            grid[i][0] = pair.getKey();
            grid[i][1] = pair.getValue();
        }

        return grid;
    }

    public static List<Integer> numIslandsII(int row, int column, int[][] grid) {

        UnionFind unionFind = new UnionFind(row, column);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            res.add(unionFind.add(grid[i][0], grid[i][1]));
        }

        return res;
    }

    public static List<Integer> numIslandsII2(int row, int column, int[][] grid) {

        UnionFindII unionFindII = new UnionFindII();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            res.add(unionFindII.add(grid[i][0], grid[i][1]));
        }

        return res;
    }

    public static class UnionFind {

        //保存所有的节点信息
        int[] help;

        //保存节点和代表节点的映射关系
        int[] parent;

        //保存代表节点对应的结合大小
        int[] size;

        int sizes;

        int column;

        int row;

        //初始化并查集数据,主要生成默认大小的数组信息
        public UnionFind(int row, int column) {
            this.row = row;
            this.column = column;
            int len = row * column;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            sizes = 0;
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

            if (row1 < 0 || row2 < 0 || column1 < 0 || column2 < 0 || row1 >= row || row2 >= row || column1 >= column || column2 >= column) {
                return;
            }

            if (size[row1 * column + column1]==0||size[row2 * column + column2]==0){
                return;
            }

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

        public int add(int r, int c) {

            int index = r * column + c;

            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sizes++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }

            return sizes;
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

    public static class UnionFindII {

        //保存节点和代表节点的映射关系
        Map<String, String> parentMap;

        //保存代表节点对应的结合大小
        Map<String, Integer> sizeMap;

        int size;

        //初始化并查集数据,主要生成默认大小的数组信息
        public UnionFindII() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            size = 0;
        }

        //查询某个节点的代表节点
        private String findFather(String key) {

            //当前找到的节点不是代表节点时，不断循环  只有代表节点的 下标和对应下标存的值是相同的 最终会找到代表节点
            while (key != parentMap.get(key)) {
                key = parentMap.get(key);
            }

            //返回代表接节点
            return key;
        }

        //判断两个节点是否在同一个集合内
        public boolean isSameSet(int row1, int column1, int row2, int column2) {
            return findFather(row1 + "_" + column1) == findFather(row2 + "_" + column2);
        }

        //合并两个集合
        public void union(int row1, int column1, int row2, int column2) {

            //查询要合并的两个节点是否是相同的代表节点，如果相同，那么不需要合并
            String aHead = findFather(row1 + "_" + column1);
            String bHead = findFather(row2 + "_" + column2);

            //如果不相同
            if (aHead != null && bHead != null && aHead != bHead) {

                //获取两个节点对应的集合大小
                if (sizeMap.get(aHead) > sizeMap.get(bHead)) {
                    sizeMap.put(aHead, sizeMap.get(aHead) + sizeMap.get(bHead));
                    parentMap.put(bHead, aHead);
                } else {
                    sizeMap.put(bHead, sizeMap.get(aHead) + sizeMap.get(bHead));
                    parentMap.put(aHead, bHead);
                }

                size--;
            }

        }

        public int add(int r, int c) {

            String key = r + "_" + c;

            if (!sizeMap.containsKey(key)) {
                parentMap.put(key, key);
                sizeMap.put(key, 1);
                size++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }

            return size;
        }

        //返回某个节点所在集合的大小
        public int size(int row, int column) {

            String key = row + "_" + column;

            //找到当前节点的代表节点
            while (key != parentMap.get(key)) {
                key = parentMap.get(key);
            }

            //获取该节点的大小
            return sizeMap.get(key);
        }

        //返回整个数据集合有几个独立的集合
        public int size() {
            return size;
        }
    }

    public static char[][] generator(int row, int column) {

        row = (int) (Math.random() * row + 1);
        column = (int) (Math.random() * column + 1);

        char[][] chars = new char[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                chars[i][j] = Math.random() < 0.5 ? '0' : '1';
            }
        }
        return chars;
    }

}
