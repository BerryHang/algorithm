package handwriting.random;

/**
 * 从[1,5] 随机 到 [1,6] 随机
 */
public class IntRandomToInt {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int ans = 0;
            do {
                ans = randToInt();
            } while (ans == 7);
            System.out.println("随机数：" + (ans + 1));
        }

    }

    public static int randToInt() {
        return (randTo01() << 2) + (randTo01() << 1) + randTo01();
    }

    public static int randTo01() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    //已知概率函数，不能修改 [1,5] 随机
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

}