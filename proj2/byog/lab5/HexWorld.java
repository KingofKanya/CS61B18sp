package byog.lab5;

import byog.TileEngine.TETile;


public class HexWorld {
    private SingleHex singleHex = new SingleHex();

    public void addTesselationHexagons(TETile[][] tiles) {
        // 单个六边形大小
        int size = 3;

        int[] nums = new int[]{3, 4, 5, 4, 3};
        addYHexagons(tiles, nums[0], size, new Position(2, 12));
        addYHexagons(tiles, nums[1], size, new Position(7, 9));
        addYHexagons(tiles, nums[2], size, new Position(12, 6));
        addYHexagons(tiles, nums[3], size, new Position(17, 9));
        addYHexagons(tiles, nums[4], size, new Position(22, 12));


    }

    private void setPosition(Position p, int i) {
        if(i == 0){
            p = new Position(2, 13);
        } else if (i == 1) {
            p = new Position(7, 9);
        }else if (i == 2) {
            p = new Position(12, 7);
        }else if (i == 3) {
            p = new Position(17, 9);
        }else if (i == 4) {
            p = new Position(22, 12);
        }
    }

    public void addYHexagons(TETile[][] tiles, int num, int size, Position position) {
        for (int i = 0; i < num; i++) {
            new SingleHex().addHexagon(position, size, tiles);
        }
    }
}
