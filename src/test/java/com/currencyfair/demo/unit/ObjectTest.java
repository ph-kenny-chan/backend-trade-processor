package com.currencyfair.demo.unit;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import com.currencyfair.demo.request.CurrencyExchangeTransactionRequest;
import com.currencyfair.demo.service.PostedMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObjectTest {
    @Autowired
    PostedMessageService postedMessageService;

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

    @Test
    void postedMessageServiceUnitTest(){
        postedMessageService.addPostedMessage(Mockito.any(CurrencyExchangeTransaction.class));
        List<CurrencyExchangeTransaction> list = postedMessageService.getPostedMessage();
        Assert.notEmpty(list,"The list is empty");

    }
}
