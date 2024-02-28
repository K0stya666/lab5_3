package models;

import utility.Validatable;

import java.util.Date;
import static java.lang.Math.*;

public class Route implements Comparable<Route>, Validatable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private int distance; //Значение поля должно быть больше 1

    public Route(String name, Coordinates coordinates, Location from, Location to) {
        //this.id = getFreeId()
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.from = from;
        this.to = to;
        this.distance = (int) (sqrt(pow(coordinates.getX() - from.getX(), 2) + pow(coordinates.getY() - from.getY(), 2)) +
                sqrt(pow(from.getX() - to.getX(), 2) + pow(from.getY() - to.getY(), 2)));
    }

    public Long getId() { return id; };
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public Date getCreationDate() { return creationDate; }
    public Location getFrom() { return from; }
    public Location getTo() { return to; }
    public int getDistance() { return distance; }

    public void setId(Long id) { this.id = id; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    public void setDistance(int distance) { this.distance = distance; }

    @Override
    public int compareTo(Route route) {
        if (this == route) return 0;
        if (this.distance < route.distance) return -1;
        else return 1;
    }
    @Override
    public boolean validate() {
        if (id == null || id <= 0) return false; // добавить метод isUnique()
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (from == null) return false;
        if (to == null) return false;
        return distance > 1;
    }

    @Override
    public String toString() {
        return String.format("id = %s; name = %s; coordinates = %s; creation_date = %s; from = %s; to = %s; distance = %s",
                id, name, coordinates, creationDate, from, to, distance);
    }

}
