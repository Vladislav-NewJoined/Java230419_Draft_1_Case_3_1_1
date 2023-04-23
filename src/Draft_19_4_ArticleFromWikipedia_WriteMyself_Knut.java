import java.io.*;
import java.net.URL;
import java.net.URLConnection;

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
//Модуль1. Урок2. Задание18. Статья Дональд Кнут.
//Модуль1. Урок2. Задание19. Напишите программу, которая сохраняет в файл случайную статью из Википедии. (Ссылку на случайную статью можно найти на вики в меню слева: https://ru.wikipedia.org/wiki/%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:%D0%A1%D0%BB%D1%83%D1%87%D0%B0%D0%B9%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0 )
public class Draft_19_4_ArticleFromWikipedia_WriteMyself_Knut {

    public static void main(String[] args) throws IOException {
        String page = downloadWebPage("https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8C_2021_%D0%B3%D0%BE%D0%B4%D0%B0");

        writeToFile(page);
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
        BufferedWriter writer = new BufferedWriter(new FileWriter("new.html"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("new.html"));
        writer.write(str);
        writer2.write(str);
        writer.close();
    }
}
