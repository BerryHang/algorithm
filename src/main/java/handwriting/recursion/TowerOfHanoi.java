package handwriting.recursion;

public class TowerOfHanoi {

    public static void main(String[] args) {
        leftToRight(3);
        System.out.println("=========");
        hanoi(3, "left", "right", "mid");
    }

    public static void hanoi(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 " + from + " to " + to);
        } else {
            hanoi(n - 1, from, other, to);
            System.out.println("move " + n + " " + from + " to " + to);
            hanoi(n - 1, other, to, from);
        }
    }


    public static void leftToRight(int n) {

        if (n == 1) {
            System.out.println("move 1 left to right");
        } else {
            leftToMid(n - 1);
            System.out.println("move " + n + " left to right");
            midToRight(n - 1);
        }
    }

    public static void leftToMid(int n) {

        if (n == 1) {
            System.out.println("move 1 left to mid");
        } else {
            leftToRight(n - 1);
            System.out.println("move " + n + " left to mid");
            rightToMid(n - 1);
        }
    }

    public static void midToRight(int n) {

        if (n == 1) {
            System.out.println("move 1 mid to right");
        } else {
            midToLeft(n - 1);
            System.out.println("move " + n + " mid to right");
            leftToRight(n - 1);
        }
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 right to mid");
        } else {
            rightToLeft(n - 1);
            System.out.println("move " + n + " right to mid");
            leftToMid(n - 1);
        }
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 mid to left");
        } else {
            midToRight(n - 1);
            System.out.println("move " + n + " mid to left");
            rightToLeft(n - 1);
        }
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 right to left");
        } else {
            rightToMid(n - 1);
            System.out.println("move " + n + " right to left");
            midToLeft(n - 1);
        }
    }

}
