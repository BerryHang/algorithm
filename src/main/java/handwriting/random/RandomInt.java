package handwriting.random;

public class RandomInt {

    public static void main(String[] args) {
        validateRandom();
        randomInt();
        randomToSquare();
        randomToCube();
    }

    public static void validateRandom() {
        long times = 10000;
        long count = 0;
        for (int i = 0; i < times; i++) {
            double random = Math.random();
            if (random < 0.3) {
                count++;
            }
        }
        System.out.println("小于0.3的概率：" + ((double) count / (double) times));
    }

    public static void randomInt() {
        int[] counts = new int[10];
        int times = 10000000;
        for (int i = 0; i < times; i++) {
            counts[(int) (Math.random() * counts.length)]++;

        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + "出现了：" + counts[i] + "次");
        }
    }

    public static void randomToSquare() {
        long times = 10000;
        long count = 0;
        for (int i = 0; i < times; i++) {
            double random = Math.max(Math.random(), Math.random());
            if (random < 0.3) {
                count++;
            }
        }
        System.out.println("小于0.3的概率：" + ((double) count / (double) times));
    }

    public static void randomToCube() {
        long times = 10000;
        long count = 0;
        for (int i = 0; i < times; i++) {
            double random = Math.max(Math.max(Math.random(), Math.random()), Math.random());
            if (random < 0.3) {
                count++;
            }
        }
        System.out.println("小于0.3的概率：" + ((double) count / (double) times));
    }

}
