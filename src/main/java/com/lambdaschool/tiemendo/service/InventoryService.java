package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Inventory;

import java.util.ArrayList;

public interface InventoryService {
    ArrayList<Inventory> findAll();
    ArrayList<Inventory> add(Inventory i);
    ArrayList<Inventory> update(Inventory i);
    ArrayList<Inventory> delete(long id);
}
