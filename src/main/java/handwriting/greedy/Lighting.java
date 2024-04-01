package handwriting.greedy;

import java.util.HashSet;

public class Lighting {


    public static void main(String[] args) {
        int times = 10000;
        int length = 30;

        for (int i = 0; i < times; i++) {

            String s = generator(length);
            int count1 = lightingByGreedy(s);
            int count2 = minLight1(s);
            if (count1 != count2) {
                System.out.printf("err");
            }
        }
    }

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static int lightingByGreedy(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < s.length(); i++) {

            //如果当前字符是 X 直接进行下一次循环，当不是时
            if (s.charAt(i) != 'X') {
                //先对灯数加一
                res++;
                //如果下一个元素已经到结束了 直接跳出循环
                if (i + 1 == s.length()) {
                    break;
                } else {
                    //那么检测下一个位置是不是 X 如果是 X那么跳到X后面一个位置
                    if (s.charAt(i + 1) == 'X') {
                        i++;
                    } else {
                        //如果不是叉直接往后跳三个位置 因为当前点的不一定是放在i位置，如果联系的点，前面点的灯可以用在i+1位置 点亮 i i+1 i+2 三个位置，因此后跳三个位置
                        i += 2;
                    }
                }
            }

        }

        return res;
    }

    public static String generator(int length) {
        length = (int) (Math.random() * length + 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (Math.random() < 0.3) {
                sb.append('X');
            } else {
                sb.append('.');
            }
        }

        return sb.toString();
    }

}
