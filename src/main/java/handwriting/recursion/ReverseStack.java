package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Stack;

public class ReverseStack {

    public static void main(String[] args) {

        int times = 1000;
        int length = 5;

        for (int i = 0; i < times; i++) {
            int size = (int) (Math.random() * length + 1);
            Stack<String> stack = new Stack<>();
            System.out.printf("压栈顺序：");
            for (int j = 0; j < size; j++) {
                String random = RandomStringUtils.randomAlphabetic(5);
                System.out.printf( random+ " ");
                stack.push(random);
            }
            System.out.println();
            reverseStack(stack);
            System.out.printf("出栈顺序：");
            while (!stack.isEmpty()){
                System.out.printf( stack.pop()+ " ");
            }
            System.out.println();
        }

    }

    //逆序栈数据，这里递归过程中，每个方法栈的栈针都是逆序拿到的栈元素
    public static void reverseStack(Stack<String> stack) {
        //当栈不为空时
        if (!stack.isEmpty()) {
            //先拿到栈底元素
            String bottom = popStackBottom(stack);
            //递归调用自己，每层都会拿到当时栈的栈底元素
            reverseStack(stack);
            //处理完后，将自己拿到的元素压回原栈
            stack.push(bottom);
        }
    }

    //弹出栈底元素，这里方法栈的栈针每次都是顺序拿到栈上的数据
    public static String popStackBottom(Stack<String> stack) {

        //从栈中弹出一个元素
        String pop = stack.pop();
        //如果栈为空，当前元素位栈底元素，直接返回
        if (stack.isEmpty()) {
            return pop;
        } else {
            //不为空时递归调用自己，并但会结果
            String ans = popStackBottom(stack);
            //在拿到结果后重新将自己弹出的元素压会原栈
            stack.push(pop);
            return ans;
        }
    }

}
