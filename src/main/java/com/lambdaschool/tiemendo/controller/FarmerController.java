package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Farmer;
import com.lambdaschool.tiemendo.service.FarmerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    // Get all Farmers Pageable
    @GetMapping(value= "/all", produces = "application/json")
    public ResponseEntity<?> getAllFarmers(Pageable pageable) {
        return new ResponseEntity<>(farmerService.findAllFarmers(pageable), HttpStatus.OK);
    }

    // Get all Farmers by Search
    @PostMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> searchFarmers(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "false") boolean lead
    ) {
        // Needs to be pageable and search withing the contact and location objects
        return new ResponseEntity<>(farmerService.searchFarmers(name, location, lead), HttpStatus.OK);
    }

    // Add Farmer
    @PostMapping(value="/add")
    public ResponseEntity<?> addNewFarmer(@Valid @RequestBody Farmer farmer) {
        return new ResponseEntity<>(farmerService.addFarmer(farmer), HttpStatus.CREATED);
    }

    // Get Farmer
    @GetMapping(value="/farmer/{id}", produces = "application/json")
    public ResponseEntity<?> getFarmerWithId(@PathVariable long id) {
        return new ResponseEntity<>(farmerService.findFarmer(id), HttpStatus.OK);
    }

    // Update Farmer
    @PutMapping(value="/farmer/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateFarmerWithId(@PathVariable long id, @Valid @RequestBody Farmer farmer
    ) {
        /*
        *  Controller uses id from the farmer object passed in to determine what farmer to update
        */
        return new ResponseEntity<>(farmerService.updateFarmer(farmer), HttpStatus.OK);
    }

    // Delete Farmer
    @DeleteMapping(value="/farmer/{id}", produces = "application/json")
    public ResponseEntity<?> deleteFarmerWithId(@PathVariable long id){
        farmerService.deleteFarmer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
