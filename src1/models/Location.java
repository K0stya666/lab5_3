package models;

import utility.Validatable;

public class Location implements Validatable {
    private Double x;
    private double y; //Поле не может быть null
    private String name; //Поле может быть null

    public Location(Double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Double getX() {
        return x;
    }
    public double getY() { return y; }
    public String getName() { return name; }

    @Override
    public boolean validate() {
        return name != null;
    }

    @Override
    public String toString(){
        return String.format("%s; %s; %s",x, y, name);
    }
}
