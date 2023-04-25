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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Кейс «Анализатор курса валют».
// 3. Очень сложное:
//- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал. Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
// Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
// Задание здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1

public class Draft_1_3_Case_3_1_1_WithDifference {

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
        String originalPage = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");
        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        String originalPageText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";

//    get the last day of month
        int lastDay = ym.lengthOfMonth();
//    Создаем массив ArrayList, куда записываем в качестве элементов курс на текущую дату.
        List<Double> listCourses = new ArrayList<>();

//    loop through the days
        for (int day = 1; day <= lastDay; day++) {
            // create the day
            LocalDate dt = ym.atDay(day);
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dtStr = dt.format(f);
            // set to midnight at JVM default timezone
            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
            int endIndex = originalPage.lastIndexOf("</Value>");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(String.valueOf(dtStr)));
            String nextDate;
            nextDate = sdf.format(c.getTime());  // entering nextDate

            // Меняем в адресе исходной страницы дату на следующую.
            String urlWithNextDate = originalPageText.replaceAll("12/11/2021", nextDate);

            String nextPage = downloadWebPage(urlWithNextDate);

            if (nextPage.contains("<Value>")) {
                String courseNextPage = nextPage.substring(startIndex, endIndex);
                // Задаём курс в виде переменной Double.
                double courseNextDoble = Double.parseDouble(courseNextPage.replace(",", "."));
                System.out.println("Курс на " + nextDate + "    " + courseNextDoble);
                listCourses.add(courseNextDoble);
            } else {
                String courseNextPage = "";
                System.out.println("Курс на " + nextDate);
            }
        }
//Далее ищем максимальные перепады курса.
        double max = maxDifference(listCourses);
        double min = minDifference(listCourses);
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("\nЗа указанный месяц курс максимально вырос между двумя соседними датами на величину: " + df.format(max) + ", это пришлось на дату: ");
        System.out.println("За указанный месяц курс максимально упал между двумя соседними датами на величину: " + df.format(min) + ", это пришлось на дату: ");


//НАХОДИМ ДАТЫ МАКСИМАЛЬНОГО РОСТА И ПАДЕНИЯ КУРСА И СКАЧИВАЕМ СТРАНИЦЫ ИЗ WIKINEWS.
        String dtStrMax = "18/03/2023"; //Объявляем дату, когда курс максимально вырос
        String[] items2 = dtStrMax.split("/");
        String dat2 = items2[0];
        String mon2 = items2[1];
        String yea2 = items2[2];
        String dtStr2 = (yea2 + "-" + mon2 + "-" + dat2);

        LocalDate localDate2 = LocalDate.parse(dtStr2);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String formattedDate1 = formatter.format(localDate2);

        String[] items3 = formattedDate1.split(" ");
        String dat3 = items3[0];
        String mon3 = items3[1];
        String yea3 = items3[2];
        String gFirst = items3[3];
        String g3 = gFirst.replace("г.", "года");

        String dtStrForChangeMax = (dat3 + "_" + mon3 + "_" + yea3 + "_" + g3);

        //СОХРАНЯЕМ СТРАНИЦЫ ИЗ ВИКИПЕДИИ MAX
        //        String pageWikiOrigin = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года");
        String pageWikiOriginText = "https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года";
        String pageWikiOriginChangedTextMax = pageWikiOriginText.replaceAll("31_марта_2023_года", dtStrForChangeMax);
        String pageWikiOriginChanged1 = downloadWebPage(pageWikiOriginChangedTextMax);
        writeToFile(pageWikiOriginChanged1);

        String dtStrMin = "03/03/2023"; //Объявляем дату, когда курс максимально упал
        String[] items4 = dtStrMin.split("/");
        String dat4 = items4[0];
        String mon4 = items4[1];
        String yea4 = items4[2];
        String dtStr4 = (yea4 + "-" + mon4 + "-" + dat4);

        LocalDate localDate4 = LocalDate.parse(dtStr4);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String formattedDate2 = formatter2.format(localDate4);

        String[] items5 = formattedDate2.split(" ");
        String dat5 = items5[0];
        String mon5 = items5[1];
        String yea5 = items5[2];
        String gSecond = items5[3];
        String g5 = gSecond.replace("г.", "года");

        String dtStrForChangeMin = (dat5 + "_" + mon5 + "_" + yea5 + "_" + g5);

//СОХРАНЯЕМ СТРАНИЦЫ ИЗ ВИКИПЕДИИ MIN
//        String pageWikiOrigin = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года");
//        String pageWikiOriginText = "https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года";
        String pageWikiOriginChangedTextMin = pageWikiOriginText.replaceAll("31_марта_2023_года", dtStrForChangeMin);
        String pageWikiOriginChanged2 = downloadWebPage(pageWikiOriginChangedTextMin);
        writeToFile(pageWikiOriginChanged2);

        System.out.println("\n" + pageWikiOriginChangedTextMax);
        System.out.println(pageWikiOriginChangedTextMin);

        System.out.println("\n" + dtStrForChangeMax + " " + "- в этот день курс вырос.");
        System.out.println(dtStrForChangeMin + " " + "- в этот день курс упал.");

        System.out.println("\nСтраница max сохранена!");
        System.out.println("Страница min сохранена!");

    }

    //Пишем классы для поиска максимальных перепадов курса.
//Сначала максимальную разницу находим.
    public static double maxDifference(List<Double> listCourses) {
        if (listCourses == null || listCourses.size() == 0) {
            return Double.MIN_VALUE;
        }
        int len = listCourses.size();
        double[] diff = new double[len - 1];
        for (int i = 0; i < len - 1; i++) {
            diff[i] = listCourses.get(i + 1) - listCourses.get(i);
        }
        return max(diff);
    }

    public static double max(double[] diff) {
        if (diff == null || diff.length == 0) {
            return Double.MIN_VALUE;
        }
        double max = diff[0];
        for (int i = 0, len = diff.length; i < len; i++) {
            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
            if (max < diff[i]) {
                max = diff[i];
            }
        }
        return max;
    }

    //Теперь минимальную разницу находим.
    public static double minDifference(List<Double> listCourses) {
        if (listCourses == null || listCourses.size() == 0) {
            return Double.MIN_VALUE;
        }
        int len = listCourses.size();
        double[] diff = new double[len - 1];
        for (int i = 0; i < len - 1; i++) {
            diff[i] = listCourses.get(i + 1) - listCourses.get(i);
        }
        return min(diff);
    }

    public static double min(double[] diff) {
        if (diff == null || diff.length == 0) {
            return Double.MIN_VALUE;
        }
        double min = diff[0];
        for (int i = 0, len = diff.length; i < len; i++) {
            //not necessary,since 'int[] data' is sorted,so 'int[] diff' is progressively increased.
            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
            if (min > diff[i]) {
                min = diff[i];
            }
        }
        return min;
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

    public static void writeToFile(String str) throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("pageDiff1.html"));
        writer1.write(str);
        writer1.close();
//    public static void writeToFile(String str) throws IOException {
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("pageDiff2.html"));
        writer2.write(str);
        writer2.close();
    }
}