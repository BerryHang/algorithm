package handwriting.graph;

import java.util.*;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {

        //初始化数组 外层数组的下标代表某个点 ，内层集合代表当前点到其他的数据信息
        List<List<int[]>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        //将点集合数据初始化到数组中
        for (int[] delay : times) {
            list.get(delay[0]).add(new int[]{delay[1], delay[2]});
        }

        //创建优先级队列，排序规则按照延迟时间进行排序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        //先将起始点假如到队列中
        priorityQueue.add(new int[]{k, 0});

        //保存那些点已经处理过了
        boolean[] flag = new boolean[n + 1];

        int count = 0;
        int delay = 0;

        //依次从优先队列中弹出元素
        while (!priorityQueue.isEmpty() && count < n) {
            int[] poll = priorityQueue.poll();

            //已经处理过的节点不进行处理
            if (flag[poll[0]]) {
                continue;
            }
            //处理过的节点数累加，并标记
            count++;
            flag[poll[0]] = true;

            //计算当前的最大延迟时间
            delay = Math.max(delay, poll[1]);

            //遍历相邻节点，看是否可以更新延迟时间
            for (int[] node : list.get(poll[0])) {
                priorityQueue.add(new int[]{node[0], node[1] + delay});
            }

        }

        //如果所有节点都处理过返回延迟时间，反之-1
        return count < n ? -1 : delay;
    }

    public static int networkDelayTime2(int[][] times, int n, int k) {

        //初始化数组 外层数组的下标代表某个点 ，内层集合代表当前点到其他的数据信息
        ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nexts.add(new ArrayList<>());
        }
        //将点集合数据初始化到数组中
        for (int[] delay : times) {
            nexts.get(delay[0]).add(new int[]{delay[1], delay[2]});
        }

        //创建一个加强堆
        Heap heap = new Heap(n);
        heap.add(k, 0);

        int num = 0;
        int max = 0;

        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int cur = record[0];
            int delay = record[1];
            num++;
            max = Math.max(max, delay);
            for (int[] next : nexts.get(cur)) {
                heap.add(next[0], delay + next[1]);
            }
        }

        return num < n ? -1 : max;
    }

    public static class Heap {

        //保存节点是否已经处理过
        public boolean[] used;

        //堆数据 0位置是节点  1位置是延迟时间
        public int[][] heap;

        //节点所在堆中的位置
        public int[] hIndex;

        //当前堆中的数据数量
        public int size;

        //初始堆
        public Heap(int n) {
            used = new boolean[n + 1];
            heap = new int[n + 1][2];
            hIndex = new int[n + 1];
            Arrays.fill(hIndex, -1);
            size = 0;
        }

        //向堆中添加数据
        public void add(int cur, int delay) {

            //已经处理过的节点不做处理
            if (used[cur]) {
                return;
            }

            //如果下标为-1代表需要新增节点，反之更新节点，无论新增还是更新都只向上进行堆排序
            if (hIndex[cur] == -1) {
                heap[size][0] = cur;
                heap[size][1] = delay;
                hIndex[cur] = size;
                heapInsert(size++);
            } else {
                int hi = hIndex[cur];
                if (delay <= heap[hi][1]) {
                    heap[hi][1] = delay;
                    heapInsert(hi);
                }
            }

        }

        //弹出一个元素
        public int[] poll() {
            int[] ans = heap[0];
            swap(0, --size);
            heapify(0);
            used[ans[0]] = true;
            hIndex[ans[0]] = -1;
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void heapInsert(int i) {
            int parent = (i - 1) / 2;
            while (heap[i][1] < heap[parent][1]) {
                swap(i, parent);
                i = parent;
                parent = (i - 1) / 2;
            }
        }

        public void heapify(int i) {
            int l = (i * 2) + 1;
            while (l < size) {
                int smallest = l + 1 < size && heap[l + 1][1] < heap[l][1] ? (l + 1) : l;
                smallest = heap[smallest][1] < heap[i][1] ? smallest : i;
                if (smallest == i) {
                    break;
                }
                swap(smallest, i);
                i = smallest;
                l = (i * 2) + 1;
            }
        }

        private void swap(int i, int j) {
            int[] o1 = heap[i];
            int[] o2 = heap[j];
            int o1hi = hIndex[o1[0]];
            int o2hi = hIndex[o2[0]];
            heap[i] = o2;
            heap[j] = o1;
            hIndex[o1[0]] = o2hi;
            hIndex[o2[0]] = o1hi;
        }

    }

}
