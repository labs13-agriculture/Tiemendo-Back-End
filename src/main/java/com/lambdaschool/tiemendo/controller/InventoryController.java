package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Inventory;
import com.lambdaschool.tiemendo.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(value="/all", produces={"application/json"})
    public ResponseEntity<?> getAllItemsInInventory(){

        return new ResponseEntity<>(inventoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value="/add", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> addItemToInventory(@Valid @RequestBody Inventory i){
        return new ResponseEntity<>(inventoryService.add(i), HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> updateItemInInventory(
            @PathVariable Long id,
            @Valid @RequestBody Inventory i
    ){
        i.setInvid(id);
        return new ResponseEntity<>(inventoryService.update(i), HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}", produces={"application/json"})
    public ResponseEntity<?> deleteItemFromInventory(@PathVariable Long id){
        return new ResponseEntity<>(inventoryService.delete(id), HttpStatus.OK);
    }
}
