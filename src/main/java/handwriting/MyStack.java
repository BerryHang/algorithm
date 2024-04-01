package handwriting;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> queue1 = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {

        int size = queue1.size() - 1;

        for (int i = 0; i < size; i++) {
            queue1.add(queue1.poll());
        }

        return queue1.poll();
    }

    public int top() {
        int size = queue1.size() - 1;

        for (int i = 0; i < size; i++) {
            queue1.add(queue1.poll());
        }

        int res =queue1.poll();
        queue1.add(res);
        return res;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
