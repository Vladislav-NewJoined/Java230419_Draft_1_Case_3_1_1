import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

//инфо здесь: https://translated.turbopages.org/proxy_u/en-ru.ru.864dbb2f-64483f5e-2bfc6fd5-74722d776562/https/stackoverflow.com/questions/17111526/writing-to-multiple-files-without-duplicating-code-java

public class Draft_28_1_Write_Multiple_Files_Origin {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        String pageWikiOriginChanged1 = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_18_марта_2023_года");
        String pageWikiOriginChanged2 = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_3_марта_2023_года");

        writeToFile(pageWikiOriginChanged1);
        writeToFile(pageWikiOriginChanged2);

        System.out.println("\nСтраницы, которые будут сохраняться в файлах: ");
        System.out.println("https://ru.wikinews.org/wiki/Лента_новостей_18_марта_2023_года");
        System.out.println("https://ru.wikinews.org/wiki/Лента_новостей_3_марта_2023_года");

        System.out.println("\nСтраница max сохранена!");
        System.out.println("Страница min сохранена!");
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
