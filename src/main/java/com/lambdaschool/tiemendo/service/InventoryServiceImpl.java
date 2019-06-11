package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Inventory;
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
        invRepo.save(i);
        return findAll();
    }

    @Override
    public ArrayList<Inventory> update(Inventory i) {
        // currently only changes qty... is there anything else we should change?
        Inventory current = findById(i.getInvid());
        if (i.getQuantity() != current.getQuantity()) {
            current.setQuantity(i.getQuantity());
        }
        invRepo.save(current);
        return findAll();
    }

    @Override
    public ArrayList<Inventory> delete(long id) {
        Inventory i = findById(id);
        invRepo.delete(i);
        return findAll();
    }
}
