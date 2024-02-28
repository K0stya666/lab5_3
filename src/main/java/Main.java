import commands.*;
import managers.*;
import utility.Runner;
import utility.StandardConsole;

public class Main {
    public static void main(String[] args) {
        var console = new StandardConsole();

        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(0);
        }

        var csVparser = new CSVparser(args[0], console);
        var collectionManager = new CollectionManager(csVparser);
        if (!collectionManager.loadCollection()) {
            System.exit(1);
        }

        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("history", new History(console, this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update id", new UpdateId(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new ExecuteScript(console));
            register("exit", new Exit(console));
            register("max_by_distance", new MaxByDistance(console, collectionManager));
            register("remove_head", new RemoveHead(console, collectionManager));
            register("add_if_max", new AddIfMax(console, collectionManager));
            register("average_of_distance", new AverageOfDistance(console, collectionManager));
            register("filter_contains_name", new FilterContainsName(console, collectionManager));
        }};

        new Runner(console, commandManager).interactiveMode();
    }
}
