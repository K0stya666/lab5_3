package models;

import utility.Console;

import java.util.Date;
import java.util.NoSuchElementException;

public class Ask {
    public static class AskBreak extends Exception {}

    public static Route askRoute(Console console, Long id) throws AskBreak {
        Coordinates coordinates = askCoordinates(console);
        try {
            String nameFrom;
            do {
                console.print("Введите наименование пункта отправления: ");
                nameFrom = console.readln().trim();
                if (nameFrom.equals("exit")) throw new AskBreak();
            } while (nameFrom.isEmpty());
            Location from = askLocation(console, nameFrom);
            String nameTo;
            do {
                console.print("Введите наименование пункта прибытия: ");
                nameTo = console.readln().trim();
                if (nameTo.equals("exit")) throw new AskBreak();
            } while (nameTo.isEmpty());
            Location to = askLocation(console, nameTo);
            String name = String.format("%s - %s",nameFrom, nameTo);
            return new Route(id, name, coordinates, from, to);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Coordinates askCoordinates(Console console) throws AskBreak {
        console.println("Введите координаты вашего местоположения");
        try {
            long x;
            while (true) {
                console.print("> Координата x: ");
                String line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
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
                String line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
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

    public static Location askLocation(Console console, String name) throws AskBreak {
        console.println(String.format("Введите координаты местоположения %s", name));
        try {
            Double x;
            while (true) {
                console.print("> Координата x: ");
                String line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try { x = Double.parseDouble(line); break; } catch (NumberFormatException ignored) {
                        console.printError("Неверный формат ввода. Попробуйте заново. --> ");
                    }
                }
            }
            double y;
            while (true) {
                console.print("> Координата y: ");
                String line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
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
