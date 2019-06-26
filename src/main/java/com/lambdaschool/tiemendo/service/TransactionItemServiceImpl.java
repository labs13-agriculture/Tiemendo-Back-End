package com.lambdaschool.tiemendo.service;



import com.lambdaschool.tiemendo.model.TransactionItem;

import com.lambdaschool.tiemendo.repository.TransactionItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "transactionItemService")
public class TransactionItemServiceImpl implements TransactionItemService
{

    // Do we ever use these?
    @Autowired
    TransactionItemRepository transactionItemRepository;

    @Override
    public List<TransactionItem> findAll(Pageable pageable)
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


    @Override
    @Transactional
    public TransactionItem update(TransactionItem transactionItem, long id) {
        TransactionItem currentTransactionItem = transactionItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (transactionItem.getQuantity() != null)
        {
            currentTransactionItem.setQuantity(transactionItem.getQuantity());
        }

        if (transactionItem.getUnitPrice() != null)
        {
            currentTransactionItem.setUnitPrice(transactionItem.getUnitPrice());
        }

        if (transactionItem.getItem() != null)
        {
            currentTransactionItem.setItem(transactionItem.getItem());
        }



        return transactionItemRepository.save(currentTransactionItem);

    }

    @Transactional
    @Override
    public TransactionItem save(TransactionItem transaction)
    {
        return transactionItemRepository.save(transaction);
    }
}
