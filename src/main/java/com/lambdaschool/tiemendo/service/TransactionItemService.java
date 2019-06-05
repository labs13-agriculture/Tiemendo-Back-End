package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.TransactionItem;

import java.util.List;

public interface TransactionItemService {

    List<TransactionItem> findAll();

    TransactionItem findTransactionItemById(long id);

    void delete(long id);

    TransactionItem save(TransactionItem transaction);
}

