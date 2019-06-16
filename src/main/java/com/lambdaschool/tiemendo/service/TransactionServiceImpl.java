package com.lambdaschool.tiemendo.service;



import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.ItemType;
import com.lambdaschool.tiemendo.model.Transaction;

import com.lambdaschool.tiemendo.model.TransactionItem;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import com.lambdaschool.tiemendo.repository.ItemTypeRepository;
import com.lambdaschool.tiemendo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ItemTypeRepository itemRepo;


    @Override
    public ArrayList<Transaction> findAll(Pageable pageable)
    {
        ArrayList<Transaction> list = new ArrayList<>();
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
    public ArrayList<Transaction> save(Transaction transaction,long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        transaction.setClient(client);

        // Im thinking that by doing this we wont create any new Item types unnecessarily
        for(TransactionItem ti: transaction.getInputs()) {
            ItemType item = itemRepo.findByNameIgnoreCase(ti.getItem().getName());
            ti.setItem(item);
        }

        transactionRepository.save(transaction);

        Pageable p = PageRequest.of(0, 25, Sort.Direction.ASC, "date");
        return findAll(p);

    }


    @Override
    @Transactional
    public Transaction update(Transaction transaction, long id) {
        Transaction currentTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (transaction.getInputs() != null)
        {
            //this seemed to be a necessary work around. Changing the result of getInput to ArrayList in Transaction threw error about mapping to a non collection
            //
            ArrayList necessaryArrayList = new ArrayList();
            List<TransactionItem> originalInputs = transaction.getInputs();

            for(TransactionItem i: originalInputs) {
                necessaryArrayList.add(i);
            }

            currentTransaction.setInputs(necessaryArrayList);
        }

        if (transaction.getClient() != null)
        {
            currentTransaction.setClient(transaction.getClient());
        }

        if (transaction.getDate() != null)
        {
            currentTransaction.setDate(transaction.getDate());
        }

        if (transaction.getPersonnel() != null)
        {
            currentTransaction.setPersonnel(transaction.getPersonnel());
        }

        if (transaction.getType() != null)
        {
            currentTransaction.setType(transaction.getType());
        }
        
        



        return transactionRepository.save(currentTransaction);

    }
}

