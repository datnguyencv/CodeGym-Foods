package com.example.codegymfoods.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtils {
    public static String formatDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
}