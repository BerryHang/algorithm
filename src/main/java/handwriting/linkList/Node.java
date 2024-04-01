package handwriting.linkList;

public class Node {

    //节点值
    int number;

    //节点在链表中所在的下标
    int index;

    //下一个指针
    Node next;

    //随机指针
    Node random;

    public Node(int number, int index) {
        this.number = number;
        this.index = index;
    }

    public Node(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", index=" + index +
                '}';
    }
}
