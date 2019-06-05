package com.lambdaschool.tiemendo.seeds;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.CropTypeRepository;
import com.lambdaschool.tiemendo.repository.FarmerRepository;
import com.lambdaschool.tiemendo.repository.ItemTypeRepository;
import com.lambdaschool.tiemendo.repository.YieldRepository;
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
    private FarmerRepository farmerRepo;
    private CropTypeRepository cropRepo;
    private YieldRepository yieldRepo;
    private ItemTypeRepository itemRepo;


    public SeedDataFarmers(FarmerRepository farmerRepo, CropTypeRepository cropRepo, YieldRepository yieldRepo, ItemTypeRepository itemRepo) {
        this.farmerRepo = farmerRepo;
        this.cropRepo = cropRepo;
        this.yieldRepo = yieldRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Faker f = new Faker();

        System.out.println("Seeding Farmer Data");
        //Farmer TESTING BELOW
        FarmerContact fc = new FarmerContact("Denise", "male", "111-555-1234", "email@example.com", new Farmer());
        FarmerLocation fl = new FarmerLocation("Summervale", "old red church", new Farmer());
        Farmer f1 = new Farmer("Moses", 2010, fl, fc);
        fl.setFarmer(f1);
        fc.setFarmer(f1);

        System.out.println("Adding Yield History to Farmers");
        CropType corn = cropRepo.findByCropName("Maize");
        Yield y1 = new Yield(21, corn, 25, 2, "2020", f1);
        Yield y2 = new Yield(26, corn, 25, 2, "2019", f1);
        Yield y3 = new Yield(18, corn, 25, 2, "2018", f1);
        Yield y4 = new Yield(12, corn, 25, 2, "2017", f1);
        ArrayList<Yield> yields = new ArrayList(Arrays.asList(y1, y2, y3, y4));
        //yieldRepo.saveAll(yields);
        f1.getYieldHistory().addAll(yields);

        System.out.println("adding Transactions to farmer");
        // get item types
        ItemType sack = itemRepo.findByNameIgnoreCase("Knapsack");
        ItemType urea = itemRepo.findByNameIgnoreCase("Urea");
        ItemType cutlass = itemRepo.findByNameIgnoreCase("Cutlasses");

        // Build out inputs list
        TransactionItem ti1 = new TransactionItem(3, sack, 5.50, new Transaction());
        TransactionItem ti2 = new TransactionItem(1, urea, 2.50, new Transaction());
        TransactionItem ti3 = new TransactionItem(1, cutlass, 3.15, new Transaction());
        ArrayList<TransactionItem> inputs = new ArrayList<>(Arrays.asList(ti1, ti2, ti3));

        // build transaction and add transaction to inputs
        Transaction t1 = new Transaction("CASH", new Date(), inputs, "Joshua");
        inputs.iterator().forEachRemaining(x -> x.setTransaction(t1));

        System.out.println("adding installment history fro farmer");
        Installment insstall1 = new Installment(10.50, new Date(), "MTN", "Joshua", f1);
        Installment insstall2 = new Installment(11.15, new Date(), "BANK", "Joshua", f1);
        Installment insstall3 = new Installment(10.25, new Date(), "CASH", "Joshua", f1);

        ArrayList<Installment> installments = new ArrayList<>(Arrays.asList(insstall1, insstall2, insstall3));
        f1.getInstallments().addAll(installments);

        farmerRepo.save(f1);
    }
}

