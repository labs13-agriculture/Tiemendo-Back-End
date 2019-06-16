//package com.lambdaschool.tiemendo.seeds;
//
//
//
//import com.lambdaschool.tiemendo.model.*;
//import com.lambdaschool.tiemendo.repository.*;
//import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Transactional
//@Component
//public class SeedTransactions implements CommandLineRunner
//{
//    TransactionRepository transactionRepository;
//    TransactionItemRepository transactionItemRepository;
//    ItemTypeRepository itemTypeRepository;
//    RetailerRepository retailerRepository;
//    RetailerContactRepository retailerContactRepository;
//    RetailerLocationRepository retailerLocationRepository;
//
//    public SeedTransactions(TransactionRepository transactionRepository, TransactionItemRepository transactionItemRepository,ItemTypeRepository itemTypeRepository,RetailerRepository retailerRepository,RetailerContactRepository retailerContactRepository,RetailerLocationRepository retailerLocationRepository) {
//        this.transactionRepository = transactionRepository;
//        this.transactionItemRepository = transactionItemRepository;
//        this.itemTypeRepository = itemTypeRepository;
//        this.retailerRepository = retailerRepository;
//        this.retailerLocationRepository = retailerLocationRepository;
//        this.retailerContactRepository = retailerContactRepository;
//
//
//    }
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        System.out.println("Seeding Transaction Data");
//        ArrayList <TransactionItem> transactionitems = new ArrayList<>();
//
//        //creating inventory items
//        ItemType ii1 = new ItemType("Bean");
//        ItemType ii2 = new ItemType("Chocolate");
//
//        //ADDING  Transaction Items
//        TransactionItem ti1 = new TransactionItem(10,.5,new ItemType(),new Transaction());
//        TransactionItem ti2 = new TransactionItem(10,1.00,new ItemType(),new Transaction());
//
//        //adding retailer
//        RetailerLocation rl1 = new RetailerLocation("123 main street", "North", "District 2", "Downtown", "Crooked tree", new Retailer());
//        RetailerContact rc1 = new RetailerContact("Mr", "John Doe", "M", "Ghanaian", "01/01/2010", "PhD", "Manager", "555-555-5555", "email@example.com", new Retailer());
//
//        Retailer r1 = new Retailer("Moes", false, 1989, rl1, rc1);
//
//        rc1.setRetailer(r1);
//        rl1.setRetailer(r1);
//
//
//
//
//        //creating transaction object
//        Transaction t1 = new Transaction("Cash",new Date(), transactionitems, "Ronaldo Bean",r1);
//
//
//        t1.getInputs().add(ti1);
//        t1.getInputs().add(ti2);
//
//        ti1.setTransaction(t1);
//        ti2.setTransaction(t1);
//
//        ti1.setItem(ii1);
//        ti2.setItem(ii2);
//
//
//
//        retailerRepository.save(r1);
//        itemTypeRepository.save(ii1);
//        itemTypeRepository.save(ii2);
//        transactionItemRepository.save(ti1);
//        transactionItemRepository.save(ti2);
//        transactionRepository.save(t1);
//
//    }
//}
//
