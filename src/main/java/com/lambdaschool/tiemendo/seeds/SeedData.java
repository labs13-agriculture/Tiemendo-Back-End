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
public class SeedData implements CommandLineRunner
{
    ItemTypeRepository itemRepo;
    CropTypeRepository cropRepo;
    InventoryRepository invRepo;

    public SeedData(ItemTypeRepository itemRepo, CropTypeRepository cropRepo, InventoryRepository invRepo) {
        this.itemRepo = itemRepo;
        this.cropRepo = cropRepo;
        this.invRepo = invRepo;
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

        ArrayList<ItemType> items = new ArrayList<>();
        itemRepo.findAll().iterator().forEachRemaining(items::add);

        var invItems = new ArrayList<Inventory>();
        for (ItemType i: items) {
           Inventory inv = new Inventory(99, i);
        }
        invRepo.saveAll(invItems);

        System.out.println("Seeding Crop Types");
        CropType c1 = new CropType("Maize");
        CropType c2 = new CropType("Sorghum");
        CropType c3 = new CropType("Fake crop");
        CropType c4 = new CropType("This is also a crop");
        CropType c5 = new CropType("A");
        CropType c6 = new CropType("C");
        CropType c7 = new CropType("B");
        cropRepo.saveAll(Arrays.asList(c1,c2, c3, c4, c5, c6, c7));
        for (var i = 0; i++<3;){
            CropType c = new CropType(f.food().spice());
            cropRepo.save(c);
        }

    }
}

