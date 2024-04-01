package handwriting.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class IsCompleteBT {

    public static void main(String[] args) {

        int times = 10000;
        int range = Integer.MAX_VALUE;
        int level = 3;
        for (int i = 0; i < times; i++) {
            Node node = generate(1, range, level);

            boolean completeBT = isCompleteBT(node);
            boolean completeBTRecursive = isCompleteBTRecursive(node);

            if (completeBT != completeBTRecursive) {
                System.out.println("err");
            }

        }

    }

    public static boolean isCompleteBTRecursive(Node node) {

        if (node==null){
            return true;
        }

        return process(node).isComplete;
    }

    public static Info process(Node node) {

        if (node == null) {
            return new Info(0, true, true);
        }

        Info left = process(node.left);
        Info right = process(node.right);

        //构建当前节点高度
        int high = Math.max(left.high, right.high) + 1;

        //判断当前节点是否为满二叉树
        boolean isFull = left.isFull && right.isFull && left.high == right.high;

        boolean isComplete = false;

        //当前节点如果为满二叉树时一定为完全二叉树
        if (isFull) {
            isComplete = true;
            //当前节点不为满二叉树时他们子树必须先满足完全二叉树的条件
        } else if (left.isComplete && right.isComplete) {

            //如果右子树为满二叉树，且和左子树高度差1 ，只有左树是完全二叉树，那当前节点一定为完全二叉树
            if (right.isFull && left.high == right.high + 1) {
                isComplete = true;
            }

            //如果左子树为满二叉树，其右树为满二叉树且高度小于1时是完全二叉树，这中情况在上一个判断时包含了
            //如果左子树为满二叉树，其右树不为满二叉树那就是完全二叉树的情况下，高度必须要相等
            if (left.isFull && left.high == right.high) {
                isComplete = true;
            }

        }

        return new Info(high, isFull, isComplete);
    }

    public static class Info {
        int high;
        boolean isFull;
        boolean isComplete;

        public Info(int high, boolean isFull, boolean isComplete) {
            this.high = high;
            this.isFull = isFull;
            this.isComplete = isComplete;
        }
    }

    public static boolean isCompleteBT(Node node) {

        if (node == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        //遇到子树为空的节点
        boolean flag = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Node pop = queue.poll();

                //当  （已经遇到过子树节点为空的节点，但是当前节点存在子节点）或者当前节点左子树为空但是右节点不为空  那必然不是完全二叉树
                if ((flag && (pop.left != null || pop.right != null)) || (pop.left == null && pop.right != null)) {
                    return false;
                }

                //添加子节点信息到队列中
                if (pop.left != null) {
                    queue.add(pop.left);
                }

                if (pop.right != null) {
                    queue.add(pop.right);
                }

                //当遇到子树为空时
                if (pop.left == null || pop.right == null) {
                    flag = true;
                }

            }

        }

        return true;
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
