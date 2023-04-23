import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static java.time.LocalDate.*;

// Кейс «Анализатор курса валют».
// 3. Очень сложное:
//- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал.
// Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
//Пример: Статья из Википедии: https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8C_2021_%D0%B3%D0%BE%D0%B4%D0%B0
// Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
// Задание здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
//Здесь Java. Поиск второго по минимальности элемента в массиве. https://youtu.be/03iETRvZrFg
//Видео по картинке NASA в занятиях здесь: Модуль 1. Урок 9. видео мин 23.32
//Урок с видео здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
//Модуль1. Урок2. Задание18. Статья Дональд Кнут.
//Модуль1. Урок2. Задание19. Напишите программу, которая сохраняет в файл случайную статью из Википедии. (Ссылку на случайную статью можно найти на вики в меню слева: https://ru.wikipedia.org/wiki/%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:%D0%A1%D0%BB%D1%83%D1%87%D0%B0%D0%B9%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0 )
public class Draft_19_5_ArticleFromWikipedia_Myself_Wikinews {

    public static void main(String[] args) throws IOException {
        String pageDiff = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года");
        writeToFile(pageDiff);
        LocalDate dateDiff = LocalDate.parse("2023-03-31");
//Форматирование даты здесь: https://www.youtube.com/watch?v=Wh2zd2oZOmo
        System.out.println(dateDiff);
        System.out.println("Article saved!");
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
        BufferedWriter writer = new BufferedWriter(new FileWriter("pageDiff.html"));
//        BufferedWriter writer2 = new BufferedWriter(new FileWriter("C:\\Users\\PC\\IdeaProjectsDrafts\\Java230419_Draft_1_Case_3_1_1\\src\\page.html"));
        writer.write(str);
//        writer2.write(str);
        writer.close();
//        writer2.close();
    }
}
