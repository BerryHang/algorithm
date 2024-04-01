package handwriting.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class IsFullBT {

    public static void main(String[] args) {
        int times = 1000;
        int range = Integer.MAX_VALUE;
        int level = 3;
        for (int i = 0; i < times; i++) {
            Node node = generate(1, range, level);

            boolean fullBT = isFullBT(node);
            boolean fullBTRecursive = isFullBTRecursive(node);

            if (fullBT != fullBTRecursive) {
                System.out.printf("err");
            }

        }

    }

    public static boolean isFullBTRecursive(Node node) {

        if (node==null){
            return true;
        }

        return process(node).isFull;
    }

    public static Info process(Node node) {

        //如果节点为空直接认为是满二叉树且高度为0
        if (node == null) {
            return new Info(0, true);
        }

        //获取左子树的信息
        Info left = process(node.left);
        //获取右子树的信息
        Info right = process(node.right);
        //当前树的高度是子树最大高度+1     只有当两个子树都是满二叉树且高度相同时，当前节点才是满二叉树
        return new Info(Math.max(left.high, right.high) + 1, left.isFull && right.isFull && left.high == right.high);
    }

    public static class Info {
        int high;
        boolean isFull;

        public Info(int high, boolean isFull) {
            this.high = high;
            this.isFull = isFull;
        }
    }

    public static boolean isFullBT(Node node) {

        if (node == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        //统计二叉树的层数
        int level = 0;
        //保存二叉树的节点数
        int nodes = 0;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            nodes += size;

            for (int i = 0; i < size; i++) {

                Node pop = queue.poll();

                if (pop.right != null) {
                    queue.add(pop.right);
                }

                if (pop.left != null) {
                    queue.add(pop.left);
                }
            }

        }
        //判断遍历的节点数和层数计算的节点数是否相同
        return (1 << level) - 1 == nodes;
    }


    public static Node generate(int level, int range, int maxLevel) {
        double random = Math.random();
        if (random < 0.1 || level > maxLevel) {
            return null;
        } else {
            Node head = new Node((int) (Math.random() * range));
            head.left = generate(level + 1, range, maxLevel);
            head.right = generate(level + 1, range, maxLevel);
            return head;
        }
    }

}
