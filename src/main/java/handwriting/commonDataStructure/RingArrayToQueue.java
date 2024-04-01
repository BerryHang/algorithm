package handwriting.commonDataStructure;

public class RingArrayToQueue {

    //存储数据的数组
    public static int[] arr = new int[10];

    //当前对列中存储的数据量
    public static int size = 0;

    //出队的位置
    public static int head = 0;

    //入队的位置
    public static int tail = 0;

    //队列的最大长度
    public static int length = 10;

    public static void main(String[] args) {
        int testTimes = 1000;
        int range = 5000;

        for (int i = 0; i < testTimes; i++) {
            if (((int) (Math.random() * 2)) % 2 == 0) {
                push((int) (Math.random() * range) + 1);
            } else {
                System.out.println("出队：" + pop());
            }
            print();
        }
    }

    private static int push(int num) {
        //当前队列中的数据量已经达到最大，不能入队
        if (size == length) {
            System.out.printf("队列满");
            return -1;
        }
        //入队数据
        arr[tail] = num;
        //判断队列入队位置是否到达数组边缘，
        tail = tail < length - 1 ? tail + 1 : 0;
        System.out.println("入队：" + num);
        //增加当前队列数据数量
        size++;
        return tail;
    }

    private static int pop() {
        //判断当前对列是否为空
        if (size == 0) {
            System.out.printf("队列空");
            return -1;
        }
        size--;
        //出队数据
        int num = arr[head];
        //判断出队位置是否达到数组边缘
        head = head < length - 1 ? head + 1 : 0;
        return num;
    }

    private static void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf(arr[(head + i) % length] + " ");
        }
        System.out.println();
    }

}
