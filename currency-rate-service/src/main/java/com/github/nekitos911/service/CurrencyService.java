package com.github.nekitos911.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface CurrencyService {
    Map<String, BigDecimal> getCurrencyByDate(LocalDate date);
}
