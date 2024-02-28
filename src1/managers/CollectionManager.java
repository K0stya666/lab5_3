package managers;

import models.Route;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CollectionManager {
    private Long currentId;
    private Map<Long, Route> routes = new HashMap<>();
    private LinkedList<Route> collection = new LinkedList<>();
    private CSVparser csVparser;

    public  CollectionManager(CSVparser csVparser) {
        this.csVparser = csVparser;
    }

    public void saveCollection(LinkedList<Route> routes) {
        this.collection = routes;
        for (Route route : routes) {
            this.routes.put(route.getId(), route);
        }
    }

    /**
     * Добавляет маршрут
     */
    public boolean add(Route route) {
        if (route == null) return false;
        routes.put(route.getId(), route);
        collection.add(route);
        return true;
    }

}
