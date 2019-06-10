package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.service.TurnoverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turnover")
public class TurnoverController {

    @Autowired
    TurnoverServiceImpl tsi;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{retailer}")
    public ResponseEntity<?> getAllByRetailer() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/turnover/{id}")
    public ResponseEntity<?> getTurnoverById() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add/{retailer}")
    public ResponseEntity<?> addTurnoverToRetailer() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTurnover() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTurnover() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
