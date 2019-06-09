//package com.lambdaschool.tiemendo.seeds;
//
//import com.lambdaschool.tiemendo.model.*;
//import com.lambdaschool.tiemendo.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//
//@Transactional
//@Component
//public class SeedDataYield implements CommandLineRunner {
//
//    FarmerRepository farmerRepository;
//    CropTypeRepository cropTypeRepository;
//    FarmerLocationRepository farmerLocationRepository;
//    FarmerContactRepository farmerContactRepository;
//    YieldRepository yieldRepository;
//
//
//    public SeedDataYield(YieldRepository yieldRepository,FarmerLocationRepository farmerLocationRepository,FarmerContactRepository farmerContactRepository,FarmerRepository farmerRepository, CropTypeRepository cropTypeRepository) {
//        this.farmerRepository = farmerRepository;
//        this.cropTypeRepository = cropTypeRepository;
//        this.farmerRepository = farmerRepository;
//        this.farmerContactRepository = farmerContactRepository;
//        this.farmerLocationRepository = farmerLocationRepository;
//        this.yieldRepository = yieldRepository;
//
//
//    }
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        System.out.println("Seeding Yield Data");
//        ArrayList<Yield> yieldhistory = new ArrayList<>();
//
//        //creating yield
//        Yield yield1 = new Yield(2,new CropType(),8,10,"Summer",new Farmer());
//        Yield yield2 = new Yield(5,new CropType(),10,6,"Autumn",new Farmer());
//
//        yieldhistory.add(yield1);
//        yieldhistory.add(yield2);
//
//        //ADDING  Crop Type to yield
//
//
//        CropType c1 = new CropType("Frijoles");
//        CropType c2 = new CropType("plantain");
//
//        yield1.setCropType(c1);
//        yield2.setCropType(c2);
//
//
//
//        //adding farmer
//        Farmer f1 = new Farmer("999 yield street", true, 1999, new FarmerLocation(), new FarmerContact(), yieldhistory);
//        FarmerContact fc1 = new FarmerContact( "John Doe", "M","555-555-5555", "email@example.com", new Farmer());
//        FarmerLocation fl1 = new FarmerLocation("999 yield street", "South", "22", "yield Community", "big hill", new Farmer());
//
//        //adding yield to farmer
//        yield1.setFarmer(f1);
//        yield2.setFarmer(f1);
//
//        //adding farmer to farmer location
//        fc1.setFarmer(f1);
//
//
//        //adding farmer to farmer contact
//
//        fl1.setFarmer(f1);
//
//        //adding farmer contact, yield history, farmerlocation to farmer
//        f1.setYieldHistory(yieldhistory);
//        f1.setFarmercontact(fc1);
//        f1.setFarmerlocation(fl1);
//
//
//
//
//
//
//        //saving all  objects
//
//
//
//        farmerRepository.save(f1);
//        farmerLocationRepository.save(fl1);
//        farmerContactRepository.save(fc1);
//        cropTypeRepository.save(c1);
//        cropTypeRepository.save(c2);
//        yieldRepository.save(yield1);
//        yieldRepository.save(yield2);
//
//    }
//}
