package handwriting.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoincidentLineSegment {

    public static void main(String[] args) {

        int length = 10;
        int testTimes = 10000;
        int min = 0;
        int max = 100;

        for (int i = 0; i < testTimes; i++) {

            Line[] lines = generate((int) (Math.random() * length + 1), min, max);

            Line[] lines1 = copy(lines);

            Line[] lines2 = copy(lines);

            int count1 = commonMethod(lines1);

            int count2 = advanceMethod(lines2);

            if (count1 != count2) {

                System.out.println("出错 count1: " + count1 + " count2: " + count2);

                print(lines);

                break;
            }

        }
    }

    public static int advanceMethod(Line[] lines) {

        //将所有的线段按照起点进行排序
        Arrays.sort(lines, new LineComparable());

        //定义一个小根堆
        PriorityQueue<Integer> queue = new PriorityQueue();

        int maxCount = 0;

        //直接循环所有线段
        for (int i = 0; i < lines.length; i++) {
            //判断堆顶元素是否，小于等于自己，如果小于则弹出
            while (!queue.isEmpty() && queue.peek() <= lines[i].start) {
                queue.poll();
            }
            //添加线段自己的终点
            queue.offer(lines[i].end);
            //获取包含当前起点的所有线段数量，并判断是否需要更新最大重复数
            maxCount = Math.max(maxCount, queue.size());
        }

        return maxCount;
    }

    public static class LineComparable implements Comparator<Line> {

        @Override
        public int compare(Line l1, Line l2) {
            return l1.start - l2.start;
        }

    }


    public static int commonMethod(Line[] lines) {

        //取得线段最小的起点值和最大的终点值
        int min = 0;
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i].start);
            max = Math.max(max, lines[i].end);
        }

        int maxCount = 0;

        //从最小值开始遍历到最大值
        for (double i = min + 0.1; i < max; i++) {
            int count = 0;

            //遍历所有的线段
            for (int j = 0; j < lines.length; j++) {

                //判断线段的区间是否包含这个数字
                if (i > lines[j].start && i < lines[j].end) {
                    count++;
                }
            }

            //确认是否更新最大重叠数
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }


    public static class Line {

        //线段起点
        int start;

        //线段终点
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Line[] copy(Line[] lines) {

        Line[] copyLines = new Line[lines.length];

        for (int i = 0; i < lines.length; i++) {
            copyLines[i] = new Line(lines[i].start, lines[i].end);
        }

        return copyLines;
    }

    public static Line[] generate(int length, int min, int max) {

        length = (int) (Math.random() * length + 1);

        Line[] lines = new Line[length];

        for (int i = 0; i < length; i++) {

            int start = (int) (Math.random() * (max - min) + min);

            int end = (int) (Math.random() * (max - min) + min + 1);
            while (start >= end) {
                end = (int) (Math.random() * (max - min) + min + 1);
            }

            lines[i] = new Line(start, end);

        }
        return lines;
    }

    public static void print(Line[] lines) {

        for (int i = 0; i < lines.length; i++) {
            System.out.println("[" + lines[i].start + "," + lines[i].end + "]");
        }

    }

}
