import java.io.*;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

// Кейс «Анализатор курса валют».
// 3. Очень сложное:
//- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал. Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
// Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
// Задание здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1

// Здесь курсы рубля за все даты месяца: https://cbr.ru/scripts/XML_dynamic.asp?date_req1=28/02/2023&date_req2=31/03/2023&VAL_NM_RQ=R01235
// МНЕ ОТВЕТ на stackoverflow: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
// Парсинг ( десериализация ) HTML: https://www.youtube.com/watch?v=R0u8HDEV1vM
// Парсинг ( десериализация ) HTML тоже: https://youtu.be/wSucpFh7ouk
// Здесь про Map: https://javarush.com/groups/posts/2542-otvetih-na-samihe-populjarnihe-voprosih-ob-interfeyse-map
public class Draft_30_4_Map_With_Case_3_1_1_NEXT3 {

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите исходные месяц и год с разделителем '/', пример: 03/2023:");
        String origMonth = buffered.readLine();  // Start month

        // Делаем парсинг введённой строки методом Split.

        String[] items = origMonth.split("/");
        String mon = items[0];
        String yea = items[1];

        int monI = Integer.parseInt(mon);
        int yeaI = Integer.parseInt(yea);

        // Преобразовываем ввод через переменную YearMonth.
        YearMonth ym = YearMonth.of(yeaI, monI);

        // Скачиваем исходный код веб-страницы Центробанка.
        // Вот здесь указаны курсы рубля по всем датам месяца, т.е. целиком месяц выводится на экран:
        // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=28/02/2023&date_req2=31/03/2023&VAL_NM_RQ=R01235
        String originalPage = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");
        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        String originalPageText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";

        //    get the last day of month
        int lastDay = ym.lengthOfMonth();
        //    Создаем массив ArrayList, куда записываем в качестве элементов курс на текущую дату.
        List<Double> ratesList = new ArrayList<>();

// Задаём Map.
        Map<String, Double> mapRatesInDates = new HashMap<>(); // todo удалить





// КАК НАЙТИ ДАТУ НА ДЕНЬ РАНЬШЕ. / todo удалить
//        int dayFirst = 1; // todo удалить
//        LocalDate dayFirstLD = ym.atDay(dayFirst); // todo удалить
//        LocalDate dayMinusOne = dayFirstLD - 1; // todo удалить
//        System.out.println(dayFirstLD); // todo удалить


//        Calendar cal  = Calendar.getInstance(); // todo удалить
//        //subtracting a day // todo удалить
//        cal.add(Calendar.DATE, -1); // todo удалить
//         // todo удалить
//        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd"); // todo удалить





        //    loop through the days
        for (int day = 1; day <= lastDay; day++) {
            // create the day
            LocalDate dt = ym.atDay(day);
            //            System.out.println(dt); // todo удалить


            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dtStr = dt.format(f);
            // set to midnight at JVM default timezone
            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
            int endIndex = originalPage.lastIndexOf("</Value>");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dtStr));
            String currentDate;
            currentDate = sdf.format(c.getTime());  // entering current Date
            // Меняем в адресе исходной страницы дату на текующую.
            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);

            String currentPage = downloadWebPage(urlWithCurrentDate);

            if (currentPage.contains("<Value>")) {
                String currentRatePage = currentPage.substring(startIndex, endIndex);
                // Задаём курс в виде переменной Double.
                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
                System.out.println("Курс на " + currentDate + "    " + doubleCurrentRate);
                // Здесь: Как найти из мапы перепады курса: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
                mapRatesInDates.put(currentDate, doubleCurrentRate); // todo удалить
                ratesList.add(doubleCurrentRate);
            } else {
                String currentRatePage = "";
                System.out.println("Курс на " + currentDate);
            }
        }


        //Далее ищем максимальные перепады курса.
        double maxDif = maxDifference(ratesList);
        double minDif = minDifference(ratesList);
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.DOWN);



// Создаём и распечатываем отсортированный список Map. Про сортировку здесь: https://rukovodstvo.net/posts/id_598/
        Map<String, Double> sortedMap = new TreeMap<>(mapRatesInDates); // - ИЗ ЭТОГО ОТСОРТ-ГО Мапа МАКСИМАЛЬНЫЕ ПЕРЕПАДЫ КУРСА БРАТЬ.
        System.out.println("\n" + "Выводим на экран отсортированный Map sortedMap");
        System.out.println(sortedMap);
//        sortedMap.entrySet().forEach(System.out::println);


        System.out.println("\nЗа указанный месяц максимальный прирост курса между двумя соседними датами: " +
                df.format(maxDif) + ", это пришлось на дату: " + "датаПриростаКурса");
        System.out.println("За указанный месяц максимальное снижение курса между двумя соседними датами: : " +
                df.format(minDif) + ", это пришлось на дату: " + "датаПаденияКурса");

    }

    //Пишем классы для поиска максимальных перепадов курса.
    //Сначала максимальную разницу находим.
    public static double maxDifference(List<Double> ratesList) {
        if (ratesList == null || ratesList.size() == 0) {
            return Double.MIN_VALUE;
        }
        int len = ratesList.size();
        double[] diff = new double[len - 1];
        for (int i = 0; i < len - 1; i++) {
            diff[i] = ratesList.get(i + 1) - ratesList.get(i);
        }
        return maxDif(diff);
    }

    public static double maxDif(double[] diff) {
        if (diff == null || diff.length == 0) {
            return Double.MIN_VALUE;
        }
        double maxDif = diff[0];
        for (int i = 0, len = diff.length; i < len; i++) {
            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
            if (maxDif < diff[i]) {
                maxDif = diff[i];
            }
        }
        return maxDif;
    }

    //Теперь минимальную разницу находим.
    public static double minDifference(List<Double> ratesList) {
        if (ratesList == null || ratesList.size() == 0) {
            return Double.MIN_VALUE;
        }
        int len = ratesList.size();
        double[] diff = new double[len - 1];
        for (int i = 0; i < len - 1; i++) {
            diff[i] = ratesList.get(i + 1) - ratesList.get(i);
        }
        return minDif(diff);
    }

    public static double minDif(double[] diff) {
        if (diff == null || diff.length == 0) {
            return Double.MIN_VALUE;
        }
        double minDif = diff[0];
        for (int i = 0, len = diff.length; i < len; i++) {
            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
            if (minDif > diff[i]) {
                minDif = diff[i];
            }
        }
        return minDif;
    }

    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
}