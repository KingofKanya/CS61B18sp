package byog.Core;

public class Room {
    @Override
    public String toString() {
        return "Room{" +
                "width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public int width;
    public int height;
    // 左下角坐标
    public int x;
    public int y;

    public Room(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

    }
}