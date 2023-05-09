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
public class Draft_1_1_Case_3_1_1 {


// Как получить все даты в указанном диапазоне  https://devmark.ru/article/all-dates-between-two-dates#header1
// Как получить предыдущую дату  https://translated.turbopages.org/proxy_u/en-ru.ru.7652851d-64515d5d-5e1b26ad-74722d776562/https/stackoverflow.com/questions/2458049/how-to-get-previous-date-in-java
// Далее писать так: Во второй цикл добавляем: если day = 1, то get previous day и добавить его во второй ArrayList, отсортировать его и по нему искать разницу.


    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите исходные месяц и год с разделителем '/', пример: 03/2023: ");
        String origMonth = buffered.readLine();  // Start month
        System.out.println(); // Добавляем пустую строку, как разделитель

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
        int lastDayPlusOneDay = ym.lengthOfMonth() + 1;
        LocalDate lastDateOfPreviousMonth;
            /*Создаем массив ArrayList, куда записываем в качестве элементов курс на текущую дату,
            и второй ArrayList, увеличенный на 1 день предыдущего месяца.*/

        /*1-й ArrayList*/ List<Double> ratesList = new ArrayList<>();
        /*2-й ArrayList*/ List<Double> ratesListPlusPreviousDay = new ArrayList<>();

// Задаём Map.
        /*1-й Map*/ Map<String, Double> mapRatesInDates = new HashMap<>(); // todo удалить
        /*2-й Map*/ Map<String, Double> mapRatesInDatesPlusPreviousDay = new HashMap<>(); // Пробуем добавить Map плюс посл. день предыдущего мес.
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

        System.out.println("ЭТО ЦИКЛ ПЕРВОГО ARRAY Курс на ...");
        //    loop 1 through the days
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
            c.setTime(sdf.parse(dtStr));
            String currentDate;
            currentDate = sdf.format(c.getTime());  // entering current Date

            // Приводим currentDate к формату LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(currentDate, formatter);
            /*System.out.println(localDate);
            System.out.println(formatter.format(localDate));*/


            // Меняем в адресе исходной страницы дату на текующую.
            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);

            String currentPage = downloadWebPage(urlWithCurrentDate);

            if (currentPage.contains("<Value>")) {
                String currentRatePage = currentPage.substring(startIndex, endIndex);
                // Задаём курс в виде переменной Double.
                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
                System.out.println(/*dt + "*/"Курс на " + currentDate + "    " + doubleCurrentRate);
                // Здесь: Как найти из мапы перепады курса: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
                mapRatesInDates.put(String.valueOf(localDate), doubleCurrentRate); // todo удалить
                ratesList.add(doubleCurrentRate);
                /*ratesListPlusPreviousDay.add(doubleCurrentRate);*/
            } else {
                String currentRatePage = "";
                System.out.println(/*dt + "*/"Курс на " + currentDate);
            }
        }

        System.out.println();  // Добавляем пустую строку, как разделитель

//        int day2 = 1;
//        int lastDay2 = ym.lengthOfMonth() + 1;
//        /*dt2 = ym.atDay(day2);*/
//        LocalDate dt2 = ym.atDay(day2).minusDays(1);
//        System.out.println(lastDay2);
//        System.out.println(dt2 + "\n");



        System.out.println("ЭТО ДВА ЦИКЛА ДЛЯ ВТОРОГО ARRAY И ВТОРОГО MAP в режиме теста. Курс на ...");
        //    loop 2 through the days
        for (int day = 1; day <= 1; day++) {
            // create the day
            LocalDate dt = ym.atDay(day).minusDays(1);

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

            // Приводим currentDate к формату LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(currentDate, formatter);
            /*System.out.println(localDate);
            System.out.println(formatter.format(localDate));*/


            // Меняем в адресе исходной страницы дату на текующую.
            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);

            String currentPage = downloadWebPage(urlWithCurrentDate);

            if (currentPage.contains("<Value>")) {
                String currentRatePage = currentPage.substring(startIndex, endIndex);
                // Задаём курс в виде переменной Double.
                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
                System.out.println(/*dt + "*/"Курс на " + currentDate + "    " + doubleCurrentRate);
                // Здесь: Как найти из мапы перепады курса: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
                mapRatesInDatesPlusPreviousDay.put(String.valueOf(localDate), doubleCurrentRate);
                /*ratesList.add(doubleCurrentRate);*/
                ratesListPlusPreviousDay.add(doubleCurrentRate);
            } else {
                String currentRatePage = "";
                System.out.println(/*dt + "*/"Курс на " + currentDate);
            }
        }

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
            c.setTime(sdf.parse(dtStr));
            String currentDate;
            currentDate = sdf.format(c.getTime());  // entering current Date

            // Приводим currentDate к формату LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(currentDate, formatter);
            /*System.out.println(localDate);
            System.out.println(formatter.format(localDate));*/


            // Меняем в адресе исходной страницы дату на текующую.
            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);

            String currentPage = downloadWebPage(urlWithCurrentDate);

            if (currentPage.contains("<Value>")) {
                String currentRatePage = currentPage.substring(startIndex, endIndex);
                // Задаём курс в виде переменной Double.
                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
                System.out.println(/*dt + "*/"Курс на " + currentDate + "    " + doubleCurrentRate);
                // Здесь: Как найти из мапы перепады курса: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
                mapRatesInDatesPlusPreviousDay.put(String.valueOf(localDate), doubleCurrentRate); // todo удалить
                /*ratesList.add(doubleCurrentRate);*/
                ratesListPlusPreviousDay.add(doubleCurrentRate);
            } else {
                String currentRatePage = "";
                System.out.println(/*dt + "*/"Курс на " + currentDate);
            }
        }











