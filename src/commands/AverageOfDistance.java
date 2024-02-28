package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда 'average_of_distance'. Выводит среднее значение поля distance для всех элементов коллекции.
 * @author Kostya
 */
public class AverageOfDistance extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AverageOfDistance(Console console, CollectionManager collectionManager) {
        super("average_of_distance", "вывести среднее значение поля distance для всех элементов коллекции");
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

        var collection = collectionManager.getCollection();
        int k = 0;
        int sumDistances = 0;
        for (var route : collection) {
            k++;
            sumDistances += route.getDistance();
        }
        if (k != 0) {
            double averageDistance = (double) sumDistances / k;
            console.println(averageDistance);
            return true;
        } else {
            console.println("Невозможно выполнить команду, т.к. коллекция пустая");
            return false;
        }
    }
}
