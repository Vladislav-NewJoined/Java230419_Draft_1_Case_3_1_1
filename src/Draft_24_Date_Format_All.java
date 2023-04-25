//package com.concretepage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

// инфо здесь: https://www.concretepage.com/java/java-8/java-datetimeformatter
public class Draft_24_Date_Format_All {
    public static void main(String[] args) {

        String dtStr = "31/03/2023";
        LocalDate localDate = LocalDate.now();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate1 = formatter1.format(localDate);
        System.out.println(formattedDate1); //Dec 17, 2018

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.CANADA);
        String formattedDate2 = formatter2.format(localDate);
        System.out.println(formattedDate2); //Dec. 17, 2018

        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String formattedDate3 = formatter3.format(localDate);
        System.out.println(formattedDate3); //Monday, December 17, 2018

        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter formatter4 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String formattedDate4 = formatter4.format(localDateTime);
        System.out.println(formattedDate4); //Dec 17, 2018, 9:14:39 PM

        DateTimeFormatter formatter5 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);
        String formattedDate5 = formatter5.format(localDateTime);
        System.out.println(formattedDate5); //December 17, 2018, 9:14 PM

        LocalTime localTime = LocalTime.now();

        DateTimeFormatter formatter6 = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        String formattedDate6 = formatter6.format(localTime);
        System.out.println(formattedDate6); //9:14:39 PM


    }
}
