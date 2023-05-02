// Java program to Find Entry
// with the Highest Value in Map
// Using Comparators in Map interface

// Importing all utility classes

import org.w3c.dom.ls.LSOutput;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
//Import java.util.Map.Entry;

import static java.lang.Double.MIN_VALUE;
import static java.util.Map.entry;

// Main class
class Draft_30_7_Map_NEXT7_overcoder_net_EDIT {
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

//        1 Пример из overcoder.net с улучшенным циклом for
//        https://overcoder.net/q/1822/%D0%BA%D0%B0%D0%BA-%D1%8D%D1%84%D1%84%D0%B5%D0%BA%D1%82%D0%B8%D0%B2%D0%BD%D0%BE-%D0%BF%D0%B5%D1%80%D0%B5%D0%B1%D1%80%D0%B0%D1%82%D1%8C-%D0%BA%D0%B0%D0%B6%D0%B4%D1%83%D1%8E-%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C-%D0%B2-java-map
//        ```
        for (Map.Entry<String, Double> entry : sortedMap.entrySet())
        {
//                         System.out.println(entry.getKey() + "/" + entry.getValue());
        }
//        ```

//        2 Пример из stackoverflow.com
//        https://stackoverflow.com/questions/3110547/java-how-to-create-new-entry-key-value
//        List<String> keys = new ArrayList<String>(sortedMap.keySet());
//        ```
//        for(int i = 0; i < sortedMap.size(); i++) {
//            String key = keys.get(i);
//            String value = sortedMap.get(key);
//            System.out.println(sortedMap.get(0));

//        ```

//            System.out.println(arr.get(0).getKey());
//            System.out.println(arr.get(0).getValue());
//            System.out.println(arr.get(1).getKey());
//            System.out.println(arr.get(1).getValue());


//        3 Пример со stakoverflow.com
//        Простой способ увидеть пары ключ-значение:
//        https://stackoverflow.com/questions/5920135/printing-hashmap-in-java
//        ```
//        System.out.println(Arrays.asList(sortedMap)); // method 1
//        System.out.println(Collections.singletonList(sortedMap)); // method 2
//        ```


//        4 Пример с нулучшенным for

//        ```
//    int i = 15;
//    int sizeOfMap = sortedMap.size();
//        System.out.println(sizeOfMap);
//        for (Map.Entry<String, Double> entry : sortedMap.entrySet())
//    {
//        if (i<sizeOfMap) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//            i++;
//        } else break;
//    }
//        ```

//        5 Пример (УДАЛСЯ!!!!!!!!!!!!!!!!!!)  с ru.stakoverflow.com
//        https://ru.stackoverflow.com/questions/495996/%D0%9A%D0%B0%D0%BA-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-fori-%D0%BF%D1%80%D0%BE%D0%B9%D1%82%D0%B8%D1%81%D1%8C-%D0%BF%D0%BE-hashmap?ysclid=lh57s1xfoc298108769
////        ```
//        List<String> keys = new ArrayList<String>(sortedMap.keySet());
//        for(int i = 1; i < keys.size(); i++) {
//            String key = keys.get(i);
//            Double value = sortedMap.get(key);
//            System.out.println(key + " / " + value);
//
//        }
////        ```

//        6 Пример с ru.stakoverflow.com
//        с попыткой найти разницу соседних элементов
//        ```
        System.out.println(); // Пустую строку добавляем
        List<String> keys = new ArrayList<String>(sortedMap.keySet());
        Double maxDiffer = sortedMap.get(keys.get(1)) - sortedMap.get(keys.get(0));
        String dataOfMaxDiffer = keys.get(0);
        for(int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Double value = sortedMap.get(key);
            System.out.println(key + " / " + value);
        }
        System.out.println(); // Распечатали список, далее пустую строку добавляем

//        Дальше в следующем цикле ищем разницу между соседними элементами
//        Сначала ищем максимальный прирост
        for(int i = 1; i < keys.size(); i++) {
            String key = keys.get(i);
            Double value = sortedMap.get(key);
            String keyMinusOne = keys.get(i-1);
            Double valueMinusOne = sortedMap.get(keyMinusOne);

            Double Differ = (value - valueMinusOne); // = MIN_VALUE;
            DecimalFormat df2 = new DecimalFormat("0.000");
            df2.setRoundingMode(RoundingMode.DOWN);
            System.out.println(df2.format(Differ));
            if ((sortedMap.get(keys.get(i)) - sortedMap.get(keys.get(i-1)) > maxDiffer)) {
                maxDiffer = (value - valueMinusOne);
                dataOfMaxDiffer = key;
            }
        }

        // The printed result
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("\n" + "Maximum is : " + df.format(maxDiffer) + " corresponds with data " + dataOfMaxDiffer);
        System.out.println("Minimum is : " + "ищем...");






































//        ```














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
