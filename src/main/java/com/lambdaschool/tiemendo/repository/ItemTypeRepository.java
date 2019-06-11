package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.ItemType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemTypeRepository extends PagingAndSortingRepository<ItemType, Long>
{
    ItemType findByNameIgnoreCase(String name);
}
