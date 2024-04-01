package handwriting.disjointSet;

import java.util.ArrayList;
import java.util.List;

public class NumIslands {

    public static void main(String[] args) {

        int times = 10000;
        int row = 20;
        int column = 20;

        for (int i = 0; i < times; i++) {
            char[][] chars = generator(row, column);
            int count1 = countByDisjointSet(chars);
            int count2 = countByDisjointSetPro(chars);
            int count3 = numIslands(chars);

            if ((count1 != count3) || (count2 != count3)) {
                System.out.printf("eerr");
            }
        }


    }

    public static int countByDisjointSetPro(char[][] grid) {
        if (grid == null) {
            return 0;
        }

        int row = grid.length;
        int column = grid[0].length;

        DisjointSetPro disjointSet = new DisjointSetPro(grid);

        for (int i = 1; i < row; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                disjointSet.union(i - 1, 0, i, 0);
            }
        }

        for (int i = 1; i < column; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                disjointSet.union(0, i - 1, 0, i);
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {

                if (grid[i][j] == '1') {

                    if (grid[i - 1][j] == '1') {
                        disjointSet.union(i - 1, j, i, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        disjointSet.union(i, j - 1, i, j);
                    }
                }
            }
        }

        return disjointSet.size();
    }

    public static int countByDisjointSet(char[][] grid) {
        if (grid == null) {
            return 0;
        }

        int row = grid.length;
        int column = grid[0].length;
        Dot[][] dots = new Dot[row][column];
        List<Dot> dotList = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    Dot dot = new Dot();

                    dots[i][j] = dot;
                    dotList.add(dot);
                }

            }
        }

        DisjointSet<Dot> disjointSet = new DisjointSet<>(dotList);

        //此处使用了三次循环处理，其实可以使用一个，但是判断会比较复杂，前面两个循环分别处理的第一行和第一列的数据，到后面的双层循环时就没有了越界情况
        for (int i = 1; i < row; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                disjointSet.union(dots[i - 1][0], dots[i][0]);
            }
        }

        for (int i = 1; i < column; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                disjointSet.union(dots[0][i - 1], dots[0][i]);
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {

                if (grid[i][j] == '1') {

                    if (grid[i - 1][j] == '1') {
                        disjointSet.union(dots[i - 1][j], dots[i][j]);
                    }
                    if (grid[i][j - 1] == '1') {
                        disjointSet.union(dots[i][j - 1], dots[i][j]);
                    }
                }
            }
        }

        return disjointSet.size();
    }

    public static int numIslands(char[][] grid) {

        if (grid == null) {
            return 0;
        }

        int sum = 0;
        int row = grid.length;
        int column = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //每发现一个1就进行加一操作，并进行递归判断，已经被递归过的1不在进入这里的if里面 ，因为底层的数据已经发生改变
                if (grid[i][j] == '1') {
                    sum++;
                    infect(grid, i, j);
                }

            }
        }

        return sum;
    }

    public static void infect(char[][] grid, int row, int column) {

        if (row < 0 || column < 0 || row >= grid.length || column >= grid[0].length) {
            return;
        }

        //如果当前节点的值是 1 那么进行递归感染
        if (grid[row][column] == '1') {
            //这里特别注意把目标值改成了2 ，改值是有必要的，不然递归是无法结束，会不断的判断已经判断过的位置
            grid[row][column] = '2';
            infect(grid, row - 1, column);
            infect(grid, row + 1, column);
            infect(grid, row, column - 1);
            infect(grid, row, column + 1);
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
