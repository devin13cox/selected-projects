package byow.Core;


import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WorldBuilder {
    private ArrayList<Point> floors;

    private ArrayList<Point> walls;
    private Point avatar;

    private ArrayList<Point> goals;
    private ArrayList<Enemy> enemies;
    private Enemy enemyOne;
    private Enemy enemyTwo;
    private Enemy enemyThree;

    private Random seed;
    private Point h1;
    private TETile[] tiles;
    private TETile[][] world;


    public WorldBuilder(Random input) {
        seed = input;
        floors = new ArrayList<>();
        walls = new ArrayList<>();
        tiles = new TETile[7];
        avatar = null;
        goals = new ArrayList<>();
        enemies = new ArrayList<>();
    }
    public void createWays(int min, int max) {
        int num = RandomUtils.uniform(seed, min, max);
        this.h1 = new Point(RandomUtils.uniform(seed, Engine.WIDTH / 3, (2 * Engine.WIDTH) / 3),
                RandomUtils.uniform(seed, Engine.HEIGHT / 5, (3 * Engine.HEIGHT) / 5));
        Hallway path = new Hallway(seed, h1);
        floors.add(h1);
        for (int x = 0; x < path.getLength(); x++) {
            floors.add(new Point(path.getStart().getX() + x, path.getStart().getY()));
        }
        for (int i = 0; i < num; i++) {
            int dir = RandomUtils.uniform(seed, 0, 2);
            if (dir == 0) {
                uphallWays();
            } else {
                downhallWays();
            }
        }
    }

    public void uphallWays() {
        /*int numHallways = RandomUtils.uniform(seed, 10, 40);
        this.h1 = new Point(RandomUtils.uniform(seed, 8, 70), RandomUtils.uniform(seed, 8, 40));
        Hallway path = new Hallway(seed, h1);
        for (int x = 0; x < path.getLength(); x++) {
            floors.add(new Point(path.getStart().getX() + x, path.getStart().getY()));
        }

        for (int i = 0; i < numHallways; i++) {*/
        if (floors.size() > 1) {
            int index = RandomUtils.uniform(seed, 0, floors.size());
            h1 = floors.get(index);
        } else if (floors.size() == 0) {
            floors.add(this.h1);
        } else {
            h1 = floors.get(0);
        }

        Hallway paths = new Hallway(seed, h1);
        if (paths.getStart().getX() < 40 && paths.getStart().getY() < 35) {
            if (paths.getLength() > 2 && paths.getDirection() == 0) {
                for (int y = 0; y < paths.getLength(); y++) {
                    Point pp = new Point(paths.getStart().getX(), paths.getStart().getY() + y);
                    floors.add(pp);
                }
            } else if (paths.getLength() > 2) {
                for (int x = 0; x < paths.getLength(); x++) {
                    floors.add(new Point(paths.getStart().getX() + x, paths.getStart().getY()));
                }
            }
        }
    }

    public void downhallWays() {
        /*this.h1 = new Point(RandomUtils.uniform(seed, 8, 71), RandomUtils.uniform(seed, 8, 41));
        Hallway path = new Hallway(seed, h1);
        for (int x = 0; x < path.getLength(); x++) {
            floors2.add(new Point(path.getStart().getX() - x, path.getStart().getY())); */
        if (floors.size() > 1) {
            int index = RandomUtils.uniform(seed, 0, floors.size());
            h1 = floors.get(index);
        } else if (floors.size() == 0) {
            floors.add(this.h1);
        } else {
            h1 = floors.get(0);
        }

        Hallway paths = new Hallway(seed, h1);
        if (paths.getStart().getX() > 15 && paths.getStart().getY() > 15) {
            if (paths.getLength() > 2 && paths.getDirection() == 0) {
                for (int y = 0; y < paths.getLength(); y++) {
                    Point pp = new Point(paths.getStart().getX(), paths.getStart().getY() - y);
                    floors.add(pp);
                }
            } else if (paths.getLength() > 2) {
                for (int x = 0; x < paths.getLength(); x++) {
                    floors.add(new Point(paths.getStart().getX() - x, paths.getStart().getY()));
                }
            }
        }

    }

    public void rooMs() {
        int numRooms = RandomUtils.uniform(seed, 20, 80);
        int count = 0;

        //for (int i = 0; i < numRooms; i++) {
        while (count < numRooms) {
            Room room = new Room(seed);
            int index = RandomUtils.uniform(seed, 0, floors.size());
            Point start = floors.get(index);
            Point above = new Point(start.getX(), start.getY() + 1);
            Point below = new Point(start.getX(), start.getY() - 1);
            Point left = new Point(start.getX() - 1, start.getY());
            Point right = new Point(start.getX() + 1, start.getY());

            if (!isFloor(above) && !isFloor(below) && !isFloor(left)
                    || !isFloor(above) && !isFloor(below) && !isFloor(right)
                    || !isFloor(left) && !isFloor(below) && !isFloor(right)
                    || !isFloor(left) && !isFloor(above) && !isFloor(right)
                    || !isFloor(left) && !isFloor(above)
                    || !isFloor(left) && !isFloor(below)) {

                count += 1;
                for (int x = 0; x < room.getWidth(); x++) {
                    for (int y = 0; y < room.getHeight(); y++) {
                        floors.add(new Point(start.getX() + x, start.getY() + y));
                    }
                }
            } else {
                int check = count % 2;
                switch (check) {
                    case 0: default:
                        Collections.sort(floors, new Comparators());
                        break;
                    case 1:
                        Collections.sort(floors, new Compare());
                        break;
                }
                Hallway paths = new Hallway(seed, floors.get(0));
                if (paths.getStart().getX() > 15 && paths.getStart().getY() > 15) {
                    if (paths.getLength() > 2 && paths.getDirection() == 0) {
                        for (int y = 0; y < paths.getLength(); y++) {
                            Point pp = new Point(paths.getStart().getX(),
                                    paths.getStart().getY() - y);
                            floors.add(pp);
                        }
                    } else if (paths.getLength() > 2) {
                        for (int x = 0; x < paths.getLength(); x++) {
                            floors.add(new Point(paths.getStart().getX() - x,
                                    paths.getStart().getY()));
                        }
                    }
                }
            }

        }
    }


    public void makeWalls() {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                Point point = new Point(x, y);
                if (isFloor(point)) {
                    Point above = new Point(x, y + 1);
                    Point below = new Point(x, y - 1);
                    Point left = new Point(x - 1, y);
                    Point right = new Point(x + 1, y);
                    Point above1 = new Point(x, y + 2);
                    Point below1 = new Point(x, y - 2);
                    Point left1 = new Point(x - 2, y);
                    Point right1 = new Point(x + 2, y);
                    int chance = RandomUtils.uniform(seed, 1, 4);
                    if (!isFloor(above) && !isFloor(below) && !isFloor(left)) {
                        walls.add(above); walls.add(below); walls.add(left);
                    } else if (!isFloor(above) && !isFloor(below) && !isFloor(right)) {
                        walls.add(above); walls.add(below); walls.add(right);
                    } else if (!isFloor(left) && !isFloor(below) && !isFloor(right)) {
                        walls.add(left); walls.add(below); walls.add(right);
                    } else if (!isFloor(left) && !isFloor(above) && !isFloor(right)) {
                        walls.add(left); walls.add(above); walls.add(right);
                    } else if (!isFloor(below) && !isFloor(left)) {
                        walls.add(below); walls.add(left);
                    } else if (!isFloor(below) && !isFloor(right)) {
                        walls.add(below); walls.add(right);
                    } else if (!isFloor(right) && !isFloor(left)) {
                        walls.add(right); walls.add(left);
                    } else if (!isFloor(above) && !isFloor(left)) {
                        walls.add(above); walls.add(left);
                    } else if (!isFloor(above) && !isFloor(right)) {
                        walls.add(above); walls.add(right);
                    } else if (!isFloor(above) && !isFloor(below)) {
                        walls.add(above); walls.add(below);
                    } else if (!isFloor(left)) {
                        walls.add(left);
                    } else if (!isFloor(right)) {
                        walls.add(right);
                    } else if (!isFloor(above)) {
                        walls.add(above);
                    } else if (!isFloor(below)) {
                        walls.add(below);

                    } else if (chance == 2 && isFloor(above) && isFloor(below)
                            && isFloor(right) && isFloor(left)
                            && isFloor(above1) && isFloor(below1)
                            && isFloor(right1) && isFloor(left1)
                            && isFloor(new Point(x - 2, y + 2))
                            && isFloor(new Point(x + 2, y + 2))
                            && isFloor(new Point(x - 2, y - 2))
                            && isFloor(new Point(x + 2, y - 2))
                            && isFloor(new Point(x - 2, y - 1))
                            && isFloor(new Point(x + 2, y - 1))
                            && isFloor(new Point(x - 2, y + 2))
                            && isFloor(new Point(x + 2, y + 1))
                            && isFloor(new Point(x - 1, y + 2))
                            && isFloor(new Point(x + 1, y + 2))
                            && isFloor(new Point(x - 1, y - 2))
                            && isFloor(new Point(x + 1, y - 2))) {

                        walls.add(point); walls.add(above); walls.add(left);
                        walls.add(below); walls.add(right);
                        walls.add(new Point(x - 1, y + 1)); walls.add(new Point(x + 1, y + 1));
                        walls.add(new Point(x - 1, y - 1)); walls.add(new Point(x + 1, y - 1));
                    }
                }
            }
        }
    }

    public TETile[][] worldDrawer(TERenderer ter, int width, int height) {
        //COMMENT OUR FOR AUTOGRADER
        ter.initialize(width, height + 6, 0, 2);
        getTheme();

        world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = tiles[0];
            }
        }

        floors.removeAll(walls);
        for (int i = 0; i < floors.size(); i++) {
            world[floors.get(i).getX()][floors.get(i).getY()] = tiles[1];
        }
        for (int j = 0; j < walls.size(); j++) {
            world[walls.get(j).getX()][walls.get(j).getY()] = tiles[2];
        }


        if (avatar == null) {
            int index = RandomUtils.uniform(seed, 0, floors.size() - 1);
            world[floors.get(index).getX()][floors.get(index).getY()] = tiles[3];
            this.avatar = floors.get(index);
        } else {
            world[avatar.getX()][avatar.getY()] = tiles[3];
        }
