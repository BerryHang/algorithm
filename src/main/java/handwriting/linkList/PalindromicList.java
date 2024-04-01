package handwriting.linkList;

import java.util.Stack;

/**
 * 判断一个链表是否为回文链表
 */
public class PalindromicList {

    public static void main(String[] args) {
        int range = 200;

        int length = 10;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(range, length);
            Node root = generateList(origArr);

            boolean isPalindromicByStack = isPalindromicByStack(root);
            boolean isPalindromicByReversed = isPalindromicByReversed(root);

            if (!(isPalindromicByStack == isPalindromicByReversed)) {
                print(origArr);
            }

        }
    }

    //使用栈进行判断是否为回文列表
    public static boolean isPalindromicByStack(Node root) {

        //定义没有节点或者只有一个节点时是回文
        if (root == null || root.next == null) {
            return true;
        }

        Node slow = root;
        Node fast = root;

        //使用快慢指针找到链表的中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Stack<Integer> stack = new Stack<>();

        //从中点开始依次遍历并压栈
        while (slow != null) {
            stack.push(slow.number);
            slow = slow.next;
        }

        //进行出栈操作并同时从头开始遍历链表
        while (!stack.isEmpty()) {
            if (root.number != stack.pop()) {
                return false;
            }
            root = root.next;
        }
        return true;
    }

    public static boolean isPalindromicByReversed(Node root) {

        //没有节点时,只有一个节点时默认是回文
        if (root == null || root.next == null) {
            return true;
        }
        //两个节点时，数字都一样才算回文
        if (root.next.next == null) {
            if (root.number == root.next.number) {
                return true;
            } else {
                return false;
            }

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

        boolean isPalindromic = true;

        //链表两端同时开始遍历
        while (left != null) {

            //发现左右不一致直接结束循环并赋值回文判断为 false
            if (left.number != right.number) {
                isPalindromic = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        //一下逻辑将链表回复原状
        left = fast.next;
        right = fast.next;
        fast.next = null;

        while (left != null) {
            left = left.next;
            right.next = fast;
            fast = right;
            right = left;
        }

        return isPalindromic;
    }

    public static int[] generate(int range, int length) {

        length = (int) (Math.random() * length) + 1;
        //是否问回文
        boolean palindromic = false;
        double random = Math.random();
        //创建奇数个元素的非回文数组
        if (random < 0.25) {
            //保证元素个数为奇数
            if (length % 2 == 0) {
                length++;
            }
            //创建奇数个元素的回文数组
        } else if (random < 0.5) {
            if (length % 2 == 0) {
                length++;
            }

            //设置回文为true
            palindromic = true;
            //创建偶数个元素的非回文数组
        } else if (random < 0.75) {

            //保证元素个数为偶数
            if (length % 2 != 0) {
                length++;
            }
            //创建偶数个元素的回文数组
        } else {
            if (length % 2 != 0) {
                length++;
            }
            palindromic = true;
        }

        int[] arr = new int[length];

        //先随机生成数组的一般元素
        for (int i = 0; i < length / 2; i++) {
            arr[i] = (int) (Math.random() * range);
        }

        //如果创建的是为回文数组，那么将对应位置元素进行复制
        if (palindromic) {
            for (int i = 0; i < length / 2; i++) {
                arr[length - i - 1] = arr[i];
            }

            //如果生成的数组长度为奇数个会漏填充中间的一个数字，进行不全
            if (length % 2 != 0) {
                arr[length / 2] = (int) (Math.random() * range);
            }
            //如果不是回文直接随机生产后半段元素
        } else {
            for (int i = length / 2; i < length; i++) {
                arr[i] = (int) (Math.random() * range);
            }
            //随机检测一个位置
            int index = (int) (Math.random() * length);
            while (arr[index] == arr[length - index - 1]) {
                arr[index] = (int) (Math.random() * range);
            }

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

}
