package com.lambdaschool.tiemendo.seeds;

import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

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

        //ADDING RETAILERS WITH CONTACTS AND LOCATIONS
        RetailerLocation rl1 = new RetailerLocation("123 main street", "North", "District 2", "Downtown", "Crooked tree", new Retailer());
        RetailerContact rc1 = new RetailerContact("Mr", "John Doe", "M", "Ghanaian", "01/01/2010", "PhD", "Manager", "555-555-5555", "email@example.com", new Retailer());

        Retailer r1 = new Retailer("Lowes", false, 1989, rl1, rc1);
    
        rc1.setRetailer(r1);
        rl1.setRetailer(r1);
    
        retailerRepository.save(r1);

    }
}

