package commands;

import managers.*;
import utility.*;

/**
 * Команда 'filter_contains_name'. Выводит элементы, значения поля name которых содержит заданную подстроку.
 * @author Kostya
 */
public class FilterContainsName extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterContainsName(Console console, CollectionManager collectionManager) {
        super("filter_contains_name", "вывести элементы, значения поля name которых ");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean apply(String[] args) {
        if (args[1].isEmpty()) {
            console.println("Неправильное количество аргументов");
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        String substring = args[1];

        var collection = collectionManager.getCollection();
        for (var route : collection) {
            if (route.getName().contains(substring)) System.out.println(route);
        }
        return true;
    }
}
