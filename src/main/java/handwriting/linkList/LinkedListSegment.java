package handwriting.linkList;

/**
 * 对链表进行分段排序
 */
public class LinkedListSegment {

    public static void main(String[] args) {
        int min = 10;
        int max = 30;
        int range = 10;
        int length = 10;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(min, max, length);

            int target = (int) (Math.random() * (max + range - min) + min - range);

            int[] arrSort = sortByArr(origArr, target);

            Node root = generateList(origArr);

            Node node = listSegment(root, target);

            if (!compare(node, arrSort)) {
                print(origArr);
            }

        }
    }

    public static Node listSegment(Node root, int target) {
        Node node = root;

        //小于区域的开始指针和结束指针
        Node smallHead = null;
        Node smallTail = null;

        //等于区域的开始指针和结束指针
        Node equalHead = null;
        Node equalTail = null;

        //大于区域的开始指针和结束指针
        Node bigHead = null;
        Node bigTail = null;

        //保留指针位置
        Node next = node;

        while (node != null) {
            next = node.next;

            //处理小于区域
            if (node.number < target) {

                //当头指针为空的时候还没有元素，直接小于区域的头尾指针都指向当前元素，反之追加到尾节点
                if (smallHead == null) {
                    smallHead = node;
                    smallTail = node;
                } else {
                    smallTail.next = node;
                    smallTail = smallTail.next;
                }
                //处理等于区域
            } else if (node.number == target) {

                //当头指针为空的时候还没有元素，直接等于区域的头尾指针都指向当前元素，反之追加到尾节点
                if (equalHead == null) {
                    equalHead = node;
                    equalTail = node;
                } else {
                    equalTail.next = node;
                    equalTail = equalTail.next;
                }
                //处理大于区域
            } else {

                //当头指针为空的时候还没有元素，直接大于区域的头尾指针都指向当前元素，反之追加到尾节点
                if (bigHead == null) {
                    bigHead = node;
                    bigTail = node;
                } else {
                    bigTail.next = node;
                    bigTail = bigTail.next;
                }
            }
            node = next;
        }

        //判断小于区域的为节点是否为空，不为空的时候链接等于区域的信息
        if (smallTail != null) {
            smallTail.next = equalHead;
            //如果等于区域的尾指针为空将等于区域的指针指向小于区域的尾指针
            equalTail = (equalTail == null ? smallTail : equalTail);
        }

        //等于区域的尾元素不为空时，连接大于区域
        if (equalTail != null) {
            equalTail.next = bigHead;
        }

        //返回不为空部分的头节点
        return smallHead == null ? equalHead == null ? bigHead : equalHead : smallHead;
    }

    //数组进行分段排序
    public static int[] sortByArr(int[] arr, int target) {

        int[] copyArr = new int[arr.length];

        int j = 0;

        //处理小于区域
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < target) {
                copyArr[j++] = arr[i];
            }
        }

        //处理等于区域
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                copyArr[j++] = arr[i];
            }
        }

        //处理大于区域
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > target) {
                copyArr[j++] = arr[i];
            }
        }

        return copyArr;
    }

    public static int[] generate(int min, int max, int length) {

        length = (int) (Math.random() * length) + 1;

        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
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

        Node node = root;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != node.number) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

}
