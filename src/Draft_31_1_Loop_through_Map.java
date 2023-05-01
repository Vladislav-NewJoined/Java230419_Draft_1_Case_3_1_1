/* Обучающее инфо по этой теме:
        java перебрать Map и найти наибольшее значение второго элемента пары
        Map — это набор пар “ключ-значение”.
        https://javascopes.com/java-find-map-max-a16a7e6f/
        https://translated.turbopages.org/proxy_u/en-ru.ru.38c534a0-644f4af8-e86fc728-74722d776562/https/www.geeksforgeeks.org/how-to-find-the-entry-with-largest-value-in-a-java-map/
        https://javarush.com/groups/posts/2542-otvetih-na-samihe-populjarnihe-voprosih-ob-interfeyse-map */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Draft_31_1_Loop_through_Map {
    public static void main(String[] args) {
        Map<String, Double> mapRatesInDates = new HashMap<>();
// {03/03/2023=75.4729, 10/03/2023=75.9028, 04/03/2023=75.4592, 11/03/2023=75.9406, 02/03/2023=75.2513, 25/03/2023=76.4479, 28/03/2023=76.5662, 01/03/2023=74.8932, 31/03/2023=77.0863, 24/03/2023=76.3072, 23/03/2023=76.9561, 29/03/2023=76.5939, 08/03/2023=75.4577, 15/03/2023=75.1927, 22/03/2023=76.8373, 07/03/2023=75.4728, 14/03/2023=75.4609, 21/03/2023=77.2422, 16/03/2023=75.7457, 30/03/2023=76.9781, 17/03/2023=76.4095, 18/03/2023=76.6044}
        mapRatesInDates.put("03/03/2023", 75.4729);
        mapRatesInDates.put("10/03/2023", 75.9028);
        mapRatesInDates.put("04/03/2023", 75.4592);
        System.out.println(mapRatesInDates);
        mapRatesInDates.entrySet().forEach(System.out::println);

        Map<String, Double> sortedMap = new TreeMap<>(mapRatesInDates);
        System.out.println(sortedMap);
        sortedMap.entrySet().forEach(System.out::println);

    }
}
