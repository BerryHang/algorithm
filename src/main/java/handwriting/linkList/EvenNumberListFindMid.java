package handwriting.linkList;

//在一个链表中如果集合元素个数为偶数个，找到当前链表的上中点、下中点和上中点的前一个
public class EvenNumberListFindMid {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = Integer.MAX_VALUE;
        int length = 50;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            Node root = generateList(origArr);
            Node frontMid = findFrontMid(root);
            if (frontMid.index != origArr.length / 2-1 || frontMid.number != origArr[origArr.length / 2-1]) {
                print(origArr);
                break;
            }
            Node behindMid = findBehindMid(root);
            if (behindMid.index != origArr.length / 2 || behindMid.number != origArr[origArr.length / 2]) {
                print(origArr);
                break;
            }
            Node frontMidPre = findFrontMidPre(root);
            if (frontMidPre.index != origArr.length / 2-2 || frontMidPre.number != origArr[origArr.length / 2-2]) {
                print(origArr);
                break;
            }
        }
    }

    public static int[] generate(int min, int max, int length) {

        length = (int) (Math.random() * length) + 3;

        //保证生产的数据样本个数为偶数个
        if (length % 2 != 0) {
            length++;
        }

        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    //查询上中点
    public static Node findFrontMid(Node root) {

        Node fast = root;
        Node slow = root;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //查询上中点
    public static Node findFrontMidPre(Node root) {

        Node fast = root;
        Node slow = root;
        fast = fast.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //查询下中点
    public static Node findBehindMid(Node root) {

        Node fast = root;
        Node slow = root;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.next;
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
