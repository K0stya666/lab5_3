package models;

public class Coordinates {
    private long x;
    private int y;

    public Coordinates(long x, int y){
        this.x = x;
        this.y = y;
    }
    public Coordinates(String s) {
        try {
            try { this.x = Long.parseLong(s.split(";")[0]); } catch (NumberFormatException ignored) {}
            try { this.y = Integer.parseInt(s.split(";")[1]); } catch (NumberFormatException ignored) {}
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        return String.format("%s; %s", x, y);
    }
}
