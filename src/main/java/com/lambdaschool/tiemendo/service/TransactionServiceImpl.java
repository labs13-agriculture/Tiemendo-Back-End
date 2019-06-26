package com.lambdaschool.tiemendo.service;



import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Transaction;

import com.lambdaschool.tiemendo.model.TransactionItem;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import com.lambdaschool.tiemendo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Override
    public Page<Transaction> findAll(Pageable pageable)
    {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Page<Transaction> findAllByClientId(Pageable pageable, long id) {
        Client c = clientRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find client with id: " + id);
        });

        return transactionRepository.findAllByClient(pageable, c);
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
    public Client save(Transaction transaction,long id) {
        Transaction newTransaction = new Transaction();
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));


        //this seemed to be a necessary work around. Changing the result of getInput to ArrayList in Transaction threw error about mapping to a non collection

        ArrayList necessaryArrayList = new ArrayList();
        List<TransactionItem> originalInputs = transaction.getInputs();

        if (originalInputs.size() <= 0) {
            // a transaction must have inputs
            throw new ResourceNotFoundException("Transaction Must Have Items");
        }

        for(TransactionItem i: originalInputs) {System.out.println("In the loop");
            if (i.getItem() == null || i.getItem().getName() == null) {
                // Items can not be null
                throw new ResourceNotFoundException("Transaction Must Have valid Items");
            }

            necessaryArrayList.add(i);
            i.setTransaction(newTransaction);
        }

        newTransaction.setInputs(necessaryArrayList);
        newTransaction.setType(transaction.getType());
        newTransaction.setPersonnel(transaction.getPersonnel());
        newTransaction.setDate(transaction.getDate());
        newTransaction.setClient(client);

        client.getTransactions().add(newTransaction);

        return clientRepository.save(client);

    }


    @Override
    @Transactional
    public Page<Transaction> update(Pageable pageable, Transaction transaction, long id) {
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
                i.setTransaction(currentTransaction);//setting current transaction in updated transaction-items constructor
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
        
        



        transactionRepository.save(currentTransaction);

        return transactionRepository.findAllByClient(pageable, currentTransaction.getClient());

    }
}

