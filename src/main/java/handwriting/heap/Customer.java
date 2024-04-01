package handwriting.heap;

public class Customer {

    //用户ID
    int id;

    //购买商品数量
    int number;

    //进去获奖区或者候选区的时间
    int enterTime;

    public Customer(int id, int number, int enterTime) {
        this.id = id;
        this.number = number;
        this.enterTime = enterTime;
    }
}

