package handwriting.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializerTreeByLevel {

    public static void main(String[] args) {
        int range = 1000;
        int level = 8;
        int times = 5;
        for (int i = 0; i < times; i++) {
            Node head = generate(range, (int) (Math.random() * level + 1));
            System.out.println("按层序列化");
            Queue<String> serializerQueue = serializerByLevel(head);
            System.out.println(serializerQueue);
            System.out.println("按层反序列化");
            Node deserializationNode = deserializationByLevel(serializerQueue);
            System.out.println();
        }
    }

    //按层级序列化二叉树
    public static Queue<String> serializerByLevel(Node head) {
        Queue<String> queue = new LinkedList<>();
        //头节点为空，直接返回
        if (head == null) {
            return queue;
        }
        //不为空的时候序列化二叉树
        return doSerializerByLevel(head, queue);
    }

    //实际按层序列化逻辑
    public static Queue<String> doSerializerByLevel(Node node, Queue<String> queue) {

        //借助队列进行二叉树的层序遍历，先将头节点压入队列中
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(node);
        queue.add(String.valueOf(node.value));

        //队列不为空时，进行遍历逻辑
        while (!nodeQueue.isEmpty()) {

            //从队列中取出一个
            Node poll = nodeQueue.poll();

            //如果子节点为空，往序列化的容器中添加占位，不为空时序列化值到容器中，并向遍历队列中添加当前节点
            if (poll.left == null) {
                queue.add(null);
            } else {
                queue.add(String.valueOf(poll.left.value));
                nodeQueue.add(poll.left);
            }

            if (poll.right == null) {
                queue.add(null);
            } else {
                queue.add(String.valueOf(poll.right.value));
                nodeQueue.add(poll.right);
            }

        }
        return queue;
    }

    //按层进行反序列化
    public static Node deserializationByLevel(Queue<String> queue) {

        //当队列为空时直接返回
        if (queue == null || queue.isEmpty()) {
            return null;
        }

        //遍历数据进行实际的反序列化操作
        return doDeserializationByLevel(queue);
    }

    public static Node doDeserializationByLevel(Queue<String> queue) {

        //首先从队列中拿出根节点添加到队列中 同样借助层序遍历的队列逻辑
        String value = queue.poll();
        Queue<Node> nodeQueue = new LinkedList<>();
        Node node = generateNode(value);
        nodeQueue.add(node);

        //当队列不为空时进行反序列化操作
        while (!nodeQueue.isEmpty()) {
            Node poll = nodeQueue.poll();
            //构建弹出节点的左右子树
            poll.left = generateNode(queue.poll());
            poll.right = generateNode(queue.poll());

            //如果子树不为空证明当前弹出节点存在子节点，进行节点队列添加
            if (poll.left != null) {
                nodeQueue.add(poll.left);
            }

            if (poll.right != null) {
                nodeQueue.add(poll.right);
            }
        }

        return node;
    }

    //构建节点
    public static Node generateNode(String value) {
        //数据为空返回空节点，反之构造新节点返回
        if (value == null) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }

    public static Node generate(int range, int level) {
        double random = Math.random();
        int curLevel = level - 1;
        if (random < 0.1 || curLevel == 0) {
            return null;
        } else {
            Node head = new Node((int) (Math.random() * range));
            head.left = generate(range, curLevel);
            head.right = generate(range, curLevel);
            return head;
        }
    }

}
