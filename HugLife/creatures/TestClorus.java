package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(1);
        assertEquals(1, c.energy(), 0.1);
        Plip p = new Plip(1);
        c.attack(p);
        assertEquals(2, c.energy(), 0.1);
        Clorus baby = c.replicate();
        assertEquals(1, c.energy(), 0.1);
        assertEquals(1, baby.energy(), 0.1);
        Clorus grandkid = baby.replicate();
        Clorus twin = c.replicate();
        assertEquals(0.5, c.energy(), 0.1);
        assertEquals(0.5, baby.energy(), 0.1);
        assertEquals(0.5, grandkid.energy(), 0.1);
        assertEquals(0.5, twin.energy(), 0.1);

    }

    @Test
    public void testChooseSurroundedOrPlip() {
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        c = new Clorus(1.2);
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> plipThere = new HashMap<Direction, Occupant>();
        plipThere.put(Direction.TOP, p);
        plipThere.put(Direction.RIGHT, new Empty());
        plipThere.put(Direction.BOTTOM, new Empty());
        plipThere.put(Direction.LEFT, new Empty());
        actual = c.chooseAction(plipThere);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual);
    }

    @Test
    public void testChooseReplicate() {
        // Energy >= 1; replicate towards an empty space.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(topEmpty);
        Action expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(expected, actual);

        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);
        Action unexpected1T = new Action(Action.ActionType.ATTACK, Direction.TOP);
        Action unexpected1R = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        Action unexpected1B = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        Action unexpected1L = new Action(Action.ActionType.ATTACK, Direction.LEFT);
        Action unexpected2T = new Action(Action.ActionType.MOVE, Direction.TOP);
        Action unexpected2R = new Action(Action.ActionType.MOVE, Direction.RIGHT);
        Action unexpected2B = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        Action unexpected2L = new Action(Action.ActionType.MOVE, Direction.LEFT);
        Action unexpected3 = new Action(Action.ActionType.DIE);
        assertNotEquals(unexpected, actual);
        assertNotEquals(unexpected1T, actual);
        assertNotEquals(unexpected1R, actual);
        assertNotEquals(unexpected1B, actual);
        assertNotEquals(unexpected1L, actual);
        assertNotEquals(unexpected2T, actual);
        assertNotEquals(unexpected2R, actual);
        assertNotEquals(unexpected2B, actual);
        assertNotEquals(unexpected2L, actual);
        assertNotEquals(unexpected3, actual);

        c = new Clorus(.99);
        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);

        // Energy < 1; move.
        c = new Clorus(.99);
        actual = c.chooseAction(allEmpty);
        unexpected = new Action(Action.ActionType.STAY);
        unexpected1T = new Action(Action.ActionType.ATTACK, Direction.TOP);
        unexpected1R = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        unexpected1B = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        unexpected1L = new Action(Action.ActionType.ATTACK, Direction.LEFT);
        unexpected2T = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        unexpected2R = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        unexpected2B = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        unexpected2L = new Action(Action.ActionType.REPLICATE, Direction.LEFT);
        unexpected3 = new Action(Action.ActionType.DIE);
        assertNotEquals(unexpected, actual);
        assertNotEquals(unexpected1T, actual);
        assertNotEquals(unexpected1R, actual);
        assertNotEquals(unexpected1B, actual);
        assertNotEquals(unexpected1L, actual);
        assertNotEquals(unexpected2T, actual);
        assertNotEquals(unexpected2R, actual);
        assertNotEquals(unexpected2B, actual);
        assertNotEquals(unexpected2L, actual);
        assertNotEquals(unexpected3, actual);
    }
}
