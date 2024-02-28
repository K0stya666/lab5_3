package commands;

import managers.CommandManager;
import utility.Console;

/**
 * Команда 'history'. Выводит историю команд.
 * @author Kostya
 */
public class History extends  Command {
    private final Console console;
    private final CommandManager commandManager;

    public History(Console console, CommandManager commandManager) {
        super("history", "выводит историю команд");
        this.console = console;
        this.commandManager = commandManager;
    }

    public boolean apply(String[] args) {
        if (!args[1].isEmpty()) {
            console.println("Неправильное количество аргументов");
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        commandManager.getCommandHistory().forEach(command -> {
            console.println(" " + command);
        });
        return true;
    }
}
