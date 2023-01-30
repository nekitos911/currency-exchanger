package com.github.nekitos911.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nekitos911.CurrencyRateServiceApplication;
import com.github.nekitos911.client.HttpDateRateCurrencyClient;
import com.github.nekitos911.dto.CurrencyResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {CurrencyRateServiceApplication.class})
public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private HttpDateRateCurrencyClient httpDateRateCurrencyClient;

    @Test
    void testGetCurrency() throws Exception {
        var cur = "USD";
        var result = getData(cur);

        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(cur);
        assertThat(result.getAmount()).isNotNull().isNotZero().isNotNegative();
    }

    @Test
    void testCache() throws Exception {
        var res1 = getData("USD");
        var res2 = getData("EUR");

        Mockito.verify(httpDateRateCurrencyClient, Mockito.times(1)).requestByDate(LocalDate.now());
    }

    private CurrencyResponse getData(String cur) throws Exception {
        var result = mockMvc.perform(get("/api/v1/currency/rate/" + cur)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), CurrencyResponse.class);
    }
}
