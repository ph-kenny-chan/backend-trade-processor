package com.currencyfair.demo.service.impl;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostedMessage {

    private List<CurrencyExchangeTransaction> postedMessage = new ArrayList<>();
    private int txId = 0;

    public int getTxId(){
        int returnId = this.txId;
        this.txId++;
        return returnId;
    }

    public void addPostedMessage(CurrencyExchangeTransaction currencyExchangeTransaction){
        postedMessage.add(currencyExchangeTransaction);
    }

    public List<CurrencyExchangeTransaction> getPostedMessage(){
        return postedMessage;
    }
}
