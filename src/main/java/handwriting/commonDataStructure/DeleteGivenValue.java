package handwriting.commonDataStructure;

import java.util.ArrayList;
import java.util.List;

//删除链表中指定数字
public class DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static void main(String[] args) {

        //数组稍微长一点，随机数稍微小一点，容易出现需要移除的数字
        int length = 10;
        int range = 5;
        int testTimes = 50;

        for (int i = 0; i < testTimes; i++) {
            //生成随机列表
            Node node = generateNodeList((int) (Math.random() * length + 1), (int) (Math.random() * range));
            //需要移除的目标数字
            int target = (int) (Math.random() * range + 1);
            List<Integer> list = generateArrayList(node, target);

            node = deleteGivenValue(node, target);
            if (!verify(list, node)) {
                System.out.println("出错，目标数据：" + target);
                print(node);
            }
        }
    }

    //验证单链表的逆序是否正确
    private static boolean verify(List<Integer> list, Node head) {

        //同时为空则正确
        if (list == null && head == null) {
            return true;
        }

        //一个为空不正确
        if ((list == null && head != null) || (list != null && head == null)) {
            return false;
        }

        //循环判断
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != head.value) return false;
            head = head.next;
        }

        //长度不一样不正确
        if (head != null) {
            return false;
        }
        return true;
    }

    //删除链表中指定的数字
    private static Node deleteGivenValue(Node head, int target) {

        while (head != null) {
            if (head.value != target) break;
            head = head.next;
        }

        Node next = head;
        Node pre = head;
        while (next != null) {
            if (next.value == target) {
                pre.next = next.next;
            } else {
                pre = next;
            }
            next = next.next;
        }
        return head;
    }

    //用单链表构建一个数组，并移除指定数字
    private static List<Integer> generateArrayList(Node node, int target) {
        List<Integer> list = null;
        while (node != null) {
            if (node.value != target) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(node.value);
            }
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
