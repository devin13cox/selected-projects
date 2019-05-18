package byow.Core;

import java.util.Comparator;

public class Compare implements Comparator<Point> {

    public int compare(Point o1, Point o2) {

        return o1.getY() + o2.getY();
    }

}
