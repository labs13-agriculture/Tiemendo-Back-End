package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
}
