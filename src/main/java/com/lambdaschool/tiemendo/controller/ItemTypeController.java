package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.ItemType;
import com.lambdaschool.tiemendo.service.ItemTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/itemtype")
public class ItemTypeController {

    @Autowired
    ItemTypeService itemTypeService;

    @ApiOperation(value = "Allows authenticated user to post an item-type to database.")
    @ApiImplicitParam(name = "itemtype object", dataType = "ItemType", paramType = "body",
            value = "item-type object")
    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewItemType(@PageableDefault(size=25, sort={"name"}) Pageable pageable, @Valid @RequestBody ItemType itemType) {
        itemTypeService.save(itemType);
        return new ResponseEntity<>(itemTypeService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all item-types.", response = ItemType.class, responseContainer = "List")
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> listAllItemTypes(@PageableDefault(size=25, sort={"name"}) Pageable pageable)
    {
        List<ItemType> typeItems = itemTypeService.findAll(pageable);
        return new ResponseEntity<>(typeItems, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes item-type based on item-type id.", response = ItemType.class)
    @DeleteMapping("/delete/{itemtypeid}")
    public ResponseEntity<?> deleteItemTypeById(@PageableDefault(size=25, sort={"name"}) Pageable pageable, @PathVariable Long itemtypeid)
    {
        itemTypeService.delete(itemtypeid);
        return new ResponseEntity<>(itemTypeService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{itemtypeid}")
    public ResponseEntity<?> updateItemType(
            @PageableDefault(size=25, sort={"name"}) Pageable pageable,
            @RequestBody ItemType updateItemType,
            @PathVariable long itemtypeid)
    {
        itemTypeService.update(updateItemType, itemtypeid);
        return new ResponseEntity<>(itemTypeService.findAll(pageable), HttpStatus.OK);
    }
}
