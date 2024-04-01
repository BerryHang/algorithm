package handwriting.heap;

import java.util.Comparator;

public class CandidateComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o2.number == o1.number ? o1.enterTime - o2.enterTime : o2.number - o1.number;
    }
}
