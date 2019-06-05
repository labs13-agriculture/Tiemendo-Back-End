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
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    InventoryItemRepository itemRepo;
    CropTypeRepository cropRepo;

    public SeedData(InventoryItemRepository itemRepo, CropTypeRepository cropRepo) {
        this.itemRepo = itemRepo;
        this.cropRepo = cropRepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Faker f = new Faker();

        System.out.println("Seeding Inventory Items");
        InventoryItem i1 = new InventoryItem("NPK");
        InventoryItem i2 = new InventoryItem("Urea");
        InventoryItem i3 = new InventoryItem("Knapsack");
        InventoryItem i4 = new InventoryItem("Wallington Boats");
        InventoryItem i5 = new InventoryItem("Cutlasses");
        InventoryItem i6 = new InventoryItem("Atrazine");
        InventoryItem i7 = new InventoryItem("Kondem");
        itemRepo.saveAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7));

        System.out.println("Seeding Crop Types");
        CropType c1 = new CropType("Maize");
        CropType c2 = new CropType("Sorghum");
        cropRepo.saveAll(Arrays.asList(c1,c2));
        for (var i = 0; i++<3;){
            CropType c = new CropType(f.beer().hop());
            cropRepo.save(c);
        }

    }
}

