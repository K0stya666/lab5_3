package models;

import utility.Validatable;

public class Location {
    private Double x;
    private double y; //Поле не может быть null
    private String name; //Поле может быть null

    public Location(Double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public Location(String s) {
        try {
            try { this.x = Double.parseDouble(s.split(";")[0]); } catch (NumberFormatException ignored) {}
            try { this.y = Double.parseDouble(s.split(";")[1]); } catch (NumberFormatException ignored) {}
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    public Double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    @Override
    public String toString(){
        return String.format("%s; %s; %s",x, y, name);
    }
}
