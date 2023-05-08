//package com.csharpcoderr.java8.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// инфо здесь: https://csharpcoderr.com/4856/
public class Draft_34_String_To_LocalDate {
    public static void main(String[] args) {

        String date = "01/03/2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);
        System.out.println(formatter.format(localDate));

    }
}
