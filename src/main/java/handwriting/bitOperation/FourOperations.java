package handwriting.bitOperation;

//正数的四则运算逻辑
public class FourOperations {

    //两数相加
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    //取相反数
    public static int negNum(int n) {
        return add(~n, 1);
    }

    //两数相减
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    //两数相乘
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    //是否为负数
    public static boolean isNeg(int n) {
        return n < 0;
    }

    //两数相除
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

    public static void main(String[] args) {

        System.out.println("5>>1:"+(5>>1));
        System.out.println("5>>>1:"+(5>>>1));
        System.out.println("-5>>1:"+(-5>>1));
        System.out.println("-5>>>1:"+(-5>>>1));




        System.out.println("8+(-5) = :"+(add(8,15)));
        System.out.println("5-8 = :"+(minus(5,8)));
        System.out.println("8*(-5) = :"+(multi(8,-5)));
        System.out.println("8/(-5) = :"+(divide(8,-5)));
    }

}
