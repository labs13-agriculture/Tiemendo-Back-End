package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>{

    Page<Transaction> findAllByClient(Pageable pageable, Client client);
}
