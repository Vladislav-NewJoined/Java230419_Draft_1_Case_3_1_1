import java.util.HashMap;
import java.util.Map;

// Здесь курсы рубля за все даты месяца: https://cbr.ru/scripts/XML_dynamic.asp?date_req1=28/02/2023&date_req2=31/03/2023&VAL_NM_RQ=R01235
// МНЕ ОТВЕТ на stackoverflow: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
// Парсинг ( десериализация ) HTML: https://www.youtube.com/watch?v=R0u8HDEV1vM
// Парсинг ( десериализация ) HTML тоже: https://youtu.be/wSucpFh7ouk
// Здесь про Map: https://javarush.com/groups/posts/2542-otvetih-na-samihe-populjarnihe-voprosih-ob-interfeyse-map
public class Draft_30_1_Map {

    public static void main(String[] args) {

        Map<String, String> mapDateRate = new HashMap<>();
        mapDateRate.put("28/02/2023", "75.4323");
        mapDateRate.put("01/03/2023", "74.8932");
        mapDateRate.put("02/03/2024", "75.2513");
        mapDateRate.put("03/03/2025", "75.4729");
        mapDateRate.put("04/03/2026", "75.4592");
        mapDateRate.put("05/03/2027", "");
        mapDateRate.put("06/03/2028", "");
        mapDateRate.put("07/03/2029", "75.4728");
        mapDateRate.put("08/03/2030", "75.4577");
        mapDateRate.put("09/03/2031", "");
        mapDateRate.put("10/03/2032", "75.9028");
        mapDateRate.put("11/03/2033", "75.9406");
        mapDateRate.put("12/03/2034", "");
        mapDateRate.put("13/03/2035", "");
        mapDateRate.put("14/03/2036", "75.4609");
        mapDateRate.put("15/03/2037", "75.1927");
        mapDateRate.put("16/03/2038", "75.7457");
        mapDateRate.put("17/03/2039", "76.4095");
        mapDateRate.put("18/03/2040", "76.6044");
        mapDateRate.put("19/03/2041", "");
        mapDateRate.put("20/03/2042", "");
        mapDateRate.put("21/03/2043", "77.2422");
        mapDateRate.put("22/03/2044", "76.8373");
        mapDateRate.put("23/03/2045", "76.9561");
        mapDateRate.put("24/03/2046", "76.3072");
        mapDateRate.put("25/03/2047", "76.4479");
        mapDateRate.put("26/03/2048", "");
        mapDateRate.put("27/03/2049", "");
        mapDateRate.put("28/03/2050", "76.5662");
        mapDateRate.put("29/03/2051", "76.5939");
        mapDateRate.put("30/03/2052", "76.9781");
        mapDateRate.put("31/03/2053", "77.0863");

        System.out.println(mapDateRate);
    }

}