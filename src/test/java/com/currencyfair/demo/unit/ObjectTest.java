package com.currencyfair.demo.unit;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import com.currencyfair.demo.request.CurrencyExchangeTransactionRequest;
import com.currencyfair.demo.service.impl.PostedMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@Slf4j
public class ObjectTest {
    @Autowired
    PostedMessage postMessage;

    @Test
    void createCurrencyExchangeTransactionRequest() {
        CurrencyExchangeTransactionRequest req = new CurrencyExchangeTransactionRequest();
        Assert.notNull(req,"CurrencyExchangeTransactionRequest is null");
    }

    @Test
    void createCurrencyExchangeTransaction() {
        CurrencyExchangeTransaction currencyExchangeTransaction = new CurrencyExchangeTransaction();
        Assert.notNull(currencyExchangeTransaction,"CurrencyExchangeTransaction is null");
    }
}
