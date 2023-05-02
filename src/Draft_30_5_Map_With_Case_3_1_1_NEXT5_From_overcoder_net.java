// Java program to Find Entry
// with the Highest Value in Map
// Using Comparators in Map interface

// Importing all utility classes

import java.util.Map;
import java.util.TreeMap;
//Import java.util.Map.Entry;

import static java.util.Map.entry;

// Main class
class Draft_30_5_Map_With_Case_3_1_1_NEXT5_From_overcoder_net {
    // Method 3
    // Main driver method
    public static void main(String[] args) {

// Задаем изначальный список Map mapRatesInDates (не отсортированный)
        Map<String, Double> mapRatesInDates = Map.ofEntries(
                // Inserting elements in the Map object
                // using entry() method
                // Custom input element addition
                entry("01/03/2023", 74.8932),
                entry("02/03/2023", 75.2513),
                entry("03/03/2023", 75.4729),
                entry("04/03/2023", 75.4592),
                entry("07/03/2023", 75.4728),
                entry("08/03/2023", 75.4577),
                entry("10/03/2023", 75.9028),
                entry("11/03/2023", 75.9406),
                entry("14/03/2023", 75.4609),
                entry("15/03/2023", 75.1927),
                entry("16/03/2023", 75.7457),
                entry("17/03/2023", 76.4095),
                entry("18/03/2023", 76.6044),
                entry("21/03/2023", 77.2422),
                entry("22/03/2023", 76.8373),
                entry("23/03/2023", 76.9561),
                entry("24/03/2023", 76.3072),
                entry("25/03/2023", 76.4479),
                entry("28/03/2023", 76.5662),
                entry("29/03/2023", 76.5939),
                entry("30/03/2023", 76.9781),
                entry("31/03/2023", 77.0863)
        );
        // Создаём и распечатываем отсортированный список Map sortedMap. Про сортировку здесь: https://rukovodstvo.net/posts/id_598/
        // Creating a Map sortedMap
        // Declaring object of string and double type
        Map<String, Double> sortedMap = new TreeMap<>(mapRatesInDates); // - ИЗ ЭТОГО ОТСОРТ-ГО Мапа МАКСИМАЛЬНЫЕ ПЕРЕПАДЫ КУРСА БРАТЬ.


        // Calling method 2 to
        // print the map
        print(sortedMap);

////         Calling method 1 to
////         find the entry with highest value and
////         print on the console
//        System.out.println(   // Работает в Draft_30_4_Map_With_Case_3_1_1_NEXT4_From_Turbopages
//                "Entry with highest value: "
//                        + getMaxEntryInMapBasedOnValue(sortedMap));

//        Map<String, String> map = ...
        for (Map.Entry<String, Double> entry : sortedMap.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }



    }


//    public static double maxDifference(Map<String, Double> sortedMap) {
//        int i = 0;
//        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
//            System.out.println(i + " " + entry.getKey() + " " + entry.getValue());
//
//            ++i; //iterate
//        }
//        return 0;
//    }

    // Method 2
    // To print the map
    public static void print(Map<String, Double> sortedMap) {

        System.out.print("Map sortedMap: ");

        // If map does not contain any value
        if (sortedMap.isEmpty()) {

            System.out.println("[]");
        } else {
            System.out.println(sortedMap);
        }
    }

}
