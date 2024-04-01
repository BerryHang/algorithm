package handwriting.binaryTree;

import java.util.Stack;

public class IsBST {

    public static void main(String[] args) {

        int times = 10000;
        int range = 1000;
        int level = 3;
        for (int i = 0; i < times; i++) {
            boolean isBST = Math.random() < 0.5;
            Node node = generate(1, 0, range, level, isBST);
            boolean isBST1 = isBST(node);
            boolean isBST2 = isBSTRecursive(node);
            if (isBST1 != isBST2) {
                System.out.println("err");
            }
        }
    }

    public static boolean isBSTRecursive(Node node) {
        if (node == null) {
            return true;
        }
        return process(node).isBST;
    }

    public static Info process(Node node) {

        if (node == null) {
            return null;
        }

        Info left = process(node.left);
        Info right = process(node.right);

        int min = node.value;
        int max = node.value;

        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
        }

        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
        }

        //默认是满足搜索二叉树
        boolean isBST = true;

        //如果左子树不为空但是不是搜索二叉树那么当前节点不是搜索二叉树
        if (left != null && !left.isBST) {
            isBST = false;
        }

        //如果左子树的最大值大于当前节点的的值那么当前节点不是搜索二叉树
        if (left != null && left.max >= node.value) {
            isBST = false;
        }

        //如果右子树不为空但是不是搜索二叉树那么当前节点不是搜索二叉树
        if (right != null && !right.isBST) {
            isBST = false;
        }
        //如果右子树的最小值大于当前节点的的值那么当前节点不是搜索二叉树
        if (right != null && right.min <= node.value) {
            isBST = false;
        }

        return new Info(min, max, isBST);
    }

    public static class Info {
        int min;
        int max;
        boolean isBST;

        public Info(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static boolean isBST(Node node) {
        if (node == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();

        Integer currentMin = null;

        while (node != null || !stack.isEmpty()) {

            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node pop = stack.pop();

                //首次弹出值时直接复制
                if (currentMin == null) {
                    currentMin = pop.value;
                    //非首存弹出值时，如果弹出值比之前记录的最小值小，返回false
                } else if (currentMin >= pop.value) {
                    return false;
                } else {
                    //满足升序条件的话更新当前弹出值为最小值
                    currentMin = pop.value;
                }

                if (pop.right != null) {
                    node = pop.right;
                }

            }

        }

        return true;
    }

    public static Node generate(int level, int min, int max, int maxLevel, boolean isBST) {
        double random = Math.random();
        if (random < 0.1 || level > maxLevel) {
            return null;
        } else {

            if (isBST && (max - min < 2)) {
                return null;
            }

            int current = (int) (Math.random() * (max - min) + min) + 1;

            while (isBST && (current == min || current == max)) {
                current = (int) (Math.random() * (max - min) + min) + 1;
            }

            Node node = new Node(current);
            if (isBST) {
                node.left = generate(level + 1, min, current, maxLevel, isBST);
                node.right = generate(level + 1, current, max, maxLevel, isBST);
            } else {
                node.left = generate(level + 1, min, max, maxLevel, isBST);
                node.right = generate(level + 1, min, max, maxLevel, isBST);
            }

            return node;
        }
    }

}