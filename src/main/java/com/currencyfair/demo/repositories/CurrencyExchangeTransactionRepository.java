package com.currencyfair.demo.repositories;


import com.currencyfair.demo.model.CurrencyExchangeTransaction;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyExchangeTransactionRepository extends CrudRepository<CurrencyExchangeTransaction,Long> {

}
