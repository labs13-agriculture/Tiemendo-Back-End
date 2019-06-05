package com.lambdaschool.tiemendo.seeds;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    ItemTypeRepository itemRepo;
    CropTypeRepository cropRepo;

    public SeedData(ItemTypeRepository itemRepo, CropTypeRepository cropRepo) {
        this.itemRepo = itemRepo;
        this.cropRepo = cropRepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Faker f = new Faker();

        System.out.println("Seeding Inventory Items");
        ItemType i1 = new ItemType("NPK");
        ItemType i2 = new ItemType("Urea");
        ItemType i3 = new ItemType("Knapsack");
        ItemType i4 = new ItemType("Wallington Boats");
        ItemType i5 = new ItemType("Cutlasses");
        ItemType i6 = new ItemType("Atrazine");
        ItemType i7 = new ItemType("Kondem");
        itemRepo.saveAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7));

        System.out.println("Seeding Crop Types");
        CropType c1 = new CropType("Maize");
        CropType c2 = new CropType("Sorghum");
        cropRepo.saveAll(Arrays.asList(c1,c2));
        for (var i = 0; i++<3;){
            CropType c = new CropType(f.food().spice());
            cropRepo.save(c);
        }

    }
}

