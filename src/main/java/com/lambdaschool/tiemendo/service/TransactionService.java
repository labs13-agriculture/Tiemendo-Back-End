package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Transaction;
import com.lambdaschool.tiemendo.model.TransactionItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll(Pageable pageable);

    Transaction findTransactionById(long id);

    void delete(long id);

    Client save(Transaction transaction,long id);

    Transaction update(Transaction transaction, long id);
}
