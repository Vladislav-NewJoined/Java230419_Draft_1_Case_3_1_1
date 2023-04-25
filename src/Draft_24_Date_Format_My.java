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

        String dtStr = "04/03/2023";
        String[] items = dtStr.split("/");
        String dat = items[0];
        String mon = items[1];
        String yea = items[2];
        String dtStr2 = (yea + "-" + mon + "-" + dat);
//        System.out.println(dtStr2); //2023-03-31

        LocalDate localDate2 = LocalDate.parse(dtStr2);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String formattedDate1 = formatter.format(localDate2);
//        System.out.println(formattedDate1); //31 марта 2023 г.

        String[] items2 = formattedDate1.split(" ");
        String dat2 = items2[0];
        String mon2 = items2[1];
        String yea2 = items2[2];
        String g = items2[3];
        String g2 = g.replace("г.", "года");

//        System.out.println(dat2 + "\t" + mon2 + "\t" + yea2 + "\t" + g2);
        String dtStrForChange = (dat2 + "_" + mon2 + "_" + yea2 + "_" + g2);
        System.out.println(dtStrForChange);
    }
}