//        System.out.println("ЭТО ЦИКЛ ВТОРОГО ARRAY УВЕЛИЧЕННОГО Курс на ...");
//        //    loop through the days
//        for (day2 = 1; day2 <= (lastDay2); day2++) {
//            // create the day
//            dt2 = ym.atDay(day2);
//
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dtStr = dt2.format(f);
//            // set to midnight at JVM default timezone
//            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
//            int endIndex = originalPage.lastIndexOf("</Value>");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.setTime(sdf.parse(dtStr));
//            String currentDate;
//            currentDate = sdf.format(c.getTime());  // entering current Date
//            // Меняем в адресе исходной страницы дату на текующую.
//            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);
//
//            String currentPage = downloadWebPage(urlWithCurrentDate);
//
//            if (currentPage.contains("<Value>")) {
//                String currentRatePage = currentPage.substring(startIndex, endIndex);
//                // Задаём курс в виде переменной Double.
//                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
//                System.out.println(/*dt + "*/"Курс на " + currentDate + "    " + doubleCurrentRate);
//                // Здесь: Как найти из мапы перепады курса: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8
//                mapRatesInDates.put(currentDate, doubleCurrentRate); // todo удалить
//                ratesList.add(doubleCurrentRate);
//                ratesListPlusPreviousDay.add(doubleCurrentRate);
//            } else {
//                String currentRatePage = "";
//                System.out.println(/*dt + "*/"Курс на " + currentDate);
//            }
//        }


// Проводим вычисления в Map
// Распечатываем изначальный список Map (не отсортированный)
//        System.out.println(mapRatesInDates); // todo удалить
// Сортируем и распечатываем отсортированный список Map. Про сортировку здесь: https://rukovodstvo.net/posts/id_598/
        Map<String, Double> sortedMap = new TreeMap<>(mapRatesInDates); // - ИЗ ЭТОГО Мапа МАКСИМАЛЬНЫЕ ПЕРЕПАДЫ КУРСА БРАТЬ.
        Map<String, Double> sortedMap2 = new TreeMap<>(mapRatesInDatesPlusPreviousDay); // - ИЗ ЭТОГО Мапа МАКСИМАЛЬНЫЕ ПЕРЕПАДЫ КУРСА БРАТЬ.
//        System.out.println(sortedMap);
        System.out.println(); // Добавляем пустую строку, как разделитель
        System.out.println("Это увеличенный НЕотсортированный Map");
        System.out.println(mapRatesInDatesPlusPreviousDay);
        System.out.println("Это увеличенный отсортированный Map");
        System.out.println(sortedMap2);
//        sortedMap.entrySet().forEach(System.out::println);

//Это старый поиск перепадов курса. Его удалить.
//        //Далее ищем максимальные перепады курса.
//        double max = maxDifference(ratesList);
//        double min = minDifference(ratesList);
//        DecimalFormat df = new DecimalFormat("0.000");
//        df.setRoundingMode(RoundingMode.DOWN);
//        System.out.println("\nЗа указанный месяц максимальный прирост курса между двумя соседними датами: " + df.format(max) + ", это пришлось на дату: ");
//        System.out.println("За указанный месяц максимальное снижение курса между двумя соседними датами: " + df.format(min) + ", это пришлось на дату: ");


// Вот отсюда начинается соответствие дат
        // Calling method 2 to
        // print the map
//        print(sortedMap);

