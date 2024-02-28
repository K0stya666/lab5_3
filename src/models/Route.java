package models;

import utility.Validatable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static java.lang.Math.*;

public class Route implements Validatable, Comparable<Route> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private int distance; //Значение поля должно быть больше 1

    public Route(Long id, String name, Coordinates coordinates, Date creationDate, Location from, Location to) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = (int) (sqrt(pow(coordinates.getX() - from.getX(), 2) + pow(coordinates.getY() - from.getY(), 2)) +
                sqrt(pow(from.getX() - to.getX(), 2) + pow(from.getY() - to.getY(), 2)));
    }

    public Route(Long id, String name, Coordinates coordinates, Location from, Location to) {
        this(id, name, coordinates,new Date(), from, to);
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Date getCreationDate() { return creationDate; }
    public Location getFrom() {
        return from;
    }
    public Location getTo() {
        return to;
    }
    public int getDistance() {
        return distance;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public static Route fromArray(String[] a) {
        Long id;
        String name;
        Date creationDate;
        Coordinates coordinates;
        Location from;
        Location to;
        int distance;
        try {
            try {
                id = Long.parseLong(a[0]);
            } catch (NumberFormatException e) { id = null; }
            name = a[1];
            coordinates = new Coordinates(a[2]);
            try {
                String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                creationDate = dateFormat.parse(a[3]);
            } catch (ParseException e) { creationDate = null; }
            from = new Location(a[4]);
            to = new Location(a[5]);
            //distance = Integer.parseInt(a[6]);
            return new Route(id, name, coordinates, creationDate, from, to);
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return null;
    }

    public static String[] toArray(Route route) {
        var list = new ArrayList<String>();
        list.add(route.getId().toString());
        list.add(route.getName());
        list.add(route.getCoordinates().toString());
        list.add(route.getCreationDate().toString());
        list.add(route.getFrom().toString());
        list.add(route.getTo().toString());
        list.add(String.valueOf(route.getDistance()));
        return list.toArray(new String[0]);
    }

    @Override
    public boolean validate() {
        if (id == null || id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (to == null) return false;
        return distance > 1;
    }
    @Override
    public int compareTo(Route route) {
        if (this == route) return 0;
        if (this.distance < route.distance) return -1;
        else return 1;
    }

    @Override
    public String toString() {
        return String.format("id = %s; name = %s; coordinates = %s; creation_date = %s; from = %s; to = %s; distance = %s",
                id, name, coordinates, creationDate, from, to, distance);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route that = (Route) obj;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, from, to, distance);
    }
}
