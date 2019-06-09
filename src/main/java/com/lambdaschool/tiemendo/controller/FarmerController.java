package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.service.ClientService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private ClientService farmerService;

    public FarmerController(ClientService farmerService) {
        this.farmerService = farmerService;
    }

    // Get all Farmers Pageable
    @GetMapping(value= "/all", produces = "application/json")
    public ResponseEntity<?> getAllFarmers(Pageable pageable) {
        return new ResponseEntity<>(farmerService.findAll(pageable), HttpStatus.OK);
    }

    // Get all Farmers by Search
    @PostMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> searchFarmers(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "false") String lead
    ) {
        // todo: Figure out how this search is going to work.
        // Needs to be pageable and search withing the contact and location objects
        return new ResponseEntity<>("This feature not implemented", HttpStatus.OK);
    }

    // Add Farmer
    @PostMapping(value="/add")
    public ResponseEntity<?> addNewFarmer(@Valid @RequestBody Client farmer) {
        return new ResponseEntity<>(farmerService.add(farmer), HttpStatus.CREATED);
    }

    // Get Farmer
    @GetMapping(value="/farmer/{id}", produces = "application/json")
    public ResponseEntity<?> getFarmerWithId(@PathVariable long id) {
        return new ResponseEntity<>(farmerService.findById(id), HttpStatus.OK);
    }

    // Update Farmer
    @PutMapping(value="/farmer/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateFarmerWithId(@PathVariable long id, @Valid @RequestBody Client farmer
    ) {
        /*
        *  Controller uses id from the farmer object passed in to determine what farmer to update
        */
        farmer.setId(id); // set the id of the object passed in to the id in path
        return new ResponseEntity<>(farmerService.update(farmer), HttpStatus.OK);
    }

    // Delete Farmer
    @DeleteMapping(value="/farmer/{id}", produces = "application/json")
    public ResponseEntity<?> deleteFarmerWithId(@PathVariable long id){
        farmerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
