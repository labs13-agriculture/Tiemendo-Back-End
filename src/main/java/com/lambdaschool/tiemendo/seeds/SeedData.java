package com.lambdaschool.tiemendo.seeds;

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

    public SeedData(ItemTypeRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        System.out.println("Seeding Inventory Items");
        ItemType i1 = new ItemType("NPK");
        ItemType i2 = new ItemType("Urea");
        ItemType i3 = new ItemType("Knapsack");
        ItemType i4 = new ItemType("Wallington Boats");
        ItemType i5 = new ItemType("Cutlasses");
        ItemType i6 = new ItemType("Atrazine");
        ItemType i7 = new ItemType("Kondem");

        ArrayList<ItemType> items = new ArrayList<>(Arrays.asList(i1, i2, i3, i4, i5, i6, i7));
        items.forEach(item -> item.setQuantity((int) Math.floor(Math.random() * 200)));
        
        itemRepo.saveAll(items);

    }
}

