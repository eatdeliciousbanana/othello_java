public class Position {
    private int x;
    private int y;

    Position() {
        this(0, 0);
    }

    Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}
