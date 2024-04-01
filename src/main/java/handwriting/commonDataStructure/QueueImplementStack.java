package handwriting.commonDataStructure;

import java.util.LinkedList;
import java.util.Queue;

//用队列实现栈结构
public class QueueImplementStack {


    public static void main(String[] args) {
        int testTimes = 1000;
        int range = 5000;
        CustomStack<Integer> stack = new CustomStack<>();

        for (int i = 0; i < testTimes; i++) {
            if (((int) (Math.random() * 2)) % 2 == 0) {
                int num = (int) (Math.random() * range) + 1;
                System.out.println("入栈：" + num);
                stack.push(num);
            } else {
                System.out.println("出栈：" + stack.pop());
            }
        }

    }

    public static class CustomStack<T> {

        //操作栈数据的队列
        private Queue<T> queue = new LinkedList<>();

        //辅助集合
        private Queue<T> help = new LinkedList<>();

        //往栈中添加数据
        public boolean push(T t){
            return queue.offer(t);
        }

        //从栈中弹出数据
        public T pop(){
            //如果队列中的数据大于一个时
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T t = queue.poll();
            Queue temp = help;
            help= queue;
            queue=temp;
            return t;
        }

        public T peek(){
            if (queue.size()>1){
                help.offer(queue.poll());
            }
            T t = queue.poll();
            help.offer(t);
            Queue temp = help;
            help= queue;
            queue=temp;
            return t;
        }

    }

}

