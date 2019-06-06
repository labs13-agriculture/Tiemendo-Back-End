package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.ItemType;

import java.util.List;

public interface InventoryItemService
{

    List <ItemType> findAll();

}
