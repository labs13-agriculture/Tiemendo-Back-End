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
    public ResponseEntity<?> searchRetailers(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String location, @RequestParam(defaultValue = "false") String lead)
    {//todo: sql search query once db structure finalized
        //Lead parameter must be string, but can be converted to boolean. Only "true" (not case sensitive) will evaluate to true with Boolean.parseBoolean()
        boolean isLead = Boolean.parseBoolean(lead);
        //Will need to figure out search once database shape is finalized
        
        return new ResponseEntity<>(retailerService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> findRetailerById(@PathVariable long id)
    {
    
    }
    
    

}
