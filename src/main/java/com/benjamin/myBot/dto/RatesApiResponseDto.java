package com.benjamin.myBot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RatesApiResponseDto {
    private String date;
    private String base;
    private String quote;
    private double rate;
}
