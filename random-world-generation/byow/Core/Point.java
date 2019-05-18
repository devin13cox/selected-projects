package byow.Core;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        return ((Point) o).getX() == this.getX() && ((Point) o).getY() == this.getY();

    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

    public static int distance(int x1, int x2, int y1, int y2) {
        return (int) Math.pow(x1 - x2, 2) + (int) Math.pow(y1 - y2, 2);
    }
}

