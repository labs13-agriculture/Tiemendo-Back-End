package com.lambdaschool.tiemendo.seeds;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Transactional
@Component
public class SeedDataRetailers implements CommandLineRunner
{
    RetailerRepository retailerRepository;
    RetailerContactRepository retailerContactRepository;
    RetailerLocationRepository retailerLocationRepository;


    public SeedDataRetailers(RetailerRepository retailerRepository, RetailerContactRepository retailerContactRepository, RetailerLocationRepository retailerLocationRepository) {
        this.retailerRepository = retailerRepository;
        this.retailerContactRepository = retailerContactRepository;
        this.retailerLocationRepository = retailerLocationRepository;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        System.out.println("Seeding Retailer Data");

        var f = new Faker();

        //ADDING RETAILERS WITH CONTACTS AND LOCATIONS
        RetailerLocation rl1 = new RetailerLocation("123 main street", "North", "District 2", "Downtown", "Crooked tree", new Retailer());
        RetailerContact rc1 = new RetailerContact("Mr", "John Doe", "M", "Ghanaian", "01/01/2010", "PhD", "Manager", "555-555-5555", "email@example.com", new Retailer());

        Retailer r1 = new Retailer("Lowes", false, 1989, rl1, rc1);


        Turnover t1 = new Turnover(f.number().numberBetween(20, 30), f.number().numberBetween(20, 40), 600, "m2", 2014, r1);
        Turnover t2 = new Turnover(f.number().numberBetween(20, 30), f.number().numberBetween(20, 40), 600, "m2", 2015, r1);
        Turnover t3 = new Turnover(f.number().numberBetween(20, 30), f.number().numberBetween(20, 40), 600, "m2", 2016, r1);
        Turnover t4 = new Turnover(f.number().numberBetween(20, 30), f.number().numberBetween(20, 40), 600, "m2", 2017, r1);
        Turnover t5 = new Turnover(f.number().numberBetween(20, 30), f.number().numberBetween(20, 40), 600, "m2", 2018, r1);
        Turnover t6 = new Turnover(f.number().numberBetween(20, 30), f.number().numberBetween(20, 40), 600, "m2", 2019, r1);
        var goals = new ArrayList<Turnover>(Arrays.asList(t1, t2, t3, t4, t5, t6));

        r1.setGoals(goals);

        rc1.setRetailer(r1);
        rl1.setRetailer(r1);
    
        retailerRepository.save(r1);
    }
}

