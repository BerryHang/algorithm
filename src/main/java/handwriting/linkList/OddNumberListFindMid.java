package handwriting.linkList;

//在一个链表中如果集合元素个数为奇数个，找到当前链表的唯一中点和中点的前一个元素
public class OddNumberListFindMid {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = Integer.MAX_VALUE;
        int length = 50;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            Node root = generateList(origArr);
            Node mid = findMid(root);
            if (mid.index != origArr.length / 2 || mid.number != origArr[origArr.length / 2]) {
                print(origArr);
                break;
            }
            Node midFront = findMidFront(root);
            if (midFront.index != origArr.length / 2-1 || midFront.number != origArr[origArr.length / 2-1]) {
                print(origArr);
                break;
            }
        }
    }

    //保证生产数据样本长度大于3，并且元素个数为奇数个小于三的奇数只有1，没有前驱节点
    public static int[] generate(int min, int max, int length) {

        length = (int) (Math.random() * length) + 3;

        //保证生产的数据样本个数为奇数个
        if (length % 2 == 0) {
            length++;
        }

        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    //查询链表的中点元素
    public static Node findMid(Node root) {

        Node fast  = root;
        Node slow = root;

        //慢指针每次向后移动一个，快指针每次移动两个
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    //查询链表的中的上一个元素
    public static Node findMidFront(Node root) {

        Node fast  = root;
        Node slow = root;
        //快指针先走一步
        if (fast.next==null){
            return slow;
        }else {
            fast=fast.next.next;
        }

        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    //将样本数据变成链表
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
