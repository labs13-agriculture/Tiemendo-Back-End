package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.ItemType;
import com.lambdaschool.tiemendo.model.TransactionItem;

import java.util.List;

public interface ItemTypeService
{

    List <ItemType> findAll();
    ItemType findItemTypeById(long id);

    void delete(long id);

    ItemType save(ItemType itemType);

    ItemType update(ItemType itemType, long id);

}
