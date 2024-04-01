package handwriting.binaryTree;

import java.util.Stack;

//非递归方式遍历二叉树
public class NoRecursiveTraversal {

    public static void main(String[] args) {
        int range = 1000;
        int level = 5;
        int times = 5;
        for (int i = 0; i < times; i++) {
            Node head = generate(range, (int) (Math.random() * level + 1));
            System.out.println("先序遍历");
            preorderTraversal(head);
            System.out.println();
            System.out.println("中序遍历");
            inorderTraversal(head);
            System.out.println();
            System.out.println("后序遍历");
            postorderTraversal(head);
            System.out.println();
            System.out.println("后序遍历，使用一个栈空间");
            postorderTraversalBySingleStack(head);
            System.out.println();
        }
    }

    //先序遍历二叉树
    public static void preorderTraversal(Node node) {

        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack();
        //首先将根节点压入栈中
        stack.push(node);
        //栈不为空的情况下进行循环处理
        while (!stack.isEmpty()) {

            //弹出节点并打印
            Node pop = stack.pop();
            System.out.printf(pop.value + "  ");
            //由于栈的具有先进后出的特点，这里一定要先把右子节点压入栈中才能保证出栈时，左节点先出栈
            if (pop.right != null) {
                stack.push(pop.right);
            }

            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    //中序遍历二叉树
    public static void inorderTraversal(Node node) {

        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack();
        //当node 和栈同时为空时暂停循环处理
        while (node != null || !stack.isEmpty()) {
            //当node不为空时进行压栈操作，并指向左子节点
            if (node != null) {
                stack.push(node);
                node = node.left;
                //左侧走到底时弹出节点并指向右节点重复if 的逻辑
            } else {
                Node pop = stack.pop();
                System.out.printf(pop.value + "  ");
                node = pop.right;
            }

        }

    }

    //后序遍历二叉树
    public static void postorderTraversal(Node node) {

        if (node == null) {
            return;
        }

        //使用两个栈进行遍历操作
        Stack<Node> stack1 = new Stack();
        Stack<Node> stack2 = new Stack();

        //下面这段逻辑和先序遍历逻辑类似，差别在于遍历顺序并不是根左右而是根右左 而且没有打印元素
        stack1.push(node);
        while (!stack1.isEmpty()) {

            //从第一个栈中出栈压入第二站中
            Node pop = stack1.pop();
            stack2.push(pop);

            if (pop.left != null) {
                stack1.push(pop.left);
            }

            if (pop.right != null) {
                stack1.push(pop.right);
            }

        }

        //由于前面是按照根右左的顺序压入第二个栈中，那么出线顺序自然是左右根
        while (!stack2.isEmpty()) {
            System.out.printf(stack2.pop().value + "  ");
        }

    }

    //后序遍历二叉树 只使用一个栈进行操作
    public static void postorderTraversalBySingleStack(Node node) {

        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack();

        stack.push(node);
        Node topNode = null;
        while (!stack.isEmpty()) {

            topNode = stack.peek();

            if (topNode.left != null && topNode.left != node && topNode.right != node) {
                stack.push(topNode.left);
            } else if (topNode.right != null && topNode.right != node) {
                stack.push(topNode.right);
            } else {
                System.out.printf(stack.pop().value + "  ");
                node = topNode;
            }
        }
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