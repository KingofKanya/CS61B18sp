package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class HexWorldDemo {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] teTiles = new TETile[WIDTH][HEIGHT];
        fillArea(teTiles);

        HexWorld hexWorld = new HexWorld();
        hexWorld.addTesselationHexagons(teTiles);

        ter.renderFrame(teTiles);
    }

    private static void fillArea(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
}
