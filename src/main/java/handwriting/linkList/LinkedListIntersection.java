package handwriting.linkList;

public class LinkedListIntersection {

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findIntersection(head1, head2).number);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findIntersection(head1, head2).number);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findIntersection(head1, head2).number);
    }

    public static Node findIntersection(Node root1, Node root2) {
        Node cycleNode1 = findCycleNode(root1);
        Node cycleNode2 = findCycleNode(root2);

        if (cycleNode1 == null && cycleNode2 == null) {
            return noLoop(root1, root2);
        }
        if (cycleNode1 != null && cycleNode2 != null) {
            return bothLoop(root1, cycleNode1, root2, cycleNode2);
        }
        return null;
    }

    public static Node bothLoop(Node root1, Node cycleNode1, Node root2, Node cycleNode2) {

        Node cur1 = null;
        Node cur2 = null;

        //判断两个入环节点是否相同，当入环节点相同时必定相交
        if (cycleNode1 == cycleNode2) {

            //这里找到第一个相交节点的位置和两个无环链表找交点的逻辑类似
            cur1 = root1;
            cur2 = root2;

            int count = 0;
            while (cur1 != cycleNode1) {
                count++;
                cur1 = cur1.next;
            }

            while (cur2 != cycleNode2) {
                count--;
                cur2 = cur2.next;
            }

            cur1 = count > 0 ? root1 : root2;
            cur2 = count > 0 ? root2 : root1;

            count = Math.abs(count);

            while (count > 0) {
                count--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        } else {
            //当入环节点不相同时，沿着其中一个链表的环进行遍历一周，如果遇到另一个链表的入环节点证明相交，反之没有交点
            cur1 = cycleNode1.next;
            while (cur1 != cycleNode1) {
                if (cur1 == cycleNode2) {
                    return cur1;
                }
            }
            return null;
        }
    }

    //请两个无环单链表的第一个相交节点
    public static Node noLoop(Node root1, Node root2) {

        //如果任意一个节点为空，必定没有交点，直接返回
        if (root1 == null || root2 == null) {
            return null;
        }

        //取头指针
        Node head1 = root1;
        Node head2 = root2;

        //定义一个计数器
        int count = 0;

        //从头遍历到尾，并计数节点数量
        while (head1.next != null) {
            count++;
            head1 = head1.next;
        }

        //从头遍历另外一个链表知道结尾，并一次递减计数信息
        while (head2.next != null) {
            count--;
            head2 = head2.next;
        }

        //如果两个链表的尾节点不相同，那一定没有相交的节点，直接返回
        if (head1 != head2) {
            return null;
        }

        //如果链表尾节点相同证明存在相交的部分，将head1指向比较长的链表 head2指向比较短的链表
        head1 = count > 0 ? root1 : root2;
        head2 = count > 0 ? root2 : root1;

        //此时count保存的是两个链表的节点数量的差值，将数据变成正数
        count = Math.abs(count);

        //从头开始遍历长链表，知道未遍历的节点数和短链表的节点数相同
        while (count > 0) {
            count--;
            head1 = head1.next;
        }

        //同时遍历两个链表，当节点相同时，则达到第一个相交节点
        while (head1 != head2) {
            head1 = head1.next;
            head2 = head2.next;
        }

        return head1;
    }

    //查询单链表是否有换，如果有返回入环节点
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

}