package handwriting.commonDataStructure;

import java.util.ArrayList;
import java.util.List;

//链表的逆序操作
public class ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static void main(String[] args) {

        int length = 50;
        int range = 100;
        int testTimes = 50;

        for (int i = 0; i < testTimes; i++) {
            Node node = generateNodeList((int) (Math.random() * length + 1), (int) (Math.random() * range));
            List<Integer> list = generateArrayList(node);
            node = reverseList(node);
            if (!verify(list, node)) {
                System.out.println("出错");
                print(node);
            }
        }
    }

    //验证单链表的逆序是否正确
    private static boolean verify(List<Integer> list, Node head) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != head.value) return false;
            head = head.next;
        }
        return true;
    }

    //反转单链表
    private static Node reverseList(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;

        }
        return pre;
    }

    //用单链表构建一个数组
    private static List<Integer> generateArrayList(Node node) {

        List<Integer> list = new ArrayList<>();

        while (node != null) {
            list.add(node.value);
            node = node.next;
        }

        return list;
    }

    //初始化单向链表
    private static Node generateNodeList(int length, int range) {

        Node head = new Node((int) (Math.random() * range) + 1);
        length--;

        Node pre = head;
        for (int i = 0; i < length; i++) {
            Node node = new Node((int) (Math.random() * range) + 1);
            pre.next = node;
            pre = node;
        }
        return head;
    }

    //打印单链表的数据信息
    private static void print(Node head) {
        while (head != null) {
            System.out.printf(head.value + " ");
            head = head.next;
        }
    }

}
