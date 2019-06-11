package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailer")
public class RetailerController
{

    @Autowired
    private RetailerService retailerService;

    @GetMapping(value = "/retailers", produces = {"application/json"})
    public ResponseEntity<?> listAllRetailers()
    {
        List<Retailer> myRetailers = retailerService.findAll();
        return new ResponseEntity<>(myRetailers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/search", produces = {"application/json"})
    public ResponseEntity<?> searchRetailers(@RequestParam(defaultValue = "") String name,
                                             @RequestParam(defaultValue = "") String location,
                                             @RequestParam(defaultValue = "false") String lead)
    {
        //Lead parameter must be string, but can be converted to boolean. Only "true" (not case sensitive) will evaluate to true with Boolean.parseBoolean()
        boolean isLead = Boolean.parseBoolean(lead);
        
        return new ResponseEntity<>(retailerService.searchRetailer(name, location, isLead), HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> findRetailerById(@PathVariable long id)
    {
        return new ResponseEntity<>(retailerService.findRetailerById(id), HttpStatus.OK);
    }
    
    @PostMapping(value = "/new", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addNewRetailer(@RequestBody Retailer newRetailer)
    {
        return new ResponseEntity<>(retailerService.save(newRetailer), HttpStatus.OK);
    }
    
    //Must include contact and location id numbers if one exists (see retailerserviceimpl)
    @PutMapping(value = "/update/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateRetailer(@PathVariable long id, @RequestBody Retailer updatedRetailer) throws Exception
    {
        
        //attempting sleep to make sure database has time to update before returning retailer
//        Thread.sleep(750);
        return new ResponseEntity<>(retailerService.update(id, updatedRetailer), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteRetailer(@PathVariable long id)
    {
        retailerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
