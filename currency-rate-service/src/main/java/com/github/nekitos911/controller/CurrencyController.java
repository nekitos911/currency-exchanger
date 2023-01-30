package com.github.nekitos911.controller;

import com.github.nekitos911.dto.CurrencyResponse;
import com.github.nekitos911.service.CbrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {
    private final CbrService cbrService;

    @GetMapping("/rate/{code}")
    public ResponseEntity<CurrencyResponse> getCurrency(@PathVariable String code) {
        return ResponseEntity.ok(cbrService.requestByCurrencyCode(code));
    }
}
