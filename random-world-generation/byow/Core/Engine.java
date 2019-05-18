package byow.Core;

import byow.InputDemo.KeyboardInputSource;
import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;


import java.util.Arrays;
import java.util.Random;


public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 75;
    public static final int HEIGHT = 55;
    private String save;
    private char[] pressed;
    private TETile[][] stage;

    

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        KeyboardInputSource keys = new KeyboardInputSource();
        pressed = new char[600];
        int flipSwitch = 0;
        WorldBuilder world = null;
        int count = 1;
        while (keys.possibleNextInput()) {
            char c = keys.getNextKey();
            if (c == 'L' || c == 'l') {
                ReadString rs = new ReadString();
                rs.load();
                stage = interactWithInputString(rs.getLoaded());
                break;
            }
            if (c == 'N' || c == 'n') {
                pressed[0] = c;
                StdDraw.text(.3, .3, "enter seed followed by S");
                char d = keys.getNextKey();
                while (d == '1' || d == '2' || d == '3' || d == '4' || d == '0'
                        || d == '5' || d == '6' || d == '7' || d == '8' || d == '9') {
                    pressed[count] = d;
                    count += 1;
                    d = keys.getNextKey();
                }
                pressed[count] = ('s');
                count += 1;
                world = intialize(new String(Arrays.copyOfRange(pressed, 1, count - 1)));
                stage = world.worldDrawer(ter, WIDTH, HEIGHT);
            }
            if (c == 'Q' || c == 'q') {
                System.exit(0);
                break;
            }

            if (c == ':' || c == ';') {
                String plssave = new String(pressed);
                WriteString.store(plssave);
            }
            if (world != null) {
                if (flipSwitch == 1) {
                    pressed[count] = c; count += 1;
                    world.showPath();
                    ter.renderFrame(stage);
                }
                if (c == 'w' || c == 'W') {
                    pressed[count] = c; count += 1;
                    world.setAvatar(1);
                    world.moveEnemy();
                    ter.renderFrame(stage);
                } else if (c == 'a' || c == 'A') {
                    pressed[count] = c; count += 1;
                    world.setAvatar(2);
                    world.moveEnemy();
                    ter.renderFrame(stage);
                } else if (c == 's' || c == 'S') {
                    pressed[count] = c; count += 1;
                    world.setAvatar(3);
                    world.moveEnemy();
                    ter.renderFrame(stage);
                } else if (c == 'd' || c == 'D') {
                    pressed[count] = c; count += 1;
                    world.setAvatar(4);
                    world.moveEnemy();
                    ter.renderFrame(stage);
                } else if (c == 'p' || c == 'P') {
                    flipSwitch = flipHelp(flipSwitch, count, world, stage, c);
                }
                for (Enemy e : world.getEnemies()) {
                    if (e.getEnemy() == world.getAvatar()) {
                        System.exit(666);
                    }
                    helpMe(world, stage);
                }
            }
        }
    }

    private int flipHelp(int flipSwitch, int count, WorldBuilder world, TETile[][] stage, char c) {
        if (flipSwitch == 0) {
            world.showPath();
            ter.renderFrame(stage);
            return 1;
        } else {
            world.clearPath();
            ter.renderFrame(stage);
            return 0;
        }
    }

    private void helpMe(WorldBuilder world, TETile[][] stage) {
        Point check = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        if (world.getAvatar().equals(check)) {
            //DISPLAY = u
            ter.setDisplay(3);
            ter.renderFrame(stage);
        } else if (world.getWalls().contains(check)) {
            //DISPLAY = WALL
            ter.setDisplay(2);
            ter.renderFrame(stage);
        } else if (world.getFloors().contains(check)) {
            //DISPLAY = Floors
            ter.setDisplay(1);
            ter.renderFrame(stage);
        } else {
            //DISPLAY = nothing
            ter.setDisplay(4);
            ter.renderFrame(stage);
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    private TETile[][] loadCase(String input) {
        StringInputDevice idk = new StringInputDevice(input);
        int start = 0;
        int rand = 0;
        int end = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = idk.getNextKey();
            if (c == 'N' || c == 'n') {
                start = i;
            } else if (c == 'S' || c == 's') {
                rand = i;
                break;
            }
        }
        String seed = input.substring(start + 1, rand);
        WorldBuilder world = intialize(seed);
        world.sworldDrawer(WIDTH, HEIGHT);


        String instruct = input.substring(rand + 1);

        for (int i = 0; i < instruct.length(); i++) {
            char c = idk.getNextKey();
            if (c == 'W' || c == 'w') {
                world.setAvatar(1);
                world.moveEnemy();
            } else if (c == 'A' || c == 'a') {
                world.setAvatar(2);
                world.moveEnemy();
            } else if (c == 'S' || c == 's') {
                world.setAvatar(3);
                world.moveEnemy();
            } else if (c == 'D' || c == 'd') {
                world.setAvatar(4);
                world.moveEnemy();
            } /*else if (c == ':') {
                end = rand + i - 1;
                save = input.substring(start, end);
                WriteString.store(save);
                    /*try {
                        FileWriter writer = new FileWriter(file, false);
                        writer.write(save);
                        writer.close();
                    } catch(IOException ie) {
                        ie.printStackTrace();
                    }
            } else if (c == 'Q' || c == 'q') {
                return created;
            }*/

        }
        return world.sworldDrawer(WIDTH, HEIGHT);
    }


    public TETile[][] interactWithInputString(String input) {
        // Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().

        //SETAVATAR W = 1, A = 2 ,S = 3, D = 4
        StringInputDevice idk = new StringInputDevice(input);
        int start = 0;
        int rand = 0;
        int end = 0;
        //SOME CHECKER FOR LOAD

        for (int i = 0; i < input.length(); i++) {
            char c = idk.getNextKey();
            if (c == 'L' || c == 'l') {
                ReadString rs = new ReadString();
                rs.load();
                String str = rs.getLoaded() + input.substring(i);
                return loadCase(str);
            }
            if (c == 'N' || c == 'n') {
                start = i;
            } else if (c == 'S' || c == 's') {
                rand = i;
                break;
            }
        }
        String seed = input.substring(start + 1, rand);
        WorldBuilder world = intialize(seed);
        world.worldDrawer(ter, WIDTH, HEIGHT);


        String instruct = input.substring(rand + 1);

        for (int i = 0; i < instruct.length(); i++) {
            char c = idk.getNextKey();
            if (c == 'W' || c == 'w') {
                world.setAvatar(1);
                world.moveEnemy();
            } else if (c == 'A' || c == 'a') {
                world.setAvatar(2);
                world.moveEnemy();
            } else if (c == 'S' || c == 's') {
                world.setAvatar(3);
                world.moveEnemy();
            } else if (c == 'D' || c == 'd') {
                world.setAvatar(4);
                world.moveEnemy();
            } else if (c == ':') {
                end = rand + i - 1;
                save = input.substring(start, end);
                WriteString.store(save);
            }
        }
        return world.worldDrawer(ter, WIDTH, HEIGHT);
    }

    private WorldBuilder intialize(String input) {
        Long bigboi = Long.parseLong(input);
        WorldBuilder world = new WorldBuilder(new Random(bigboi));
        world.createWays(80, 230);
        world.rooMs();
        world.makeWalls();
        return world;
    }



}
