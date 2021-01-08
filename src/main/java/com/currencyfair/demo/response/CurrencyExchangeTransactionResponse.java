package com.currencyfair.demo.response;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CurrencyExchangeTransactionResponse {

    @NotNull
    private List<CurrencyExchangeTransaction> data;

}
