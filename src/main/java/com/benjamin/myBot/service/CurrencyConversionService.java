package com.benjamin.myBot.service;

import com.benjamin.myBot.dto.RatesApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final RestClient restClient;

    private double getRate(String from, String to) {
        return restClient.get()
                .uri("https://api.frankfurter.dev/v2/rates?base="+from+"&quotes="+to)
                .retrieve()
                .body(RatesApiResponseDto[].class)[0]
                .getRate();
    }

    public double convert(double amount, String from, String to) {
        return amount * getRate(from, to);
    }
}
