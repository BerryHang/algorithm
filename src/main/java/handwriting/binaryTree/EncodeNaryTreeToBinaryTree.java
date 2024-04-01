package handwriting.binaryTree;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
public class EncodeNaryTreeToBinaryTree {

    // 测试用例中已给出的N叉结构体
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 测试用例中已给出的二叉树结构体
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        Codec codec = new Codec();

        int range = 1000;
        //树的深度
        int level = 4;
        //树的分支个数
        int branches = 3;
        int times = 10;
        for (int i = 0; i < times; i++) {
            Node head = generate(range, branches, level);
            TreeNode treeNode = codec.encode(head);
            Node decode = codec.decode(treeNode);
            System.out.println("原N叉树");
            print(head);
            System.out.println("");
            System.out.println("反序列化后的N叉树");
            print(decode);
            System.out.println("");
        }
    }

    static class Codec {

        // Encodes an n-ary tree to a binary tree. 实现的N叉树转二叉树逻辑
        public TreeNode encode(Node root) {

            if (root == null) {
                return null;
            }

            //先进行根节点处理并返回根节点
            TreeNode head = new TreeNode(root.val);

            //将N叉树的孩子节点复制给二叉树的左子树
            head.left = en(root.children);

            return head;
        }

        private TreeNode en(List<Node> childrens) {

            if (childrens == null || childrens.isEmpty()) {
                return null;
            }

            TreeNode head = null;
            TreeNode cur = null;

            //循环遍历N叉树的子节点列表
            for (Node childNode : childrens) {

                //常见出节点
                TreeNode node = new TreeNode(childNode.val);

                //这里只有循环第一次的时候才会进入if分支，创建出了子树的第一个节点
                if (head == null) {
                    head = node;
                } else {
                    //第一次之后的循环都会进这里不断将N叉树的节点添加到右侧
                    cur.right = node;
                }
                cur = node;
                //递归处理当前节点的孩子节点  深度优先
                cur.left = en(childNode.children);
            }

            return head;
        }

        // Decodes your binary tree to an n-ary tree. 实现的二叉树转N叉树逻辑
        public Node decode(TreeNode root) {

            if (root == null) {
                return null;
            }

            //直接创建根节点，并将二叉树的左子树处理为N叉树的孩子节点
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode treeNode) {

            List<Node> nodes = new ArrayList<>();
            //对子树不断右移进行添加
            while (treeNode != null) {
                //添加的同时也进行将当前节点的左子树处理为自节点，深度优化
                nodes.add(new Node(treeNode.val, de(treeNode.left)));
                treeNode = treeNode.right;
            }

            return nodes;
        }

    }

    //初始化N叉树逻辑
    public static Node generate(int range, int branches, int level) {
        double random = Math.random();
        int curLevel = level - 1;
        if (random < 0.1 || curLevel == 0) {
            return null;
        }
        //创建出根节点
        Node head = new Node((int) (Math.random() * range));
        //处理孩子节点
        head.children = doGenerate(range, branches, level);
        return head;
    }

    //同样使用深度优化算法创建N叉树
    public static List<Node> doGenerate(int range, int branches, int level) {
        double random = Math.random();

        //当前已达到树的深度直接范湖
        int curLevel = level - 1;
        if (random < 0.1 || curLevel == 0) {
            return null;
        }

        //随机分支个数，进行遍历创建子节点列表
        branches = (int) (Math.random() * branches) + 1;
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < branches; i++) {
            //在添加每一个子节点的同时先创建出当前子节点的孩子节点
            nodes.add(new Node((int) (Math.random() * range), doGenerate(range, branches, curLevel)));
        }

        return nodes;
    }

    //打印N叉树信息
    public static void print(Node root) {

        if (root == null) {
            return;
        }
        System.out.printf(root.val + " ");

        if (root.children != null) {
            for (Node node : root.children) {
                print(node);
            }
        }

    }

}
