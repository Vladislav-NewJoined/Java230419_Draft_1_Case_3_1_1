import java.io.*;
import java.net.URL;
import java.net.URLConnection;

//инфо здесь: https://translated.turbopages.org/proxy_u/en-ru.ru.864dbb2f-64483f5e-2bfc6fd5-74722d776562/https/stackoverflow.com/questions/17111526/writing-to-multiple-files-without-duplicating-code-java
//и здесь: https://www.golinuxcloud.com/java-create-write-to-file-examples/
public class Draft_28_2_Write_Multiple_Files {
    // java main method which throws exception if accurs
    public static void main(String args[])throws IOException {

        String pageWikiOriginChanged1 = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_18_марта_2023_года");
        String pageWikiOriginChanged2 = downloadWebPage("https://ru.wikinews.org/wiki/Лента_новостей_3_марта_2023_года");

        System.out.println("\nСтраницы, которые будут сохраняться в файлах: ");
        System.out.println("https://ru.wikinews.org/wiki/Лента_новостей_18_марта_2023_года");
        System.out.println("https://ru.wikinews.org/wiki/Лента_новостей_3_марта_2023_года");

        // создаём новый буферизированный объект
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("pageGrowthRate.html"));
        // добавляем название переменной со страницей, которую сохраняем
        writer1.write(pageWikiOriginChanged1);
        // закрываем writer
        writer1.close();

        BufferedWriter writer2 = new BufferedWriter(new FileWriter("pageDeclineRate.html"));
        writer2.write(pageWikiOriginChanged2);
        writer2.close();

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
