package byog.Test;

import byog.Core.Game;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;

public class TestGame {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    @Test
    public void generateWorldTest() {
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] teTile = game.generateWorld(1234);

        ter.renderFrame(teTile);
        while (true) {
            // 为了页面不闪退
        }
    }

    @Test
    public void playWithInputStringTest() {
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] teTile = game.playWithInputString("n1234s");

        ter.renderFrame(teTile);
        while (true) {
            // 为了页面不闪退
        }
    }

    @Test
    public void generateLHallwayTest() {
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        game.fillArea(world);

        game.generateLHallway(2, 1, 10, 9, world);

        ter.renderFrame(world);
        while (true) {
            // 为了页面不闪退
        }
    }
}
