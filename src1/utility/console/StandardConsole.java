package utility.console;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class StandardConsole implements Console {
    private static final Scanner fileScanner = new Scanner(System.in);

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }
    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
    @Override
    public void printError(Object obj) {
        System.err.println("Ошибка: " + obj);
    }
    @Override
    public String readLine() throws NoSuchElementException, IllegalStateException {
        return fileScanner.nextLine().trim();
    }
}
