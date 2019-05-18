package byow.Core;
import java.util.Random;

public class Room {
    private int height;
    private int width;
    private int size;
    //private String theme;
    //private int exits[][];
    private boolean twoExits;
    private boolean hasEnemies;
    private Random seed;

    //ADD THEME LATER
    public Room(Random seed) {
        this.seed = seed;
        this.height = RandomUtils.gaussian(this.seed, 4, 2);
        this.width = RandomUtils.gaussian(this.seed, 4, 2);
        this.size = this.height * this.width;
        //this.theme = Theme;
        this.twoExits = RandomUtils.bernoulli((this.seed), 0.8);
        //this.exits = new int[2][2];

    }

    //formatted [x,y] so [width, height]
    //FOR SIDE: 1 = top, 2 == bottom, 3 == left, 4 == right
    //determines the side and placement of the exit(s) of the room
    //i imagine we'll add the exit coordinates to the BOTTOM LEFT CORNER of the room's coordinates
   /* public void createExits() {
        int side = RandomUtils.uniform(seed, 1, 5);
        if (side == 1) {
            this.exits[0][0] = RandomUtils.uniform(seed, 1, this.width);
            this.exits[0][1] = this.height - 2;
        } else if (side == 2) {
            this.exits[0][0] = RandomUtils.uniform(seed, 1, this.width);
            this.exits[0][1] = 1;
        } else if (side == 3) {
            this.exits[0][0] = 1;
            this.exits[0][1] = RandomUtils.uniform(seed, 1, this.height);
        } else if (side == 4) {
            this.exits[0][0] = this.width - 2;
            this.exits[0][1] = RandomUtils.uniform(seed, 1, this.height);
        }


        if (twoExits) {
            int side2 = RandomUtils.uniform(seed, 1, 5);
            if (side2 == side) {
                if (side == 1) {
                    side2 = RandomUtils.uniform(seed, 2, 5);
                } else {
                    side2 = RandomUtils.uniform(seed, 1, side);
                }
            }
            if (side2 == 1) {
                this.exits[1][0] = RandomUtils.uniform(seed, 1, this.width);
                this.exits[1][1] = this.height - 2;
            } else if (side2 == 2) {
                this.exits[1][0] = RandomUtils.uniform(seed, 1, this.width);
                this.exits[1][1] = 1;
            } else if (side == 3) {
                this.exits[1][0] = 1;
                this.exits[1][1] = RandomUtils.uniform(seed, 1, this.height);
            } else if (side == 4) {
                this.exits[1][0] = this.width - 2;
                this.exits[1][1] = RandomUtils.uniform(seed, 1, this.height);
            }
        }
    }
    */

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Random getSeed() {
        return seed;
    }

    public boolean isHasEnemies() {
        return hasEnemies;
    }

    //public String getTheme() {
        //return theme;
    //}

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        return ((Room) o).getWidth() == this.getWidth()
                && ((Room) o).getHeight() == this.getHeight();
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }


}

