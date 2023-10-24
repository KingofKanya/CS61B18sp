package byog.lab5;

/**
 * p标记六边形左上角的坐标
 */
public class Position {
    public int getX() {
        return x;
    }

    public Position() {
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
