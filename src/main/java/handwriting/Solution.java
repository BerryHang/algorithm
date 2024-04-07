package handwriting;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return process(root, false);
    }

    public static int process(TreeNode node, boolean self) {

        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return self ? node.val : 0;
        }

        return process(node.left, true) + process(node.right, false);
    }

}