package handwriting.binaryTree;

public class MaxSubBST {

    // 提交时不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    public static int largestBSTSubtree(TreeNode node) {

        //空节点直接返回0
        if (node == null) {
            return 0;
        }

        //调用递归处理逻辑
        return process(node).maxBSTSubtreeSize;
    }

    public static Info process(TreeNode node) {

        //如果节点为空直接返回空节点
        if (node == null) {
            return null;
        }

        //获取左右子树信息
        Info left = process(node.left);
        Info right = process(node.right);

        //保存当前树的最大值和最小值
        int min = node.val;
        int max = node.val;

        //左子树最大二叉搜索树的节点数
        int leftMaxBSTSubtreeSize = -1;

        //当前节点的节点总数
        int allNodes = 1;

        //如果左子树不为空更新最小值、最大值、节点总数
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            allNodes += left.allNodes;
            leftMaxBSTSubtreeSize = left.maxBSTSubtreeSize;
        }

        //左子树最大二叉搜索树的节点数
        int rightMaxBSTSubtreeSize = -1;

        //如果右子树不为空更新最小值、最大值、节点总数
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            allNodes += right.allNodes;
            rightMaxBSTSubtreeSize = right.maxBSTSubtreeSize;
        }

        //当前节点最大搜索二叉树的节点总数
        int currentMaxBSTSubtreeSize = -1;

        //左右子树都是搜索二叉树的情况下，当前节点才可能为搜索二叉树
        boolean leftBST = left == null ? true : left.allNodes == left.maxBSTSubtreeSize;
        boolean rightBST = left == null ? true : right.allNodes == right.maxBSTSubtreeSize;

        if (leftBST && rightBST) {

            //判断数据是否满足搜索二叉树
            boolean lefMaxLessCur = left == null ? true : left.max < node.val;
            boolean rightMinMoreCur = right == null ? true : right.min > node.val;

            if (lefMaxLessCur && rightMinMoreCur) {

                //求出当前节点是搜索二叉树的节点总数
                int leftSize = left == null ? 0 : left.allNodes;
                int rightSize = right == null ? 0 : right.allNodes;

                currentMaxBSTSubtreeSize = leftSize + rightSize + 1;
            }

        }

        //构造结果信息 其中最大二叉搜索子树的节点总数取  当前节点、左子树和右子树中二叉搜索子树节点数最大的
        return new Info(Math.max(Math.max(leftMaxBSTSubtreeSize, rightMaxBSTSubtreeSize), currentMaxBSTSubtreeSize), allNodes, max, min);
    }

    public static class Info {

        //存储当前节点最大二叉搜索子树的节点个数
        int maxBSTSubtreeSize;

        //存储当前节点已经其所有子树的节点总和
        int allNodes;

        //以当前节点为根的二叉树的最大值
        public int max;

        //以当前节点为根的二叉树的最小值
        public int min;

        public Info(int maxBSTSubtreeSize, int allNodes, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allNodes = allNodes;
            this.max = max;
            this.min = min;
        }
    }

}