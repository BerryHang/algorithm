package handwriting.graph;

import java.util.*;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<int[]>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] delay : times) {
            list.get(delay[0]).add(new int[]{delay[1], delay[2]});
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        priorityQueue.add(new int[]{k, 0});

        boolean[] flag = new boolean[n + 1];

        int count = 0;
        int delay = 0;

        while (!priorityQueue.isEmpty() && count < n) {
            int[] poll = priorityQueue.poll();

            if (flag[poll[0]]) {
                continue;
            }
            count++;
            flag[poll[0]] = true;

            delay = Math.max(delay, poll[1]);

            for (int[] node : list.get(poll[0])) {
                priorityQueue.add(new int[]{node[0], node[1] + delay});
            }

        }

        return count < n ? -1 : delay;
    }

    public static void main(String[] args) {
        networkDelayTime2(new int[][]{{4, 9, 29}, {8, 15, 56}, {11, 5, 44}, {15, 14, 33}, {9, 10, 48}, {4, 15, 9}, {8, 4, 40}, {14, 2, 76}, {7, 5, 13}, {2, 13, 94}, {9, 4, 50}, {11, 15, 46}, {3, 14, 79}, {11, 6, 28}, {5, 13, 6}, {6, 11, 87}, {11, 4, 63}, {3, 4, 56}, {13, 12, 29}, {14, 1, 31}, {1, 7, 77}, {3, 5, 9}, {5, 11, 41}, {10, 2, 30}, {15, 3, 12}, {3, 13, 12}, {13, 8, 20}, {4, 6, 93}, {15, 7, 8}, {7, 13, 53}, {14, 13, 13}, {14, 10, 91}, {8, 3, 37}, {2, 12, 79}, {9, 1, 44}, {2, 4, 88}, {9, 2, 61}, {6, 8, 66}, {3, 1, 93}, {11, 13, 90}, {3, 12, 90}, {13, 3, 47}, {7, 2, 53}, {3, 8, 51}, {4, 11, 91}, {12, 5, 5}, {10, 12, 71}, {10, 8, 60}, {15, 2, 42}, {10, 6, 66}, {5, 4, 28}, {8, 7, 21}, {12, 2, 10}, {4, 13, 6}, {8, 2, 11}, {1, 15, 98}, {9, 6, 47}, {6, 1, 14}, {4, 12, 83}, {2, 9, 41}, {13, 7, 99}, {5, 8, 62}, {13, 9, 24}, {4, 5, 92}, {10, 1, 47}, {6, 12, 30}, {9, 8, 46}, {1, 4, 69}, {13, 1, 57}, {9, 12, 14}, {2, 1, 83}, {6, 10, 60}, {9, 14, 4}, {2, 7, 59}, {10, 9, 46}, {6, 2, 89}, {12, 11, 60}, {3, 15, 33}, {10, 11, 16}, {4, 1, 7}, {3, 10, 74}, {4, 10, 56}, {9, 11, 98}, {14, 15, 16}, {1, 5, 3}, {2, 14, 93}, {15, 9, 54}, {12, 8, 88}, {12, 10, 40}, {11, 9, 1}, {7, 11, 72}, {8, 6, 59}, {1, 12, 69}, {5, 6, 15}, {1, 13, 3}, {10, 7, 32}, {6, 9, 61}, {11, 1, 83}, {11, 2, 87}, {1, 8, 17}, {4, 8, 33}, {10, 3, 95}, {14, 8, 94}, {13, 4, 3}, {7, 10, 87}, {8, 5, 50}, {14, 4, 17}, {1, 2, 10}, {15, 11, 3}, {7, 4, 14}, {8, 14, 69}, {5, 14, 95}, {15, 6, 60}, {3, 2, 65}, {1, 9, 67}, {3, 6, 3}, {14, 7, 41}, {11, 3, 15}, {1, 10, 37}, {8, 9, 66}, {5, 15, 11}, {12, 7, 33}, {1, 14, 63}, {13, 2, 72}, {7, 14, 27}, {2, 15, 30}, {2, 6, 59}, {6, 4, 46}, {12, 14, 12}, {14, 3, 47}, {14, 6, 10}, {7, 3, 3}, {14, 9, 48}, {7, 6, 86}, {13, 11, 29}, {8, 12, 44}, {14, 5, 8}, {12, 4, 53}, {4, 14, 63}, {2, 11, 92}, {12, 9, 62}, {13, 15, 40}, {2, 5, 72}, {11, 10, 77}, {2, 8, 65}, {6, 15, 64}, {6, 3, 2}, {10, 14, 93}, {5, 1, 50}, {13, 5, 22}, {15, 5, 84}, {14, 12, 29}, {15, 4, 91}, {9, 13, 13}, {5, 12, 51}, {10, 15, 15}, {4, 3, 32}, {7, 8, 38}, {13, 6, 26}, {9, 7, 73}, {13, 14, 41}, {12, 13, 45}, {5, 2, 12}, {1, 3, 54}, {5, 9, 7}, {7, 1, 94}, {2, 10, 67}, {1, 11, 95}, {7, 9, 4}, {11, 12, 56}, {3, 7, 56}, {5, 7, 72}, {6, 7, 17}, {12, 6, 46}, {5, 3, 44}, {8, 13, 44}, {10, 4, 4}, {2, 3, 1}, {3, 9, 56}, {15, 13, 21}, {5, 10, 93}, {12, 3, 36}, {6, 5, 74}, {15, 12, 58}, {13, 10, 76}, {10, 13, 9}, {12, 15, 23}, {6, 14, 2}, {11, 8, 29}, {1, 6, 49}, {8, 10, 76}, {10, 5, 88}, {4, 7, 45}, {7, 12, 83}, {7, 15, 62}, {12, 1, 36}, {4, 2, 71}, {9, 5, 59}, {15, 8, 72}, {3, 11, 47}, {8, 1, 34}, {14, 11, 56}, {9, 3, 8}, {8, 11, 0}, {11, 7, 66}, {15, 1, 42}, {11, 14, 84}, {9, 15, 8}, {6, 13, 39}, {15, 10, 30}}, 15, 9);
    }

    public static int networkDelayTime2(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nexts.add(new ArrayList<>());
        }

        for (int[] delay : times) {
            nexts.get(delay[0]).add(new int[]{delay[1], delay[2]});
        }

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

        boolean[] used;
        int[][] heap;
        int[] hIndex;
        int size;

        public Heap(int n) {
            this.used = new boolean[n + 1];
            this.heap = new int[n + 1][2];
            this.hIndex = new int[n + 1];
            Arrays.fill(hIndex, -1);
            this.size = 0;
        }

        public void add(int cur, int delay) {

            if (used[cur]) {
                return;
            }

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
            int l = i * 2 + 1;
            while (l < size) {
                int smallIndex = l + 1 < size && heap[l + 1][1] < heap[l][1] ? l + 1 : l;
                smallIndex = heap[smallIndex][1] < heap[i][1] ? smallIndex : i;
                if (smallIndex == i) {
                    break;
                }
                swap(smallIndex, i);
                i = l;
                l = i * 2 + 1;
            }
        }

        public void swap(int i, int j) {
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
