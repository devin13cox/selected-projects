package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        this.energy += c.energy();
    }

    double temp = 0;
    public void move() {
        if (this.energy <= 0.03) {
            this.energy = 0;
        } else {
            this.energy -= 0.03;
        }
    }

    public void stay() {
        if (this.energy <= 0.01) {
            this.energy = 0;
        } else {
            this.energy -= 0.01;
        }
    }

    public Clorus replicate() {
        this.energy = energy() / 2;
        double baby = energy();
        Clorus child = new Clorus(baby);
        return child;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addFirst(d);
            }
            if (neighbors.get(d).name().equals("plip")) {
                plipNeighbors.addFirst(d);
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plipNeighbors.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (this.energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }



}
