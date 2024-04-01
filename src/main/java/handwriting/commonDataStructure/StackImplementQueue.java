package handwriting.commonDataStructure;

import java.util.Stack;

//用栈实现队列
public class StackImplementQueue {

    //入队的栈
    private static Stack<Integer> stack1 = new Stack<>();

    //出队的栈
    private static Stack<Integer> stack2 = new Stack<>();

    public static void main(String[] args) {
        int testTimes = 1000;
        int range = 5000;

        for (int i = 0; i < testTimes; i++) {
            if (((int) (Math.random() * 2)) % 2 == 0) {
                System.out.println("入队：" + push((int) (Math.random() * range) + 1));
            } else {
                System.out.println("出队：" + pop());
            }
        }
    }

    //入队操作
    private static int push(int num) {
        return stack1.push(num);
    }

    //出队操作
    private static int pop() {
        //先判断出队的栈是否为空
        if (stack2.isEmpty()) {
            //再判断入队的栈是否为空，如果为空则证明这个队列中没有数据
            if (stack1.isEmpty()){
                System.out.printf("队列为空");
                return -1;
            }
            //入队的栈不为空时，将入队栈中的数据依次弹出到出队的栈这中，实现数据顺序的反转
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        //先判断出队的栈不为空直接弹出元素
        return stack2.pop();
    }

}
