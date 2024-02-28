package utility.console;

import java.util.Scanner;

public interface Console {
    void print(Object obj);
    void println(Object obj);
    void printError(Object obj);
    String readLine();
}
