package com.lambdaschool.tiemendo.seeds;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Transactional
@Component
public class SeedDataFarmers implements CommandLineRunner
{
    private ClientRepository clientRepo;
    private CropTypeRepository cropRepo;
    private ItemTypeRepository itemRepo;
    private YieldRepository yieldRepo;


    public SeedDataFarmers(ClientRepository clientRepo, CropTypeRepository cropRepo, ItemTypeRepository itemRepo, YieldRepository yieldRepo) {
        this.clientRepo = clientRepo;
        this.cropRepo = cropRepo;
        this.itemRepo = itemRepo;
        this.yieldRepo = yieldRepo;
    }

    @Override
    public void run(String[] args)
    {
        Faker f = new Faker();
        System.out.println("Seeding Farmer Data");
        //Farmer SEEDING

        // create other objects needed for farmer seed data
        // create CropTypes for yield data
        CropType corn = cropRepo.findByCropName("Maize");
        // create ItemTypes for Transaction data
        ItemType sack = itemRepo.findByNameIgnoreCase("Knapsack");
        ItemType urea = itemRepo.findByNameIgnoreCase("Urea");
        ItemType cutlass = itemRepo.findByNameIgnoreCase("Cutlasses");
        ArrayList<ItemType> items = new ArrayList<>(Arrays.asList(sack, urea, cutlass));

        // make an array of 10 farmers type clients
        var farmers = new ArrayList<Client>();

        for (var i=0; i++<10;) {
            // Create a farmer
            Client farmer = new Client(f.bool().bool(), (long) Math.ceil(Math.random() * 20) + 2000,
                    f.address().fullAddress(), f.gameOfThrones().house(), f.gameOfThrones().character(),
                    f.phoneNumber().phoneNumber(), f.internet().emailAddress());
            farmer.setType("FARMER");
            // Add Transactions to farmer
            // create list of transactions
            var transactions = new ArrayList<Transaction>();
            // loop to create 5 transaction
            for (var nt=0; nt++<5;) {
                // create list on inputs
                var inputs = new ArrayList<TransactionItem>();
                // create transaction
                var transaction = new Transaction("CREDIT", new Date(), new ArrayList<>(), "Joshua", farmer);
                // generate inputs to add to list
                for (var ni=0; ni++<3;) {
                    TransactionItem ti = new TransactionItem(
                            f.number().numberBetween(1, 3),
                            items.get(ni-1),
                            f.number().randomDouble(2, 2, 10),
                            transaction
                    );
                    inputs.add(ti);
                }
                // add list of inputs to transaction
                transaction.setInputs(inputs);
                transactions.add(transaction);
            }
            // add transaction to farmer
            farmer.setTransactions(transactions);
            // add farmer to list
            farmers.add(farmer);
        }

//        System.out.println("Adding Yield History to Farmers");
//
//        Yield y1 = new Yield(21, corn, 25, 2, "2020", f1);
//        Yield y2 = new Yield(26, corn, 25, 2, "2019", f1);
//        Yield y3 = new Yield(18, corn, 25, 2, "2018", f1);
//        Yield y4 = new Yield(12, corn, 25, 2, "2017", f1);
//        ArrayList<Yield> yields = new ArrayList(Arrays.asList(y1, y2, y3, y4));
//        //yieldRepo.saveAll(yields);
//        f1.getYieldHistory().addAll(yields);
//
//
//        System.out.println("adding installment history for farmer");
//        Installment insstall1 = new Installment(10.50, new Date(), "MTN", "Joshua", f1);
//        Installment insstall2 = new Installment(11.15, new Date(), "BANK", "Joshua", f1);
//        Installment insstall3 = new Installment(10.25, new Date(), "CASH", "Joshua", f1);
//
//        ArrayList<Installment> installments = new ArrayList<>(Arrays.asList(insstall1, insstall2, insstall3));
//        f1.getInstallments().addAll(installments);

        clientRepo.saveAll(farmers);
//        yieldRepo.saveAll(yields);
    }
}

