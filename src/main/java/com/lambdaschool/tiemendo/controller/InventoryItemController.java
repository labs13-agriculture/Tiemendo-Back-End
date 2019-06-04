package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.InventoryItem;
import com.lambdaschool.tiemendo.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryItemController
{

    @Autowired
    private InventoryItemService inventoryItemService;

    @GetMapping(value = "/inventory-items", produces = {"application/json"})
    public ResponseEntity<?> listAllInventoryItems()
    {
        List<InventoryItem> myInventory = inventoryItemService.findAll();
        return new ResponseEntity<>(myInventory, HttpStatus.OK);
    }

}
