package commands;

import managers.CollectionManager;
import models.Ask;
import utility.Console;

/**
 * Команда 'update'. Обновляет элемент коллекции.
 * @author Kostya
 */
public class UpdateId extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public UpdateId(Console console, CollectionManager collectionManager) {
        super("update id {element}", "обновить значение элемента по id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean apply(String[] args) {
        try {
            if (args[1].isEmpty()) {
                console.println("Неправильное количество аргументов");
                console.println("Использование: '" + getName() + "'");
                return false;
            }
            Long id = 1L;
            try { id = Long.parseLong(args[1].trim()); } catch (NumberFormatException e) { console.println("id не распознан"); return false; }

            if (collectionManager.byId(id) == null || !collectionManager.getCollection().contains(collectionManager.byId(id))) {
                console.println("не существующий id");
                return false;
            }

            console.println("* Создание нового маршрута: ");
            var route = Ask.askRoute(console, collectionManager.getFreeId());
            if (route != null && route.validate()) {
                collectionManager.add(route);
                collectionManager.addLog("add " + route.getId(), true);
                collectionManager.update();

                var old = collectionManager.byId(id);
                collectionManager.swap(route.getId(), id);
                collectionManager.addLog("swap " + old.getId() + " " + id, false);
                collectionManager.update();

                collectionManager.remove(old.getId());
                collectionManager.addLog("remove " + old.getId(), false);
                collectionManager.update();
                return true;
            } else {
                console.println("Поля маршрута не валидны. Маршрут не создан.");
                return false;
            }
        } catch (Ask.AskBreak e) {
            console.println("Отмена");
            return false;
        }
    }
}
