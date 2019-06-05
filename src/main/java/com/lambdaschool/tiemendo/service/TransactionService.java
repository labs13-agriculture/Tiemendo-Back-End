package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();

    Transaction findTransactionById(long id);

    void delete(long id);

    Transaction save(Transaction transaction);
}
