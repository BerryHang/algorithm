package handwriting.linkList;

//对链表进行编号后重新排序
public class LinkedListTranslocationSort {

    public static void main(String[] args) {
        int range = 200;
        int length = 10;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(range, length);
            Node root = generateList(origArr);

            Node node = translocationSort(root);

            if (!compare(node, origArr)) {
                print(origArr);
            }


        }
    }

    public static Node translocationSort(Node root) {

        //没有节点时,只有一个节点时默认是回文
        if (root == null || root.next == null || root.next.next == null) {
            return root;
        }

        //以下是三个节点及以上逻辑处理
        Node slow = root;
        Node fast = root;

        //先进行快慢指针滑动，找到链表的终点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //当链表元素个数为偶数是，快指针没有划到链表尾部，因此多滑动一位
        if (fast.next != null) {
            fast = fast.next;
        }

        Node left = slow.next;
        Node right = slow.next;
        slow.next = null;

        //修改链表结构，使链表的后半部分逆序
        while (right != null) {
            right = right.next;
            left.next = slow;
            slow = left;
            left = right;
        }

        left = root;
        right = fast;

        //左右交错进行重新定向
        while (left != null && right != null) {
            Node leftNext = left.next;
            Node rightNext = right.next;
            left.next = right;
            if (rightNext == left) {
                right.next = null;
            } else {
                right.next = leftNext;
            }

            left = leftNext;
            right = rightNext;
        }

        return root;
    }

    public static int[] generate(int range, int length) {

        length = (int) (Math.random() * length) + 1;

        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * range);
        }

        return arr;
    }

    public static Node generateList(int[] arr) {
        Node root = new Node(arr[0], 0);
        Node node = root;

        for (int i = 1; i < arr.length; i++) {

            node.next = new Node(arr[i], i);
            node = node.next;

        }
        return root;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean compare(Node root, int[] arr) {

        //数组长度较小时直接判断
        if (arr.length == 1) {
            return arr[0] == root.number;
        }

        if (arr.length == 1) {
            return arr[0] == root.number && arr[1] == root.next.number;
        }

        Node node = root;

        //循环对比数据
        for (int i = 0; i < arr.length / 2; i++) {

            if (arr[i] != node.number || arr[arr.length - i - 1] != node.next.number) {
                return false;
            }

            if (node.next != null) {
                node = node.next.next;
            }
        }

        //当元素个数为奇数时，会剩余中间元素没有对比，额外对比一次
        if (node != null) {
            if (node.number != arr[arr.length / 2]) {
                return false;
            }
        }

        return true;
    }
}