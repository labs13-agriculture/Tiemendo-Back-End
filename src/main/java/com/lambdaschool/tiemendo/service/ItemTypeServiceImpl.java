package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.ItemType;

import com.lambdaschool.tiemendo.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "inventoryItemService")
public class ItemTypeServiceImpl implements ItemTypeService
{
    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private ItemTypeService itemTypeService;

    @Transactional
    @Override
    public List<ItemType> findAll()
    {
        List<ItemType> list = new ArrayList<>();
        itemTypeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ItemType findItemTypeById(long id) {
        return itemTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        itemTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        itemTypeRepository.deleteById(id);
    }

    @Override
    public ItemType save(ItemType itemType) {


            return itemTypeRepository.save(itemType);

    }

    @Override
    public ItemType update(ItemType itemType, long id) {
        ItemType currentItemType = itemTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));


        if (itemType.getName() != null)
        {
            currentItemType.setName(itemType.getName());
        }

        if (itemType.getActive() != null)
        {
            currentItemType.setActive(itemType.getActive());
        }

        if (itemType.getTransactions() != null)
        {
            currentItemType.setTransactions(itemType.getTransactions());
        }

        return itemTypeRepository.save(currentItemType);

    }


}
