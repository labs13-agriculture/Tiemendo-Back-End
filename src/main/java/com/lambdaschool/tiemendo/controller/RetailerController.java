package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailer")
public class RetailerController
{

    @Autowired()
    private ClientService retailerService;

    @GetMapping(value = "/retailers", produces = {"application/json"})
    public ResponseEntity<?> listAllRetailers(Pageable pageable)
    {
        List<Client> myRetailers = retailerService.findAll(pageable);
        return new ResponseEntity<>(myRetailers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/search", produces = {"application/json"})
    public ResponseEntity<?> searchRetailers(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String location, @RequestParam(defaultValue = "false") String lead)
    {//todo: sql search query once db structure finalized
        //Lead parameter must be string, but can be converted to boolean. Only "true" (not case sensitive) will evaluate to true with Boolean.parseBoolean()
        boolean isLead = Boolean.parseBoolean(lead);
        
        
        return new ResponseEntity<>(/*retailerService.search(name, location, isLead),*/ HttpStatus.OK);
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
        retailerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