//        6 Пример с ru.stakoverflow.com (УДАЛСЯ!!!!!!!!!!!!!!!!!!)
//        с попыткой найти разницу соседних элементов
//        ```
        System.out.println(); // Добавляем пустую строку, как разделитель
        List<String> keys = new ArrayList<String>(sortedMap2.keySet());
        System.out.println("ПЕЧАТЬ ТРЕТЬЯ");
        /*ПЕЧАТЬ ТРЕТЬЯ  */      System.out.println(keys); // TODO Только для проверки вывел на печать ПЕЧАТЬ ТРЕТЬЯ
        Double maxDiffer = sortedMap2.get(keys.get(1)) - sortedMap2.get(keys.get(0));
        String dateOfMaxDiffer = keys.get(0); // TODO Здесь сделать формат вида 28/02/2023
        String dateOfMaxDifferFormatted = null;


//        String[] frs = (keys.get(0)).split("-");
//        String fr1 = frs[0];
//        String fr2 = frs[1];
//        String fr3 = frs[2];
//        String dateOfMaxDifferFormatted = (fr3 + "/" + fr2 + "/" + fr1);
//        System.out.println("\nПЕЧАТЬ ТРЕТЬЯ.ДВА");
///*ПЕЧАТЬ ТРЕТЬЯ.ДВА  */      System.out.println(dateOfMaxDifferFormatted + "\n"); // TODO Здесь сформатированный ключ для второй Мапы

//        String[] frs = dateOfMaxDiffer.split("-");
//        String fr1 = frs[0];
//        String fr2 = frs[1];
//        String fr3 = frs[2];
//        String dateOfMaxDifferFormatted = (fr3 + "/" + fr2 + "/" + fr1);
//        System.out.println("\nПЕЧАТЬ ТРЕТЬЯ.ДВА");
///*ПЕЧАТЬ ТРЕТЬЯ.ДВА  */      System.out.println(dateOfMaxDifferFormatted + "\n"); // TODO Здесь сформатированный ключ для второй Мапы

        Double minDiffer = sortedMap2.get(keys.get(1)) - sortedMap2.get(keys.get(0));
        String dateOfMinDiffer = keys.get(0);
        String dateOfMinDifferFormatted = null;

        System.out.println("ПЕЧАТЬ ЧЕТВЁРТАЯ");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Double value = sortedMap2.get(key);
            /*ЭТО ПЕЧАТЬ ЧЕТВЁРТАЯ*/ System.out.println(key + " / " + value);
        }
        System.out.println(); // Распечатали список, далее пустую строку добавляем

//        Дальше в следующем цикле ищем разницу между соседними элементами
//        Сначала ищем максимальный прирост

        System.out.println("ПЕЧАТЬ ПЯТАЯ");
        for (int i = 1; i < keys.size(); i++) {
            String key = keys.get(i);
            Double value = sortedMap2.get(key);
            String keyMinusOne = keys.get(i - 1);
            Double valueMinusOne = sortedMap2.get(keyMinusOne);

            Double Differ = (value - valueMinusOne); // = MIN_VALUE;
            DecimalFormat df2 = new DecimalFormat("0.000");
            df2.setRoundingMode(RoundingMode.DOWN);
            /*ЭТО ПЕЧАТЬ ПЯТАЯ*/            System.out.println(df2.format(Differ));
            if ((sortedMap2.get(keys.get(i)) - sortedMap2.get(keys.get(i - 1)) > maxDiffer)) {
                maxDiffer = (value - valueMinusOne);
                dateOfMaxDiffer = key;
                String[] frs = dateOfMaxDiffer.split("-");
                String fr1 = frs[0];
                String fr2 = frs[1];
                String fr3 = frs[2];
                dateOfMaxDifferFormatted = (fr3 + "/" + fr2 + "/" + fr1);


            }
            if ((sortedMap2.get(keys.get(i)) - sortedMap2.get(keys.get(i - 1)) < minDiffer)) {
                minDiffer = (value - valueMinusOne);
                dateOfMinDiffer = key;
                String[] frs = dateOfMinDiffer.split("-");
                String fr1 = frs[0];
                String fr2 = frs[1];
                String fr3 = frs[2];
                dateOfMinDifferFormatted = (fr3 + "/" + fr2 + "/" + fr1);

            }
        }

        // The printed result
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println(); // Добавляем пустую строку, как разделитель
        System.out.println("ПЕЧАТЬ ШЕСТАЯ");
        System.out.println("За указанный месяц максимальный прирост курса между двумя соседними датами: " + df.format(maxDiffer) + ", это пришлось на дату: " + dateOfMaxDiffer);
        System.out.println("За указанный месяц максимальное снижение курса между двумя соседними датами: " + df.format(minDiffer) + ", это пришлось на дату: " + dateOfMinDiffer);
