import com.opencsv.exceptions.CsvValidationException;
import commands.*;
import managers.*;
import models.*;
import utility.console.StandardConsole;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        StandardConsole console = new StandardConsole();

        if (args.length == 0) {
            console.println("Введите имя загружаемого файла");
            System.exit(1);
        }

        CSVparser csVparser = new CSVparser(args[0], console);
        CollectionManager collectionManager = new CollectionManager(csVparser);

        CommandManager commandManager = new CommandManager() {{
//            register("help", new Help(console, this));
//            register("history", new History(console, this));
//            register("info", new Info(console, collectionManager));
//            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
//            register("update", new Update(console, collectionManager));
//            register("remove_by_id", new RemoveById(console, collectionManager));
//            register("clear", new Clear(console, collectionManager));
//            register("save", new Save(console, collectionManager));
//            register("execute_script", new ExecuteScript(console));
//            register("exit", new Exit(console));
        }};

    }
}