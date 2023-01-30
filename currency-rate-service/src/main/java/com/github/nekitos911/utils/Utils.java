package com.github.nekitos911.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@UtilityClass
public class Utils {
    public BigDecimal parseBigDecimalWithLocale(String data) {
        try {
            double parse = NumberFormat.getNumberInstance(Locale.getDefault()).parse(data).doubleValue();
            return BigDecimal.valueOf(parse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
