package com.lambdaschool.tiemendo.repository;


import com.lambdaschool.tiemendo.model.TransactionItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionItemRepository extends PagingAndSortingRepository<TransactionItem, Long>
{
    @Override
    void deleteAll(Iterable<? extends TransactionItem> iterable);
}
