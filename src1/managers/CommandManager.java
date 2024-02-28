package managers;

import commands.*;

import java.util.*;

/**
 * Менеджер, который управляет командами.
 * @author Kostya
 */
public class CommandManager {
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    /**
     * Добавляет команду.
     * @param commandName
     * @param command
     */
    public void register(String commandName, Command command){
        commands.put(commandName, command);
    }

    /**
     * @return История команд.
     */
    public Map<String, Command> getCommands(){
        return commands;
    }

    /**
     * Добавляет команду в историю.
     * @param command Команда.
     */
    public void addToHistory(String command){
        commandHistory.add(command);
    }
}
