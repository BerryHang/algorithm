package handwriting.commonDataStructure;

import java.util.Stack;

public class MinStack {

    public static class CustomMinStack {

        private Stack<Integer> dataStack = new Stack<>();

        private Stack<Integer> minStack = new Stack<>();

        public Integer push(Integer num) {
            System.out.println("入栈：" + num);
            if (minStack.isEmpty()) {
                minStack.push(num);
            } else {
                minStack.push(Math.min(num, minStack.peek()));
            }
            return dataStack.push(num);
        }

        public Integer pop() {

            if (dataStack.isEmpty()) {
                return null;
            }
            minStack.pop();
            return dataStack.pop();
        }

        public boolean isEmpty() {
            return dataStack.isEmpty();
        }

        public Integer getMin() {
            if (minStack.isEmpty()) {
                return null;
            }
            return minStack.peek();
        }

    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int range = 5000;
        CustomMinStack stack = new CustomMinStack();
        for (int i = 0; i < testTimes; i++) {
            double random = Math.random();
            if (random < 0.33) {
                stack.push((int) (Math.random() * range) + 1);
            } else if (random >= 0.33 && random <= 0.67) {
                System.out.println("出栈：" + stack.pop());
            } else {
                System.out.println("最小值：" + stack.getMin());
            }
        }
    }

}
