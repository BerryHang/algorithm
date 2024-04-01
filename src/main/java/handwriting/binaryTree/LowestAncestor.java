package handwriting.binaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class LowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        int times = 10000;
        int range = 50;
        int level = 10;

        for (int i = 0; i < times; i++) {
            Node head = generator(range, level);

            Node randomA = findRandom(head, level);
            Node randomB = findRandom(head, level);

            Node res1 = lowestAncestor(head, randomA, randomB);

            Node res2 = lowestAncestorRecursive(head, randomA, randomB);

            if (res1 != res2) {
                System.out.printf("err");
            }

        }

    }

    public static Node lowestAncestorRecursive(Node head, Node randomA, Node randomB) {

        if (head == null) {
            return null;
        }

        return process(head, randomA, randomB).ancestor;
    }

    public static Info process(Node node, Node randomA, Node randomB) {

        //节点为空直接返回未找到数据
        if (node == null) {
            return new Info(false, false, null);
        }

        //获取左右子树的信息
        Info left = process(node.left, randomA, randomB);
        Info right = process(node.right, randomA, randomB);

        //是否发现目标接节点  当前节点是目标节点  或者左右节点发现次节点均为发现目标节点
        boolean findA = node == randomA || left.findA || right.findA;
        boolean findB = node == randomB || left.findB || right.findB;

        //判断最低祖先节点是否找到
        Node ancestor = null;

        //子节点已经找到使用子接点的
        if (left.ancestor != null) {
            ancestor = left.ancestor;
        } else if (right.ancestor != null) {
            ancestor = right.ancestor;
            //子节点中没有如果在当前节点发现了全部目标节点，那么当前节点为最低祖先节点
        } else if (findA && findB) {
            ancestor = node;
        }

        return new Info(findA, findB, ancestor);
    }

    public static class Info {

        boolean findA;
        boolean findB;

        Node ancestor;

        public Info(boolean findA, boolean findB, Node ancestor) {
            this.findA = findA;
            this.findB = findB;
            this.ancestor = ancestor;
        }
    }

    public static Node lowestAncestor(Node head, Node randomA, Node randomB) {

        if (head == null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        HashMap<Node, Node> nodeMap = new HashMap<>();

        nodeMap.put(head, null);

        //利用先序遍历过程，将没一个节点的和其父节点建立关系
        while (!stack.isEmpty()) {

            Node pop = stack.pop();

            if (pop.right != null) {
                stack.push(pop.right);
                nodeMap.put(pop.right, pop);
            }

            if (pop.left != null) {
                stack.push(pop.left);
                nodeMap.put(pop.left, pop);
            }

        }

        HashSet<Node> set = new HashSet<>();
        set.add(randomA);

        Node cur = randomA;

        //将A节点的路径全部记录下来
        while (nodeMap.get(cur) != null) {
            Node parent = nodeMap.get(cur);
            set.add(parent);
            cur = parent;
        }

        cur = randomB;

        //沿着B节点向上找到A节点路径上相同的节点即为最低公共祖先
        while (!set.contains(cur)) {
            cur = nodeMap.get(cur);
        }

        return cur;
    }

    private static Node findRandom(Node head, int level) {

        int randomLevel = (int) (Math.random() * level + 1);

        Node randomNode = head;

        while (randomLevel > 0 && randomNode != null) {
            randomLevel--;
            if (Math.random() < 0.5) {
                if (randomNode.left != null) {
                    randomNode = randomNode.left;
                }
            } else {
                if (randomNode.right != null) {
                    randomNode = randomNode.right;
                }
            }
        }

        return randomNode;
    }

    private static Node generator(int range, int level) {

        if (level == 0) {
            return null;
        }

        Node node = new Node((int) (Math.random() * range + 1));
        node.left = generator(range, level - 1);
        node.right = generator(range, level - 1);
        return node;

    }

}
