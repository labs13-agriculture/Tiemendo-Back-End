package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.service.RetailerSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/retailer")
public class RetailerController
{

    @Autowired
    private RetailerSerivce retailerSerivce;

    @GetMapping(value = "/retailers", produces = {"application/json"})
    public ResponseEntity<?> listAllRetailers()
    {
        List<Retailer> myRetailers = retailerSerivce.findAll();
        return new ResponseEntity<>(myRetailers, HttpStatus.OK);
    }

}
