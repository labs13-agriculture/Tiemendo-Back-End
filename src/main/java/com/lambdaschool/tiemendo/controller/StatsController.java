package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
public class StatsController
{
    private StatsService statsService;
    
    public StatsController(StatsService statsService)
    {
        this.statsService = statsService;
    }
    
    @GetMapping(value = "/farmercount", produces = {"application/json"})
    public ResponseEntity<?> getCountFarmerAndGender()
    {
        return new ResponseEntity<>(statsService.getCountFarmerAndGender(), HttpStatus.OK);
    }
}
