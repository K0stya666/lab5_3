package models;

public class Coordinates {
    private long x;
    private int y;

    public Coordinates(long x, int y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }
    public int getY() { return y; }

    @Override
    public String toString(){
        return String.format("%s; %s", x, y);
    }
}
