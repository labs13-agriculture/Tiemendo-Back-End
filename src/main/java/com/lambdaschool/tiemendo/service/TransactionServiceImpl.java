package com.lambdaschool.tiemendo.service;



import com.lambdaschool.tiemendo.model.Transaction;
import com.lambdaschool.tiemendo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "transactionService")
public class TransactionServiceImpl implements TransactionService
{
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll()
    {
        List<Transaction> list = new ArrayList<>();
        transactionRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Transaction findTransactionById(long id)
    {
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public void delete(long id)
    {
        transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        transactionRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Transaction save(Transaction transaction)
    {
        return transactionRepository.save(transaction);
    }
}