//        ```
//Вот здесь заканчивается соответствие дат


        //СКАЧИВАЕМ СТРАНИЦЫ ИЗ WIKINEWS.
        //СОХРАНЯЕМ СТРАНИЦУ ИЗ ВИКИПЕДИИ MAX
        String dtStrMax = dateOfMaxDifferFormatted; //Объявляем дату, когда курс максимально вырос TODO Здесь поменять dateOfMaxDiffer на отформатиррованный
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
        String gFirst3 = items3[3];
        String g3 = gFirst3.replace("г.", "года");

        String dtStrForChangeMax = (dat3 + "_" + mon3 + "_" + yea3 + "_" + g3);

        String pageWikiOriginText = "https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года";
        String pageWikiOriginChangedTextMax = pageWikiOriginText.replaceAll("31_марта_2023_года", dtStrForChangeMax);
        String pageWikiOriginChanged1 = downloadWebPage(pageWikiOriginChangedTextMax);

        //СОХРАНЯЕМ СТРАНИЦУ ИЗ ВИКИПЕДИИ MIN
        String dtStrMin = dateOfMinDifferFormatted; //Объявляем дату, когда курс максимально упал
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
        String gSecond5 = items5[3];
        String g5 = gSecond5.replace("г.", "года");

        String dtStrForChangeMin = (dat5 + "_" + mon5 + "_" + yea5 + "_" + g5);

        String pageWikiOriginChangedTextMin = pageWikiOriginText.replaceAll("31_марта_2023_года", dtStrForChangeMin);
        String pageWikiOriginChanged2 = downloadWebPage(pageWikiOriginChangedTextMin);

        //Подытоживаем
        System.out.println("ПЕЧАТЬ СЕДЬМАЯ");
        System.out.println("\nСтраницы, которые будут сохраняться в файлах: ");
        /*ЭТО ПЕЧАТЬ СЕДЬМАЯ*/  System.out.println(pageWikiOriginChangedTextMax);
        /*ЭТО ПЕЧАТЬ СЕДЬМАЯ*/  System.out.println(pageWikiOriginChangedTextMin);

        System.out.println("ПЕЧАТЬ ВОСЬМАЯ");
        /*ЭТО ПЕЧАТЬ ВОСЬМАЯ*/  System.out.println("\n" + dtStrForChangeMax + " " + "- в этот день курс максимально вырос.");
        /*ЭТО ПЕЧАТЬ ВОСЬМАЯ*/  System.out.println(dtStrForChangeMin + " " + "- в этот день курс максимально упал.");

        // создаём новый буферизированный объект
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("1pageGrowthRate.html"));
        // добавляем название переменной со страницей, которую сохраняем
        writer1.write(pageWikiOriginChanged1);
        // закрываем writer
        writer1.close();

        BufferedWriter writer2 = new BufferedWriter(new FileWriter("2pageDeclineRate.html"));
        writer2.write(pageWikiOriginChanged2);
        writer2.close();

        System.out.println("\nСтраницы из Википедии сохранены");


    }

//    //Пишем классы для поиска максимальных перепадов курса.
//    //Сначала максимальную разницу находим.
//    public static double maxDifference(List<Double> ratesList) {
//        if (ratesList == null || ratesList.size() == 0) {
//            return Double.MIN_VALUE;
//        }
//        int len = ratesList.size();
//        double[] diff = new double[len - 1];
//        for (int i = 0; i < len - 1; i++) {
//            diff[i] = ratesList.get(i + 1) - ratesList.get(i);
//        }
//        return max(diff);
//    }
//
//    public static double max(double[] diff) {
//        if (diff == null || diff.length == 0) {
//            return Double.MIN_VALUE;
//        }
//        double max = diff[0];
//        for (int i = 0, len = diff.length; i < len; i++) {
//            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
//            if (max < diff[i]) {
//                max = diff[i];
//            }
//        }
//        return max;
//    }
//
//    //Теперь минимальную разницу находим.
//    public static double minDifference(List<Double> ratesList) {
//        if (ratesList == null || ratesList.size() == 0) {
//            return Double.MIN_VALUE;
//        }
//        int len = ratesList.size();
//        double[] diff = new double[len - 1];
//        for (int i = 0; i < len - 1; i++) {
//            diff[i] = ratesList.get(i + 1) - ratesList.get(i);
//        }
//        return min(diff);
//    }
//
//    public static double min(double[] diff) {
//        if (diff == null || diff.length == 0) {
//            return Double.MIN_VALUE;
//        }
//        double min = diff[0];
//        for (int i = 0, len = diff.length; i < len; i++) {
//            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
//            if (min > diff[i]) {
//                min = diff[i];
//            }
//        }
//        return min;
//    }

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
    // Method 2
    // To print the map
//    public static void print(Map<String, Double> sortedMap) {

//        System.out.print("Map sortedMap: ");


    // If map does not contain any value
//        if (sortedMap.isEmpty()) {

//            System.out.println("[]");
//        } else {
//            System.out.println(sortedMap);
//        }
//    }


}