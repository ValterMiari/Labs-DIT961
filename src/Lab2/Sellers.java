package Lab2;

import java.util.Comparator;
import Lab2.Bid;

public class Sellers implements Comparator<Bid> {

    @Override
    public int compare(Bid o1, Bid o2) {
        if (o1.equals(o2)) return 0;
        return (o1.getBid() < o2.getBid()) ? 1 : -1;
    }
}
