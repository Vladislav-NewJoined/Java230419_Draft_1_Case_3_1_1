/* Обучающее инфо по этой теме:
java перебрать Map и найти наибольшее значение второго элемента пары
Map — это набор пар “ключ-значение”.
https://javascopes.com/java-find-map-max-a16a7e6f/
https://translated.turbopages.org/proxy_u/en-ru.ru.38c534a0-644f4af8-e86fc728-74722d776562/https/www.geeksforgeeks.org/how-to-find-the-entry-with-largest-value-in-a-java-map/
https://javarush.com/groups/posts/2542-otvetih-na-samihe-populjarnihe-voprosih-ob-interfeyse-map */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Map.entry;

public class Draft_32_1_Through_Map_Find_MaxMin_Changes {
    public static void main(String[] args) {

/* Используем модель
Map<String, Integer> cities = Map.ofEntries(
entry("Brussels", 1139000),
entry("Cardiff", 341000)
);
инфо здесь: https://stackoverflow.com/questions/8261075/adding-multiple-entries-to-a-hashmap-at-once-in-one-statement */

        // Задаем пары sortedMap
        Map<String, Double> sortedMap = Map.ofEntries(
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
    }
//        public static ValCurs readRates(YearMonth month) {
//
//
//// ТЕСТ
//// прочитать котировки
//        ValCurs valCurs = readRates(YearMonth.of(2023, 3));//xmlMapper.readValue(XML, ValCurs.class);
//// вывести первых пять записей
//        valCurs.getRates().stream().limit(5).forEach(System.out::println);
//// найти максимальные изменения
//        findMaxChanges(valCurs.getRates());
//
//
//
//
//
//
//
//
//
//
//
//            LocalDate from = month.atDay(1).minusDays(1);
//            LocalDate to = month.atEndOfMonth();
//            DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String url = String.format(
//                    "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=%s&date_req2=%s&VAL_NM_RQ=R01235",
//                    from.format(ddMMyyyy),
//                    to.format(ddMMyyyy)
//            );
//            XmlMapper xmlMapper = new XmlMapper();
//            xmlMapper.registerModule(new JavaTimeModule());
//
//            return xmlMapper.readValue(new URL(url), ValCurs.class);
//        }
//
//        public static void findMaxChanges(List<CurrencyRate> rates) {
//            CurrencyRate prev = rates.get(0);
//            CurrencyRate maxPlus = null;
//            CurrencyRate maxMinus = null;
//            double max = 0.0, min = 0.0, sum = 0.0;
//            for (int i = 1, n = rates.size(); i < n; i++) {
//                CurrencyRate curr = rates.get(i);
//                double change = curr.getValue() - prev.getValue();
//                if (change > 0) {
//                    if (maxPlus == null || change > max) {
//                        max = change;
//                        maxPlus = curr;
//                    }
//                } else if (change < 0) {
//                    if (maxMinus == null || change < min) {
//                        min = change;
//                        maxMinus = curr;
//                    }
//                }
//                sum += change;
//                prev = curr;
//            }
//            if (maxPlus != null) {
//                System.out.printf("Максимальный прирост: %.4f соответствует дате %s%n", max, maxPlus.getDate());
//            }
//            if (maxMinus != null) {
//                System.out.printf("Максимальное снижение: %.4f соответствует дате %s%n", min, maxMinus.getDate());
//            }
//            System.out.printf("Суммарное изменение за месяц: %.4f%n", sum);
//            System.out.printf("Среднее ежедневное изменение за месяц: %.4f для %d котировок%n", sum / (rates.size() - 1), rates.size() - 1);
//        }

// {01/03/2023=74.8932, 02/03/2023=75.2513, 03/03/2023=75.4729, 04/03/2023=75.4592, 07/03/2023=75.4728, 08/03/2023=75.4577, 10/03/2023=75.9028, 11/03/2023=75.9406, 14/03/2023=75.4609, 15/03/2023=75.1927, 16/03/2023=75.7457, 17/03/2023=76.4095, 18/03/2023=76.6044, 21/03/2023=77.2422, 22/03/2023=76.8373, 23/03/2023=76.9561, 24/03/2023=76.3072, 25/03/2023=76.4479, 28/03/2023=76.5662, 29/03/2023=76.5939, 30/03/2023=76.9781, 31/03/2023=77.0863}
        // 01/03/2023=74.8932
        // 02/03/2023=75.2513
        // 03/03/2023=75.4729
        // 04/03/2023=75.4592
        // 07/03/2023=75.4728
        // 08/03/2023=75.4577
        // 10/03/2023=75.9028
        // 11/03/2023=75.9406
        // 14/03/2023=75.4609
        // 15/03/2023=75.1927
        // 16/03/2023=75.7457
        // 17/03/2023=76.4095
        // 18/03/2023=76.6044
        // 21/03/2023=77.2422
        // 22/03/2023=76.8373
        // 23/03/2023=76.9561
        // 24/03/2023=76.3072
        // 25/03/2023=76.4479
        // 28/03/2023=76.5662
        // 29/03/2023=76.5939
        // 30/03/2023=76.9781
        // 31/03/2023=77.0863



}
