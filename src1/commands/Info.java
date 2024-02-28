package commands;

import models.Route;

import java.util.Date;
import java.util.LinkedList;
import java.util.SortedMap;

public class Info {
//    private static LinkedList<Route> collection;
//
//    public Info(LinkedList<Route> collection) {
//        Info.collection = collection;
//    }

    public static void printCollectionInfo(LinkedList<Route> collection) {
        System.out.println("Тип коллекции: " + collection.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + new Date());
        System.out.println("Количество элементов: " + collection.size());
    }
}
