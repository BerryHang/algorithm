package handwriting.commonDataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//双端队列
public class DoubleWaysQueue {

    //自定义队列节点
    public static class Node<T> {
        public T value;
        public Node<T> pre;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    //自定义双端队列
    public static class CustomDoubleWaysQueue<T> {

        //队列头节点
        private Node<T> head;

        //队列尾节点
        private Node<T> tail;

        //从队列头入队元素
        public boolean pushFromHead(T t) {

            Node<T> node = new Node<>(t);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.pre = node;
                head = node;
            }
            return true;
        }

        //从队列尾入队元素
        public boolean pushFromTail(T t) {
            Node<T> node = new Node<>(t);
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                node.pre = tail;
                tail.next = node;
                tail = node;
            }
            return true;
        }

        //从队列头出队元素
        public T popFromHead() {

            if (head == null) {
                return null;
            }

            Node<T> node = head;
            if (head == tail) {
                tail = null;
                head = null;
            } else {
                head = head.next;
                head.pre = null;
                node.next = null;
            }
            return node.value;
        }

        //从队列尾出队元素
        public T popFromTail() {
            if (tail == null) {
                return null;
            }
            Node<T> node = tail;
            if (head == tail) {
                tail = null;
                head = null;
            } else {
                tail = tail.pre;
                tail.next = null;
                node.pre = null;
            }
            return node.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class CustomStack<T> {

        private CustomDoubleWaysQueue<T> doubleWaysQueue;

        public CustomStack() {
            doubleWaysQueue = new CustomDoubleWaysQueue<>();
        }

        public boolean push(T t) {
            return doubleWaysQueue.pushFromHead(t);
        }

        public T pop() {
            return doubleWaysQueue.popFromHead();
        }

        public boolean isEmpty() {
            return doubleWaysQueue.isEmpty();
        }
    }

    public static class CustomQueue<T> {

        private CustomDoubleWaysQueue<T> doubleWaysQueue;

        public CustomQueue() {
            doubleWaysQueue = new CustomDoubleWaysQueue<>();
        }

        public boolean push(T t) {
            return doubleWaysQueue.pushFromHead(t);
        }

        public T pop() {
            return doubleWaysQueue.popFromTail();
        }

        public boolean isEmpty() {
            return doubleWaysQueue.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            CustomStack<Integer> customStack = new CustomStack<>();
            CustomQueue<Integer> customQueue = new CustomQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    customStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        customStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(customStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    customQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        customQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(customQueue.pop(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
