package com.github.nekitos911.service.impl;

import com.github.nekitos911.dto.CurrencyResponse;
import com.github.nekitos911.service.CbrService;
import com.github.nekitos911.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CbrServiceImpl implements CbrService {
    private final CurrencyService currencyService;

    @Override
    public CurrencyResponse requestByCurrencyCode(String code) {
        return new CurrencyResponse(code, currencyService.getCurrencyByDate(LocalDate.now()).get(code));
    }
}
