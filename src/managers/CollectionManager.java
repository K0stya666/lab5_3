package managers;

import models.*;

import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private Long currentId = 1l;
    private Map<Long, Route> routes = new HashMap<>();
    private LinkedList<Route> collection = new LinkedList<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final CSVparser csVparser;
    private final ArrayDeque<String> logStack = new ArrayDeque<>();

    public CollectionManager(CSVparser csVparser) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.csVparser = csVparser;
    }

    /**
     * @return Последнее время инициализации
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Последнее время сохранения
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return коллекция
     */
    public LinkedList<Route> getCollection() {
        return collection;
    }

    /**
     * @param id id Route
     * @return Route по id
     */
    public Route byId(Long id) {
        return routes.get(id);
    }

    /**
     * @param route Route
     * @return содержит ли коллекция Route
     */
    public boolean isContain(Route route) {
        return route == null || byId(route.getId()) != null;
    }

    /**
     * @return свободный id
     */
    public Long getFreeId() {
        while (byId(++currentId) != null);
        return currentId;
    }

    /**
     * Добавляет Route
     */
    public boolean add(Route route) {
        if (isContain(route)) return false;
        routes.put(route.getId(), route);
        collection.add(route);
        update();
        return true;
    }

    /**
     * Обновляет Route
     */
    public boolean update(Route route) {
        if (!isContain(route)) return false;
        collection.remove(byId(route.getId()));
        routes.put(route.getId(), route);
        collection.add(route);
        update();
        return true;
    }

    /**
     * Удаляет Route по id
     */
    public boolean remove(Long id) {
        var route = byId(id);
        if (route == null) return false;
        routes.remove(route.getId());
        collection.remove(route);
        update();
        return true;
    }

    /**
     * Фиксирует изменения коллекции
     */
    public void update() {
        Collections.sort(collection);
    }

    public boolean init() {
        collection.clear();
        routes.clear();
        csVparser.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (var e : collection)
            if (byId(e.getId()) != null) {
                collection.clear();
                routes.clear();
                return false;
            } else {
                if (e.getId() > currentId) currentId = e.getId();
                routes.put(e.getId(), e);
            }
        update();
        return true;
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        csVparser.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Создаёт транзакцию или добавляет операцию в транзакцию
     */
    public void addLog(String cmd, boolean isFirst) {
        if (isFirst) logStack.push("+");
        if (!cmd.isEmpty()) logStack.push(cmd);
    }

    /**
     * @return true в случае успеха.
     */
    public boolean swap(Long id, Long repId) {
        var e = byId(id);
        var re = byId(repId);
        if (e == null) return false;
        if (re == null) return false;
        var ind = collection.indexOf(e);
        var rind = collection.indexOf(re);
        if (ind < 0) return false;
        if (rind < 0) return false;

        e.setId(repId);
        re.setId(id);

        routes.put(e.getId(), e);
        routes.put(re.getId(), re);

        update();
        return true;
    }

    /**
     * @return максимальная дистанция коллекции
     */
    public int getMaxDistance() {
        int max = 0;
        for (var e : collection) {
            if (e.getDistance() > max) max = e.getDistance();
        }
        return max;
    }

    public boolean loadCollection() {
        routes.clear();
        csVparser.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (var e : collection)
            if (byId(e.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (e.getId() > currentId) currentId = e.getId();
                routes.put(e.getId(), e);
            }
        update();
        return true;
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста";
        StringBuilder info = new StringBuilder();
        for (var route : collection) {
            info.append(route).append("\n\n");
        }
        return info.toString().trim();
    }
}
