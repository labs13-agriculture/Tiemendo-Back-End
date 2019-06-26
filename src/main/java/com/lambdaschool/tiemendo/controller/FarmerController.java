package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/farmers")
public class FarmerController extends AbstractController {

    private ClientService farmerService;
    private ClientService clientService;

    public FarmerController(ClientService farmerService, ClientService clientService) {
        this.farmerService = farmerService;
        this.clientService = clientService;
    }

    // Get all Farmers Pageable
    @GetMapping(value= "/all", produces = "application/json")
    public ResponseEntity<?> getAllFarmers(
            @PageableDefault(size=25, sort={"firstName"}) Pageable pageable,
            @RequestParam(defaultValue = "false") boolean lead,
            PagedResourcesAssembler<Client> assembler
    ) {
        Page<Client>  p = farmerService.findAll(pageable, lead);

        // Check the Abstract Controller for these
        var content = getContents(p, assembler);
        var headers = getHeaders(p, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    //https://stackoverflow.com/questions/21346387/how-to-correctly-use-pagedresourcesassembler-from-spring-data
    // Get all Farmers by Search
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

        search.put("type", "FARMER");
        search.put("lead", lead);

        Page<Client>  p = farmerService.search(pageable, search);

        // Check the Abstract Controller for these
        var content = getContents(p, assembler);
        var headers = getHeaders(p, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
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
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
