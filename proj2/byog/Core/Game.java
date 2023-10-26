package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public final Random random = new Random();
    public Room[] rooms;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        int seed = 0;
        input = input.toLowerCase();

        int k = 0;
        while (k < input.length()) {
            char ch = input.charAt(k);
            if (ch == 'n') {
                System.out.println("New Game");
                k++;
                while (k < input.length() && Character.isDigit(input.charAt(k))) {
                    seed = seed * 10 + input.charAt(k) - '0';
                    k++;
                }
                System.out.println("Get seed = " + seed);
                System.out.println("Generate new game");
                finalWorldFrame = generateWorld(seed);
                seed = 0;
            } else if (ch == 'l') {
                break;
            } else if (ch == 'q') {
                break;
            } else if (ch == 's') {
                break;
            }
        }
        return finalWorldFrame;
    }

    public TETile[][] generateWorld(int seed) {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillArea(world);

        random.setSeed(seed);

        //rooms = generateRooms(4, world);
        rooms = generateRooms(random.nextInt(10) + 10, world);
        connectRooms(rooms, world);
        generateWall(world);
        return world;
    }

    private void generateWall(TETile[][] world) {
        for (int i = 1; i < WIDTH - 1; i++) {
            for (int j = 1; j < HEIGHT - 1; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    if (world[i - 1][j] == Tileset.NOTHING) {
                        world[i - 1][j] = Tileset.WALL;
                    }
                    if (world[i - 1][j + 1] == Tileset.NOTHING) {
                        world[i - 1][j + 1] = Tileset.WALL;
                    }
                    if (world[i - 1][j - 1] == Tileset.NOTHING) {
                        world[i - 1][j - 1] = Tileset.WALL;
                    }
                    if (world[i][j + 1] == Tileset.NOTHING) {
                        world[i][j + 1] = Tileset.WALL;
                    }
                    if (world[i][j - 1] == Tileset.NOTHING) {
                        world[i][j - 1] = Tileset.WALL;
                    }
                    if (world[i + 1][j + 1] == Tileset.NOTHING) {
                        world[i + 1][j + 1] = Tileset.WALL;
                    }
                    if (world[i + 1][j - 1] == Tileset.NOTHING) {
                        world[i + 1][j - 1] = Tileset.WALL;
                    }
                    if (world[i + 1][j] == Tileset.NOTHING) {
                        world[i + 1][j] = Tileset.WALL;
                    }
                }
            }
        }
    }

    public void connectRooms(Room[] rooms, TETile[][] world) {
        for (int i = 0; i < rooms.length - 1; i++) {
            connectTwoRooms(rooms[i], rooms[i + 1], world);
        }
    }

    public void connectTwoRooms(Room room1, Room room2, TETile[][] world) {
        System.out.println("connect");
        System.out.println(room1.toString());
        System.out.println(room2.toString());
        int x1 = room1.x + random.nextInt(room1.width) - 1;
        int y1 = room1.y + random.nextInt(room1.height) - 1;
        int x2 = room2.x + random.nextInt(room2.width) - 1;
        int y2 = room2.y + random.nextInt(room2.height) - 1;
        int min_x = Math.min(x1, x2);
        int min_y = Math.min(y1, y2);
        int max_x = Math.max(x1, x2);
        int max_y = Math.max(y1, y2);

        int x = random.nextInt(2);
        if (x < 100) {
            generateLHallway(min_x, min_y, max_x, max_y, world);
        } else {

        }
    }

    public void generateLHallway(int min_x, int min_y, int max_x, int max_y, TETile[][] world) {
        int p = random.nextInt(2);
        if (p == 0) {
            //左下
            for (int i = min_y; i <= max_y; i++) {
                world[min_x][i] = Tileset.FLOOR;
            }
            for (int i = min_x; i <= max_x; i++) {
                world[i][min_y] = Tileset.FLOOR;
            }
        } else {
            //右上
            for (int i = min_x; i <= max_x; i++) {
                world[i][max_y] = Tileset.FLOOR;
            }
            for (int i = min_y; i <= max_y; i++) {
                world[max_x][i] = Tileset.FLOOR;
            }
        }
    }

    public Room[] generateRooms(int num, TETile[][] world) {
        System.out.println("generate " + num + " rooms");
        Room[] res = new Room[num];
        for (int i = 0; i < num; i++) {
            res[i] = generateRoom(world);
        }
        return res;
    }

    public Room generateRoom(TETile[][] world) {
        int width = random.nextInt(9) + 1;
        int height = random.nextInt(9) + 1;
        int x = random.nextInt(WIDTH - width) + 1;
        int y = random.nextInt(HEIGHT - height) + 1;
        Room room = new Room(width, height, x, y);
        System.out.println(room.toString());
        fillRoom(room, world);

        return room;
    }

    public void fillRoom(Room room, TETile[][] world) {
        for (int i = room.x; i < room.x + room.width; i++) {
            for (int j = room.y; j < room.y + room.height; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

    /**
     * fill tiles with Tileset.NOTHING
     *
     * @param tiles the tiles to fill with Tileset.NOTHING
     */
    public void fillArea(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
}
