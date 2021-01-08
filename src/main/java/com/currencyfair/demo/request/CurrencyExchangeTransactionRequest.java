package com.currencyfair.demo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CurrencyExchangeTransactionRequest {

    @NotNull
    private String userId;
    @NotNull
    private String currencyFrom;
    @NotNull
    private String currencyTo;
    @NotNull
    private double amountSell;
    @NotNull
    private double amountBuy;
    @NotNull
    private float rate;
    @NotNull
    private String timePlaced;
    @NotNull
    private String originatingCountry;

}
