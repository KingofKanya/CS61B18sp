package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Draws a world consisting of hexagonal regions.
 * 原点在左下角，向上+y，向左+x
 */
public class SingleHex {

    private static TETile teTile;

    public void addHexagon(Position p, int size, TETile[][] tiles) {
        teTile = randomTileset();

        for (int i = 0; i < size; i++) {
            drawXLine(tiles, p, size + i * 2);
            p.setX(p.getX() - 1);
            p.setY(p.getY() + 1);
        }

        p.setX(p.getX() + 1);
        for (int i = size - 1; i >= 0; i--) {
            drawXLine(tiles, p, size + i * 2);
            p.setX(p.getX() + 1);
            p.setY(p.getY() + 1);
        }
        p.setX(p.getX() - 1);
    }

    private static TETile randomTileset() {
        int i = StdRandom.uniform(5);
        return switch (i) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.PLAYER;
            case 2 -> Tileset.FLOOR;
            case 3 -> Tileset.GRASS;
            case 4 -> Tileset.FLOWER;
            default -> null;
        };
    }

    private static void drawXLine(TETile[][] tiles, Position position, int length) {
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < length; i++) {
            tiles[x + i][y] = teTile;
        }
    }
}
