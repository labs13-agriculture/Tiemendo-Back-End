package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemTypeService
{

    Page<ItemType> findAll(Pageable pageable);

    ItemType findItemTypeById(long id);
    void delete(long id);
    ItemType save(ItemType itemType);
    ItemType update(ItemType itemType, long id);

}
