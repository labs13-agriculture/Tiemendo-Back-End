package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.ItemType;
import com.lambdaschool.tiemendo.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "inventoryItemService")
public class ItemTypeServiceImpl implements ItemTypeService
{
    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Transactional
    @Override
    public List<ItemType> findAll()
    {
        List<ItemType> list = new ArrayList<>();
        itemTypeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
