package commands;

import managers.CollectionManager;
import models.Ask;
import models.Route;
import utility.Console;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 */
public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
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

            if (route != null && route.validate()) {
                collectionManager.add(route);
                collectionManager.addLog("add " + route.getId(), true);
                console.println("Маршрут добавлен");
                return true;
            } else {
                console.printError("Поля маршрута не валидны. Маршрут не создан.");
                return false;
            }
        } catch (Ask.AskBreak e) {
            console.println("Отмена");
            return false;
        }
    }
}
