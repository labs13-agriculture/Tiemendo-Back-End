package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/retailer")
public class RetailerController extends AbstractController
{

    @Autowired
    private ClientService retailerService;
    
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/retailers", produces = {"application/json"})
    public ResponseEntity<?> listAllRetailers(
            @PageableDefault(size=25, sort={"firstName"}) Pageable pageable,
            @RequestParam(defaultValue = "false") boolean lead,
            PagedResourcesAssembler<Client> assembler
    ) {
        // todo: this needs to be updated to use paged resource assembler
        Page<Client> page = retailerService.findAll(pageable, lead);

        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<?> searchFarmers(
            @PageableDefault(size=25, sort={"firstName"}) Pageable pageable,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "false") String lead,
            PagedResourcesAssembler<Client> assembler
    ) {
        var search = new HashMap<String, String>();
        if (name != null && !name.equals("")) search.put("name", name);
        if (location != null && !location.equals("")) search.put("location", location);

        search.put("type", "RETAILER");
        search.put("lead", lead);

        Page<Client> page = retailerService.search(pageable, search);

        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> findRetailerById(@PathVariable long id)
    {
        return new ResponseEntity<>(retailerService.findById(id), HttpStatus.OK);
    }
    
    @PostMapping(value = "/new", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addNewRetailer(@RequestBody Client newRetailer)
    {
        return new ResponseEntity<>(retailerService.add(newRetailer), HttpStatus.OK);
    }
    
    //Must include contact and location id numbers if one exists (see retailerserviceimpl)
    @PutMapping(value = "/update/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateRetailer(@PathVariable long id, @RequestBody Client updatedRetailer)
    {
        updatedRetailer.setId(id);
        return new ResponseEntity<>(retailerService.update(updatedRetailer), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteRetailer(@PathVariable long id)
    {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
