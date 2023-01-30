package com.github.nekitos911.service.impl;

import com.github.nekitos911.client.HttpDateRateCurrencyClient;
import com.github.nekitos911.service.CurrencyService;
import com.github.nekitos911.utils.Utils;
import com.github.nekitos911.utils.XmlMarshaller;
import generated.ValCurs;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final HttpDateRateCurrencyClient httpDateRateCurrencyClient;

    @Override
    @Cacheable("currencyByDate")
    public Map<String, BigDecimal> getCurrencyByDate(LocalDate date) {
        var xml = httpDateRateCurrencyClient.requestByDate(date);
        var valCur = XmlMarshaller.unmarshal(xml, ValCurs.class);
        return valCur.getValute().stream().collect(Collectors.toMap(ValCurs.Valute::getCharCode,
                data -> Utils.parseBigDecimalWithLocale(data.getValue())));
    }
}
