package handwriting.linkList;

import java.util.HashMap;
import java.util.Map;

public class CopyLinkedList {

    public static void main(String[] args) {
        int min = 10;
        int max = 30;
        int length = 10;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(min, max, length);

            Node root = generateList(origArr);

            Node node2 = copyLinkedListByMap(root);

            Node node1 = copyLinkedList(root);

            if (!compare(node1, node2)||!compare(root, node1)||!compare(root, node2)) {
                print(origArr);
            }

        }
    }

    //不使用容器复制链表
    public static Node copyLinkedList(Node root) {

        if (root == null) {
            return null;
        }

        Node head = root;

        //先进行节点复制，复制节点均在原节点的下一个节点，而原来的节点的下一个节点变为复制节点的下一个
        while (head != null) {
            Node copyNode = new Node(head.number, head.index);
            copyNode.next = head.next;
            head.next = copyNode;
            head = copyNode.next;
        }

        //设置复制节点的 random 指针
        head = root;
        while (head != null) {
            head.next.random = head.random == null ? null : head.random.next;
            head = head.next.next;
        }

        head = root.next;
        Node cur = root;
        Node next = root.next;

        //节点进行分离
        while (cur != null) {
            cur.next = next.next;
            next.next = cur.next == null ? null : cur.next.next;
            cur = cur.next;
            next = next.next == null ? null : next.next;
        }

        return head;
    }

    //使用容器进行复制链表
    public static Node copyLinkedListByMap(Node root) {

        if (root == null) {
            return null;
        }

        //定义一个Map  key 是老节点 value 是对老节点的复制
        Map<Node, Node> nodeMap = new HashMap<>();

        Node cur = root;

        //先复制接口的值
        while (cur != null) {
            nodeMap.put(cur, new Node(cur.number, cur.index));
            cur = cur.next;
        }

        cur = root;

        //再设置节点的指针
        while (cur != null) {

            //设置新链表的 next 指针
            nodeMap.get(cur).next = nodeMap.get(cur.next);

            //设置新链表的 random 指针
            nodeMap.get(cur).random = nodeMap.get(cur.random);

            //指针后移
            cur = cur.next;
        }

        return nodeMap.get(root);
    }

    public static int[] generate(int min, int max, int length) {

        length = (int) (Math.random() * length) + 1;

        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }

        return arr;
    }

    public static Node generateList(int[] arr) {

        Node root = new Node(arr[0], 0);
        Node node = root;

        Node[] arrNode = new Node[arr.length];
        arrNode[0] = node;

        for (int i = 1; i < arr.length; i++) {
            Node nextNode = new Node(arr[i], i);
            node.next = nextNode;
            node = node.next;
            arrNode[i] = nextNode;
        }
        node = root;

        while (node != null) {

            int random = (int) (Math.random() * (arr.length + 2));

            if (random > arr.length - 1) {
                node.random = null;
            } else {
                node.random = arrNode[random];
            }
            node = node.next;
        }

        return root;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean compare(Node header1, Node header2) {

        if (header1 == null && header2 == null) {
            return true;
        }

        if ((header1 != null && header2 == null) || (header1 == null && header2 != null)) {
            return false;
        }

        Node node1 = header1;
        Node node2 = header2;

        while (node1 != null) {
            if (node2 == null || node1.number != node2.number) {
                return false;
            }

            Node random1 = node1.random;
            Node random2 = node2.random;

            node1 = node1.next;
            node2 = node2.next;

            if (random1 == null && random2 == null) {
                continue;
            }

            if ((random1 != null && random2 == null) || (random1 == null && random2 != null) || (random1.number != random2.number)) {
                return false;
            }
        }

        if (node2 != null) {
            return false;
        }

        return true;
    }

}
