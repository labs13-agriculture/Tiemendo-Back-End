package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.ItemType;
import com.lambdaschool.tiemendo.service.ItemTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/itemtype")
public class ItemTypeController extends AbstractController {

    @Autowired
    ItemTypeService itemTypeService;

    @ApiOperation(value = "Allows authenticated user to post an item-type to database.")
    @ApiImplicitParam(name = "itemtype object", dataType = "ItemType", paramType = "body", value = "item-type object")
    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewItemType(
            @PageableDefault(size=25, sort={"name"}) Pageable pageable,
            @Valid @RequestBody ItemType itemType,
            PagedResourcesAssembler<ItemType> assembler
    ) {
        itemTypeService.save(itemType);

        Page<ItemType> page = itemTypeService.findAll(pageable);
        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all item-types.", response = ItemType.class, responseContainer = "List")
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> listAllItemTypes(
            @PageableDefault(size=25, sort={"name"}) Pageable pageable,
            PagedResourcesAssembler<ItemType> assembler
    ) {
        Page<ItemType> page = itemTypeService.findAll(pageable);
        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes item-type based on item-type id.", response = ItemType.class)
    @DeleteMapping("/delete/{itemtypeid}")
    public ResponseEntity<?> deleteItemTypeById(
            @PageableDefault(size=25, sort={"name"}) Pageable pageable,
            @PathVariable Long itemtypeid,
            PagedResourcesAssembler<ItemType> assembler
    ) {
        itemTypeService.delete(itemtypeid);

        Page<ItemType> page = itemTypeService.findAll(pageable);
        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{itemtypeid}")
    public ResponseEntity<?> updateItemType(
            @PageableDefault(size=25, sort={"name"}) Pageable pageable,
            @RequestBody ItemType updateItemType,
            @PathVariable long itemtypeid,
            PagedResourcesAssembler<ItemType> assembler
    ) {
        itemTypeService.update(updateItemType, itemtypeid);

        Page<ItemType> page = itemTypeService.findAll(pageable);
        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
