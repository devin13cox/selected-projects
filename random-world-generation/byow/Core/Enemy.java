package byow.Core;

import java.util.ArrayList;

public class Enemy {
    private Point enemy;
    private int counter;
    private ArrayList<Point> path;
    private boolean activated;
    private int index;
    private int wait;

    public Enemy(Point location, int wait) {
        this.enemy = location;
        this.activated = false;
        this.path = new ArrayList<>();
        this.index = 0;
        this.counter = 0;
        this.wait = wait;
    }

    public Point getEnemy() {
        return enemy;
    }

    public void activate() {
        activated = true;
    }

    public boolean isActivated() {
        return activated;
    }

    public void pathAdd(Point p) {
        path.add(p);
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void move() {
        if (isActivated() && counter < wait + 3) {
            counter++;
        } else if (isActivated() && index < path.size()) {
            enemy = path.get(index);
            index++;
        }
    }

    public int getIndex() {
        return index;
    }




}
