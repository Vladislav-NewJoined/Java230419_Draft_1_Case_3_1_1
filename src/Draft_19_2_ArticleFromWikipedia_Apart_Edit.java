import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Draft_19_2_ArticleFromWikipedia_Apart_Edit {
// Кейс «Анализатор курса валют».
// 3. Очень сложное:
//- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал.
// Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
// Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
// Задание здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
//Здесь Java. Поиск второго по минимальности элемента в массиве. https://youtu.be/03iETRvZrFg
//Статья из Википедии здесь: https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8C_2021_%D0%B3%D0%BE%D0%B4%D0%B0
    public static void main(String[] args) throws IOException, ParseException {
        String originalString = downloadWebPage("https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8C_2021_%D0%B3%D0%BE%D0%B4%D0%B0");
//https://ru.wikipedia.org/wiki/8_февраля
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите исходную дату с разделителем '/': пример: 14/02/2020");
        String originalDate = buffered.readLine();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(originalDate));
        c.add(Calendar.DATE, -1);  // number of days to add
        String oneDayBeforeDate;
        oneDayBeforeDate = sdf.format(c.getTime());  // entering oneDayBeforeDate
        c.add(Calendar.DATE, 2);  // number of days to add
        String oneDayAfterDate;
        oneDayAfterDate = sdf.format(c.getTime());  // entering oneDayAfterDate

        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
//        System.out.println("Исходная страница:");
        String originalStrText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
//        System.out.println(originalStrText + System.lineSeparator());

        // Меняем в адресе исходной страницы даты на введённые (в трех строках).
        String urlWithDate1 = originalStrText.replaceAll("12/11/2021", originalDate);
        String urlWithDate2 = originalStrText.replaceAll("12/11/2021", oneDayBeforeDate);
        String urlWithDate3 = originalStrText.replaceAll("12/11/2021", oneDayAfterDate);
//        System.out.println(urlWithDate3);

        String page1 = downloadWebPage(urlWithDate1);
        String page2 = downloadWebPage(urlWithDate2);
        String page3 = downloadWebPage(urlWithDate3);

        // Задаём курсы с типом переменной double.
        double courseDoble1 = 0;
        double courseDoble2 = 0;
        double courseDoble3 = 0;

        if (page1.contains("<Value>")) {
            int startIndex1 = page1.lastIndexOf("<Value>");
            int endIndex1 = page1.lastIndexOf("</Value>");
            String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
            double course1 = Double.parseDouble(courseStr1.replace(",", "."));
            courseDoble1 = course1;
        }
        if (page2.contains("<Value>")) {
            int startIndex2 = page2.lastIndexOf("<Value>");
            int endIndex2 = page2.lastIndexOf("</Value>");
            String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);
            double course2 = Double.parseDouble(courseStr2.replace(",", "."));
            courseDoble2 = course2;
        }
        if (page3.contains("<Value>")) {
            int startIndex3 = page3.lastIndexOf("<Value>");
            int endIndex3 = page3.lastIndexOf("</Value>");
            String courseStr3 = page3.substring(startIndex3 + 7, endIndex3);
            double course3 = Double.parseDouble(courseStr3.replace(",", "."));
            courseDoble3 = course3;
        }

        System.out.println();

        String noCurse = "На указанную дату курс отсутствует";
        if (courseDoble1 == 0) {
            System.out.println("Курс на " + originalDate + ": " + noCurse);
        } else {
            System.out.println("Курс на " + originalDate + ": " + courseDoble1);
        }

        if (courseDoble2 == 0) {
            System.out.println("Курс на " + oneDayBeforeDate + ": " + noCurse);
        } else {
            System.out.println("Курс на " + oneDayBeforeDate + ": " + courseDoble2);
        }

        if (courseDoble3 == 0) {
            System.out.println("Курс на " + oneDayAfterDate + ": " + noCurse);
        } else {
            System.out.println("Курс на " + oneDayAfterDate + ": " + courseDoble3);
        }

        System.out.println();

        if (courseDoble1 == 0 || courseDoble2 == 0 || courseDoble3 == 0) {
            System.out.println("Имеются даты с отсутствующим курсом. Повторите программу и введите другую дату.");
        } else {
            if (courseDoble3 > courseDoble2) {
                System.out.print("Курс вырос на ");
                System.out.println(courseDoble3 - courseDoble2 + "\n");
            } else {
                if (courseDoble3 < courseDoble2) {
                    System.out.print("Курс снизился на ");
                    System.out.println(courseDoble2 - courseDoble3 + "\n");
                }
            }
            boolean courseDoble1Max = courseDoble1 > courseDoble2 && courseDoble1 > courseDoble3;
            boolean courseDoble2Max = courseDoble2 > courseDoble1 && courseDoble2 > courseDoble3;
            if (courseDoble1Max) {
                System.out.println("Максимальный курс: " + courseDoble1 + "; " + "Приходится на дату: " + originalDate);
            } else {
                if (courseDoble2Max) {
                    System.out.println("Максимальный курс: " + courseDoble2 + "; " + "Приходится на дату: " + oneDayBeforeDate);
                } else {
                    System.out.println("Максимальный курс: " + courseDoble3 + "; " + "Приходится на дату: " + oneDayAfterDate);
                }
            }
            boolean courseDoble1Min = courseDoble1 < courseDoble2 && courseDoble1 < courseDoble3;
            boolean courseDoble2Min = courseDoble2 < courseDoble1 && courseDoble2 < courseDoble3;
            if (courseDoble1Min) {
                System.out.println("Минимальный курс: " + courseDoble1 + "; " + "Приходится на дату: " + originalDate);
            } else {
                if (courseDoble2Min) {
                    System.out.println("Минимальный курс: " + courseDoble2 + "; " + "Приходится на дату: " + oneDayBeforeDate);
                } else {
                    System.out.println("Минимальный курс: " + courseDoble3 + "; " + "Приходится на дату: " + oneDayAfterDate);
                }
            }
        }

        String pageNasa = downloadWebPage("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        int urlBegin = pageNasa.lastIndexOf("url");
        int urlEnd = pageNasa.lastIndexOf("}");
        String urlPhoto = pageNasa.substring(urlBegin + 6, urlEnd - 1);
        try (InputStream in = new URL(urlPhoto).openStream()) {
            Files.copy(in, Paths.get("new.jpg"));
        }

        System.out.println("\n" + "Картинка сохранена!");

        int explanationBegin = pageNasa.lastIndexOf("explanation");
        int explanationEnd = pageNasa.lastIndexOf("hdurl");
        String explanation = pageNasa.substring(explanationBegin + 14, explanationEnd - 3);
        System.out.println(explanation);

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
