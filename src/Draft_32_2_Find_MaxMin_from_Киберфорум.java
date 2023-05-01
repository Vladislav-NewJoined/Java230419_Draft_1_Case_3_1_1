/* Обучающее инфо по этой теме:
java перебрать Map и найти наибольшее значение второго элемента пары
Map — это набор пар “ключ-значение”.
https://javascopes.com/java-find-map-max-a16a7e6f/
https://translated.turbopages.org/proxy_u/en-ru.ru.38c534a0-644f4af8-e86fc728-74722d776562/https/www.geeksforgeeks.org/how-to-find-the-entry-with-largest-value-in-a-java-map/
https://javarush.com/groups/posts/2542-otvetih-na-samihe-populjarnihe-voprosih-ob-interfeyse-map */

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Draft_32_2_Find_MaxMin_from_Киберфорум {
    public static void main(String[] args) throws MalformedURLException {


// Тест
// прочитать котировки
        ValCurs valCurs = readRates(YearMonth.of(2023, 3));//xmlMapper.readValue(XML, ValCurs.class);
// вывести первых пять записей
//        valCurs.getRates().stream().limit(5).forEach(System.out::println);
//// найти максимальные изменения
//        findMaxChanges(valCurs.getRates());









    }

    public static ValCurs readRates(YearMonth month) throws MalformedURLException {
        LocalDate from = month.atDay(1).minusDays(1);
        LocalDate to = month.atEndOfMonth();
        DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String url = String.format(
                "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=28/02/2023&date_req2=31/03/2023&VAL_NM_RQ=R01235",
                from.format(ddMMyyyy),
                to.format(ddMMyyyy)
        );

//// Тест
//// прочитать котировки
//        ValCurs valCurs = readRates(YearMonth.of(2023, 3));//xmlMapper.readValue(XML, ValCurs.class);
//// вывести первых пять записей
//        valCurs.getRates().stream().limit(5).forEach(System.out::println);
//// найти максимальные изменения
//        findMaxChanges(valCurs.getRates());










        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());

        return xmlMapper.readValue(new URL(url), ValCurs.class);
    }

    private static class ValCurs {
    }

    private static class XmlMapper {
        public void registerModule(JavaTimeModule javaTimeModule) {

        }

        public ValCurs readValue(URL url, Class<ValCurs> valCursClass) {
            return null;
        }
    }

    private static class JavaTimeModule {
    }

    public static void findMaxChanges(List<CurrencyRate> rates) {
        CurrencyRate prev = rates.get(0);
        CurrencyRate maxPlus = null;
        CurrencyRate maxMinus = null;
        double max = 0.0, min = 0.0, sum = 0.0;
        for (int i = 1, n = rates.size(); i < n; i++) {
            CurrencyRate curr = rates.get(i);
            double change = curr.getValue() - prev.getValue();
            if (change > 0) {
                if (maxPlus == null || change > max) {
                    max = change;
                    maxPlus = curr;
                }
            } else if (change < 0) {
                if (maxMinus == null || change < min) {
                    min = change;
                    maxMinus = curr;
                }
            }
            sum += change;
            prev = curr;
        }
        if (maxPlus != null) {
            System.out.printf("Максимальный прирост: %.4f соответствует дате %s%n", max, maxPlus.getDate());
        }
        if (maxMinus != null) {
            System.out.printf("Максимальное снижение: %.4f соответствует дате %s%n", min, maxMinus.getDate());
        }
        System.out.printf("Суммарное изменение за месяц: %.4f%n", sum);
        System.out.printf("Среднее ежедневное изменение за месяц: %.4f для %d котировок%n", sum / (rates.size() - 1), rates.size() - 1);
    }

    private static class CurrencyRate {
        public Object getDate() {
            return null;
        }

        public double getValue() {
            return 0;
        }
    }
}
