package commands;

import managers.CollectionManager;
import models.Route;
import models.Asker;
import utility.console.Console;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 * @author Kostya
 */
public class Add extends Command{
    private final Console console;
    private final CollectionManager collectionManager;


    public Add(Console console, CollectionManager collectionManager){
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
                console.println("Вы не указали второй аргумент команды " + getName());
                return false;
            }

            console.println(" Создание нового Маршрута");
            Route route = Asker.askRoute(console);

            if (route != null && route.validate()) {
                collectionManager.add(route);

                console.println("Маршрут добавлен");
                return true;
            } else {
                console.printError("Поля маршрута не валидны. Маршрут не создан.");
                return false;
            }
        } catch (Exception e) {
            console.println("Отмена");
            return false;
        }
    }
}
