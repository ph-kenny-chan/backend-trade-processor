package com.currencyfair.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyExchangeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long txId;
    private String userId;
    private String currencyFrom;
    private String currencyTo;
    private double amountSell;
    private double amountBuy;
    private float rate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date timePlaced;
    private String originatingCountry;

    public CurrencyExchangeTransaction(String userId, String currencyFrom, String currencyTo, double amountSell, double amountBuy, float rate, Date timePlaced, String originatingCountry) {
        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountSell = amountSell;
        this.amountBuy = amountBuy;
        this.rate = rate;
        this.timePlaced = timePlaced;
        this.originatingCountry = originatingCountry;
    }
}
