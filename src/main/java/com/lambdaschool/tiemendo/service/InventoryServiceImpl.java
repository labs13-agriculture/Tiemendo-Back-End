package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Inventory;
import com.lambdaschool.tiemendo.model.ItemType;
import com.lambdaschool.tiemendo.repository.InventoryRepository;
import com.lambdaschool.tiemendo.repository.ItemTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService{

    InventoryRepository invRepo;
    ItemTypeRepository itemRepo;

    public InventoryServiceImpl(InventoryRepository invRepo, ItemTypeRepository itemRepo) {
        this.invRepo = invRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public ArrayList<Inventory> findAll() {
        var items = new ArrayList<Inventory>();
        invRepo.findAll().iterator().forEachRemaining(items::add);
        items.sort(
                (i1, i2) -> i1.getItem().getName().compareToIgnoreCase(i2.getItem().getName())
        );
        return items;
    }

    public Inventory findById(long id) {
        return invRepo.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find Inventory resource with id: " + id);
        });
    }

    @Override
    public ArrayList<Inventory> add(Inventory i) {
        ItemType it = itemRepo.save(new ItemType(i.getItem().getName()));
        i.setItem(it);
        invRepo.save(i);
        return findAll();
    }

    @Override
    public ArrayList<Inventory> update(Inventory i) {
        // get the associated item type and update active
        ItemType it = itemRepo.findByNameIgnoreCase(i.getItem().getName());
        if (i.getItem().getActive() != it.getActive()) {
            it.setActive(i.getItem().getActive());
        }

        Inventory current = findById(i.getInvid());
        current.setQuantity(i.getQuantity());

        i.setItem(it);
        invRepo.save(current);
        return findAll();
    }

    @Override
    public ArrayList<Inventory> delete(long id) {
        Inventory i = findById(id);
        ItemType it = i.getItem();
        invRepo.delete(i);
        itemRepo.delete(it);
        return findAll();
    }
}
