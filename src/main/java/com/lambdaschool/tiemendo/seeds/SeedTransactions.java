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
    ItemTypeRepository itemTypeRepository;

    public SeedTransactions(TransactionRepository transactionRepository, TransactionItemRepository transactionItemRepository,ItemTypeRepository itemTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionItemRepository = transactionItemRepository;
        this.itemTypeRepository = itemTypeRepository;

    }

    @Override
    public void run(String[] args) throws Exception
    {
        System.out.println("Seeding Transaction Data");
        ArrayList <TransactionItem> transactionitems = new ArrayList<>();

        //creating inventory items
        ItemType ii1 = new ItemType("Bean");
        ItemType ii2 = new ItemType("Chocolate");

        //ADDING  Transaction Items
        TransactionItem ti1 = new TransactionItem(10,.5,new ItemType(),new Transaction());
        TransactionItem ti2 = new TransactionItem(10,1.00,new ItemType(),new Transaction());

        //adding retailer


        //creating transaction object
        Transaction t1 = new Transaction("Cash",new Date(), transactionitems, "Ronaldo Bean",);


        t1.getInputs().add(ti1);
        t1.getInputs().add(ti2);

        ti1.setTransaction(t1);
        ti2.setTransaction(t1);

        ti1.setItem(ii1);
        ti2.setItem(ii2);




        itemTypeRepository.save(ii1);
        itemTypeRepository.save(ii2);
        transactionItemRepository.save(ti1);
        transactionItemRepository.save(ti2);
        transactionRepository.save(t1);

    }
}

