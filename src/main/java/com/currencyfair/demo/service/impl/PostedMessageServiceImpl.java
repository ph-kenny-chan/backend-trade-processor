package com.currencyfair.demo.service.impl;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import com.currencyfair.demo.repositories.CurrencyExchangeTransactionRepository;
import com.currencyfair.demo.service.PostedMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PostedMessageServiceImpl implements PostedMessageService {

    @Autowired
    CurrencyExchangeTransactionRepository currencyExchangeTransactionRepository;
    private List<CurrencyExchangeTransaction> postedMessage = new ArrayList<>();

    public void addPostedMessage(CurrencyExchangeTransaction currencyExchangeTransaction){
        CurrencyExchangeTransaction savedEntity = currencyExchangeTransactionRepository.save(currencyExchangeTransaction);
        postedMessage.add(savedEntity);
    }

    public List<CurrencyExchangeTransaction> getPostedMessage(){
        return postedMessage;
    }

    @Override
    public CurrencyExchangeTransaction getLatestTransaction() {
        if (postedMessage!=null && !postedMessage.isEmpty()){
            return postedMessage.get(postedMessage.size()-1);
        }
        return null;
    }

    @Override
    public Map<String, Object> getSummary() {
        Map<String, Object> summaryMap = new HashMap<>();
        if (postedMessage!=null && !postedMessage.isEmpty()) {
            Map<String, Long> originMap = postedMessage.stream().collect(
                    groupingBy(CurrencyExchangeTransaction::getOriginatingCountry, counting()));
            summaryMap.put("totalTransaction", postedMessage.size());
            summaryMap.put("originDistribution", originMap.keySet().toString());
        }
        return summaryMap;
    }
}
