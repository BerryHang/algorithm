package handwriting.binaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SerializerTree {

    public static void main(String[] args) {
        int range = 1000;
        int level = 8;
        int times = 5;
        for (int i = 0; i < times; i++) {
            Node head = generate(range, (int) (Math.random() * level + 1));
            System.out.println("先序序列化");
            Queue<String> preQueue = preSerializer(head);
            System.out.println(preQueue);
            System.out.println("后序序列化");
            Queue<String> postQueue = postSerializer(head);
            System.out.println(postQueue);
            System.out.println("前序反序列化");
            Node preDeserializationNode = preDeserialization(preQueue);
            System.out.println();
            System.out.println("前序反序列化");
            Node postDeserializationNode = postDeserialization(postQueue);
            System.out.println();
        }
    }

    //前序序列化
    public static Queue<String> preSerializer(Node head) {
        Queue<String> queue = new LinkedList<>();
        doPreSerializer(head, queue);
        return queue;
    }

    //递归进行树的前序序列化操作
    public static void doPreSerializer(Node node, Queue<String> queue) {

        //节点为空时直接添加空，证明走到了叶子节点的子节点
        if (node == null) {
            queue.add(null);
        } else {
            //先将当前节点添加到队列中，之后在添加左子树的节点新想 最后添加右子树的节点信息
            queue.add(String.valueOf(node.value));
            doPreSerializer(node.left, queue);
            doPreSerializer(node.right, queue);
        }
    }

    //前序反序列化
    public static Node preDeserialization(Queue<String> queue) {

        if (queue == null || queue.isEmpty()) {
            return null;
        }

        return doPreDeserialization(queue);
    }

    //递归进行树的前序的反序列化操作，实际递归逻辑和序列化时相似
    public static Node doPreDeserialization(Queue<String> queue) {

        String value = queue.poll();
        Node node = null;
        if (value != null) {
            node = new Node(Integer.valueOf(value));
            node.left = doPreDeserialization(queue);
            node.right = doPreDeserialization(queue);
        }

        return node;
    }

    //后序序列化
    public static Queue<String> postSerializer(Node head) {
        Queue<String> queue = new LinkedList<>();
        doPostSerializer(head, queue);
        return queue;
    }

    //递归进行后序序列化操作
    public static void doPostSerializer(Node node, Queue<String> queue) {

        if (node == null) {
            queue.add(null);
        } else {
            //相比前序只是改变了添加节点的时机，将左右子树的节点都添加完后，在加根节点
            doPostSerializer(node.left, queue);
            doPostSerializer(node.right, queue);
            queue.add(String.valueOf(node.value));
        }
    }

    //后序反序列化
    public static Node postDeserialization(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }

        //后序的序列化化的顺序是左右根，这里将队列中的数据依次压入到栈栈中，
        // 就形成了根右左的顺序，在使用类似先序的逻辑进行反序列化
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()){
            stack.push(queue.poll());
        }

        return doPostDeserialization(stack);
    }

    //递归进行后序反序列化
    public static Node doPostDeserialization(Stack<String> stack) {
        String value = stack.pop();
        Node node = null;
        if (value != null) {
            //注意这里的添加顺序是先处理右子树，后处理左子树
            node = new Node(Integer.valueOf(value));
            node.right = doPostDeserialization(stack);
            node.left = doPostDeserialization(stack);
        }

        return node;
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
