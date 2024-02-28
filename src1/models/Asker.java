package models;

import utility.console.Console;
import java.util.NoSuchElementException;

/**
 * Класс чтения объекта
 * @author Kostya
 */
public class Asker {

    public static Route askRoute(Console console) {
        Coordinates coordinates = askCoordinates(console);
        try {
            String nameFrom;
            do {
                console.print("Введите наименование пункта отправления: ");
                nameFrom = console.readLine();
            } while (nameFrom.isEmpty());
            Location from = askLocation(console, nameFrom);
            String nameTo;
            do {
                console.print("Введите наименование пункта прибытия: ");
                nameTo = console.readLine();
            } while (nameTo.isEmpty());
            Location to = askLocation(console, nameTo);
            String name = String.format("%s - %s",nameFrom, nameTo);
            return new Route(name, coordinates, from, to);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Coordinates askCoordinates(Console console) {
        console.println("Введите координаты вашего местоположения");
        try {
            long x;
            while (true) {
                console.print("> Координата x: ");
                String line = console.readLine();
                if (!line.isEmpty()) {
                    try {
                        x = Long.parseLong(line);
                        break;
                    } catch (NumberFormatException ignored) {
                        console.printError("Неверный формат ввода. Попробуйте заново. -->");
                    }
                }
            }
            int y;
            while (true) {
                console.print("> Координата y: ");
                String line = console.readLine();
                if (!line.isEmpty()) {
                    try {
                        y = Integer.parseInt(line);
                        break;
                    } catch (NumberFormatException ignored) {
                        console.printError("Неверный формат ввода. Попробуйте заново. --> ");
                    }
                }
            }
            return new Coordinates(x, y);
        } catch (IllegalStateException | NoSuchElementException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }


    public static Location askLocation(Console console, String name) {
        console.println(String.format("Введите координаты местоположения %s", name));
        try {
            Double x;
            while (true) {
                console.print("> Координата x: ");
                String line = console.readLine();
                if (!line.isEmpty()) {
                    try { x = Double.parseDouble(line); break; } catch (NumberFormatException ignored) {
                        console.printError("Неверный формат ввода. Попробуйте заново. --> ");
                    }
                }
            }
            double y;
            while (true) {
                console.print("> Координата y: ");
                String line = console.readLine();
                if (!line.isEmpty()) {
                    try { y = Double.parseDouble(line); break; } catch (NumberFormatException ignored) {
                        console.printError("Неверный формат ввода. Попробуйте заново. --> ");
                    }
                }
            }
            return new Location(x, y, name);
        } catch (IllegalStateException | NoSuchElementException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}
