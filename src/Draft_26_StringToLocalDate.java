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
        System.out.println(dtStr2);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        LocalDate ld = LocalDate.parse(dtStr2, formatter);
        System.out.println(ld);

//        String dtStr2 = dtStr.replaceAll("/", "-");
//        System.out.println(dtStr2);
//        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
//        LocalDate dtLocal;
//        dtLocal = LocalDate.parse(dtStr2, formatter);


//        System.out.println(dtLocal);

//        Date dtDate = new SimpleDateFormat("dd/MM/yyyy").parse(dtStr);
//        System.out.println(dtStr + "\t" + dtDate);
    }
}