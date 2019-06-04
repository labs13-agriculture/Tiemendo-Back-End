package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.InventoryItem;
import org.springframework.data.repository.CrudRepository;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long>
{
    InventoryItem findByName(String name);
}
