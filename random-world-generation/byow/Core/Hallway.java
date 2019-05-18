package byow.Core;

import java.util.Random;

public class Hallway {
    private Random seed;
    private Point start;
    private Point end;
    private int length;
    private int direction;


    public Hallway(Random rand, Point p) {
        this.seed = rand;
        this.direction = RandomUtils.uniform(seed, 0, 2);
        this.start = p;
        if (this.direction == 0) {
            this.end = new Point(start.getX(),
                    RandomUtils.uniform(seed, start.getY() - 9, start.getY() + 9));
            //EDIT IF CHANGE HEIGHT
            if (start.getY() > 50 || start.getY() < 15) {
                this.length = 0;
            }
            this.length = Math.abs(start.getY() - end.getY());
        } else {
            this.end = new Point(RandomUtils.uniform(seed,
                    start.getX() - 9, start.getX() + 9), start.getY());
            //EDIT IF CHANGE WIDTH
            if (start.getX() > 50 || start.getX() < 15) {
                this.length = 0;
            }
            this.length = Math.abs(start.getX() - end.getX());
        }

    }

    public int getLength() {
        return length;
    }

    public int getDirection() {
        return direction;
    }

    public Point getStart() {
        return start;
    }
}
