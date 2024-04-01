package handwriting.binaryTree;

import java.util.Stack;

public class InorderSuccessor {

    public static void main(String[] args) {

        int range = 1000;
        int level = 20;
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int realLevel = (int) (Math.random() * level + 1);
            TreeNode head = generate(range, realLevel, null);
            TreeNode target = setTarget(head, realLevel);
            if (target==null){
                continue;
            }
            TreeNode treeNode1 = inorderSuccessor(head, target);
            TreeNode treeNode2 = inorderSuccessorByParent(target);

            if (treeNode1 != treeNode2) {
                System.out.printf("err");
            }

        }
    }

    private static TreeNode setTarget(TreeNode head, int realLevel) {

        int randomLevel = (int) (Math.random() * realLevel + 1);

        while (randomLevel > 0) {
            double random = Math.random();

            if (head==null){
                break;
            }

            if (random < 0.5) {

                if (head.left != null) {
                    head = head.left;
                }

            } else {
                if (head.right != null) {
                    head = head.right;
                }
            }

            randomLevel--;
        }

        return head;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();

        boolean flag = false;

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {

                TreeNode pop = stack.pop();

                if (flag) {
                    return pop;
                }

                if (pop == p) {
                    flag = true;
                }

                root = pop.right;
            }

        }

        return null;
    }


    public static TreeNode inorderSuccessorByParent(TreeNode p) {

        if (p.right != null) {

            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (p.parent != null && p == p.parent.right) {
            p = p.parent;
        }

        return p.parent;
    }


    public static TreeNode generate(int range, int level, TreeNode parent) {
        double random = Math.random();
        int curLevel = level - 1;
        if (random < 0.01 || curLevel == 0) {
            return null;
        } else {
            TreeNode head = new TreeNode((int) (Math.random() * range));
            head.parent = parent;
            head.left = generate(range, curLevel, head);
            head.right = generate(range, curLevel, head);
            return head;
        }
    }

}
