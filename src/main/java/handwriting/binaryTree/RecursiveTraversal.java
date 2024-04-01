package handwriting.binaryTree;

//递归遍历二叉树
public class RecursiveTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void main(String[] args) {
        int range = 1000;
        int level = 6;
        int times = 5;
        for (int i = 0; i < times; i++) {
            Node head = generate(range, (int) (Math.random() * level + 1));
            preorderTraversal(head);
            System.out.println("");
            inorderTraversal(head);
            System.out.println("");
            postorderTraversal(head);
            System.out.println("");
        }
    }

    //先序遍历二叉树
    public static void preorderTraversal(Node node) {

        if (node == null) {
            return;
        }
        System.out.printf(node.value+ "  ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    //中序遍历二叉树
    public static void inorderTraversal(Node node) {

        if (node == null) {
            return;
        }

        inorderTraversal(node.left);
        System.out.printf(node.value+ "  ");
        inorderTraversal(node.right);
    }

    //后序遍历二叉树
    public static void postorderTraversal(Node node) {

        if (node == null) {
            return;
        }

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.printf(node.value+ "  ");
    }

    public static Node generate(int range, int level) {
        double random = Math.random();
        int curLevel = level - 1;
        if (random < 0.01 || curLevel == 0) {
            return null;
        } else {
            Node head = new Node((int) (Math.random() * range));
            head.left = generate(range, curLevel);
            head.right = generate(range, curLevel);
            return head;
        }
    }

}
