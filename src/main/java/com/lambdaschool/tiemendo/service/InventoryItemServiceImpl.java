package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.InventoryItem;
import com.lambdaschool.tiemendo.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "inventoryItemService")
public class InventoryItemServiceImpl implements InventoryItemService
{
    @Autowired
    private InventoryItemRepository inventoryrepos;

    @Transactional
    @Override
    public List<InventoryItem> findAll()
    {
        List<InventoryItem> list = new ArrayList<>();
        inventoryrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
