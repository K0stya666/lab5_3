package managers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import models.Coordinates;
import models.Location;
import models.Route;
import models.Asker;
import utility.console.Console;
import utility.console.StandardConsole;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVparser {
    private static LinkedList<Route> routes = new LinkedList<>();
    private static Map<Long, Route> dict = new HashMap<>();
    private static Long currentId;


    private final String fileName;
    private final Console console;

    public CSVparser(String fileName, Console console){
        this.fileName = fileName;
        this.console = console;
    }

    public void readCSV() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String[] line = reader.readNext();
            while ((line = reader.readNext()) != null) {
                try {
                    Long id = Long.parseLong(line[0]);
                    String name = line[1].trim();

                    long coordX = Long.parseLong(line[2].split(";")[0].trim());
                    int coordY = Integer.parseInt(line[2].split(";")[1].trim());
                    Coordinates coordinates = new Coordinates(coordX, coordY);

                    String dateString = line[3];
                    String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
                    Date creationDate = dateFormat.parse(dateString);

                    Double fromX = Double.parseDouble(line[4].split(";")[0].trim());
                    double fromY = Double.parseDouble(line[4].split(";")[1].trim());
                    String nameFrom = line[4].split(";")[2].trim();
                    Location from = new Location(fromX, fromY, nameFrom);

                    Double toX = Double.parseDouble(line[5].split(";")[0].trim());
                    double toY = Double.parseDouble(line[5].split(";")[1].trim());
                    String nameTo = line[5].split(";")[2].trim();
                    Location to = new Location(toX, toY, nameTo);

                    int distance = Integer.parseInt(line[6]);

                    Route route = new Route(name, coordinates, from, to);
                    route.setId(id);
                    route.setCreationDate(creationDate);
                    route.setDistance(distance);

                    routes.add(route);
                    dict.put(id, route);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCSV() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Файл создан: " + fileName);
                } else {
                    System.out.println("Не удалось создать файл: " + fileName);
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        readCSV();

        Route route = Asker.askRoute(new StandardConsole());
        routes.add(route);
        Collections.sort(routes);

        try (CSVWriter writer = new CSVWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))))) {
            String[] header = {"ID", "Name", "Coordinates", "Date", "From", "To", "Distance"};
            writer.writeNext(header);


            for (Route e : routes) {
                if (e.getId() != null) {
                    currentId = e.getId();
                } else {
                    currentId = getFreeId(dict);
                }
                String[] data = {
                        String.valueOf(currentId),
                        e.getName(),
                        e.getCoordinates().toString(),
                        e.getCreationDate().toString(),
                        e.getFrom().toString(),
                        e.getTo().toString(),
                        String.valueOf(e.getDistance())
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Long getFreeId(Map<Long, Route> dict) {
        if (!dict.isEmpty()) return Collections.max(dict.keySet()) + 1;
        return 1L;
    }

    public LinkedList<Route> getCollection() {
        return routes;
    }

//    public static void generateCSV(String fileName, LinkedList<Route> routes) {
//        try (CSVWriter writer = new CSVWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))))) {
//            String[] header = {"ID", "Name", "Coordinates", "Date", "From", "To", "Distance"};
//            writer.writeNext(header);
//
//            for (Route route : routes) {
//                String[] data = {
//                        String.valueOf(1),
//                        route.getName(),
//                        route.getCoordinates().toString(),
//                        String.valueOf(new Date()),
//                        route.getFrom().toString(),
//                        route.getTo().toString(),
//                        String.valueOf(route.getDistance())
//                };
//                writer.writeNext(data);
//            }
//
//            System.out.println("CSV-файл успешно создан: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
