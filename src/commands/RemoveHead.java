package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда 'remove_head'. Выводит первый элемент коллекции и удаляет его.
 * @author Kostya
 */
public class RemoveHead extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveHead(Console console, CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
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
        Long id = collectionManager.getCollection().getFirst().getId();

        if (collectionManager.byId(id) == null || !collectionManager.getCollection().contains(collectionManager.byId(id))) {
            console.println("не существующий id");
            return false;
        }
        console.println(collectionManager.byId(id));
        collectionManager.remove(id);
        collectionManager.addLog("remove " + id, true);
        collectionManager.update();
        console.println("Маршрут успешно удалён");
        return true;
    }
}
