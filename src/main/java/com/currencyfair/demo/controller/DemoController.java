package com.currencyfair.demo.controller;

import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import com.currencyfair.demo.request.CurrencyExchangeTransactionRequest;
import com.currencyfair.demo.response.CurrencyExchangeTransactionResponse;
import com.currencyfair.demo.service.impl.PostedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
@RequestMapping("/v1/demo")
public class DemoController {

    @Autowired
    PostedMessage message;

    @GetMapping(value="/healthCheck")
    public String healthCheck(){
        return "Success";
    }

    @PostMapping(value="/postCurExTx", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendCurrencyExchangeTransaction(
            @RequestBody @Valid CurrencyExchangeTransactionRequest request) {
        CurrencyExchangeTransaction transaction;
        try {
            transaction = translate(request);
        }catch (ParseException pe){
            return new ResponseEntity<>("Wrong Date Format", HttpStatus.BAD_REQUEST);
        }
        message.addPostedMessage(transaction);
        return new ResponseEntity<>("Posted Success", HttpStatus.OK);
    }

    @GetMapping(value="/getTx", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyExchangeTransactionResponse> getTransaction() {
        CurrencyExchangeTransactionResponse response = new CurrencyExchangeTransactionResponse();
        response.setData(message.getPostedMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private CurrencyExchangeTransaction translate(CurrencyExchangeTransactionRequest request) throws ParseException {
        DateFormatter df = new DateFormatter("dd-MMM-yy HH:mm:ss");

        return  new CurrencyExchangeTransaction(message.getTxId(), request.getUserId(), request.getCurrencyFrom(), request.getCurrencyTo(),
                request.getAmountSell(),request.getAmountBuy(),request.getRate(),df.parse(request.getTimePlaced(),
                Locale.US),request.getOriginatingCountry());
    }
}


