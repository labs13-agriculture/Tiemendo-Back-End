package com.lambdaschool.tiemendo.service;



import com.lambdaschool.tiemendo.model.TransactionItem;

import com.lambdaschool.tiemendo.repository.TransactionItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "transactionItemService")
public class TransactionItemServiceImpl implements TransactionItemService
{
    @Autowired
    TransactionItemRepository transactionItemRepository;

    @Override
    public List<TransactionItem> findAll()
    {
        List<TransactionItem> list = new ArrayList<>();
        transactionItemRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public TransactionItem findTransactionItemById(long id)
    {
        return transactionItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public void delete(long id)
    {
        transactionItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        transactionItemRepository.deleteById(id);
    }


    @Transactional
    @Override
    public TransactionItem save(TransactionItem transaction)
    {
        return transactionItemRepository.save(transaction);
    }
}
