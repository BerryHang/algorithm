package handwriting.binaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class MaxDistance {

    //Recursive
    public static void main(String[] args) {
        int times = 1000;
        int range = 1000;
        int level = 5;
        for (int i = 0; i < times; i++) {
            Node node = generate(1, range, level);

            int maxDistance1 = maxDistance(node);
            int maxDistance2 = maxDistanceRecursive(node);

            if (maxDistance1 != maxDistance2) {
                System.out.printf("err");
            }

        }

    }

    //非递归，暴力方法破解
    public static int maxDistance(Node node) {

        if (node == null) {
            return 0;
        }

        //保存节点和父节点的映射关系 key为子节点 value为父节点
        HashMap<Node, Node> parentMap = new HashMap<>();

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        parentMap.put(node, null);

        //使用先序遍历，遍历所有节点，这里使用什么遍历方式，并不影响结果，只要将所有的节点遍历一遍即可
        while (!stack.isEmpty()) {
            Node pop = stack.pop();

            if (pop.right != null) {
                stack.push(pop.right);
                parentMap.put(pop.right, pop);
            }

            if (pop.left != null) {
                stack.push(pop.left);
                parentMap.put(pop.left, pop);
            }
        }
        AtomicInteger max = new AtomicInteger();

        Set<Node> nodes = parentMap.keySet();

        //双层循环计算出任意两个节点的距离后找到最大值
        nodes.forEach(node1 -> {
            nodes.forEach(node2 -> {

                if (node1 == node2) {
                    max.set(Math.max(max.get(), 1));
                    return;
                }

                Set<Node> nodes1 = new HashSet<>();

                nodes1.add(node1);

                Node cur = node1;

                //将node1及其所有的祖先节点保存起来
                while (parentMap.get(cur) != null) {
                    cur = parentMap.get(cur);
                    nodes1.add(cur);
                }

                cur = node2;
                //寻找node1和 node2的公共祖先,并记录node2节点到该祖先节点的距离
                int halfPath = 1;
                while (!nodes1.contains(cur)) {
                    halfPath++;
                    cur = parentMap.get(cur);
                }

                //记录祖先节点
                Node ancestor = cur;

                cur = node1;
                //计算node1到该祖先节点的距离
                int otherHalfPath = 1;

                while (ancestor != cur) {
                    otherHalfPath++;
                    cur = parentMap.get(cur);
                }

                //计算整体路径长度，由于祖先节点计算了两次需要减1
                max.set(Math.max(max.get(), halfPath + otherHalfPath - 1));
            });
        });

        return max.get();
    }

    //递归方式处理
    public static int maxDistanceRecursive(Node node) {

        if (node == null) {
            return 0;
        }

        return process(node).maxDistance;
    }

    public static Info process(Node node) {

        if (node == null) {
            return new Info(0, 0);
        }

        //获取左右节点的数据信息
        Info left = process(node.left);
        Info right = process(node.right);

        //计算当前高度位最大子树高度加一
        int high = Math.max(left.high, right.high) + 1;

        //计算最大距离取两棵子树以及经过当前节点的路径长度（经过当前节点的路径长度就等于两棵子树的高度相加再加上节点本身的1）
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.high + right.high + 1);

        return new Info(high, maxDistance);
    }

    public static class Info {
        //当前节点的高度
        int high;
        //当前节点所在二叉树的结构中最大的距离
        int maxDistance;

        public Info(int high, int maxDistance) {
            this.high = high;
            this.maxDistance = maxDistance;
        }
    }

    public static Node generate(int level, int range, int maxLevel) {
        double random = Math.random();
        if (random < 0.3 || level > maxLevel) {
            return null;
        } else {
            Node head = new Node((int) (Math.random() * range));
            head.left = generate(level + 1, range, maxLevel);
            head.right = generate(level + 1, range, maxLevel);
            return head;
        }
    }
}
