package com.lambdaschool.tiemendo.seeds;

import com.lambdaschool.tiemendo.model.Farmer;
import com.lambdaschool.tiemendo.model.FarmerContact;
import com.lambdaschool.tiemendo.model.FarmerLocation;
import com.lambdaschool.tiemendo.repository.FarmerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class SeedDataFarmers implements CommandLineRunner
{
    private FarmerRepository farmerRepo;

    public SeedDataFarmers(FarmerRepository farmerRepo) {
        this.farmerRepo = farmerRepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {

        System.out.println("Seeding Farmer Data");
        //Farmer TESTING BELOW
        FarmerContact fc = new FarmerContact("Denise", "male", "111-555-1234", "email@example.com", new Farmer());
        FarmerLocation fl = new FarmerLocation("Summervale", "old red church", new Farmer());
        Farmer f1 = new Farmer("Moses", 2010, fl, fc);
        fl.setFarmer(f1);
        fc.setFarmer(f1);

        System.out.println("Creating Yield History");


        farmerRepo.save(f1);
    }
}

