package com.github.nekitos911.service;

import com.github.nekitos911.dto.CurrencyResponse;

public interface CbrService {
    CurrencyResponse requestByCurrencyCode(String code);
}
