package com.example.codegymfoods.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatterUtils {
    public static String formatNumber(double number) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,###");

        return df.format(number);
    }
}
