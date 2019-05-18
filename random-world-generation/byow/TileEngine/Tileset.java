package byow.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "you");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor");

    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile BLOCK = new TETile('█', Color.red, Color.black,
            "block");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");

    public static final TETile SHIP = new TETile('⛴', Color.white, Color.black, "ship");
    public static final TETile SKULL = new TETile('☠', Color.red, Color.black, "skull");
    public static final TETile MONEY = new TETile('$', Color.green, Color.white, "money");

    public static final TETile ROAD = new TETile('¦', Color.white, Color.gray, "road");
    public static final TETile HOUSE = new TETile('⌂', Color.red, Color.green, "house");
    public static final TETile CAR = new TETile('⛟', Color.blue, Color.white, "car");
    public static final TETile NUKE = new TETile('☢', Color.yellow, Color.black, "nuke");
    public static final TETile FLAG = new TETile('⛿', Color.white, Color.black, "flag");

    public static final TETile ARROW = new TETile('➼', Color.black, Color.white, "arrow");
    public static final TETile CLOUD = new TETile('☁', Color.black, Color.white, "cloud");
    public static final TETile TARGET = new TETile('⊕', Color.black, Color.white, "target");

    public static final TETile HORSE = new TETile('♞', Color.black, Color.green, "horse");
    public static final TETile CLOVER = new TETile('☘', Color.green, Color.white, "clover");
    public static final TETile SNAKE = new TETile('⚕', Color.yellow, Color.black, "snake");
    public static final TETile CASTLE = new TETile('♖', Color.black, Color.gray, "castle");

}


