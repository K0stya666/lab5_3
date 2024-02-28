package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 * @author Kostya
 */
public class RemoveById extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveById(Console console, CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    public boolean apply(String[] args) {
        if (args[1].isEmpty()) {
            console.println("Неправильное количество аргументов");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        Long id = -1L;
        try { id = Long.parseLong(args[1].trim()); } catch (NumberFormatException e) { console.println("id не распознан"); return false; }

        if (collectionManager.byId(id) == null || !collectionManager.getCollection().contains(collectionManager.byId(id))) {
            console.println("не существующий id");
            return false;
        }
        collectionManager.remove(id);
        collectionManager.addLog("remove " + id, true);
        collectionManager.update();
        console.println("Маршрут успешно удалён");
        return true;
    }
}
