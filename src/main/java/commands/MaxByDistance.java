package commands;

import managers.CollectionManager;
import models.Route;
import utility.Console;

/**
 * Команда 'max_by_distance'. Вывести любой объект из коллекции, значение поля distance которого является максимальным.
 * @author Kostya
 */
public class MaxByDistance extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public MaxByDistance(Console console, CollectionManager collectionManager) {
        super("max_by_distance", "вывести любой объект из коллекции, значение поля distance которого является максимальным");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    public boolean apply(String[] args) {
        if (!args[1].isEmpty()) {
            console.println("Неправильное количество аргументов");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        Route myMax = null;
        for (var e : collectionManager.getCollection()) {
            if (myMax == null || myMax.getDistance() > myMax.getDistance()) myMax = e;
        }
        if (myMax == null) {
            console.println("Маршрутов не обнаружено.");
        } else {
            console.println(myMax.toString() + "\n");
        }
        return true;
    }
}
