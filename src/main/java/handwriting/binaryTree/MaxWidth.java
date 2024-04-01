package handwriting.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidth {


    public static void main(String[] args) {
        int range = 1000;
        int level = 10;
        int times = 5;
        for (int i = 0; i < times; i++) {
            Node head = generate(range, (int) (Math.random() * level + 1));
            System.out.println("最大宽度为：" + widthOfBinaryTree(head));
        }
    }

    public static int widthOfBinaryTree(Node node) {

        if (node == null) {
            return 0;
        }

        int max = 1;

        Queue<Node> queue = new LinkedList();
        queue.add(node);

        while (!queue.isEmpty()) {
            //保存每一层的节点数量
            int size = queue.size();

            max = Math.max(max, size);

            for (int i = 0; i < size; i++) {

                Node poll = queue.poll();

                if (poll.left != null) {
                    queue.add(poll.left);
                }

                if (poll.right != null) {
                    queue.add(poll.right);
                }

            }

        }

        return max;
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
