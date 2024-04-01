package handwriting.heap;

import java.util.Comparator;

public class WinnerComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.number == o2.number ? o1.enterTime - o2.enterTime : o1.number - o2.number;
    }

}