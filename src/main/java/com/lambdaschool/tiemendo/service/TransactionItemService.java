package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.TransactionItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionItemService {

    List<TransactionItem> findAll(Pageable pageable);

    TransactionItem findTransactionItemById(long id);

    void delete(long id);

    TransactionItem save(TransactionItem transactionItem);

    TransactionItem update(TransactionItem transactionItem, long id);
}

