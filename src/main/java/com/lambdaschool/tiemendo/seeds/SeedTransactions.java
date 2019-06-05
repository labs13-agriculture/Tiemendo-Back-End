package com.lambdaschool.tiemendo.seeds;



import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class SeedTransactions implements CommandLineRunner
{
    TransactionRepository transactionRepository;
    TransactionItemRepository transactionItemRepository;
    InventoryItemRepository inventoryItemRepository;

    public SeedTransactions(TransactionRepository transactionRepository, TransactionItemRepository transactionItemRepository,InventoryItemRepository inventoryItemRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionItemRepository = transactionItemRepository;
        this.inventoryItemRepository = inventoryItemRepository;

    }

    @Override
    public void run(String[] args) throws Exception
    {
        System.out.println("Seeding Transaction Data");
        ArrayList <TransactionItem> transactionitems = new ArrayList<>();

        //creating inventory items
        InventoryItem ii1 = new InventoryItem("Bean");
        InventoryItem ii2 = new InventoryItem("Chocolate");

        //ADDING  Transaction Items
        TransactionItem ti1 = new TransactionItem(10,.5,new InventoryItem(),new Transaction());
        TransactionItem ti2 = new TransactionItem(10,1.00,new InventoryItem(),new Transaction());

        //creating transaction object
        Transaction t1 = new Transaction("Cash",new Date(), transactionitems, 0.00, "Ronaldo Bean");


        t1.getInputs().add(ti1);
        t1.getInputs().add(ti2);

        ti1.setTransaction(t1);
        ti2.setTransaction(t1);

        ti1.setItem(ii1);
        ti2.setItem(ii2);

        t1.computeTotal();//testing out compute total for two TransactionItems in one Transaction. Appears to be working


        inventoryItemRepository.save(ii1);
        inventoryItemRepository.save(ii2);
        transactionItemRepository.save(ti1);
        transactionItemRepository.save(ti2);
        transactionRepository.save(t1);

    }
}

