package handwriting;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }
        return process(root, res);
    }

    public static List<Integer> process(Node root, List<Integer> res) {

        res.add(root.val);

        if (root.children != null && root.children.size() > 0) {
            for (Node child : root.children) {
                process(child, res);
            }
        }

        return res;
    }


    public List<Integer> preorder1(Node root) {

        ArrayList<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            res.add(pop.val);

            List<Node> children = pop.children;

            if (children != null && children.size() > 0) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }

        }

        return res;
    }
}