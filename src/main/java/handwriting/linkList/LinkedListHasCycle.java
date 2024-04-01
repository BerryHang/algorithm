package handwriting.linkList;

public class LinkedListHasCycle {

    public static void main(String[] args) {

        int range = Integer.MAX_VALUE;
        int length = 20;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            Node root = generate(range, length);

            Node node = findCycleNode(root);

            print(node);
        }
    }

    //查询单链表的入环节点
    public static Node findCycleNode(Node root) {

        //先过滤一些简单场景，同时也避免空指针一场
        if (root == null || root.next == null || root.next.next == null) {
            return null;
        }

        Node fast = root.next.next;
        Node slow = root.next;

        //循环遍历列表，当快慢指针相遇时暂停
        while (fast != slow) {

            //当快指针指向 null 时证明无环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }

            //指针移动
            slow = slow.next;
            fast = fast.next.next;
        }

        //快指针重新回到头节点上
        fast = root;

        //重新遍历列表，此时快慢指针都一次一个位置后移
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    public static Node generate(int range, int length) {

        length = (int) (Math.random() * length + 1);

        Node root = new Node((int) (Math.random() * range), 0);

        Node head = root;

        for (int i = 1; i < length; i++) {
            head.next = new Node((int) (Math.random() * range), i);
            head = head.next;
        }

        Node tail = head;
        head = root;

        if (Math.random() < 0.5) {
            int index = (int) (Math.random() * length);

            for (int i = index; i >= 0; i--) {
                head = head.next;
            }

            tail.next = head;
        }

        return root;
    }

    public static void print(Node node){
        if (node!=null){
            System.out.println("入环节点："+node);
        }else {
            System.out.println("无环");
        }
    }

}
