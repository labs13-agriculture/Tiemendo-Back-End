package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.ItemType;
import org.springframework.data.repository.CrudRepository;

public interface ItemTypeRepository extends CrudRepository<ItemType, Long>
{
    ItemType findByNameIgnoreCase(String name);
}
