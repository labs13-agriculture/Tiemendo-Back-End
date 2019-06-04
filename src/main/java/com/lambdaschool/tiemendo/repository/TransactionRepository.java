package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>{
}
