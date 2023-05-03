import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Draft_33_ArrayList_С_Предыдущим_Днем {
    public static void main(String[] args) {

//        Пример 1
//        https://translated.turbopages.org/proxy_u/en-ru.ru.7652851d-64515d5d-5e1b26ad-74722d776562/https/stackoverflow.com/questions/2458049/how-to-get-previous-date-in-java

        Calendar cal  = Calendar.getInstance();
        //subtracting a day
        cal.add(Calendar.DATE, -1);

        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s3 = new SimpleDateFormat("dd/MM/yyyy");
        String result = s.format(new Date(cal.getTimeInMillis()));
        String result2 = s2.format(new Date(cal.getTimeInMillis()));
        String result3 = s3.format(new Date(cal.getTimeInMillis()));
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);

        // Как получить все даты в указанном диапазоне  https://devmark.ru/article/all-dates-between-two-dates#header1
        // Как получить предыдущую дату  https://translated.turbopages.org/proxy_u/en-ru.ru.7652851d-64515d5d-5e1b26ad-74722d776562/https/stackoverflow.com/questions/2458049/how-to-get-previous-date-in-java
// Далее писать так: Во второй цикл добавляем: если day = 1, то get previous day и добавить его во второй ArrayList, отсортировать его и по нему искать разницу.
    }
}
