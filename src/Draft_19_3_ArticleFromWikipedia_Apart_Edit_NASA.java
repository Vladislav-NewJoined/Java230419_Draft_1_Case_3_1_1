import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;

// Кейс «Анализатор курса валют».
// 3. Очень сложное:
//- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал.
// Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
// Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
// Задание здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
//Здесь Java. Поиск второго по минимальности элемента в массиве. https://youtu.be/03iETRvZrFg
//Видео по картинке NASA в занятиях здесь: Модуль 1. Урок 9. видео мин 23.32
//Урок с видео здесь: https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
//Статья из Википедии здесь: https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8C_2021_%D0%B3%D0%BE%D0%B4%D0%B0
public class Draft_19_3_ArticleFromWikipedia_Apart_Edit_NASA {

    public static void main(String[] args) throws IOException {

        String page = downloadWebPage("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        int urlBegin = page.lastIndexOf("url");
        int urlEnd = page.lastIndexOf("}");
        String url = page.substring(urlBegin + 6, urlEnd - 1);

        String image = downloadWebPage(url);
        try(InputStream in = new URL(url).openStream()){
            Files.copy(in, Paths.get("new.jpg"));
        }
        System.out.println("Picture saved!");

        int explanationBegin = page.lastIndexOf("explanation");
        int explanationEnd = page.lastIndexOf("hdurl");
        String explanation = page.substring(explanationBegin + 13, explanationEnd - 2);
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
