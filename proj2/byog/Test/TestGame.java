package byog.Test;

import byog.Core.Game;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;

public class TestGame {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    @Test
    public void generateWorldTest(){
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] teTile = game.generateWorld(343);

        ter.renderFrame(teTile);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void playWithInputStringTest() {
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] teTile = game.playWithInputString("n23342s");

        ter.renderFrame(teTile);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void generateLHallwayTest() {
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        game.fillArea(world);

        game.generateLHallway(71, 11, 9, 3, world);

        ter.renderFrame(world);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void playWithKeyboardTest(){
        Game game = new Game();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        game.playWithKeyboard();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
