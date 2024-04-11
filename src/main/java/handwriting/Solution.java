package handwriting;

import java.util.Stack;

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

    public boolean isUnivalTree(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int value = root.val;
        while (!stack.isEmpty()) {

            TreeNode pop = stack.pop();

            if (pop.val!=value){
                return false;
            }

            if (pop.left!=null){
                stack.push(pop.left);
            }

            if (pop.right!=null){
                stack.push(pop.right);
            }

        }
        return true;
    }

}