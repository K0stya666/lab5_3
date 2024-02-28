package commands;

import managers.CollectionManager;
import models.*;
import utility.Console;

/**
 * Команда 'add_if_max'. Добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.
 * @author Kostya
 */
public class AddIfMax extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMax(Console console, CollectionManager collectionManager) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команда.
     */
    public boolean apply(String[] args) {
        try {
            if (!args[1].isEmpty()) {
                console.println("Неправильное количество аргументов");
                console.println("Использование: '" + getName() + "'");
                return false;
            }

            console.println("* Создание нового маршрута:");
            Route route = Ask.askRoute(console, collectionManager.getFreeId());
            int maxValue = collectionManager.getMaxDistance();

            if (route != null && route.validate() && route.getDistance() > maxValue) {
                collectionManager.add(route);
                collectionManager.addLog("add_if_max " + route.getId(), true);
                console.println("Маршрут добавлен");
                return true;
            } else if (route != null && route.validate() && route.getDistance() > maxValue) {
                console.printError("Поля маршрута не валидны. Маршрут не создан.");
                return false;
            } else {
                assert route != null;
                console.printError("Маршрут не добавлен, дистанция не максимальная (" + route.getDistance() + " < " + maxValue + ")");
                return false;
            }
        } catch (Ask.AskBreak e) {
            console.println("Отмена");
            return false;
        }
    }
}
