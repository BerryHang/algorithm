package handwriting.binaryTree;

public class PaperFolding {

    public static void main(String[] args) {

        int times = 2;
        int n = 50;

        for (int i = 0; i < times; i++) {
            paperFolding((int) (Math.random() * n + 1));
            System.out.println("");
        }

    }

    public static void paperFolding(int n) {
        process(true, n);
    }

    public static void process(boolean down, int n) {
        if (n == 0) {
            return;
        }

        process(true, n - 1);

        String s = down ? "down" : "up";
        System.out.printf(s + " ");
        process(false, n - 1);
    }

}
