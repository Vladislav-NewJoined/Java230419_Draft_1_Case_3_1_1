//package com.concretepage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

// инфо здесь: https://www.concretepage.com/java/java-8/java-datetimeformatter
public class Draft_24_Date_Format_My {
    public static void main(String[] args) throws IOException, ParseException {

        String dtStr = "2023-03-31";
        LocalDate localDate2 = LocalDate.parse(dtStr);

        DateTimeFormatter formatter5 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String formattedDate5 = formatter5.format(localDate2);
        System.out.println(formattedDate5); //25 апреля 2023 г.

    }
}
