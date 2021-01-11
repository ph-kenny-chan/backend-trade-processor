package com.currencyfair.demo.controller;

import com.currencyfair.demo.request.CurrencyExchangeTransactionRequest;
import com.currencyfair.demo.response.CurrencyExchangeTransactionResponse;
import com.currencyfair.demo.response.LatestCurrencyExchangeTransactionResponse;
import com.currencyfair.demo.response.SummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    void sendCurrencyExchangeTransactionSuccess() throws URISyntaxException {
        ResponseEntity<String> result = this.sendPostCurExTxRequest(this.normalRequest());
        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Posted Success", result.getBody());
    }

    @Test
    void sendCurrencyExchangeTransactionFail() throws URISyntaxException {
        ResponseEntity<String> result = this.sendPostCurExTxRequest(this.dateFormatRequest());
        //Verify request failed
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Wrong Date Format", result.getBody());
    }

    @Test
    void getTransaction() throws URISyntaxException {
        this.sendPostCurExTxRequest(this.normalRequest());
        ResponseEntity<CurrencyExchangeTransactionResponse> result = this.sendGetTxRequest();
        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody().getData());
        assertEquals("124542", result.getBody().getData().get(0).getUserId());
    }

    @Test
    void getLatestTransaction() throws URISyntaxException {
        this.sendPostCurExTxRequest(this.normalRequest());
        ResponseEntity<LatestCurrencyExchangeTransactionResponse> result = this.sendGetLatestTxRequest();
        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody().getData());
        assertEquals("124542", result.getBody().getData().getUserId());
    }

    @Test
    void getNoLatestTransaction() throws URISyntaxException {
        ResponseEntity<LatestCurrencyExchangeTransactionResponse> result = this.sendGetLatestTxRequest();
        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        Assertions.assertNull(result.getBody().getData());
    }

    @Test
    void getSummary() throws URISyntaxException {
        this.sendPostCurExTxRequest(this.normalRequest());
        ResponseEntity<SummaryResponse> result = this.sendGetSummaryRequest();
        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody().getData());
        assertEquals(1, result.getBody().getData().get("totalTransaction"));
    }

    private ResponseEntity<String> sendPostCurExTxRequest(CurrencyExchangeTransactionRequest requestBody) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final String baseUrl = "http://localhost:"+randomServerPort+"/v1/demo/postCurExTx";
        URI uri = new URI(baseUrl);
        HttpEntity<CurrencyExchangeTransactionRequest> request = new HttpEntity<>(requestBody, headers);
        return this.testRestTemplate.postForEntity(uri, request, String.class);
    }

    private CurrencyExchangeTransactionRequest normalRequest(){
        return new CurrencyExchangeTransactionRequest("124542","HKD","GBP", 988.14,
                10.12, 10000, "03-Jan-2021 10:27:44", "HK"
                );
    }

    private CurrencyExchangeTransactionRequest dateFormatRequest(){
        return new CurrencyExchangeTransactionRequest("124541","HKD","GBP", 988.14,
                10.12, 10000, "93-Jan-2021 10:27:44", "HK"
        );
    }

    private ResponseEntity<CurrencyExchangeTransactionResponse> sendGetTxRequest() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final String baseUrl = "http://localhost:"+randomServerPort+"/v1/demo/getTx";
        URI uri = new URI(baseUrl);
        HttpEntity request = new HttpEntity(null,headers);
        return this.testRestTemplate.getForEntity(uri,CurrencyExchangeTransactionResponse.class);
    }

    private ResponseEntity<LatestCurrencyExchangeTransactionResponse> sendGetLatestTxRequest() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final String baseUrl = "http://localhost:"+randomServerPort+"/v1/demo/getLatestTx";
        URI uri = new URI(baseUrl);
        HttpEntity request = new HttpEntity(null,headers);
        return this.testRestTemplate.getForEntity(uri,LatestCurrencyExchangeTransactionResponse.class);
    }

    private ResponseEntity<SummaryResponse> sendGetSummaryRequest() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final String baseUrl = "http://localhost:"+randomServerPort+"/v1/demo/getSummary";
        URI uri = new URI(baseUrl);
        HttpEntity request = new HttpEntity(null,headers);
        return this.testRestTemplate.getForEntity(uri,SummaryResponse.class);
    }
}