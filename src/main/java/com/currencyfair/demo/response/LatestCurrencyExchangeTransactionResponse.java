package com.currencyfair.demo.response;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)

public class LatestCurrencyExchangeTransactionResponse {
    @NotNull
    private CurrencyExchangeTransaction data;
}
