package handwriting.heap;

import java.util.PriorityQueue;

public class PriorityQueueOperation {

    public static void main(String[] args) {

        //初始化优先级队列，默认是小根堆
        PriorityQueue<Integer> queue = new PriorityQueue();

        //像优先队列中添加元素
        queue.add(3);
        queue.add(1);
        queue.add(5);

        //peek方法，查看堆顶元素，并不会吧元素移除
        System.out.println("查看对顶元素" + queue.peek());

        //堆中是可以添加相同元素的，并且不会覆盖
        queue.add(5);
        queue.add(4);

        //poll弹出堆顶的元素
        System.out.println("弹出堆顶元素" + queue.poll());
        System.out.println("=========");

        //依次弹出堆中的数据，可以看到是从小到大的依次弹出
        while (!queue.isEmpty()){
            System.out.println("" + queue.poll());
        }
    }

}