//initializes enemies and goals
        Point first = floors.get(seed.nextInt(floors.size() / 3));
        Point second = floors.get(seed.nextInt(floors.size() / 3) + (floors.size() / 3));
        Point third = floors.get(seed.nextInt(floors.size() / 3) + 2 * (floors.size() / 3));
        world[first.getX()][first.getY()] = tiles[5];
        world[second.getX()][second.getY()] = tiles[5];
        world[third.getX()][third.getY()] = tiles[5];
        goals.add(0, first);
        goals.add(1, second);
        goals.add(2, third);
        enemyOne = new Enemy(first, seed.nextInt(5));
        enemyTwo = new Enemy(second, seed.nextInt(5));
        enemyThree = new Enemy(third, seed.nextInt(5));
        enemies.add(enemyOne);
        enemies.add(enemyTwo);
        enemies.add(enemyThree);

        //COMMENT OUT FOR AUTOGRADER
        ter.renderFrame(world);
        return world;
    }

    //I DIDNT REMOVE AVATR FROM FLOORS
    public TETile[][] sworldDrawer(int width, int height) {
        getTheme();
        world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = tiles[0];
            }
        }

        floors.removeAll(walls);
        for (int i = 0; i < floors.size(); i++) {
            world[floors.get(i).getX()][floors.get(i).getY()] = tiles[1];
        }
        for (int j = 0; j < walls.size(); j++) {
            world[walls.get(j).getX()][walls.get(j).getY()] = tiles[2];
        }

        if (avatar == null) {
            int index = RandomUtils.uniform(seed, 0, floors.size() - 1);
            world[floors.get(index).getX()][floors.get(index).getY()] = tiles[3];
            this.avatar = floors.get(index);
        } else {
            world[avatar.getX()][avatar.getY()] = tiles[3];
        }

        return world;
    }

    private boolean isFloor(Point p) {

        return floors.contains(p);
    }

    private TETile[] getTheme() { //0 is nothing, 1 is floor, 2 is wall, 3 is avatar, MAKE 4 enemy
        int theme = 1;
        theme  = RandomUtils.uniform(seed, 1, 5);

        switch (theme) {
            // PIRATES
            case 1:
                tiles[0] = Tileset.NOTHING; //nothing
                tiles[1] = Tileset.WATER; //floor
                tiles[2] = Tileset.SAND; //wall
                tiles[3] = Tileset.SHIP; //avatar
                tiles[4] = Tileset.SKULL; //enemy
                tiles[5] = Tileset.MONEY; //goal
                tiles[6] = Tileset.BLOCK;
                break;
            //ROAD
            case 2:
                tiles[0] = Tileset.NOTHING;
                tiles[1] = Tileset.ROAD;
                tiles[2] = Tileset.HOUSE;
                tiles[3] = Tileset.CAR;
                tiles[4] = Tileset.NUKE;
                tiles[5] = Tileset.FLAG;
                tiles[6] = Tileset.BLOCK;
                break;
            //FOREST
            case 3:
                tiles[0] = Tileset.NOTHING;
                tiles[1] = Tileset.TREE;
                tiles[2] = Tileset.MOUNTAIN;
                tiles[3] = Tileset.ARROW;
                tiles[4] = Tileset.CLOUD;
                tiles[5] = Tileset.TARGET;
                tiles[6] = Tileset.BLOCK;
                break;
            //CASTLE
            case 4:
                tiles[0] = Tileset.NOTHING;
                tiles[1] = Tileset.FLOWER;
                tiles[2] = Tileset.CASTLE;
                tiles[3] = Tileset.HORSE;
                tiles[4] = Tileset.SNAKE;
                tiles[5] = Tileset.CLOVER;
                tiles[6] = Tileset.BLOCK;
                break;
            default:
                break;


        }
        return tiles;

    }

    public Point getAvatar() {
        return avatar;
    }

    public void setAvatar(int dir) {
        int style = 0;
        style = dir;
        switch (style) {
            //W = UP
            case 1:
                Point above = new Point(avatar.getX(), avatar.getY() + 1);
                if (floors.contains(above)) {
                    world[avatar.getX()][avatar.getY()] = tiles[1];
                    world[above.getX()][above.getY()] = tiles[3];
                    avatar = above;
                    if (goals.contains(above)) {
                        release(above);
                    }
                    for (Enemy e : enemies) {
                        if (e.isActivated()) {
                            e.pathAdd(above);
                        }
                    }
                }
                break;
            //A == Left
            case 2:
                Point left = new Point(avatar.getX() - 1, avatar.getY());
                if (floors.contains(left)) {
                    world[avatar.getX()][avatar.getY()] = tiles[1];
                    world[left.getX()][left.getY()] = tiles[3];
                    avatar = left;
                    if (goals.contains(left)) {
                        release(left);
                    }
                    for (Enemy e : enemies) {
                        if (e.isActivated()) {
                            e.pathAdd(left);
                        }
                    }
                }
                break;
            //S == Down
            case 3:
                Point below = new Point(avatar.getX(), avatar.getY() - 1);
                if (floors.contains(below)) {
                    world[avatar.getX()][avatar.getY()] = tiles[1];
                    world[below.getX()][below.getY()] = tiles[3];
                    avatar = below;
                    if (goals.contains(below)) {
                        release(below);
                    }
                    for (Enemy e : enemies) {
                        if (e.isActivated()) {
                            e.pathAdd(below);
                        }
                    }
                }
                break;
            //D == Right
            case 4:
                Point right = new Point(avatar.getX() + 1, avatar.getY());
                if (floors.contains(right)) {
                    world[avatar.getX()][avatar.getY()] = tiles[1];
                    world[right.getX()][right.getY()] = tiles[3];
                    avatar = right;
                    if (goals.contains(right)) {
                        release(right);
                    }
                    for (Enemy e : enemies) {
                        if (e.isActivated()) {
                            e.pathAdd(right);
                        }
                    }
                }
                break;
            default:
                break;
        }

    }

    public ArrayList<Point> getFloors() {
        return floors;
    }

    public ArrayList<Point> getWalls() {
        return walls;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void moveEnemy() {
        for (Enemy e : enemies) {
            if (e.isActivated()) {
                Point s = e.getEnemy();
                world [s.getX()][s.getY()] = tiles[1];
                e.move();
                Point p = e.getEnemy();
                world[p.getX()][p.getY()] = tiles[4];
            }
        }
    }

    private void release(Point p) {
        if (p.equals(goals.get(0))) {
            enemyOne.activate();
        } else if (p.equals(goals.get(1))) {
            enemyTwo.activate();
        } else {
            enemyThree.activate();
        }
    }

    public void showPath() {
        for (Enemy e : enemies) {
            ArrayList<Point> tempPath = e.getPath();
            int tempIndex = e.getIndex();
            for (int i = tempIndex + 1; i < tempPath.size(); i++) {
                Point p = new Point(tempPath.get(i).getX(), tempPath.get(i).getY());
                if (!p.equals(getAvatar())) {
                    world[tempPath.get(i).getX()][tempPath.get(i).getY()] = tiles[6];
                }
            }
            for (int i = 0; i < tempIndex - 1; i++) {
                Point p = new Point(tempPath.get(i).getX(), tempPath.get(i).getY());
                if (!p.equals(getAvatar())) {
                    world[tempPath.get(i).getX()][tempPath.get(i).getY()] = tiles[1];
                }
            }
        }
    }

    public void clearPath() {
        for (Enemy e : enemies) {
            ArrayList<Point> tempPath = e.getPath();
            for (int i = 0; i < tempPath.size(); i++) {
                Point p = new Point(tempPath.get(i).getX(), tempPath.get(i).getY());
                if (!p.equals(getAvatar())) {
                    world[tempPath.get(i).getX()][tempPath.get(i).getY()] = tiles[1];
                }
            }
        }
    }

//    public void attack() {
//        for (int i = 0; i < enemies.size(); i++) {
//            if (this.target == null) {
//                int randDir = this.rand.nextInt(4); //0 is up, 1 is right, 2 is down, 3 is left
//                if (randDir == 0 && enemy.getY() != topBound) {
//
//                } else if (randDir <= 1 && enemy.getX() != rightBound) {
//
//                } else if (randDir <= 2 && enemy.getY() != bottomBound) {
//
//                } else if (randDir <= 3 && enemy.getX() != leftBound) {
//
//                }
//            } else {
//
//            }
//        }
//
//    }
}
