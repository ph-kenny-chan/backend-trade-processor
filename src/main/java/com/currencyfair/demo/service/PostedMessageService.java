package com.currencyfair.demo.service;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;

import java.util.List;
import java.util.Map;

public interface PostedMessageService {

    void addPostedMessage(CurrencyExchangeTransaction currencyExchangeTransaction);

    List<CurrencyExchangeTransaction> getPostedMessage();

    CurrencyExchangeTransaction getLatestTransaction();

    Map<String,Object> getSummary();
}
