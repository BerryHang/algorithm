package handwriting.prefixTree;

import org.apache.commons.lang3.RandomStringUtils;

public class TestPrefixTree {

    public static void main(String[] args) {

        int testTimes = 10000;
        int charLength = 5;
        int length = 10000;

        for (int i = 0; i < testTimes; i++) {

            String[] generator = generator(charLength, length);

            NodeByArray nodeByArray = new NodeByArray();
            NodeByMap nodeByMap = new NodeByMap();

            Standard standard = new Standard();

            for (int j = 0; j < generator.length; j++) {

                double random = Math.random();

                //利用随机数实现不同的操作
                if (random<0.25){
                    int r1 = nodeByArray.insert(generator[j]);
                    int r2 = nodeByMap.insert(generator[j]);
                    int r3 = standard.insert(generator[j]);

                    if (r1!=r3||r2!=r3){
                        System.out.printf("err");
                        break;
                    }

                } else if (random<0.5) {
                    int r1 = nodeByArray.search(generator[j]);
                    int r2 = nodeByMap.search(generator[j]);
                    int r3 = standard.search(generator[j]);
                    if (r1!=r3||r2!=r3){
                        System.out.printf("err");
                        break;
                    }
                } else if (random<0.75) {
                    int r1 = nodeByArray.preSearch(generator[j]);
                    int r2 = nodeByMap.preSearch(generator[j]);
                    int r3 = standard.preSearch(generator[j]);
                    if (r1!=r3||r2!=r3){
                        System.out.printf("err");
                        break;
                    }
                }else {
                    boolean r1 = nodeByArray.delete(generator[j]);
                    boolean r2 = nodeByMap.delete(generator[j]);
                    boolean r3 = standard.delete(generator[j]);
                    if (r1!=r3||r2!=r3){
                        System.out.printf("err");
                        break;
                    }
                }

            }

        }
    }

    public static String[] generator(int charLength, int length) {

        //生成随机的样本数据长度
        length = (int) (Math.random() * length + 1);

        //创建数组
        String[] res = new String[length];

        for (int i = 0; i < length; i++) {
            //生成一个随机长度的小写字符串信息
            res[i] = RandomStringUtils.randomAlphabetic((int) (Math.random() * charLength + 1)).toLowerCase();
        }
        return res;
    }

}
