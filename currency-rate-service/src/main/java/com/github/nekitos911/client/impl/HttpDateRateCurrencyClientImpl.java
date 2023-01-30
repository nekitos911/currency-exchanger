package com.github.nekitos911.client.impl;

import com.github.nekitos911.client.HttpDateRateCurrencyClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Log4j2
public class HttpDateRateCurrencyClientImpl implements HttpDateRateCurrencyClient {
    private static final String DATE_URL_PARAM = "date_req";
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Value("${http.client.cb.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public HttpDateRateCurrencyClientImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String requestByDate(LocalDate date) {
        log.info("Sending request to cb");
        return restTemplate.getForObject(buildRequest(date), String.class);
    }

    private String buildRequest(LocalDate date) {
        return UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .queryParam(DATE_URL_PARAM, DATE_TIME_FORMATTER.format(date))
                .encode()
                .toUriString();
    }
}
