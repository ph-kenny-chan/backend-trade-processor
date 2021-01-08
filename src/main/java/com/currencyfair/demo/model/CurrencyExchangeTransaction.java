package com.currencyfair.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor

public class CurrencyExchangeTransaction {
    private String userId;
    private String currencyFrom;
    private String currencyTo;
    private double amountSell;
    private double amountBuy;
    private float rate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date timePlaced;
    private String originatingCountry;
}
