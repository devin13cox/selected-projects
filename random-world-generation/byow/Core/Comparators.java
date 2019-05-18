package byow.Core;
import java.util.Comparator;


public class Comparators implements Comparator<Point> {
    public int compare(Point o1, Point o2) {
        return o1.getX() - o2.getX();
    }
}

