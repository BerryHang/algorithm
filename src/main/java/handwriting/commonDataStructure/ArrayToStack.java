package handwriting.commonDataStructure;

//用数组实现一个栈
public class ArrayToStack {

    public static int[] arr = new int[10];

    public static int index = 0;

    public static void main(String[] args) {

        int testTimes = 1000;
        int rondom = 9;
        int range = 5000;

        for (int i = 0; i < testTimes; i++) {
            if (((int) (Math.random() * rondom)) % 2 == 0) {
                push((int) (Math.random() * range)+1);
            } else {
                System.out.println("出栈：" + pop());
            }
            print();
        }
    }


    private static int push(int num) {
        if (index == arr.length) return -1;
        System.out.println("压栈：" + num);
        arr[index++] = num;
        return index;
    }

    private static int pop() {
        if (index == 0) return -1;
        return arr[--index];
    }

    private static void print() {
        for (int i = 0; i < index; i++) {
            System.out.printf(arr[i]+ " ");
        }
        System.out.println();
    }

}
