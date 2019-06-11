package com.lambdaschool.tiemendo.service;



import com.lambdaschool.tiemendo.model.*;

import com.lambdaschool.tiemendo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    InventoryRepository inventoryRepo;
    @Autowired
    TransactionItemRepository tiRepo;
    @Autowired
    ItemTypeRepository itemTypeRepo;




    @Override
    public List<Transaction> findAll(Pageable pageable)
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
    @Transactional
    public void delete(long id)
    {
        Transaction t = findTransactionById(id);
        var inputs = t.getInputs();

        for (TransactionItem ti: inputs) {
            ItemType item = ti.getItem();
            Inventory inv = item.getInventory();
            inv.setQuantity(inv.getQuantity() + ti.getQuantity());

            inventoryRepo.save(inv);
        }
        transactionRepository.delete(t);
    }


    @Transactional
    @Override
    public Client save(Transaction transaction,long id) {
        Transaction newTransaction = new Transaction();
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        //this seemed to be a necessary work around. Changing the result of getInput to ArrayList in Transaction threw error about mapping to a non collection

        ArrayList<TransactionItem> inputs = new ArrayList<>();
        transaction.getInputs().iterator().forEachRemaining(inputs::add);

        newTransaction.setInputs(inputs);
        newTransaction.setType(transaction.getType());
        newTransaction.setPersonnel(transaction.getPersonnel());
        newTransaction.setDate(transaction.getDate());
        newTransaction.setClient(client);

        client.getTransactions().add(newTransaction);

        for (TransactionItem ti: inputs) {
            ItemType item = ti.getItem();
            Inventory inv = item.getInventory();
            inv.setQuantity(inv.getQuantity() - ti.getQuantity());

            inventoryRepo.save(inv);
        }

        return clientRepository.save(client);

    }


    @Override
    @Transactional
    public Transaction update(Transaction transaction, long id) {
        Transaction currentTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (transaction.getInputs() != null)
        {
            ArrayList<TransactionItem> mergedInputs = new ArrayList<>();

            ArrayList<TransactionItem> incomingInputs = new ArrayList<>();
            transaction.getInputs().iterator().forEachRemaining(incomingInputs::add);

            for (TransactionItem ti: incomingInputs) {

                // see if we can find input in db
                if (tiRepo.findById(ti.getId()).isPresent()) {
                    TransactionItem indb = tiRepo.findById(ti.getId()).get();

                    // if it isn't the same get the itemType then that item Types inventory and update accordingly
                    if (!ti.getQuantity().equals(indb.getQuantity())) {
                        ItemType it = indb.getItem();
                        Inventory inv = it.getInventory();

                        //update inventory with the difference of incoming and current
                        int change = ti.getQuantity() - indb.getQuantity();
                        inv.setQuantity(inv.getQuantity() - change);
                        inventoryRepo.save(inv);
                    }

                } else {
                    //if not in database update inventory and add to merged list so it can be saved and added to db
                    ItemType it = itemTypeRepo.findByNameIgnoreCase(ti.getItem().getName());
                    Inventory inv = it.getInventory();
                    inv.setQuantity(inv.getQuantity() - ti.getQuantity());
                }

                mergedInputs.add(ti);
            }

            currentTransaction.setInputs(mergedInputs);
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

