import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

// инфо здесь: https://www.javatpoint.com/java-string-to-date
public class Draft_26_StringToLocalDate {
    public static void main(String[] args) throws Exception, ParseException {

        String dtStr = "31/03/2023";
        String[] items = dtStr.split("/");
        String dat = items[0];
        String mon = items[1];
        String yea = items[2];
        String dtStr2 = (yea + "-" + mon + "-" + dat);
        System.out.println(dtStr2); //2023-03-31

    }
}