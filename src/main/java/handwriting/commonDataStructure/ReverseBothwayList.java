package handwriting.commonDataStructure;

import java.util.ArrayList;
import java.util.List;

//链表的逆序操作
public class ReverseBothwayList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class BothwayNode {
        public int value;
        public BothwayNode next;
        public BothwayNode pre;

        public BothwayNode(int data) {
            value = data;
        }
    }

    public static void main(String[] args) {

        int length = 50;
        int range = 100;
        int testTimes = 50;

        for (int i = 0; i < testTimes; i++) {
            BothwayNode node = generateBothwayNodeList((int) (Math.random() * length + 1), (int) (Math.random() * range));
            List<Integer> list = generateArrayList(node);
            node = reverseList(node);
            if (!verify(list, node)) {
                System.out.println("出错");
                print(node);
            }
        }

    }

    private static void print(BothwayNode head) {
        while (head != null) {
            System.out.printf(head.value + " ");
            head = head.next;
        }
    }

    private static boolean verify(List<Integer> list, BothwayNode head) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != head.value) return false;
            head = head.next;
        }
        return true;
    }

    private static BothwayNode reverseList(BothwayNode head) {
        BothwayNode pre = null;
        BothwayNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static List<Integer> generateArrayList(BothwayNode node) {

        List<Integer> list = new ArrayList<>();

        while (node != null) {
            list.add(node.value);
            node = node.next;
        }

        return list;
    }

    //初始化双向链表
    private static BothwayNode generateBothwayNodeList(int length, int range) {

        BothwayNode head = new BothwayNode((int) (Math.random() * range) + 1);
        length--;

        BothwayNode pre = head;
        for (int i = 0; i < length; i++) {
            BothwayNode node = new BothwayNode((int) (Math.random() * range) + 1);
            pre.next = node;
            node.pre = pre;
            pre = node;
        }
        return head;
    }

}
