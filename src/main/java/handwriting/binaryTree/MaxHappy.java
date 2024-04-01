package handwriting.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy {

    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }
    }

    public static void main(String[] args) {
        int times = 10000;
        int happy = 50;
        int level = 10;
        int child = 10;

        for (int i = 0; i < times; i++) {

            Employee master = generator(happy, (int) (Math.random() * level + 1), child);

            if (maxHappyRecursive1(master) != maxHappyRecursive2(master)) {
                System.out.printf("err");
            }

        }

    }

    public static int maxHappyRecursive2(Employee master) {
        if (master == null) {
            return 0;
        }
        // master 没有上级，默认上级不参加
        return process2(master, false);
    }

    //  boolean join 代表上级是否参加
    public static int process2(Employee employee, boolean join) {

        if (join) {
            //当上级参加的情况下，自己不能参加，初始值为0，并请求下级，告诉下级自己不参加
            int no = 0;

            for (Employee child : employee.subordinates) {
                no += process2(child, false);
            }
            return no;
        } else {
            //当上级不参加的情况下，自己自己可以参加也可以不参加
            int yes = employee.happy;
            int no = 0;

            //分别求出自己参加和不参加的情况下子节点的情况
            for (Employee child : employee.subordinates) {
                yes += process2(child, true);
                no += process2(child, false);
            }
            return Math.max(yes, no);
        }
    }

    public static int maxHappyRecursive1(Employee master) {

        if (master == null) {
            return 0;
        }

        Info info = process1(master);
        return Math.max(info.no, info.yes);
    }

    public static Info process1(Employee employee) {

        if (employee == null) {
            return new Info(0, 0);
        }

        //当前节点参加和不参加的情况进行初始化
        int yes = employee.happy;
        int no = 0;

        for (Employee child : employee.subordinates) {
            //获取子节点的信息
            Info process = process1(child);
            //当前节点参加的情况下只能加下级不参加的数据
            yes += process.no;

            //当前节点不参加的情况下 取下级参加和不参加两种情况的最大值
            no += Math.max(process.no, process.yes);
        }

        return new Info(yes, no);
    }

    public static class Info {

        //当前节点参加的最大happy值
        int yes;
        //当前节点不参加的最大happy值
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    private static Employee generator(int happy, int level, int child) {

        if (level == 0) {
            return null;
        }

        ArrayList<Employee> employees = new ArrayList<>();

        int random = (int) (Math.random() * child + 1);

        for (int i = 0; i < random; i++) {
            Employee employee = generator(happy, level - 1, child);
            if (employee != null) {
                employees.add(employee);
            }
        }

        return new Employee((int) (Math.random() * happy + 1), employees);
    }

}
