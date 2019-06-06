package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Farmer;
import com.lambdaschool.tiemendo.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    FarmerService farmerService;

    // Get all Farmers Pageable
    public ResponseEntity<?> getAllFarmers(Pageable pageable) {
        return new ResponseEntity(HttpStatus.OK);
    }
    // Get all Farmers by Search
    public ResponseEntity<?> getFarmersWithCriteria(Pageable pageable) {
        return new ResponseEntity(HttpStatus.OK);
    }
    // Get Farmer
    public ResponseEntity<?> getFarmerWithId(long id) {
        return new ResponseEntity(HttpStatus.OK);
    }
    // Add Farmer
    public ResponseEntity<?> addNewFarmer(Farmer farmer) {
        return new ResponseEntity(HttpStatus.OK);
    }
    // Update Farmer
    public ResponseEntity<?> updateFarmerWithId(
            @PathVariable long id, @Valid @RequestBody Farmer farmer
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
    // Delete Farmer
    public ResponseEntity<?> deleteFarmerWithId(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
