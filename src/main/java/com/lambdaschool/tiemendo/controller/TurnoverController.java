package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Turnover;
import com.lambdaschool.tiemendo.service.TurnoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/turnover")
public class TurnoverController {

    @Autowired
    TurnoverService tsi;

    @GetMapping(value="/all", produces={"application/json"})
    public ResponseEntity<?> getAll(Pageable p) {
        return new ResponseEntity<>(tsi.findAll(p), HttpStatus.OK);
    }

    @GetMapping(value="/all/{retailer}", produces={"application/json"})
    public ResponseEntity<?> getAllByRetailer(Pageable pageable, @PathVariable Long retailer) {
        /*
            {retailer} should be a long representing the id of the retailer you want to search
        */
        return new ResponseEntity<>(tsi.findAllByRetailer(pageable, retailer), HttpStatus.OK);
    }

    @GetMapping(value="/turnover/{id}", produces={"application/json"})
    public ResponseEntity<?> getTurnoverById(@PathVariable Long id) {
        /*
        * {id} refers to the id of the Turnover record you would like to look up
        */
        return new ResponseEntity<>(tsi.findById(id), HttpStatus.OK);
    }

    @PostMapping(value="/add/{retailer}", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> addTurnoverToRetailer(
            @PathVariable Long retailer,
            @Valid @RequestBody Turnover turnover
    ) {
        /*
            {retailer} should be a long representing the id of the retailer you want to add Turnover record to
        */
        return new ResponseEntity<>(tsi.add(turnover, retailer), HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> updateTurnover(
            @PathVariable Long id,
            @Valid @RequestBody Turnover turnover
    ) {
        /*
         * {id} refers to the id of the Turnover record you would like to look up
         */
        turnover.setId(id);
        return new ResponseEntity<>(tsi.update(turnover), HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}", produces={"application/json"})
    public ResponseEntity<?> deleteTurnover() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
