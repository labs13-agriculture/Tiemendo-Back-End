package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TransactionService {

    Page<Transaction> findAll(Pageable pageable);
    Page<Transaction> findAllByClientId(Pageable pageable, long id);

    Transaction findTransactionById(long id);

    void delete(long id);

    Client save(Transaction transaction,long id);
    
    Page<Transaction> update(Pageable pageable, Transaction transaction, long id);
}
