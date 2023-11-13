package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private final Random random = new Random();
    private Room[] rooms;
    private Player player;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        TETile[][] world = generateWorld(1234);
        this.player = generatePlayer(world);
        ter.renderFrame(world);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                handleKeyPress(key, world);
                ter.renderFrame(world);
            }
        }
    }

    /**
     * 处理按键事件
     *
     * @param key
     * @param world
     */
    public void handleKeyPress(char key, TETile[][] world) {
        int x = player.x;
        int y = player.y;
        if (key == 'W' || key == 'w') {
            System.out.println("w");
            if (y + 1 < HEIGHT && world[x][y] == Tileset.FLOOR) {
                world[x][y] = Tileset.FLOOR;
                world[x][y + 1] = Tileset.PLAYER;
                player.y++;
            }
        } else if (key == 'S' || key == 's') {
            System.out.println("s");
            if (y - 1 > 0 && world[x][y] == Tileset.FLOOR) {
                world[x][y] = Tileset.FLOOR;
                world[x][y - 1] = Tileset.PLAYER;
                player.y--;
            }
        } else if (key == 'A' || key == 'a') {
            System.out.println("a");
            if (x - 1 > 0 && world[x][y] == Tileset.FLOOR) {
                world[x][y] = Tileset.FLOOR;
                world[x - 1][y] = Tileset.PLAYER;
                player.x--;
            }
        } else if (key == 'D' || key == 'd') {
            System.out.println("d");
            if (x + 1 < WIDTH && world[x][y] == Tileset.FLOOR) {
                world[x][y] = Tileset.FLOOR;
                world[x + 1][y] = Tileset.PLAYER;
                player.x++;
            }
        }
    }


    private Player generatePlayer(TETile[][] world) {
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        while (world[x][y] != Tileset.FLOOR) {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
        }
        world[x][y] = Tileset.PLAYER;
        System.out.println("generate player at (" + x + "," + y + ")");
        return new Player(x, y);
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
        // Fill out this method to run the game using the input passed in,
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
        System.out.println("\nconnect");
        System.out.println(room1.toString());
        System.out.println(room2.toString());
        int x1 = room1.x + random.nextInt(room1.width);
        int y1 = room1.y + random.nextInt(room1.height);
        int x2 = room2.x + random.nextInt(room2.width);
        int y2 = room2.y + random.nextInt(room2.height);

        System.out.println("(" + x1 + "," + y1 + ")   " + "(" + x2 + "," + y2 + ")   ");

        generateLHallway(x1, y1, x2, y2, world);
    }

    public void generateLHallway(int x1, int y1, int x2, int y2, TETile[][] world) {
        int x3;
        int y3;
        if (random.nextInt(2) == 0) {
            x3 = x1;
            y3 = y2;
        } else {
            x3 = x2;
            y3 = y1;
        }
        generateLine(x1, y1, x3, y3, world);
        generateLine(x2, y2, x3, y3, world);
    }

    private void generateLine(int x1, int y1, int x2, int y2, TETile[][] world) {
        if (x1 == x2) {
            int min_y = Math.min(y1, y2);
            int max_y = Math.max(y1, y2);
            for (int i = min_y; i <= max_y; i++) {
                world[x1][i] = Tileset.FLOOR;
            }
        } else {
            int min_x = Math.min(x1, x2);
            int max_x = Math.max(x1, x2);
            for (int i = min_x; i <= max_x; i++) {
                world[i][y1] = Tileset.FLOOR;
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
        int x = random.nextInt(WIDTH - width - 1) + 1;
        int y = random.nextInt(HEIGHT - height - 1) + 1;
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
